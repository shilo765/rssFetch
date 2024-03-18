
Android Developer Home Assignment README

Introduction:
This project is a small Android application developed as a Home Assignment for Android Developers. The goal of the assignment is to create a mobile app that presents information from RSS feeds in a visually pleasing manner and incorporates various functionalities.

Features:
First Page:

Displays the developer's name- shilo ben natan.
Dynamically updates the date and time every minute.
Contains an empty label.
Provides a button to navigate to the second page.


Second Page:

Consists of two tabs:
The first tab displays a grid of information from the RSS feed of CNN.COM -> Travel.
The second tab combines data from RSS feeds of CNN.COM -> World Sport and CNN.COM -> Entertainment, presenting them in a list. Items from "World Sport" are displayed first followed by items from "Entertainment".
Each item in the list has a background color differentiating between sport and entertainment news.
Selecting a feed item opens a WebView with the corresponding link.
Returning to the first page updates the empty label with the title of the last opened feed from the second page. Clicking on the label redirects the user to the WebView again.
The application checks each RSS source every 5 seconds for updates, ensuring immediate UI updates upon new information.

![image](https://github.com/shilo765/rssFetch/assets/63932084/6fcbb95a-89a6-4c73-8ea3-772cfc6403bc)
