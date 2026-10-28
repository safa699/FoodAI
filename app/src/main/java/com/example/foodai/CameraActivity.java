
package com.example.foodai;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;

import com.google.common.util.concurrent.ListenableFuture;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;

public class CameraActivity extends AppCompatActivity {

    private PreviewView previewView;
    private ImageCapture imageCapture;
    private Camera camera;
    private boolean flashOn = false;

    private final ActivityResultLauncher<String> permissionLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.RequestPermission(),
                    granted -> {
                        if (granted) {
                            startCamera();
                        } else {
                            Toast.makeText(this,
                                    "Camera permission zaroori hai",
                                    Toast.LENGTH_LONG).show();
                            finish();
                        }
                    });

    private final ActivityResultLauncher<String> galleryLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.GetContent(),
                    this::onImagePicked);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        previewView = findViewById(R.id.previewView);

        ImageButton btnBack = findViewById(R.id.btnBack);
        ImageButton btnCapture = findViewById(R.id.btnCapture);
        View btnGallery = findViewById(R.id.btnGallery);
        View btnFlash = findViewById(R.id.btnFlash);

        btnBack.setOnClickListener(v -> finish());
        btnCapture.setOnClickListener(v -> takePhoto());
        btnGallery.setOnClickListener(
                v -> galleryLauncher.launch("image/*"));
        btnFlash.setOnClickListener(v -> toggleFlash());

        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
            startCamera();
        } else {
            permissionLauncher.launch(Manifest.permission.CAMERA);
        }
    }

    private void startCamera() {
        ListenableFuture<ProcessCameraProvider> future =
                ProcessCameraProvider.getInstance(this);

        future.addListener(() -> {
            try {
                ProcessCameraProvider provider = future.get();

                Preview preview = new Preview.Builder().build();
                preview.setSurfaceProvider(
                        previewView.getSurfaceProvider());

                imageCapture = new ImageCapture.Builder().build();

                provider.unbindAll();

                camera = provider.bindToLifecycle(
                        this,
                        CameraSelector.DEFAULT_BACK_CAMERA,
                        preview,
                        imageCapture);

            } catch (ExecutionException | InterruptedException e) {
                Toast.makeText(this,
                        "Camera error: " + e.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        }, ContextCompat.getMainExecutor(this));
    }

    private void toggleFlash() {
        if (camera != null
                && camera.getCameraInfo().hasFlashUnit()) {
            flashOn = !flashOn;
            camera.getCameraControl().enableTorch(flashOn);
        } else {
            Toast.makeText(this,
                    "Flash maujood nahi",
                    Toast.LENGTH_SHORT).show();
        }
    }

    // Camera se photo lena aur classify karna
    private void takePhoto() {
        if (imageCapture == null) {
            Toast.makeText(this,
                    "Camera abhi ready nahi hai",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        File photoFile = new File(
                getCacheDir(),
                "scan_" + System.currentTimeMillis() + ".jpg");

        ImageCapture.OutputFileOptions options =
                new ImageCapture.OutputFileOptions.Builder(
                        photoFile).build();

        imageCapture.takePicture(
                options,
                ContextCompat.getMainExecutor(this),
                new ImageCapture.OnImageSavedCallback() {

                    @Override
                    public void onImageSaved(
                            @NonNull ImageCapture.OutputFileResults results) {

                        Bitmap bitmap = BitmapFactory.decodeFile(
                                photoFile.getAbsolutePath());

                        if (bitmap == null) {
                            Toast.makeText(CameraActivity.this,
                                    "Photo open nahi ho saki",
                                    Toast.LENGTH_LONG).show();
                            return;
                        }

                        classifyAndShowResult(
                                bitmap,
                                photoFile.getAbsolutePath(),
                                null);
                    }

                    @Override
                    public void onError(
                            @NonNull ImageCaptureException e) {
                        Toast.makeText(CameraActivity.this,
                                "Capture fail: " + e.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });
    }

    // Gallery se select ki hui image
    private void onImagePicked(Uri uri) {
        if (uri == null) {
            return;
        }

        Bitmap bitmap;

        try (InputStream inputStream =
                     getContentResolver().openInputStream(uri)) {

            if (inputStream == null) {
                Toast.makeText(this,
                        "Image open nahi hui",
                        Toast.LENGTH_LONG).show();
                return;
            }

            bitmap = BitmapFactory.decodeStream(inputStream);

        } catch (IOException e) {
            Toast.makeText(this,
                    "Image open error: " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
            return;
        }

        if (bitmap == null) {
            Toast.makeText(this,
                    "Image read nahi ho saki",
                    Toast.LENGTH_LONG).show();
            return;
        }

        classifyAndShowResult(bitmap, null, uri.toString());
    }

    // Camera aur gallery dono ka common prediction method
    private void classifyAndShowResult(
            Bitmap bitmap, String imagePath, String imageUri) {

        FoodClassifier classifier = null;

        try {
            classifier = new FoodClassifier(this);

            FoodClassifier.Result result =
                    classifier.classify(bitmap);

            Intent intent = new Intent(
                    CameraActivity.this,
                    ResultActivity.class);

            intent.putExtra(
                    ResultActivity.EXTRA_LABEL,
                    result.label);

            intent.putExtra(
                    ResultActivity.EXTRA_CONFIDENCE,
                    result.confidence);

            if (imagePath != null) {
                intent.putExtra(
                        ResultActivity.EXTRA_IMAGE_PATH,
                        imagePath);
            }

            if (imageUri != null) {
                intent.putExtra(
                        ResultActivity.EXTRA_IMAGE_URI,
                        imageUri);
            }

            startActivity(intent);

        } catch (Exception e) {
            Toast.makeText(this,
                    "Prediction error: " + e.getMessage(),
                    Toast.LENGTH_LONG).show();

        } finally {
            if (classifier != null) {
                classifier.close();
            }

            if (!bitmap.isRecycled()) {
                bitmap.recycle();
            }
        }
    }
}
