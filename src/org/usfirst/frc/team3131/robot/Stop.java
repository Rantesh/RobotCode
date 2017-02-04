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
	private boolean initialized;

	private void init() {
		startTime = new Date();
		initialized = true;
	}
	
	public void periodic() {
		if (!initialized){
			init();
		}
		myRobot.drive(0, 0);
		currentTime = new Date();
	}

	public boolean finished() {
		return (currentTime.getTime() >= startTime.getTime() + 1000);
	}
}
