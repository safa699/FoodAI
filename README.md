# 🍎 FoodAI — AI-Powered Food Recognition Android App

<p align="center">
  ⭐Smart Food Recognition | TensorFlow Lite | Android⭐
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Platform-Android-green?style=for-the-badge&logo=android" />
  <img src="https://img.shields.io/badge/Language-Java-orange?style=for-the-badge&logo=java" />
  <img src="https://img.shields.io/badge/AI-TensorFlow%20Lite-blue?style=for-the-badge&logo=tensorflow" />
  <img src="https://img.shields.io/badge/Camera-CameraX-purple?style=for-the-badge" />
</p>

---

## 📱 About FoodAI

**FoodAI** is an AI-powered Android application developed as a mini-project for the **Mobile Application Development** course.

The application demonstrates how Artificial Intelligence (AI) and Machine Learning (ML) can be integrated into Android applications using **TensorFlow Lite**.

FoodAI allows users to capture a food image using their mobile camera or select an existing image from the gallery. The image is then processed by a TensorFlow Lite model to predict the food category.

After classification, the application displays the predicted food name, prediction confidence, and available nutritional information.

The project combines Android development, image processing, machine learning inference, and a user-friendly interface into one mobile application.

## ✨ Key Features

| Feature | Description |
|---|---|
| 🚀 Splash Screen | Displays the application introduction when launched. |
| 🔐 Login & Sign Up | Provides a local demo login and registration system. |
| 🏠 Home Screen | Provides access to the main application functionality. |
| 📸 Camera Integration | Captures food images using CameraX. |
| 🖼️ Gallery Selection | Allows users to select images from their device gallery. |
| 🤖 AI Food Recognition | Uses TensorFlow Lite for food image classification. |
| 📊 Prediction Confidence | Displays the confidence score of the prediction. |
| 🥗 Nutrition Information | Displays predefined nutritional values for supported food items. |

## 🔄 How FoodAI Works

The application follows a simple workflow:

1. **Launch:** The splash screen appears when the application starts.
2. **Authentication:** The user can log in or create a demo account.
3. **Home Screen:** The user accesses the food scanning feature.
4. **Image Input:** The user captures a photo or selects one from the gallery.
5. **Image Processing:** The image is resized and converted into the required input format.
6. **AI Prediction:** TensorFlow Lite processes the image and generates prediction scores.
7. **Result Display:** The application displays the predicted food category and confidence.
8. **Nutrition Details:** Available predefined nutritional information is displayed.

## 🛠️ Technologies Used

| Technology | Purpose |
|---|---|
| Java | Application development |
| XML | Android user interface design |
| Android Studio | Development environment |
| TensorFlow Lite | On-device machine learning inference |
| CameraX | Camera preview and image capture |
| SharedPreferences | Local demo account storage |
| Git & GitHub | Version control and project hosting |

## 📂 Project Structure

```text
FoodAI/
│
├── app/
│   └── src/
│       └── main/
│           │
│           ├── java/
│           │   └── com/example/foodai/
│           │       ├── SplashActivity.java
│           │       ├── LoginActivity.java
│           │       ├── MainActivity.java
│           │       ├── CameraActivity.java
│           │       ├── FoodClassifier.java
│           │       └── ResultActivity.java
│           │
│           ├── assets/
│           │   ├── model.tflite
│           │   └── labels.txt
│           │
│           ├── res/
│           │
│           └── AndroidManifest.xml
│
├── build.gradle
├── settings.gradle
└── README.md
```

### 📌 Main Components

**1. SplashActivity.java**

Displays the splash screen and automatically navigates to the login screen after a short delay.

**2. LoginActivity.java**

Handles login and sign-up operations, validates user input, and stores demo account information locally using SharedPreferences.

**3. MainActivity.java**

Provides the main application interface and navigation to the camera scanning feature.

**4. CameraActivity.java**

Manages camera permission, CameraX preview, photo capture, flash control, and image selection from the gallery.

**5. FoodClassifier.java**

Handles TensorFlow Lite model loading, label reading, image preprocessing, model inference, and prediction generation.

**6. ResultActivity.java**

Displays the predicted food name, confidence percentage, selected image, and available nutritional information.

## 🧠 AI Model Integration

FoodAI uses a TensorFlow Lite model to perform food image classification directly within the Android application.

### Model Files

The machine learning files are stored in the following directory:

```text
app/src/main/assets/
│
├── model.tflite
└── labels.txt
```

| File | Purpose |
|---|---|
| `model.tflite` | Contains the trained machine learning model. |
| `labels.txt` | Contains the class names associated with model predictions. |

### Classification Process

The `FoodClassifier.java` class performs the following operations:

