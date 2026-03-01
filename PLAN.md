# Portfolio Refactoring Plan: ToyamaKankou

## Overview
Refactor the Toyama tourism guide app from a code-duplicated prototype into a clean-architecture demonstration project. Focus on eliminating 75% code duplication through proper localization, data-driven patterns, and lifecycle management.

**Timeline**: 1-2 weeks (20-30 hours)  
**Goal**: Demonstrate expert-level Android architecture & best practices  
**Scope**: Keep multi-language support (JP/EN/CN) with proper implementation  

---

## Current State Assessment

### ✅ Strengths
- Clean, polished Material Design UI
- Complete multi-language content (24 JSON files)
- Proper ViewBinding implementation
- Responsive layouts (portrait/landscape)
- Good serialization practices

### ❌ Critical Issues
- **MASSIVE CODE DUPLICATION** (~75% of codebase)
  - 27 language-specific activities (9 categories × 3 languages)
  - Identical logic repeated in MainActivityJp/En/Cn, EventJp/En/Cn, etc.
  - Cross-language imports (MainActivityEn imports classes from Jp package)
  
- **Anti-Pattern Localization**
  - Separate activities per language instead of using locale-based resources
  - Should use `strings.xml` variants, not 3x code branches
  - 3x maintenance burden for any bug fix
  
- **Incomplete Implementation**
  - OnsenJp, DenshaJp, MannerJp: Empty screens, don't load JSON data
  - bottomNavigationView declared but never used in EN/CN versions
  - No error handling for missing images (will crash)
  
- **Architecture Gaps**
  - No MVVM/MVP/clean architecture pattern
  - No ViewModels (poor lifecycle management)
  - No dependency injection
  - Hard-coded JSON filenames scattered throughout code
  - No separation of concerns (UI + routing logic mixed)
  
- **Missing Best Practices**
  - No unit tests (placeholder tests only)
  - No instrumentation tests
  - No logging
  - No accessibility support (content descriptions)
  - No ProGuard/R8 minification
  - No documentation or KDoc comments
  - No Navigation Component (using raw Intents)

### Project Maturity
- **Status**: Partially complete / prototype
- **Sustainability**: HIGH MAINTENANCE RISK due to duplication
- **Scalability**: LOW without architectural refactoring
- **Production Readiness**: Not ready (no tests, incomplete features)

---

## Improvement Plan: 6 Phases

### Phase 1: Refactor Language Architecture (6-8 hours)

**Objective**: Eliminate 75% code duplication by using proper Android localization instead of parallel code branches.

**Tasks**:
1. Create language resource files
   - `res/values/strings.xml` (default English strings)
   - `res/values-ja/strings.xml` (Japanese strings)
   - `res/values-zh/strings.xml` (Simplified Chinese strings)
   - Define all UI strings, category names, menu labels

2. Create `LocalizationManager` singleton
   - Detect system locale on app startup
   - Support runtime language switching (without restarting app)
   - Store selected language preference in SharedPreferences
   - Provide method to get current language code

3. Consolidate Activities (9 total after refactoring instead of 27)
   - **MainActivity** (replaces MainActivityJp/En/Cn): Category selection UI
   - **ListActivity** (replaces 9 category list activities): Generic list activity
     - Takes `Bundle` parameter: `categoryId`, `region`
     - Uses `CategoryRepository` to load JSON data
     - Displays results using shared `SightAdapter`
   
   - **DetailActivity** (already exists, refactor to use ViewModel)
   - **LanguageActivity** (refactored from Language.kt)
   - Remove entirely: All Jp/, En/, Cn/ package branches

4. Update Intent routing
   - Replace `startActivity(Intent(this, EventJp::class.java))` with:
     ```kotlin
     startActivity(Intent(this, ListActivity::class.java).apply {
       putExtra("category_id", "event")
       putExtra("region", region)
     })
     ```
   - Use `Intent` extras instead of class names for routing

**Code Impact**:
- Delete ~850 lines of duplicate code
- Keep ~250 lines total activity code (now generic)
- Add ~80 lines for LocalizationManager
- **Net result**: 40% reduction in total codebase

**Success Criteria**:
- All 3 language flows work without code duplication
- Language can be switched at runtime without restarting app
- No compilation errors
- Jp/En/Cn package directories deleted

---

### Phase 2: Implement Data-Driven Pattern (4-5 hours)

