package org.usfirst.frc.team3131.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;

public class ForwardDistance implements AutoCommand{
	ForwardDistance(RobotDrive myRobot, Encoder encRight, double distanceInInches) {
		this.myRobot = myRobot;
		this.encRight = encRight;
		this.distance = distanceInInches;
	}

	RobotDrive myRobot;
	Encoder encRight;
	boolean isFinished;
	boolean isInitialized;
	double distance;
	double curveCorrect;

	private void init() {
		encRight.reset();
	}
	
	public void periodic() {
		if (!isInitialized){
			init();
			isInitialized = true;
		}

		if (0 > distance) {
			myRobot.drive(-0.25,0);
		}
		else {
			myRobot.drive(0.25,0);
		}
	}

	public boolean isFinished() {
		if (!isInitialized){
			return false;
		}
		if (isFinished) {
			return true;
		}
		if (0 > distance) {
			isFinished = (encRight.getDistance() < distance);
		}
		else {
			isFinished = (encRight.getDistance() > distance);
		}
		return isFinished;
	}
}