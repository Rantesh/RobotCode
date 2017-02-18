package org.usfirst.frc.team3131.robot;

import java.util.Date;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;

public class ForwardDistance implements AutoCommand{
	ForwardDistance(RobotDrive myRobot, Encoder encLeft, Encoder encRight, double distanceInInches) {
		this.myRobot = myRobot;
		this.encLeft = encLeft;
		this.encRight = encRight;
		this.distance = distanceInInches;
	}

	RobotDrive myRobot;
	Encoder encLeft;
	Encoder encRight;
	boolean isFinished;
	boolean isInitialized;
	double distance;
	double curveCorrect;

	private void init() {
		encLeft.reset();
		encRight.reset();
	}
	
	public void periodic() {
		if (!isInitialized){
			init();
			isInitialized = true;
		}
		if (encLeft.getDistance() > encRight.getDistance()){
			curveCorrect = curveCorrect + .01;
		}
		else if (encRight.getDistance() < encLeft.getDistance()){
			curveCorrect = curveCorrect - .01;
		}
		myRobot.drive(0.25,0);
	}

	public boolean isFinished() {
		if (!isInitialized){
			return false;
		}
		if (isFinished) {
			return true;
		}
		isFinished = (encLeft.getDistance() > distance);
		return isFinished;
	}
}