**Objective**: Eliminate hard-coded strings, centralize data loading, improve maintainability.

**Tasks**:
1. Create data models
   ```kotlin
   // models/TourismCategory.kt
   data class TourismCategory(
       val id: String,           // "event", "kankou", etc.
       val nameResId: Int,        // R.string.category_event
       val regions: List<String> // ["himi", "toyama", "kurobe"]
   )
   
   // Already exists but enhance:
   data class Sight(
       val name: String,
       val imageName: String,
       val description: String,
       val kind: String
   )
   ```

2. Create `CategoryRepository`
   - Centralize JSON asset loading
   - Cache parsed JSON data (avoid re-parsing on each view)
   - Return `List<Sight>` for given category ID and region
   - Implement null-safety:
     ```kotlin
     fun getSights(categoryId: String, region: String): List<Sight> {
         // Load from assets/json/
         // Parse using Kotlin serialization
         // Handle missing file gracefully
         // Cache result
     }
     ```

3. Define category constants
   ```kotlin
   object CategoryConstants {
       const val CATEGORY_KANKOU = "kankou"
       const val CATEGORY_EVENT = "event"
       const val CATEGORY_ONSEN = "onsen"
       const val CATEGORY_DENSHA = "densha"
       const val CATEGORY_BUNKA = "bunka"
       const val CATEGORY_SHOKUJI = "shokuji"
       
       const val REGION_HIMI = "himi"
       const val REGION_TOYAMA = "toyama"
       const val REGION_KUROBE = "kurobe"
       
       val ALL_CATEGORIES = listOf(
           TourismCategory(CATEGORY_KANKOU, R.string.category_attractions, listOf(REGION_HIMI, REGION_TOYAMA, REGION_KUROBE)),
           TourismCategory(CATEGORY_SHOKUJI, R.string.category_food, listOf(REGION_HIMI, REGION_TOYAMA, REGION_KUROBE)),
           // ... etc
       )
   }
   ```

4. Update image loading with fallback
   ```kotlin
   fun getImageResourceId(imageName: String): Int {
       val resourceId = resources.getIdentifier(imageName, "drawable", packageName)
       return if (resourceId != 0) resourceId else R.drawable.ic_placeholder
   }
   ```

5. Create `SightAdapter` enhancement
   - Use ViewBinding properly
   - Add content descriptions for accessibility
   - Use fallback image gracefully

**Code Impact**:
- Add ~150 lines for repository + constants
- Remove 100+ lines of hard-coded JSON filenames
- Improve maintainability significantly

**Success Criteria**:
- All data loads from CategoryRepository
- JSON parsing centralized and cached
- No hard-coded filenames in activities
- Missing images don't crash app

---

### Phase 3: Introduce ViewModels & Lifecycle Management (3-4 hours)

**Objective**: Proper lifecycle management, configuration change handling, state preservation.

**Tasks**:
1. Add ViewModel dependencies
   ```gradle
   dependencies {
       implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1'
       implementation 'androidx.activity:activity-ktx:1.7.2'
       implementation 'androidx.fragment:fragment-ktx:1.6.1'
   }
   ```

2. Create `MainViewModel`
   ```kotlin
   class MainViewModel : ViewModel() {
       private val _selectedLanguage = MutableLiveData<String>()
       val selectedLanguage: LiveData<String> = _selectedLanguage
       
       private val _selectedCategory = MutableLiveData<String>()
       val selectedCategory: LiveData<String> = _selectedCategory
       
       fun setLanguage(languageCode: String) { /* ... */ }
       fun setCategory(categoryId: String) { /* ... */ }
   }
   ```

3. Create `ListViewModel`
   ```kotlin
   class ListViewModel(
       private val repository: CategoryRepository
   ) : ViewModel() {
       private val _sights = MutableLiveData<List<Sight>>()
       val sights: LiveData<List<Sight>> = _sights
       
       fun loadSights(categoryId: String, region: String) {
           _sights.value = repository.getSights(categoryId, region)
       }
   }
   ```

4. Create `DetailViewModel`
   ```kotlin
   class DetailViewModel : ViewModel() {
       private val _currentPosition = MutableLiveData<Int>()
       val currentPosition: LiveData<Int> = _currentPosition
       
       fun setPosition(position: Int) { /* ... */ }
   }
   ```

