# ToyamaKankou (富山観光)

An Android application demonstrating localization, data-driven design patterns, lifecycle-aware components and modern Android development best practices. The app serves as a comprehensive travel guide to Toyama Prefecture, Japan, offering tourist information across three languages: **English**, **Japanese**, and **Simplified Chinese**.

---
## App Preview

| Home Screen                                                                                              | Travel View                                                                                                | Event Page                                                                                            |
| :---------------------------------------------------------------------------------------------------------| :---------------------------------------------------------------------------------------------------------| :---------------------------------------------------------------------------------------------------------|
| <img src="https://github.com/user-attachments/assets/3f55e99a-9830-44ee-b324-f42f22793d04" width="100%"> | <img src="https://github.com/user-attachments/assets/a7e8ec84-c37d-43b1-a10c-56007b62e598" width="100%"> | <img src="https://github.com/user-attachments/assets/dfa7a6aa-abb7-4e88-9ebc-2c286768e7d4" width="100%"> |

---

##  Core Features

### 1. Unified Localization System
Instead of maintaining duplicate codebases for English (`en`), Japanese (`ja`), and Chinese (`zh`), the application implements the custom `LocalizationManager` which:
*   Automatically detects the system default locale.
*   Allows seamless switching between English, Japanese, and Chinese at runtime.
*   Persists user preferences using `SharedPreferences`.
*   Recreates application context using dynamic configuration locale override.

### 2. Data-Driven Sight Guides
Instead of hardcoding layout details, sights are defined dynamically in localized JSON files (`assets/kankou1jp.json`, `assets/kankou1en.json`, etc.). Sights are categorized by:
*   **Attractions (観光地):** Scenic spots across three key regions: Himi/Tonami area, Toyama City area, and Kurobe/Tateyama area.
*   **Food (食事):** Local gourmet recommendations by region.
*   **Events (イベント):** Festivals and local events.
*   **Culture (文化):** Cultural workshops, museums, and historical activities.
*   **Etiquette (マナー):** Travel manners and dining rules.
*   **Hot Springs (温泉):** Hot spring baths and spa resorts.
*   **Transportation (電車):** Transit routes and train systems.

### 3. Bulletproof Architecture
*   **ViewModel Cache Layer:** Sights data parsed from JSON is cached dynamically in `CategoryRepository` using a memory cache map.
*   **Lifecycle Awareness:** ViewModels handle data updates using `LiveData` to prevent visual bugs when rotating screens or moving across activities.
*   **Fail-safe Fallbacks:** Missing images or invalid resource references resolve gracefully to `ic_placeholder.xml` instead of causing app crashes.

---

##  Key Achievements & Refactoring Impact
The project underwent a significant architectural overhaul, refactoring it from a code-duplicated prototype to a production-ready portfolio application.

| Metric | Before Refactoring | After Refactoring | Architectural Impact |
|:---|:---|:---|:---|
| **Code Duplication** | ~75% | **<15%** | Eliminates redundant classes and code maintenance overhead. |
| **Activity Count** | 27 Activities | **10 Activities** | High maintainability, unified entry points, and dynamic loading. |
| **Localization Method** | 3x Parallel Code Branches (e.g. `MainActivityJp`, `MainActivityEn`, `MainActivityCn`) | **Locale-based Resource Resolution** | Proper Android system resource qualifiers with standard system locale support. |
| **Data Loading** | Hardcoded JSON filenames inside Views | **Repository Pattern with Caching** | Centralized data logic, memory cache to avoid unnecessary asset parsing. |
| **State Preservation** | Reset on screen rotation | **ViewModel & LiveData** | State is preserved across configuration changes (e.g., orientation). |
| **Accessibility (a11y)** | Missing descriptions | **Fully Accessible Views** | Dynamic content descriptions and announcements for screen readers. |

---

##  Tech Stack & Modern Architecture
*   **Language:** Kotlin (100%)
*   **Architecture Pattern:** MVVM (Model-View-ViewModel) + Repository Pattern
*   **UI & Views:** ViewBinding, Material Design 3, Fragments, `RecyclerView`
*   **Data Serialization:** Kotlinx Serialization (JSON Parsing)
*   **Localization:** Dynamic runtime locale shifting, Android locale qualifiers (`values-ja/`, `values-zh/`), and SharedPreferences persistence.
*   **Tests:** Local JUnit tests & Android Instrumentation tests (Espresso).
*   **Optimizations:** ProGuard/R8 minification enabled for release builds.

---

##  Project Structure Overview
```
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/travelapp/
│   │   │   │   ├── data/
│   │   │   │   │   ├── CategoryConstants.kt      # Core identifiers & layout resource mapping
│   │   │   │   │   └── CategoryRepository.kt     # Cached JSON parsing with error recovery fallbacks
│   │   │   │   ├── util/
│   │   │   │   │   └── LocalizationManager.kt    # Singleton runtime locale switcher
│   │   │   │   ├── viewmodel/
│   │   │   │   │   ├── MainViewModel.kt          # UI state for MainActivity
│   │   │   │   │   ├── ListViewModel.kt          # UI state for ListFragment
│   │   │   │   │   └── DetailViewModel.kt        # UI state for DetailFragment
│   │   │   │   ├── MainActivity.kt               # Gateway dashboard screen
│   │   │   │   ├── ChooseActivity.kt             # Regional filter screen (Himi, Toyama, Kurobe)
│   │   │   │   ├── ListActivity.kt               # Dynamic listings host activity
│   │   │   │   ├── ListFragment.kt               # Multi-region sights recycler list view
│   │   │   │   ├── DetailActivity.kt             # Dynamic swipe detail host activity
│   │   │   │   ├── DetailFragment.kt             # Individual tourist spot information view
│   │   │   │   ├── MannerActivity.kt             # Etiquette guide activity
│   │   │   │   ├── SightAdapter.kt               # RecyclerView list adapter with ViewBinding
│   │   │   │   └── Sight.kt                      # Kotlinx-Serializable data class representation
│   │   │   ├── assets/                           # Multilingual JSON content databases
│   │   │   └── res/                              # Localized string and theme resource qualifiers
│   │   └── test/                                 # Local unit testing suites
│   │   └── androidTest/                          # UI & integration tests
│   └── build.gradle.kts                          # App build configuration & dependencies
└── settings.gradle.kts                           # Module definition
```

---

##  Developer Setup & Instructions

### Prerequisites
*   **JDK 17** or higher
*   **Android Studio Jellyfish / Koala** (or newer)
*   **Gradle 8.13** (included via wrapper)

### Build Commands
To build the application locally, use the following commands from the root directory:

```bash
# Clean the project build directories
./gradlew clean

# Build the debug APK
./gradlew assembleDebug

# Build and optimize release APK (with ProGuard/R8 shrinking enabled)
./gradlew assembleRelease
```

### Running Tests
Ensure all behaviors are functional by running:

```bash
# Execute local unit tests (caching logic, parsing, utility classes)
./gradlew test

# Execute Android instrumentation/Espresso UI tests (running device or emulator needed)
./gradlew connectedAndroidTest
```