- Loads the TensorFlow Lite model from the assets folder.
- Reads food category labels from `labels.txt`.
- Resizes the input image to 224 × 224 pixels.
- Extracts RGB pixel values.
- Converts image data into a ByteBuffer.
- Executes inference using the TensorFlow Lite Interpreter.
- Identifies the class with the highest prediction score.
- Returns the predicted label and confidence value.

**Model compatibility:** The input dimensions, pixel preprocessing, and output format must match the actual trained model.

## ⚙️ Prerequisites

Before installing FoodAI, make sure the following requirements are available:

- Android Studio
- Android SDK
- Java development environment compatible with the project configuration
- Git installed on your computer
- Android Emulator or a physical Android device
- Internet connection for downloading Gradle dependencies

## 📦 Dependencies

FoodAI uses the following Android libraries:

### CameraX

```gradle
implementation "androidx.camera:camera-core:1.3.4"
implementation "androidx.camera:camera-camera2:1.3.4"
implementation "androidx.camera:camera-lifecycle:1.3.4"
implementation "androidx.camera:camera-view:1.3.4"
```

### TensorFlow Lite

```gradle
implementation "org.tensorflow:tensorflow-lite:2.14.0"
```

**Why these libraries?**

- **CameraX:** Provides camera preview, lifecycle management, and image capture.
- **TensorFlow Lite:** Enables machine learning model execution on Android devices.

## 🚀 Installation & Setup

Follow these steps to get the FoodAI project on your computer.

### Step 1 — Clone the Repository

Open a terminal or command prompt and execute:

```bash
git clone https://github.com/safa699/FoodAI.git
```

### Step 2 — Open in Android Studio

1. Launch Android Studio.
2. Select **Open** from the welcome screen.
3. Navigate to the cloned FoodAI folder.
4. Select the project directory.
5. Wait for Android Studio to load the project.

### Step 3 — Verify Project Files

Make sure the following important files are available:

- All six Java source files.
- `model.tflite`
- `labels.txt`
- `AndroidManifest.xml`
- Gradle configuration files.

### Step 4 — Sync Gradle

1. Open the project in Android Studio.
2. Select **File → Sync Project with Gradle Files**.
3. Wait for the dependencies to synchronize.
4. Check for any configuration errors.

## ▶️ Running the Application

To run FoodAI:

1. Open the project in Android Studio.
2. Connect an Android device or start an emulator.
3. Ensure the camera permission is configured.
4. Select the `app` run configuration.
5. Click the **Run ▶** button.
6. Wait for installation and application launch.
7. Explore the available application screens and features.

**Camera Permission:** Grant camera access when requested to use the camera scanning functionality.

## 🧪 Testing & Troubleshooting

| Issue | Possible Solution |
|---|---|
| Gradle Sync Failed | Check internet connection and dependency configuration. |
| Camera Not Working | Check camera permission and device compatibility. |
| Model Not Found | Verify `model.tflite` inside the assets folder. |
| Labels Not Found | Verify `labels.txt` inside the assets folder. |
| App Not Installing | Check USB debugging and device installation settings. |
| Incorrect Prediction | Check model compatibility and image preprocessing. |

## 🔮 Future Enhancements

Possible improvements for future versions include:

- Adding more food categories.
- Improving classification accuracy.
- Adding support for more image formats.
- Implementing secure user authentication.
- Adding a food history feature.
- Improving the user interface and user experience.
- Expanding nutritional information.
- Adding personalized food recommendations.

## 📚 Learning Outcomes

Through the development of FoodAI, the project demonstrates:

- Android application development using Java.
- Understanding Android Studio project architecture.
- Integrating third-party Android libraries.
- Implementing camera functionality using CameraX.
- Loading and executing TensorFlow Lite models.
- Understanding image preprocessing and model inference.
- Passing data between Android Activities.
- Managing local application data using SharedPreferences.

## 🙏 Acknowledgements

This project was developed for academic purposes as part of the **Mobile Application Development** course.

Special acknowledgement to the course instructor for guidance and the reference Android project:

**Instructor Reference Repository:**

[NumberGuessingAppTflite](https://github.com/Uzair390-Del/NumberGuessingAppTflite)

This repository was consulted as a reference for understanding TensorFlow Lite integration in Android applications. It is not the FoodAI food-classification model.

Technologies and libraries used include Android Studio, Java, CameraX, and TensorFlow Lite.

## 👩‍💻 Developer Information

| Detail | Information |
|---|---|
|  Name | Safa Waseem |
| Program | BS Computer Science |
| Institute | KICSIT, IST Kahuta Campus |
| Course | Mobile Application Development |
| Instructor | Sir Uzair Hassan |
| Project | FoodAI |

## 📄 License

This project is developed for educational and academic purposes.

---

<p align="center">
  **FoodAI — Making Food Recognition Smarter with AI 🍎**
</p>

<p align="center">
  Developed as a Mobile Application Development Mini Project.
</p>