5. Update Activities to use ViewModels
   ```kotlin
   class ListActivity : AppCompatActivity() {
       private val viewModel: ListViewModel by viewModels()
       
       override fun onCreate(savedInstanceState: Bundle?) {
           super.onCreate(savedInstanceState)
           
           val categoryId = intent.getStringExtra("category_id") ?: ""
           val region = intent.getStringExtra("region") ?: ""
           
           viewModel.loadSights(categoryId, region)
           viewModel.sights.observe(this) { sights ->
               adapter.submitList(sights)
           }
       }
   }
   ```

6. Handle Fragment state properly
   - Use `ListFragment` with ViewModel
   - Preserve scroll position on configuration changes
   - Save/restore detail view position

**Code Impact**:
- Add ~200 lines for ViewModels
- Remove instance variable storage from Activities
- Activities become ~30% simpler

**Success Criteria**:
- Rotate device: state preserved (no data reload)
- All data loads via ViewModel LiveData
- No crash on configuration changes
- Proper lifecycle cleanup

---

### Phase 4: Complete Missing Features (2-3 hours)

**Objective**: Implement the 3 currently empty screens to make the app fully functional.

**Tasks**:
1. Wire up Manner (Etiquette) info
   - Load `mannerjp.json`, `manneren.json`, `mannercn.json`
   - Display in ListActivity with category = "manner"
   - Show etiquette guidelines with images

2. Wire up Onsen (Hot Springs) info
   - Load `onsenjp.json`, `onsenen.json`, `onsencn.json`
   - Display onsen information (location, features, phone)
   - Show proper bathing etiquette in DetailActivity

3. Wire up Densha (Train) info
   - Load `denshajp.json`, `denshan.json`, `denshacn.json`
   - Display train routes, schedules
   - Show transportation etiquette

4. Test all navigation flows
   - Manually test each category in JP/EN/CN
   - Test deep drilling: Category → Region → List → Detail
   - Verify images load correctly

**Code Impact**:
- No new code needed if CategoryRepository is generic
- Just ensure JSON files exist and are loaded properly
- Add 20-30 lines for any missing screens

**Success Criteria**:
- All 6 categories display content
- All 3 languages work for all categories
- No empty screens
- All images load

---

### Phase 5: Add Testing & Quality (3-4 hours)

**Objective**: Demonstrate testing expertise and production readiness.

**Tasks**:
1. Create unit tests for `CategoryRepository`
   ```kotlin
   // test/CategoryRepositoryTest.kt
   @Test
   fun testLoadSightsFromJson() {
       // Load "kankou1jp.json"
       // Verify parsed correctly
       // Check item count, fields
   }
   
   @Test
   fun testMissingJsonHandledGracefully() {
       // Request non-existent category
       // Verify returns empty list, not crash
   }
   
   @Test
   fun testCachingWorks() {
       // Load twice, verify second is cached
   }
   ```

2. Create unit tests for localization
   ```kotlin
   // test/LocalizationManagerTest.kt
   @Test
   fun testLanguageSwitching() {
       // Set language to EN
       // Verify strings returned in English
       // Switch to JP
       // Verify strings returned in Japanese
   }
   ```

3. Create instrumentation test for navigation
   ```kotlin
   // androidTest/NavigationFlowTest.kt
   @Test
   fun testNavigationPath() {
       // Start app
       // Select language
       // Click category
       // Select region
       // Verify list displays
       // Click item
       // Verify detail shows
   }
   ```

4. Enable ProGuard/R8 minification
   - Update `build.gradle.kts` for release builds:
     ```gradle
     buildTypes {
         release {
             minifyEnabled true
             shrinkResources true
             proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
         }
     }
     ```
   - Update `proguard-rules.pro` to keep:
     - Kotlin serialization classes
     - Model classes (Sight, TourismCategory)
     - Activities, ViewModels

5. Add basic logging
   - Log category selections
   - Log JSON load times
   - Log language switches
   - Keep logs disabled in release

**Code Impact**:
- Add ~200 lines of test code
- Config changes to gradle files
- ~30 lines for logging

**Success Criteria**:
- `./gradlew test` passes all unit tests
- `./gradlew connectedAndroidTest` passes UI tests
- `./gradlew build` in release mode succeeds without warnings
- Release APK 20-30% smaller due to minification

---

### Phase 6: Polish & Documentation (2-3 hours)

**Objective**: Professional presentation and knowledge transfer.

**Tasks**:
1. Add accessibility support
   ```kotlin
   // In SightAdapter
   holder.image.contentDescription = sight.name
   holder.itemView.announceForAccessibility("${sight.name}, ${sight.description}")
   ```

