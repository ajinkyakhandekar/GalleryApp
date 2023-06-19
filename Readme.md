# GalleryApp
This repository contains a Gallery app built with Jetpack Compose and MVVM architecture
The goal is to display list of photos along with an option to mark the photos as Favorites and diplay them in another screen.

This app showcase the use of Jetpack Compose, Material components, UI state handling, Dependency Injection and implementing Room, Retrofit and Repository Pattern.
It is also supported by Unit tests for viewmodels, repository & data sources


## Screenshots

| Gallery | Gallery | Favorite |
| --- | --- | --- |
|<img src="https://github.com/ajinkyakhandekar/GalleryApp/blob/master/ss1.jpg">| <img src="https://github.com/ajinkyakhandekar/GalleryApp/blob/master/ss2.jpg">| <img src="https://github.com/ajinkyakhandekar/GalleryApp/blob/master/ss3.jpg">|

## Features

The Gallery App contains 1 home screen which contains 2 Bottom Navigation Tabs - Gallery & Favorites

- Home - Screen that host bottom navigation tabs and the corresponding screens

- Gallery - Where we display list (2 column grid) of Photos
User can add or remove the photos as Favorites

- Favorites - Where we display list (2 column grid) of Photos that are marked as Favorites by the user
User can add or remove the photos as Favorites and it will be reflected on gallery screen as well


## Data
Images are sourced from https://picsum.photos/ and loaded using coil.

Favorites are stored in the Room database.
We add an entry in the database when user adds any photo as favorite and remove the entry when user unselects it.

