# Star Wars RPG Dice

## What is it?
The Star Wars RPG Dice app is an RPG dice-roller for Star Wars tabletop games, such as [Edge of the Empire](https://rpggeek.com/rpg/19360/star-wars-edge-empire), 
which this app was made for specifically. The app is for Android devices only.

## Tech Stack
App is built in Kotlin via Android Studio and utilises the inbuilt constraint layout system for the UI. 
Not a bit of React Native in sight, how wonderful!

## Features
The app allows the player to select which die they want to roll from the main page:

![main screen](https://res.cloudinary.com/dtnhdg7eq/image/upload/c_scale,w_200/v1679343919/sw_main_page_mcaw1g.jpg)

Then, if the game only includes one type of that die, it will roll it immediately. If it is a die that comes 
in multiple forms (as most do in the game), the app will instead prompt the user for the number of those 
particular dice they wish to roll:

![number prompt](https://res.cloudinary.com/dtnhdg7eq/image/upload/c_scale,w_200/v1679343919/sw_number_select_eqt7rc.jpg)

Finally, the app will perform the necessary number of rolls and show the results:

![roll result](https://res.cloudinary.com/dtnhdg7eq/image/upload/c_scale,w_200/v1679343919/sw_results_owvmlo.jpg)

Presto!

## Installing
While the project can be loaded into Android Studio and the debug version installed that way, the production version APK 
file is also included in the [release](app/release) directory. The latest release can be located on the Git 'Release' tab, where 
the APK file is included on its own, for ease.

Note, though the APK file is digitally signed, you will need to allow app installations from unknown locations on your device. Any 
average user of Android is likely familiar with that at this point; the setting for which can usually be found under the 'Security' 
section of your device.

## Licence & Copyright
All original copyright of the Star Wars name is held by [LucasFilm](https://www.lucasfilm.com/) and, by extension, [Disney](https://www.disney.com/). 
Copyright of the Star Wars tabletop games and all dice imagery is held by [Fantasy Flight Games](https://www.fantasyflightgames.com/). This app is in no way affiliated 
with either LucasFilm, Disney or Fantasy Flight Games and serves only as a personal project, not monetised or commercially released in any way. 
This app's source code has been released under the MIT licence.