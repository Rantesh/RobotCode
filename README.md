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