
Android Developer Home Assignment README

Introduction:
This project is a small Android application developed as a Home Assignment for Android Developers. The goal of the assignment is to create a mobile app that presents information from RSS feeds in a visually pleasing manner and incorporates various functionalities.

Features:
First Page:

Displays the developer's name- shilo ben natan.
Dynamically updates the date and time every minute.
Contains an empty label.
Provides a button to navigate to the second page.
![image](https://github.com/shilo765/rssFetch/assets/63932084/8b09661e-17fe-405d-aabd-65e55cc3973b)



Second Page:

Consists of two tabs:
The first tab displays a grid of information from the RSS feed of CNN.COM -> Travel.
The second tab combines data from RSS feeds of CNN.COM -> World Sport and CNN.COM -> Entertainment, presenting them in a list. Items from "World Sport" are displayed first followed by items from "Entertainment".
Each item in the list has a background color differentiating between sport and entertainment news.
Selecting a feed item opens a WebView with the corresponding link.
Returning to the first page updates the empty label with the title of the last opened feed from the second page. Clicking on the label redirects the user to the WebView again.
The application checks each RSS source every 5 seconds for updates, ensuring immediate UI updates upon new information.

![image](https://github.com/shilo765/rssFetch/assets/63932084/6fcbb95a-89a6-4c73-8ea3-772cfc6403bc)



![image](https://github.com/shilo765/rssFetch/assets/63932084/efc958f1-837a-4047-802a-7da60390ad0f)

i maid the first page also in react native app page:




![image](https://github.com/shilo765/rssFetch/assets/63932084/1293e964-7bb2-4ebb-a5a9-01d99cb6a110)



Installing projects
Android Studio:
1) Build a new Kotlin project
2) Paste all the folders in place except REACTPAGE and replace them instead of the default folders
Run the project (the project runs on Android 31 and above)

React Native:
1) Build a new React Native project
2) Replace the APP.jsx file with the APP file found in REACTPAGE and run the project

