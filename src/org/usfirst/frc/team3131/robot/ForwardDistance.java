package org.usfirst.frc.team3131.robot;

import java.util.Date;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;

public class ForwardDistance implements AutoCommand{
	ForwardDistance(RobotDrive myRobot, Encoder enc, double distanceInInches) {
		this.myRobot = myRobot;
		this.enc = enc;
		this.distance = distanceInInches;
	}

	RobotDrive myRobot;
	Encoder enc;
	boolean isFinished;
	boolean isInitialized;
	double distance;

	private void init() {
		enc.reset();
	}
	
	public void periodic() {
		if (!isInitialized){
			init();
			isInitialized = true;
		}
		myRobot.drive(-0.5,0);
	}

	public boolean isFinished() {
		if (!isInitialized){
			return false;
		}
		if (isFinished) {
			return true;
		}
		isFinished = (enc.getDistance() > distance);
		return isFinished;
	}
}