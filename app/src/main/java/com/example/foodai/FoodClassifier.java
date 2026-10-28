package com.example.foodai;

import android.content.Context;
import android.graphics.Bitmap;

import org.tensorflow.lite.Interpreter;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

/**
 * FoodClassifier: TensorFlow Lite model ko app ke andar chalata hai.
 *
 * Kaam ka tareeqa:
 *   1. model.tflite aur labels.txt "assets" folder se load hoti hain
 *   2. Photo ko 224x224 size mein badla jata hai (model ka input size)
 *   3. Photo ke pixels ko ek buffer mein rakha jata hai
 *   4. Interpreter model chalata hai aur har class ki probability deta hai
 *   5. Sab se zyada probability wala naam wapis diya jata hai
 */
public class FoodClassifier {

    // Assets folder mein files ke naam
    private static final String MODEL_FILE = "model.tflite";
    private static final String LABEL_FILE = "labels.txt";

    // MobileNetV2 ka input size 224 x 224 pixels hai (Colab mein bhi yahi tha)
    private static final int INPUT_SIZE = 224;

    // Interpreter: TFLite ka wo object jo model ko chalata hai
    private final Interpreter interpreter;

    // Class names (apple, banana, ...). Order wahi hai jo Colab ki labels.txt mein tha
    private final List<String> labels;

    /** Prediction ka natija: food ka naam aur kitna yaqeen (0 se 1 tak) */
    public static class Result {
        public final String label;
        public final float confidence;

        public Result(String label, float confidence) {
            this.label = label;
            this.confidence = confidence;
        }
    }

    /** Model aur labels load karta hai. Agar file na mile to IOException aata hai. */
    public FoodClassifier(Context context) throws IOException {
        interpreter = new Interpreter(loadModel(context));
        labels = loadLabels(context);
    }

    /** assets se model.tflite padh kar ByteBuffer mein deta hai (Interpreter ko yahi chahiye). */
    private ByteBuffer loadModel(Context context) throws IOException {
        try (InputStream in = context.getAssets().open(MODEL_FILE)) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] chunk = new byte[8192];
            int n;
            while ((n = in.read(chunk)) != -1) {
                out.write(chunk, 0, n);
            }
            byte[] bytes = out.toByteArray();

            // "Direct" buffer aur native byte order: TFLite ki zaroorat
            ByteBuffer buffer = ByteBuffer.allocateDirect(bytes.length);
            buffer.order(ByteOrder.nativeOrder());
            buffer.put(bytes);
            buffer.rewind();
            return buffer;
        }
    }

    /** assets se labels.txt padhta hai. Har line mein ek class ka naam hota hai. */
    private List<String> loadLabels(Context context) throws IOException {
        List<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(context.getAssets().open(LABEL_FILE)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    list.add(line.trim());
                }
            }
        }
        return list;
    }

    /** Ek photo (Bitmap) ko pehchanta hai aur sab se behtar natija wapis deta hai. */
    public Result classify(Bitmap source) {
        // Step 1: Photo ko 224 x 224 mein badlo
        Bitmap bitmap = Bitmap.createScaledBitmap(source, INPUT_SIZE, INPUT_SIZE, true);

        // Step 2: Input buffer banao.
        // Shape [1, 224, 224, 3], har pixel ke 3 float numbers (R, G, B).
        // 4 bytes har float ke liye.
        ByteBuffer input = ByteBuffer.allocateDirect(4 * INPUT_SIZE * INPUT_SIZE * 3);
        input.order(ByteOrder.nativeOrder());

        int[] pixels = new int[INPUT_SIZE * INPUT_SIZE];
        bitmap.getPixels(pixels, 0, INPUT_SIZE, 0, 0, INPUT_SIZE, INPUT_SIZE);

        for (int pixel : pixels) {
            // Pixel ek int hota hai jisme Alpha, Red, Green, Blue bhare hote hain
            float r = (pixel >> 16) & 0xFF;
            float g = (pixel >> 8) & 0xFF;
            float b = pixel & 0xFF;

            // Colab mein normalization (0..255 se -1..1) model ke ANDAR shamil thi,
            // is liye yahan seedha 0..255 wali values dete hain.
            input.putFloat(r);
            input.putFloat(g);
            input.putFloat(b);
        }

        // Step 3: Output array: [1][number_of_classes]. Har class ki probability.
        float[][] output = new float[1][labels.size()];

        // Step 4: Model chalao (inference)
        interpreter.run(input, output);

        // Step 5: Sab se bari probability dhoondo
        int best = 0;
        for (int i = 1; i < output[0].length; i++) {
            if (output[0][i] > output[0][best]) {
                best = i;
            }
        }
        return new Result(labels.get(best), output[0][best]);
    }

    /** Kaam khatam hone par memory wapis lene ke liye */
    public void close() {
        interpreter.close();
    }
}