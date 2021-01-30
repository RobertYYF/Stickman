
Game: Stickman
Version: 2.0
Autor: Yifan Yao


*** HOW TO RUN THE CODE ***

Create a new gradle project and replace src & build.gradle with the given one

Then enter "gradle build run" in the terminal to run the code


*** Features ***

When the hero reaches a flag at a level, the hero will enter the next level, 
and the hero's life would be carried on for level transition. 

When the hero reaches the flag for the last level, the game would stop (timeline stops) 
and a “You Win” banner can be seen on the screen.

Now the game is able to record the score and time. 
When the hero enters the next level, the current level score would be reset to zero 
and the total score would continue accumulating. The timer would continue ticking 
until the hero reaches the end of the game.

For every 1 second below the target time, there is 1 point added into the score; 
whereas for every 1 second over this time, there is 1 point deducted from the score. 
Score would change according to the rule until the hero reaches the end of the game 
or the mark goes down to zero.

Stickman now has more than one life displayed, which needs to be set in the configuration file. 
Losing a life does not impact points. The minimum score is 0. If all lives lost, the game is 
over with showing 'Game Over'. After the game is over, the game would automatically restart from level1.

Current level score and total score are visible on the top right corner to the window.

The player now is able to save the state of the game, which includes the entities, score, time, 
and positions of all entities by pressing “q” on the keyboard. And by pressing “e” on the keyboard, 
the player is able to load the previously saved state.

There’s only one state saved, every time the player pressed the “q” on the keyboard to save, 
the previous saved state would be overwritten.


*** Patterns ***

1. Singleton --- HeroSingle
2. Memento --- Memento, CareTaker, Oringinator


*** Controls ***

Quick Save: Q
Quick Load: E

*** Configuration File ***

The configuration file can be found at "src/main/resources/config.json".

All level data is caputured in the "levels" section of the configuration file.
There is currently 3 levels built
If you want to add a new level, simply add a new JSON object with key specified as "2", "3", "4" etc.

An example of the config file can be seen below.

**** EXAMPLE Configuration file *****


