# Team 3131 Steamworks Robot

Use this file to keep notes about the coding project. Please update this file with anything that will be useful to the team.

## Getting Started with GitHub

* Download GitHub desktop app with Internet Explorer (if not already on computer).  Will fail if using Microsoft Edge.
* Create an account at www.github.com
* Go to https://www.github.com/Rantesh/RobotCode and star and watch project.
* Request for Ryan to add you as a collaborator (you must accept Ryan's invite via email before you will have access).
* Follow the directions at https://help.github.com/desktop/guides/contributing/cloning-a-repository-from-github-desktop/ to clone the https://www.github.com/Rantesh/RobotCode to the computer if it isn't already there.


## How to work

Make sure to pull the latest version of the code from Github before you start working (you can use the "sync" feature in Github Desktop). 

Commit and push your changes to Github before leaving for the day. This will make sure everyone else has access to your latest changes when they start work on the code.

Update this readme.md file daily with a brief status update so other students coming in the following day know where the project is at.


## To Do:
* Autonomous
   * what should the robot do?
      * deposit a gear?
      * drive across the baseline?
      * shoot balls in upper goal?
      * deposit balls in lower goal?
   * where should it start and what path should it take?
   * how accurate and consistent is the robot in following the coded path? Do we need any sensors? 
   * understand the geometry of the robot and which axis it turns around
* allow operator to switch between tank and arcide drive by pressing a button on the controller
* Get debugging working!
* *ADD IDEAS TO THIS LIST WHEN YOU THINK OF THEM*

##Port mapping:
* PWM
   * Robot drive is on ports 1 and 2
   * The climb talons are on port 3 and 4
   * The shooter wall servo is on port 5
   * The flywheel is on port 6
   * The (currently unused) agitator servo is on port 7

## TEST TEST TEST

Get the software on the robot as early and often as possible! This will allow us to prove out and continuously improve our code as we go. It will also help mechanical and electrical to discover problems within their areas sooner. And its fun.

## Daily Status Updates
* 1/13 Ryan split out robot.java into 3 separate files and published the project to Github
* 1/14 Set up Autonomous for testing, including SmartDashboard in Autonomous.  Judah has GitHub account and permissions now.
* 1/18 Tested autonomous a ton.  Got to go straight for seven feet.  Gage, Josh, Frankie, and Hunter have GitHub with permissions.
* 1/20 We made the Ramp class and implemented it into teleop and autonomous drive. Minor adjustments to methods in teleop.
* 1/21 Got the ultraSonic sensor working.  Tested it in autonomous.
* 1/24 Worked on SmartDashboard; got the autoSwitch working.  Helped mechanical test climbing.  Created an interface for multiple autonomous programs.  Started working on editing robot preferences from the SmartDashboard.  
* 1/28 Changed deadband program to use a parabolic power rate.  Tested it.  It works.
* 1/31 Updated autonomous code's runAutoStep to include the distance sensor. 
* 2/1 Autonomous - got our first autonomous working (must test for precision, still). Could not test the distance sensor code for it, though.
* 2/2 Set up climbTalon to work with a Ramp.  Started planning for a complete change in how Autonomous runs (command based).
* 2/3 Created commands for Autonomous2
* 2/4 Major rewrite of Autonomous2 and its commands, started working on preferences, and did some teleop testing.
* 2/7 Created and began implementing the interface AutoCommand.  Fixed bugs in AutonomousDriver2 that prevented it from running.
* 2/8 Got a ton of errors for ForwardUntilWall and apparently fixed them.
* 2/9 AUTODRIVER2 WORKS!!!!  65 POINTS IN THE BAG! The ultraSonic sensor appears to be broke, so we are making due without..
* 2/10 Made a new autonomous program for the right gear position, isn't quite working yet.
* 2/11 Refactored Auto2 and Auto3, tested Auto2 and works successfuly, tested Auto3 and it appears to use Forward and ForwardCurve, but nothing else.
* 2/14 Refactored Auto2 and Auto3.  Auto3 should work but we forgot to test it.
* 2/16 Spent the whole night testing Auto.  At the end of the night we got the bad RoboRio working!
* 2/17 Setup encoder for testing, put all of our timed autonomous variables into SmartDashboard, still need to test this.
* 2/18 Whichever encoder we put in ports 0 and 1 works; the other will not work.  We may have fixed this error by using k2X instead of k4X, but couldn't test.  Setup but have not tested ForwardDistance for use going backwards.
* 2/20 Got the shooter working.  Implemented a servo to accomplish this.
* 3/4 Have been working on getting Synthesis to work - we seem to be making progress, but it has not succeded at all yet.
* 3/14 Testing Encoders.  Right Encoder is working.  Code is still in test mode with DigitalInputs instead of Encoder objects.