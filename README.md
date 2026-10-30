
# FoodAI – AI-Based Food Recognition Android Application

## 1. Project Overview

FoodAI is an AI-based Android application developed as a mini-project for the Mobile Application Development course.

The purpose of this application is to demonstrate the integration of Artificial Intelligence (AI) and Machine Learning (ML) into an Android application using TensorFlow Lite.

The application is designed to use a food classification model to recognize food items from images captured through the mobile camera. The model processes the input image and generates a prediction based on the food categories available in its labels file.

**Project Name:** FoodAI  
**Platform:** Android  
**Programming Language:** Java  
**AI Framework:** TensorFlow Lite  
**Package Name:** `com.example.foodai`

## 2. Project Objectives

- Develop an AI-based Android application.
- Understand Android Studio project structure.
- Integrate a TensorFlow Lite model into an Android project.
- Understand image processing and model inference.
- Use CameraX for camera functionality.
- Display classification results through an Android interface.
- Understand the basic workflow of AI-based mobile applications.

## 3. Features

The planned application workflow includes:

- Android-based user interface.
- Camera integration using CameraX.
- Image input for food recognition.
- TensorFlow Lite model integration.
- Food classification using the provided model.
- Display of predicted results.

**Note:** The availability and successful operation of each feature depend on the final model integration and application testing.

## 4. Technologies and Tools

| Technology | Purpose |
|---|---|
| Android Studio | Android application development |
| Java | Application programming |
| XML | User interface design |
| TensorFlow Lite | Machine learning model inference |
| CameraX | Camera integration |
| Git | Version control |
| GitHub | Source code hosting |

## 5. Project Structure

The main project structure is:

```text
FoodAI/
│
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── java/
│   │       │   └── com/example/foodai/
│   │       │       ├── MainActivity.java
│   │       │       ├── CameraActivity.java
│   │       │       ├── FoodClassifier.java
│   │       │       └── ResultActivity.java
│   │       │
│   │       ├── assets/
│   │       │   ├── model.tflite
│   │       │   └── labels.txt
│   │       │
│   │       ├── res/
│   │       └── AndroidManifest.xml
│   │
│   └── build.gradle
│
├── build.gradle
├── settings.gradle
└── README.md
```

**Main Java Files:**

- `MainActivity.java`: Handles the main application screen.
- `CameraActivity.java`: Handles camera-related functionality.
- `FoodClassifier.java`: Responsible for loading and using the TensorFlow Lite model.
- `ResultActivity.java`: Displays the classification result.

**Assets:**

- `model.tflite`: TensorFlow Lite machine learning model.
- `labels.txt`: Contains the class labels used by the model.

## 6. Prerequisites

Before running the project, make sure you have:

1. Android Studio installed.
2. Android SDK configured.
3. Java development environment compatible with the project's Gradle configuration.
4. A physical Android device or Android Emulator.
5. USB debugging enabled if using a physical device.
6. Git installed (for cloning the repository).

## 7. Dependencies

The project uses the following libraries:

### CameraX Dependencies

```gradle
implementation "androidx.camera:camera-core:1.3.4"
implementation "androidx.camera:camera-camera2:1.3.4"
implementation "androidx.camera:camera-lifecycle:1.3.4"
implementation "androidx.camera:camera-view:1.3.4"
```

### TensorFlow Lite Dependency

```gradle
implementation "org.tensorflow:tensorflow-lite:2.14.0"
```

These dependencies provide camera functionality and TensorFlow Lite model inference.

## 8. Installation Instructions

Follow these steps to download and open the project.

### Step 1: Clone the Repository

Open the terminal or command prompt and run:

```bash
git clone https://github.com/safa699/FoodAI.git
```

### Step 2: Open the Project

1. Open Android Studio.
2. Select **Open**.
3. Navigate to the cloned FoodAI folder.
4. Select the project folder.
5. Wait for Gradle synchronization to complete.

### Step 3: Check Project Files

Verify that the following files are available:

- MainActivity.java
- CameraActivity.java
- FoodClassifier.java
- ResultActivity.java
- model.tflite
- labels.txt
- AndroidManifest.xml

### Step 4: Sync Gradle

In Android Studio:

1. Open the project.
2. Select **File → Sync Project with Gradle Files**.
3. Wait for synchronization to finish.
4. Resolve any dependency or configuration errors if they appear.

## 9. Execution Instructions

To run the application:

1. Open the FoodAI project in Android Studio.
2. Connect an Android device or start an emulator.
3. Make sure the required camera permissions are configured.
4. Select the `app` configuration.
5. Click the **Run** button.
6. Wait for the application to install and launch.

If the camera feature is used, grant camera permission when requested.

**Important:** The application should only be considered successfully executed after the FoodAI project builds, installs, and its features have been tested on an emulator or physical device.

## 10. AI Model Integration

FoodAI uses TensorFlow Lite to integrate a machine learning model into the Android application.

The model file is stored in:

```text
app/src/main/assets/model.tflite
```

The labels file is stored in:

```text
app/src/main/assets/labels.txt
```

The general model inference workflow is:

1. Capture or obtain an input image.
2. Preprocess the image according to the model's input requirements.
3. Pass the processed image to the TensorFlow Lite interpreter.
4. Execute model inference.
5. Interpret the model output using the corresponding labels.
6. Display the predicted result.

**Note:** The model's input dimensions, preprocessing requirements, output format, and supported food categories must match the actual `model.tflite` file.

## 11. Troubleshooting

Some common issues and their possible solutions are:

| Issue | Possible Solution |
|---|---|
| Gradle Sync Failed | Check internet connection and Gradle dependencies. |
| Camera Not Working | Check camera permissions and device compatibility. |
| Model Not Found | Verify the model file inside the assets folder. |
| Labels Not Found | Check that labels.txt exists in the assets folder. |
| App Not Installing | Check USB debugging and device installation permissions. |
| Incorrect Prediction | Verify image preprocessing and model input/output configuration. |

## 12. Future Improvements

The project can be improved by:

- Adding support for more food categories.
- Improving model accuracy.
- Adding image selection from the gallery.
- Improving the user interface.
- Displaying prediction confidence.
- Adding food information and nutritional details.

## 13. Acknowledgements

This project was developed for academic purposes as part of the Mobile Application Development course.

The project uses Android development tools, CameraX, and TensorFlow Lite.

**Instructor Reference Repository:**

https://github.com/Uzair390-Del/NumberGuessingAppTflite

The instructor's repository is used as a reference for understanding TensorFlow Lite integration in Android. It is not the FoodAI food-classification model.

## 14. Student Information

**Student Name:** Safa Waseem  
**Registration Number:** 242201054  
**Program:** BS Computer Science  
**Section:** BSCS 5-B  
**Institute:** KICSIT, IST Kahuta Campus  
**Course:** Mobile Application Development  
**Instructor:** Sir Uzair Hassan

---

## 15. License

This project is developed for educational and academic purposes.
