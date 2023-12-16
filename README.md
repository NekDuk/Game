This is a game engine, which is acts as building blocks to easily create any game in java.
You will need to know the basics of programming in java to continue (variables, methods, loops, etc)
This game engine is heavily based on the Unity game engine.
Most of what applies there will also apply to some extent here.
Documentation for Unity can be found here (https://docs.unity3d.com/560/Documentation/Manual/UnityOverview.html)

This game engine is still a work-in-progress
Which means features will be added later down the line. This game engine is only for 2D (for now?)
github link can be found here (https://github.com/NekDuk/Game/)

READ THIS SECTION IF YOU ARE GOING TO USE THE CODE
Put all your code inside the `Game` Folder (Not to be confused with the projects name which is also game, i.e. keep opening the folder called game)
It should already contain a class called `GameManager`
Any classes that need to be on a game object *must* extend `Component`
Info on how components work can be found in the documentation docx
Put any code that needs to be executed on start inside the `GameManager`s start function (ex. spawning the player, etc) (Acts as the "main" method)
Do not mess with any of the code outside of the `Game` package unless you know what you are doing as it might break the engine

Steps in downloading the project
1. Download the project by clicking `code` and click `download as zip`
2. Extract it and open th `Game` folder
3. Create a new project in your chosen IDE (Mine is Eclipse)
4. Transfer all the folders inside `Game` into your new project
5. Make sure to setup and link the `res`folder to your project

How to link `res` folder (Eclipse only)
1. Right click your project and click Build Path > Configure Build Path
2. Click the `Source` tab on the top
3. Click `add folder` and add the `res` folder

