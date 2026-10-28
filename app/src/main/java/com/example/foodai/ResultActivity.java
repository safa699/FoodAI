package com.example.foodai;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ResultActivity extends AppCompatActivity {

    public static final String EXTRA_IMAGE_PATH = "image_path";
    public static final String EXTRA_IMAGE_URI = "image_uri";
    public static final String EXTRA_LABEL = "label";
    public static final String EXTRA_CONFIDENCE = "confidence";

    // Nutrition per 100g: {kcal, carbs, protein, fat}
    private static final Map<String, float[]> NUTRITION = new HashMap<>();
    static {
        NUTRITION.put("orange",     new float[]{52f, 13f, 1.0f, 0.2f});
        NUTRITION.put("apple",      new float[]{52f, 14f, 0.3f, 0.2f});
        NUTRITION.put("banana",     new float[]{89f, 23f, 1.1f, 0.3f});
        NUTRITION.put("strawberry", new float[]{32f, 7.7f, 0.7f, 0.3f});
        NUTRITION.put("grape",      new float[]{69f, 18f, 0.7f, 0.2f});
        NUTRITION.put("mango",      new float[]{60f, 15f, 0.8f, 0.4f});
        NUTRITION.put("tomato",     new float[]{18f, 3.9f, 0.9f, 0.2f});
        NUTRITION.put("potato",     new float[]{77f, 17f, 2.0f, 0.1f});
        NUTRITION.put("pizza",      new float[]{266f, 33f, 11f, 10f});
        NUTRITION.put("burger",     new float[]{295f, 24f, 17f, 14f});
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        ImageButton btnBack = findViewById(R.id.btnBack);
        ImageView imgFood = findViewById(R.id.imgFood);
        TextView tvFoodName = findViewById(R.id.tvFoodName);
        TextView tvConfidence = findViewById(R.id.tvConfidence);
        TextView tvCalories = findViewById(R.id.tvCalories);
        TextView tvCarbs = findViewById(R.id.tvCarbs);
        TextView tvProtein = findViewById(R.id.tvProtein);
        TextView tvFat = findViewById(R.id.tvFat);

        btnBack.setOnClickListener(v -> finish());

        String label = getIntent().getStringExtra(EXTRA_LABEL);
        float confidence = getIntent().getFloatExtra(EXTRA_CONFIDENCE, 0f);
        String imagePath = getIntent().getStringExtra(EXTRA_IMAGE_PATH);
        String imageUri = getIntent().getStringExtra(EXTRA_IMAGE_URI);

        // Photo dikhana (camera se ya gallery se)
        if (imagePath != null) {
            Bitmap bmp = BitmapFactory.decodeFile(imagePath);
            if (bmp != null) imgFood.setImageBitmap(bmp);
        } else if (imageUri != null) {
            imgFood.setImageURI(Uri.parse(imageUri));
        }

        if (label == null) label = "Unknown";
        tvFoodName.setText(capitalize(label));
        tvConfidence.setText("Prediction confidence  " + Math.round(confidence * 100) + "%");

        float[] n = NUTRITION.get(label.toLowerCase(Locale.ROOT));
        if (n != null) {
            tvCalories.setText(fmt(n[0]) + " kcal");
            tvCarbs.setText(fmt(n[1]) + " g");
            tvProtein.setText(fmt(n[2]) + " g");
            tvFat.setText(fmt(n[3]) + " g");
        } else {
            tvCalories.setText("--");
            tvCarbs.setText("--");
            tvProtein.setText("--");
            tvFat.setText("--");
        }
    }

    private String capitalize(String s) {
        s = s.replace('_', ' ');
        return s.isEmpty() ? s : Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }

    private String fmt(float v) {
        return (v == Math.floor(v))
                ? String.format(Locale.US, "%.0f", v)
                : String.format(Locale.US, "%.1f", v);
    }
}