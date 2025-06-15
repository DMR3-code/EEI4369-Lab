# EEI4369 Android Lab Project – Task Integration App

This is a complete Android application developed for the lab test of **EEI4369 – Mobile Application Development for Android** (BSE Honours, Open University of Sri Lanka).

## 🔧 Project Details

- **Student Name:** D.M.R.R. Dissanayake
- **Student ID (SID):** S23010301
- **Registration Number:** 423594893
- **Academic Year:** 2024/2025
- **Language:** Java
- **IDE:** Android Studio
- **Minimum SDK:** 21
- **Target SDK:** 34

---

## 📱 App Features (Tasks)

### 🔹 Task 01 – Simple GUI
- A login screen with a username/password form.
- Avatar and background images using `ConstraintLayout`.
- Validates empty fields and navigates to the Map screen on success.

### 🔹 Task 02 – Multimedia Integration
- Background image with transparency.
- Avatar image centered in the UI.
- Stylish UI with custom drawable inputs.

### 🔹 Task 03 – Google Maps Integration
- Uses `Geocoder` to convert user-entered address to coordinates.
- Displays the location on Google Map with a marker.
- Allows map zoom and user location detection.

### 🔹 Task 04 – Sensor Integration
- Uses ambient temperature sensor.
- Triggers an audio alert when temperature exceeds **01°C**.
- Displays live temperature data on-screen.

### 🔹 Task 05 – SQLite Database
- Saves login details (username, password) to local SQLite DB.
- Shows a `Toast` message confirming the save status.

---

## 🚀 App Flow

1. Launch app → Login Screen (with multimedia UI)
2. Click login → Map Screen (search and show location)
3. Click "Check Temperature" → Sensor Screen (alerts on heat)

---

## 📂 Project Structure Highlights

├── MainActivity.java # Login + DB integration
├── MapActivity.java # Google Maps integration
├── SensorActivity.java # Sensor + audio alert
├── DatabaseHelper.java # SQLite database helper
├── res/layout # XML UIs (activity_main, activity_map, activity_sensor)
├── res/raw/alert.mp3 # Offline audio alert
├── res/drawable # Avatar, background, border assets
├── AndroidManifest.xml # Permissions and activity declarations


---

## 🔑 Permissions Used

```xml
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
<uses-permission android:name="android.permission.INTERNET" />