{
    "general" : {
        "application" : "Stickman",
        "subject" : "SOFT2201",
        "purpose" : "Assignment_2",
        "boilerplatecodeauthor" : "TheUniversityOfSydney",  		**** General information
        "applicationcreator" : {
        }
    },

    "game" : {
        "levelcount" : 1,			                **** Would be useful when there is more than one level
        "screensize" : [640, 400]		            **** To know then there are no more levels to load.
    },

    "levels" : {
        "1" : {
            "floor" : {
                "dirt" : [
                    {"x_axis": true, "from" : 0.0, "to" : 750.0, "qty" : 16, "pos" : 350.0},
                    {"x_axis" : false, "from" : 200.0, "to" : 300.0, "qty" : 2, "pos" : 700.0},
                    {"x_axis": true, "from" : 950.0, "to" : 3450.0, "qty" : 63, "pos" : 350.0}			**** The floor is made up of tiles.
                ],												                                        **** x_axis is true for when the pos refes to an x cordinate
                "top" : [											                                    **** and build a row of the tiles.
                    {"x_axis" : true, "from" : 0.0, "to" : 800.0, "qty" : 10, "pos" : 300.0},			**** x_axis is false for when a column of tiles needs to be
                    {"x_axis" : true, "from" : 700.0, "to" : 750.0, "qty" : 1, "pos" : 150.0}			**** built.
                ],
                "left" : [
                    {"x_axis" : false, "from" : 200.0, "to" : 300.0, "qty" : 2, "pos" : 650.0},
                    {"x_axis" : false, "from" : 200.0, "to" : 450.0, "qty" : 2, "pos" : 900.0}
                ],
                "right" : [
                    {"x_axis" : false, "from" : 200.0, "to" : 450.0, "qty" : 2, "pos" : 750.0},
                    {"x_axis" : false, "from" : 200.0, "to" : 300.0, "qty" : 2, "pos" : 1000.0}
                ],
                "left_cnr" : [
                    {"x_axis" : true, "from" : 650.0, "to" : 700.0, "qty" : 1, "pos" : 150.0},
                    {"x_axis" : true, "from" : 900.0, "to" : 950.0, "qty" : 1, "pos" : 150.0}
                ],
                "right_cnr" : [
                    {"x_axis" : true, "from" : 750.0, "to" : 800.0, "qty" : 1, "pos" : 150.0},
                    {"x_axis" : true, "from" : 1000.0, "to" : 1050.0, "qty" : 1, "pos" : 150.0}
                ]
            },
            "platforms" : [
                {"x_from" : 200.0, "x_to" : 300.0, "qty" : 1, "ypos" : 200.0},
                {"x_from" : 400.0, "x_to" : 650.0, "qty" : 4, "ypos" : 225.0},
                {"x_from" : 1050.0, "x_to" : 1150.0, "qty" : 1, "ypos" : 225.0},		**** Platforms are built by knowing where you want one or more
                {"x_from" : 1150.0, "x_to" : 1350.0, "qty" : 3, "ypos" : 100.0},		**** platforms to start and finish. The width is variable
                {"x_from" : 1350.0, "x_to" : 1500.0, "qty" : 2, "ypos" : 25.0},			**** and is determined by the equal division of the length
                {"x_from" : 1550.0, "x_to" : 1600.0, "qty" : 1, "ypos" : -25.0},		**** by the amount of platforms (qty). All platforms are 
                										                                **** horizontal and so the 'pos' attribute is always for the
												                                        **** y-axis.
                {"x_from" : 651.0, "x_to" : 651.1, "qty" : 1, "ypos" : 149.5},
                {"x_from" : 799.0, "x_to" : 799.1, "qty" : 1, "ypos" : 149.5},
                {"x_from" : 901.0, "x_to" : 901.1, "qty" : 1, "ypos" : 149.5},
                {"x_from" : 1049.0, "x_to" : 1049.1, "qty" : 1, "ypos" : 149.5},		**** These platforms are micro platforms.
                {"x_from" : 1151.0, "x_to" : 1151.2, "qty" : 1, "ypos" : 98.0},			**** The micro platforms create small unoticable objects
                {"x_from" : 1349.0, "x_to" : 1349.1, "qty" : 1, "ypos" : 99.5},			**** that the Slimes use to know when to change direction.
                {"x_from" : 1251.0, "x_to" : 1251.1, "qty" : 1, "ypos" : -100.5},		**** A Slime will chnage its direction when it hits a stationary
                {"x_from" : 1449.0, "x_to" : 1449.1, "qty" : 1, "ypos" : -100.5},		**** object. So micro platfors are used to create their boundary.
                {"x_from" : 1401.0, "x_to" : 1401.1, "qty" : 1, "ypos" : -250.5}
               
            ],
            "trees" : [
                {"x" : 25.0, "y" : 103.0, "w" : 128.0, "h" : 197.0}
            ],
            "flags" : [
                {"x" : 3165.0, "y" : -285.0, "w" : 127.0, "h" : 200.0}				    **** Other object being Trees, Flags, Mushrooms, Slimes and the
            ],											**** Hero are created here.
            "mushrooms" : [
                {"x" : 515.0, "y" : 265.0, "w" : 31.2, "h" : 41.4},
                {"x" : 2625.0, "y" : 65.0, "w" : 31.2, "h" : 41.4}
            ],
            "slimes" : [
                {"x" : 250.0, "y" : 240.0},
                {"x" : 450.0, "y" : 240.0},
                {"x" : 600.0, "y" : 240.0}
            ],
            "hero" : {"x" :  250.0, "y" :  50.0, "small" : true}				        **** The Hero can be 'true' or 'false' for the "small" attribute.
            "targetTime": 10,
            "heroLife": 3
},
	"2" : {
            "floor" : {
                "dirt" : [
                    {"x_axis": true, "from" : 0.0, "to" : 750.0, "qty" : 16, "pos" : 350.0},
                    {"x_axis" : false, "from" : 200.0, "to" : 300.0, "qty" : 2, "pos" : 700.0},
                    {"x_axis": true, "from" : 950.0, "to" : 3450.0, "qty" : 63, "pos" : 350.0}			**** The floor is made up of tiles.
                ],												                                        **** x_axis is true for when the pos refes to an x cordinate
                "top" : [											                                    **** and build a row of the tiles.
                    {"x_axis" : true, "from" : 0.0, "to" : 800.0, "qty" : 10, "pos" : 300.0},			**** x_axis is false for when a column of tiles needs to be
                    {"x_axis" : true, "from" : 700.0, "to" : 750.0, "qty" : 1, "pos" : 150.0}			**** built.
                ],
                "left" : [
                    {"x_axis" : false, "from" : 200.0, "to" : 300.0, "qty" : 2, "pos" : 650.0},
                    {"x_axis" : false, "from" : 200.0, "to" : 450.0, "qty" : 2, "pos" : 900.0}
                ],
                "right" : [
                    {"x_axis" : false, "from" : 200.0, "to" : 450.0, "qty" : 2, "pos" : 750.0},
                    {"x_axis" : false, "from" : 200.0, "to" : 300.0, "qty" : 2, "pos" : 1000.0}
                ],
                "left_cnr" : [
                    {"x_axis" : true, "from" : 650.0, "to" : 700.0, "qty" : 1, "pos" : 150.0},
                    {"x_axis" : true, "from" : 900.0, "to" : 950.0, "qty" : 1, "pos" : 150.0}
                ],
                "right_cnr" : [
                    {"x_axis" : true, "from" : 750.0, "to" : 800.0, "qty" : 1, "pos" : 150.0},
                    {"x_axis" : true, "from" : 1000.0, "to" : 1050.0, "qty" : 1, "pos" : 150.0}
                ]
            },
            "platforms" : [
                {"x_from" : 200.0, "x_to" : 300.0, "qty" : 1, "ypos" : 200.0},
                {"x_from" : 400.0, "x_to" : 650.0, "qty" : 4, "ypos" : 225.0},
                {"x_from" : 1050.0, "x_to" : 1150.0, "qty" : 1, "ypos" : 225.0},		**** Platforms are built by knowing where you want one or more
                {"x_from" : 1150.0, "x_to" : 1350.0, "qty" : 3, "ypos" : 100.0},		**** platforms to start and finish. The width is variable
                {"x_from" : 1350.0, "x_to" : 1500.0, "qty" : 2, "ypos" : 25.0},			**** and is determined by the equal division of the length
                {"x_from" : 1550.0, "x_to" : 1600.0, "qty" : 1, "ypos" : -25.0},		**** by the amount of platforms (qty). All platforms are 
                										                                **** horizontal and so the 'pos' attribute is always for the
												                                        **** y-axis.
                {"x_from" : 651.0, "x_to" : 651.1, "qty" : 1, "ypos" : 149.5},
                {"x_from" : 799.0, "x_to" : 799.1, "qty" : 1, "ypos" : 149.5},
                {"x_from" : 901.0, "x_to" : 901.1, "qty" : 1, "ypos" : 149.5},
                {"x_from" : 1049.0, "x_to" : 1049.1, "qty" : 1, "ypos" : 149.5},		**** These platforms are micro platforms.
                {"x_from" : 1151.0, "x_to" : 1151.2, "qty" : 1, "ypos" : 98.0},			**** The micro platforms create small unoticable objects
                {"x_from" : 1349.0, "x_to" : 1349.1, "qty" : 1, "ypos" : 99.5},			**** that the Slimes use to know when to change direction.
                {"x_from" : 1251.0, "x_to" : 1251.1, "qty" : 1, "ypos" : -100.5},		**** A Slime will chnage its direction when it hits a stationary
                {"x_from" : 1449.0, "x_to" : 1449.1, "qty" : 1, "ypos" : -100.5},		**** object. So micro platfors are used to create their boundary.
                {"x_from" : 1401.0, "x_to" : 1401.1, "qty" : 1, "ypos" : -250.5}
               
            ],
            "trees" : [
                {"x" : 25.0, "y" : 103.0, "w" : 128.0, "h" : 197.0}
            ],
            "flags" : [
                {"x" : 700.0, "y" : -285.0, "w" : 127.0, "h" : 200.0}				    **** Other object being Trees, Flags, Mushrooms, Slimes and the
            ],											**** Hero are created here.
            "mushrooms" : [
                {"x" : 515.0, "y" : 265.0, "w" : 31.2, "h" : 41.4},
            ],
            "slimes" : [
                {"x" : 200.0, "y" : 240.0},
                {"x" : 650.0, "y" : 240.0},
            ],
            "hero" : {"x" :  250.0, "y" :  50.0, "small" : true},
	    "targetTime": 10,
            "heroLife": 3
        }
    }

}
