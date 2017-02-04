package org.usfirst.frc.team3131.robot;

import java.util.Date;

import edu.wpi.first.wpilibj.RobotDrive;

public class Stop {
	Stop(RobotDrive myRobot) {
		this.myRobot = myRobot;
	}
	
	RobotDrive myRobot;
	private Date startTime;
	private Date currentTime;

	public void init() {
		startTime = new Date();
	}
	
	public void periodic() {
		myRobot.drive(0, 0);
		currentTime = new Date();
	}

	public boolean finished() {
		if (currentTime.getTime() >= startTime.getTime() + 1000) {
			return true;
		}
		else {
			return false;
		}
	}

}
