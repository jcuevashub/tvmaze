# tvmaze

Design pattern: Model-View-ViewModel (MVVM)

Using Room to persist the favorite shows

Libraries

* 		Android Jetpack
*  	  ViewModel ViewModel is designed to store and manage UI-related data in a lifecycle conscious way. This allows data to survive configuration changes such as screen rotations.
* 		Glide is an image loading and caching library for Android.
* 		Kotlin coroutines Executing code asynchronously.
* 		Retrofit is a Type-safe HTTP client for Android, Java and Kotlin by Square.
* 		Gson is a serialization/deserialization library to convert objects into JSON and back.
* 		Material Design Build beautiful, usable products using Material Components for Android.
*   Biometric
*   Circleimageview

Features:

* List all of the series contained in the API used by the paging scheme provided by the API.
* Allow users to search series by name.
* The listing and search views must show at least the name and poster image of the
series.
* After clicking on a series, the application should show the details of the series, showing
the following information:
  * Name
  * Poster
  * Days and time during which the series airs
  * Genres
  * Summary
  * List of episodes separated by season
  
* Allow the user to set a PIN number to secure the application and prevent unauthorized users.
* For supported phones, the user must be able to choose if they want to enable fingerprint authentication to avoid typing the PIN number while opening the app.
* Allow the user to save a series as a favorite.
* Allow the user to delete a series from the favorites list. (with Swipe)
* Allow the user to browse their favorite series in alphabetical order, and click on one to
see its details.
* Create a people search by listing the name and image of the person.
* After clicking on a person, the application should show the details of that person, such
as:
  * Name
  * Image

How to install APKs

Install Apps from Unknown Sources in Android 8.0 Oreo and 9.0 Pie
https://www.maketecheasier.com/assets/uploads/2019/02/install-unknown-sources-android-8-9-blocked.png.webp

As of Android 8.0, the method to install apps from unknown sources has changed. Instead of the previous method where you gave permission to download APKs through any existing app on your phone, from now on you need to give permission to specific apps that are allowed to install APKs from unknown sources.

To set these permissions, go to “Settings -> Apps & notifications -> Advanced -> Special app access -> Install unknown apps”.

https://www.maketecheasier.com/assets/uploads/2019/02/install-unknown-sources-android-8-9-install-unknown-app.png.webp

Here, select the app (usually your internet browser) that you want to grant permission to install from unknown sources. Then once you’ve tapped it, tap “Allow from this source” to enable unknown sources for that specific app.

https://www.maketecheasier.com/assets/uploads/2019/02/install-unknown-sources-android-8-9-allow-source.png.webp
