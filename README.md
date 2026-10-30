
# FoodAI – AI-Based Food Recognition Android Application

## 1. Project Overview

FoodAI is an AI-based Android application developed as a mini-project for the Mobile Application Development course.

The main purpose of this project is to demonstrate the integration of Artificial Intelligence (AI) and Machine Learning (ML) into an Android application using TensorFlow Lite.

The application is designed to recognize food items from images using a machine learning model. It uses Android development technologies, Java, CameraX, and TensorFlow Lite.

### Project Information

- **Project Name:** FoodAI
- **Platform:** Android
- **Programming Language:** Java
- **User Interface:** XML
- **AI Framework:** TensorFlow Lite
- **Camera Library:** CameraX
- **Package Name:** `com.example.foodai`

## 2. Project Objectives

- Develop an AI-based Android application.
- Understand Android Studio project structure.
- Integrate a TensorFlow Lite model into an Android application.
- Understand image processing and model inference.
- Implement camera functionality using CameraX.
- Display application results through an Android interface.
- Learn the basic workflow of AI-based mobile applications.

## 3. Application Activities

The FoodAI application contains the following five main Java Activities:

### 1. SplashActivity
Responsible for displaying the splash screen when the application starts.

### 2. LoginActivity
Handles the login screen and provides the application's login interface.

### 3. MainActivity
Represents the main screen of the application and provides access to its main functionality.

### 4. CameraActivity
Handles camera-related functionality and provides image input for food recognition.

### 5. ResultActivity
Displays the result of the food classification process.

### Additional Java Class

**FoodClassifier.java**

This class is responsible for handling the TensorFlow Lite model and performing food classification.

## 4. Features

The application is designed to include:

- Splash screen.
- Login interface.
- Main application screen.
- Camera integration using CameraX.
- Food image classification using TensorFlow Lite.
- Result display screen.

Actual feature availability depends on successful implementation and testing.

## 5. Technologies and Tools

| Technology | Purpose |
|---|---|
| Android Studio | Android application development |
| Java | Application programming |
| XML | User interface design |
| TensorFlow Lite | Machine learning model integration |
| CameraX | Camera functionality |
| Git | Version control |
| GitHub | Source code hosting |

## 6. Project Structure

The main Java package structure is:

```text
FoodAI/
│
├── app/
│   └── src/
│       └── main/
│           ├── java/
│           │   └── com/example/foodai/
│           │       ├── CameraActivity.java
│           │       ├── FoodClassifier.java
│           │       ├── LoginActivity.java
│           │       ├── MainActivity.java
│           │       ├── ResultActivity.java
│           │       └── SplashActivity.java
│           │
│           ├── assets/
│           │   ├── model.tflite
│           │   └── labels.txt
│           │
│           ├── res/
│           └── AndroidManifest.xml
│
├── build.gradle
├── settings.gradle
└── README.md
```

### Description of Important Files

| File | Description |
|---|---|
| SplashActivity.java | Displays the application splash screen. |
| LoginActivity.java | Provides the login interface. |
| MainActivity.java | Handles the main application screen. |
| CameraActivity.java | Handles camera-related operations. |
| FoodClassifier.java | Loads and executes the TensorFlow Lite model. |
| ResultActivity.java | Displays food classification results. |
| model.tflite | Contains the TensorFlow Lite model. |
| labels.txt | Contains the model's class labels. |
| AndroidManifest.xml | Contains application and activity configurations. |

## 7. Prerequisites

Before running this project, the following tools and requirements are needed:

1. Android Studio installed.
2. Android SDK configured.
3. Java development environment compatible with the project.
4. Android Emulator or physical Android device.
5. Git installed for cloning the repository.
6. Internet connection for downloading project dependencies.

## 8. Dependencies

The project uses the following dependencies:

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

CameraX provides camera functionality, while TensorFlow Lite is used for machine learning model inference.

## 9. Installation Instructions

Follow these steps to install the project:

### Step 1: Clone the Repository

Open the terminal or command prompt and execute:

```bash
git clone https://github.com/safa699/FoodAI.git
```

### Step 2: Open in Android Studio

1. Open Android Studio.
2. Select **Open**.
3. Browse to the cloned FoodAI project folder.
4. Select the project and open it.
5. Wait for Android Studio to load the project.

### Step 3: Verify Project Files

Make sure the following files are available:

- SplashActivity.java
- LoginActivity.java
- MainActivity.java
- CameraActivity.java
- FoodClassifier.java
- ResultActivity.java
- model.tflite
- labels.txt

### Step 4: Sync Gradle

1. Open the project in Android Studio.
2. Select **File → Sync Project with Gradle Files**.
3. Wait for Gradle synchronization.
4. Resolve any dependency or configuration errors if they occur.

## 10. Execution Instructions

To run the application:

1. Open the FoodAI project in Android Studio.
2. Connect an Android device or start an emulator.
3. Make sure the required permissions are configured.
4. Select the app run configuration.
5. Click the **Run** button.
6. Wait for the application to install and launch.
7. Test the available application screens and functionality.

If the application uses the camera, grant camera permission when requested.

**Note:** Successful execution should be confirmed by building, installing, and testing the FoodAI application.

## 11. AI Model Integration

FoodAI uses TensorFlow Lite to integrate a machine learning model into the Android application.

The model and labels are stored in the assets directory:

```text
app/src/main/assets/model.tflite
app/src/main/assets/labels.txt
```

### General Model Workflow

1. Capture or obtain an image.
2. Preprocess the image according to the model requirements.
3. Pass the processed image to the TensorFlow Lite interpreter.
4. Execute model inference.
5. Interpret the output using the labels file.
6. Display the predicted result.

The image dimensions, preprocessing method, model input format, and output format must match the actual TensorFlow Lite model.

## 12. Troubleshooting

| Issue | Possible Solution |
|---|---|
| Gradle Sync Failed | Check internet connection and dependencies. |
| Camera Not Working | Check camera permissions. |
| Model Not Found | Verify model.tflite in the assets folder. |
| Labels Not Found | Verify labels.txt in the assets folder. |
| App Not Installing | Check device connection and USB debugging. |
| Incorrect Prediction | Verify image preprocessing and model configuration. |

## 13. Future Improvements

The project can be improved by:

- Adding more food categories.
- Improving model accuracy.
- Adding image selection from the gallery.
- Improving the user interface.
- Displaying prediction confidence.
- Providing nutritional information about recognized food items.

## 14. Acknowledgements

This project was developed for academic purposes as part of the Mobile Application Development course.

The project uses Android Studio, Java, CameraX, and TensorFlow Lite.

### Instructor Reference Repository

https://github.com/Uzair390-Del/NumberGuessingAppTflite

This repository is used as a reference for understanding TensorFlow Lite integration in Android applications. It is not the FoodAI food classification model.

## 15. Student Information

- **Student Name:** Safa Waseem
- **Registration Number:** 242201054
- **Program:** BS Computer Science
- **Section:** BSCS 5-B
- **Institute:** KICSIT, IST Kahuta Campus
- **Course:** Mobile Application Development
- **Instructor:** Sir Uzair Hassan

## 16. License

This project is developed for educational and academic purposes.
