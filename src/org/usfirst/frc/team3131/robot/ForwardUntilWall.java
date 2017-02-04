package org.usfirst.frc.team3131.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.RobotDrive;

public class ForwardUntilWall {
	ForwardUntilWall(RobotDrive myRobot, AnalogInput ultraSonic) {
	this.myRobot = myRobot;	
	this.ultraSonic = ultraSonic;
	}
	
RobotDrive myRobot;
AnalogInput ultraSonic; 

public void Periodic() {
	myRobot.drive(1, 0);
}

public boolean HasHitWall() {
	if (.6 > ultraSonic.getVoltage()){
		return true; 
	}
	else {
		return false;
	}
}

}
