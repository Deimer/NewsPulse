### **News Pulse**

This project is a mobile application developed as part of a technical test to demonstrate Android development skills, applying modern architecture and best coding practices. The application focuses on consuming a public API, displaying a list of items with details, and providing additional features such as swipe-to-dismiss and pull-to-refresh.

---

## **Project Overview**

The goal of this project was to meet the requirements outlined in the technical test document. The application implements the following key features:

- Consumption of a public API to display a list of items.
- Detailed view of items upon selection.
- Interactive features like swipe-to-dismiss.
- Pull-to-refresh functionality for real-time data updates.

---

## **Key Features**

### **Splash Screen**
- **Logo Display:** Initial splash screen with the app logo.
- **Navigation:** Automatically navigates to the home screen after a short delay.

### **List Screen**
- **Item List:** Displays data fetched from the API in a scrollable format.
- **Swipe-to-Dismiss:** Allows users to delete items with a left-swipe gesture.
- **Divider:** Each item is separated by a customizable divider.
- **Pull-to-Refresh:** Refresh the data with a downward swipe gesture.

### **Detail Screen**
- **Complete Information:** Displays full details of the selected item, such as title, author, date, and more.
- **Link Rendering:** URL links displayed as clickable text that opens in an internal WebView.
- **Navigation Support:** Back button to return to the list screen.

### **WebView**
- **URL Rendering:** Opens links directly in a WebView within the application.
- **Enhanced Security:** Blocks malicious content and ensures secure browsing.

---

## **Architecture and Principles**

### **Clean Architecture**
The application adheres to Clean Architecture principles, organizing responsibilities into distinct layers:
1. **Presentation Layer:** Built with Jetpack Compose for modern UI development.
2. **Domain Layer:** Contains business logic and use cases.
3. **Data Layer:** Manages data sources, repositories, and network configurations.

### **MVVM Pattern**
The interaction between the UI and data is handled using ViewModels, ensuring decoupled logic from the graphical interface.

### **SOLID Principles**
The code is designed to be extensible and maintainable by applying principles such as single responsibility and dependency inversion.

---

## **Project Structure**

```plaintext
project/
├── app/
├── network/
├── database/
├── datasource/
├── repository/
├── usecase/
├── presentation/
└── build.gradle
```

### **Modules**
- **app:** Application configuration and entry point.
- **network:** Retrofit setup and DTOs.
- **database:** Room database setup for local storage.
- **datasource:** Handles remote and local data sources.
- **repository:** Core logic for data retrieval and handling.
- **usecase:** Business logic use cases.
- **presentation:** Contains UI components and theming.

---

## **UI Development**

- **Jetpack Compose:** All views are developed using the modern Compose framework.
- **Swipe-to-Dismiss:** Custom swipe gestures implemented for dismissing items.
- **Pull-to-Refresh:** Uses Material3's `PullToRefreshBox` for list updates.
- **Theme Compatibility:** Supports light and dark themes with customizable styles.

---

## **Internationalization**
The application supports English and Spanish, accommodating different regional settings.

---

## **Testing**
- **Unit Testing:** Business logic and repository tests using JUnit.

---

## **Dependency Injection**
The project uses Hilt for dependency injection, ensuring modular and testable code.

---

## **Installation**

### **Prerequisites**
- Android Studio configured
- Android SDK 21+
- Internet access to fetch API data

### **Instructions**
1. Clone the repository:
   ```bash
   git clone https://github.com/Deimer/NewsPulse.git
   ```
2. Open the project in Android Studio.
3. Build and run on an emulator or physical device.
4. Follow the navigation instructions in the app.

---

## **License**
This project is licensed under the MIT License. See the `LICENSE` file for details.
