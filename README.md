# SurveyMod

Cave surveying tools, for  actual cavers.

Have you ever used a minimap mod with a cave overlay and thought: "this is too easy and doesn't involve enough external software"? Well, good news! This mod adds some tools that will help you quickly produce .svx files for use in cave survey software of your choice.

Requires Forge.

Craft a survey stick with the following recipe:
```
  R
 S 
S
```
Where R is a redstone torch, and S is a stick.

Before starting a survey, set the filename with ```/survey name [SURVEY_NAME]```, and the cave name with ```/survey cave [CAVE_SURVEY_NAME]```. Then you can start the survey with ```/survey begin```. Each trip needs to be named with ```/survey trip [TRIP_NAME]``` before you start collecting data. Start a trip by right clicking with the survey stick, then right click all survey stations on the trip. Shift right click to stop collecting data. Once you finish a trip, you can start a new trip with ```/survey trip [TRIP_NAME]```. Type ```/survey save``` after completing a trip to save all unsaved trips up to this point. Once you finish, use the command ```/survey end``` to automatically generate exports for every trip and the survey overall. The file is written to the "MinecraftSurveyTool" directory in your home directory. This cannot be changed, yet.

You can use the command ```/survey clear active``` to remove all the data collected on the current leg. Alternatively, use ```/survey clear all``` to delete all data in the current survey.

This is an alpha release, and it's super rough around the edges. See this link for a video demo of V1.0.0a:
https://youtu.be/H05nH6NLJkw
