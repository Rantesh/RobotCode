package org.usfirst.frc.team3131.robot;

import java.util.Date;

import edu.wpi.first.wpilibj.RobotDrive;

public class Stop implements AutoCommand {
	Stop(RobotDrive myRobot) {
		this.myRobot = myRobot;
	}
	
	RobotDrive myRobot;
	private Date startTime;
	private boolean initialized;
	public boolean isFinished = false;

	public void init() {
		startTime = new Date();
		initialized = true;
	}
	
	public void periodic() {
		if (!initialized){
			init();
		}
		myRobot.drive(0, 0);
	}

	public boolean finished() {
		if (isFinished) {
			initialized = false;
			return true;
		}
		else if (!initialized){
			return false;
		}
		Date currentTime = new Date();
		isFinished = (currentTime.getTime() >= startTime.getTime() + 1000);
		return isFinished;
	}
}
