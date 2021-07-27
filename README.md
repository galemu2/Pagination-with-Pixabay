# Pagination-with-Pixabay

Pagination with Pixabay is a simple app that shows a list of cat images from pixabay.  It used retrofit to `GET` impages and display result in `Paged` `RecyclerView`.
## API key setup
Api Key was obtained from [pixabay](https://pixabay.com/service/about/api/)
1. add `gradle.properties` into `.gitignore` file
2. Place api key in `gradle.properties` file
```
    API_KEY="your pixabay api key"
```
3. Add following line in app level `build.gradle` file,
```gradle
android {
    ...
    defaultConfig {
    ...
        buildConfigField("String", "API_KEY", API_KEY)
    }
}
```
4. Use API key within code using BuildConfig
```
    val API_KEY :String = BuildConfig.API_KEY
 ```

 ## License

MIT