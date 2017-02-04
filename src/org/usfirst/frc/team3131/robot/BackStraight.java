package org.usfirst.frc.team3131.robot;

import edu.wpi.first.wpilibj.RobotDrive;

public class BackStraight {
	BackStraight(RobotDrive myRobot) {
		this.myRobot = myRobot;	
	}
	
RobotDrive myRobot;
int counter;

public void Periodic() {
	myRobot.drive(.7, 0);
}
public boolean Timer() {
	if (counter == 100) {
		return true;
	}
	else {
		counter = counter+1;
		return false;
	}
}	


}