2. Create comprehensive README.md
   ```markdown
   # ToyamaKankou - Tourism Guide App
   
   ## Architecture Overview
   - **Language**: Kotlin
   - **Pattern**: Data-driven with ViewModels
   - **Localization**: Proper Android locale-based strings
   - **Data**: JSON assets with repository caching
   - **UI**: Material Design 3, ViewBinding
   
   ## Project Structure
   - `data/` - Repository, models, JSON loading
   - `ui/` - Activities, Fragments, Adapters
   - `util/` - LocalizationManager, Constants
   - `res/values-*` - Localized strings
   
   ## Key Improvements Made
   - Reduced code duplication from 75% to <15%
   - Consolidated 27 activities into 10
   - Implemented proper localization (not code branching)
   - Added ViewModels for lifecycle management
   - Completed missing features (Manner, Onsen, Densha)
   - Added unit + instrumentation tests
   - Enabled R8 minification for release builds
   - Added accessibility support
   
   ## Running Tests
   - Unit tests: `./gradlew test`
   - Instrumentation: `./gradlew connectedAndroidTest`
   - Build release: `./gradlew assembleRelease`
   ```

3. Add KDoc comments to public classes
   ```kotlin
   /**
    * Repository for loading tourism data from JSON assets.
    * Implements caching to avoid repeated JSON parsing.
    *
    * @param context Android context for resource access
    */
   class CategoryRepository(private val context: Context) {
       // ...
   }
   ```

4. Code cleanup
   - Remove all commented-out code
   - Remove debug TODO comments
   - Fix any remaining formatting issues
   - Verify no unused imports

5. Final manual testing checklist
   - [ ] App opens to language selection
   - [ ] All 3 languages selectable
   - [ ] All 6 categories visible
   - [ ] All 3 regions have content
   - [ ] Detail screens show correct info + images
   - [ ] Rotation preserves state
   - [ ] No crashes or warnings in logcat
   - [ ] All tests pass

**Code Impact**:
- Add ~100 lines of documentation/comments
- Improve code readability significantly

**Success Criteria**:
- README clearly explains architecture
- Every public class has KDoc
- No compiler warnings
- Project is "portfolio ready"

---

## Portfolio Talking Points

After completing all phases, you can discuss:

1. **Architectural Excellence**
   - "Recognized and eliminated massive code duplication (75% → <15%)"
   - "Implemented proper Android localization instead of anti-pattern code branching"
   - "Introduced ViewModels for proper lifecycle management"

2. **Data-Driven Design**
   - "Centralized data loading through Repository pattern"
   - "Implemented generic Activities parameterized by data instead of creating duplicates"
   - "Reduced maintenance burden through single source of truth"

3. **Testing & Quality**
   - "Added unit tests for JSON parsing and localization"
   - "Wrote instrumentation tests for navigation flows"
   - "Enabled R8 minification demonstrating release optimization knowledge"
   - "Added accessibility support (content descriptions)"

4. **Best Practices**
   - "Proper Android resource localization (strings.xml variants)"
   - "Used ViewBinding for type-safe view access"
   - "Implemented proper error handling with fallbacks"
   - "Added comprehensive documentation (README + KDoc)"

5. **Problem Solving**
   - "Refactored from prototype to production-ready app"
   - "Completed incomplete features that were blocking functionality"
   - "Improved code quality without losing existing features"
   - "Balanced technical excellence with practical timeline constraints"

---

## Success Metrics

| Metric | Before | After | Portfolio Value |
|--------|--------|-------|-----------------|
| Code Duplication | 75% | <15% | **CRITICAL** |
| Total Activities | 27 | 10 | Shows consolidation skill |
| Lifecycle Management | None | ViewModels | Modern Android practices |
| Test Coverage | 0% (placeholder) | 40%+ | Demonstrates testing knowledge |
| Accessibility | None | Content descriptions | Professional quality |
| Documentation | None | README + KDoc | Developer readiness |
| Production Ready | No | Yes | Hireable demo |

---

## Implementation Notes

- Commit frequently to git with clear messages
- Test after each phase completion
- Phase 2 is gating (CategoryRepository must work for Phase 3)
- Phases 4-6 can happen in parallel
- Total: ~25 hours of focused work
- Result: Professional portfolio project showing 3-4 years of Android experience
