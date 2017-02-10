package org.usfirst.frc.team3131.robot;

import java.util.Date;

import edu.wpi.first.wpilibj.RobotDrive;

public class BackCurve implements AutoCommand {
	BackCurve(RobotDrive myRobot, Ramp ramp) {
		this.myRobot = myRobot;	
		this.ramp = ramp;
	}
	
	RobotDrive myRobot;
	private Date startTime;
	private Ramp ramp;
	private boolean initialized;
	public boolean isFinished = false;
	
	public void init(){
		startTime = new Date();
		ramp.reset();
		initialized = true;
	}
	
	public void periodic() {
		if (!initialized){
			init();
		}
		myRobot.drive(ramp.get(), -0.35);
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
		isFinished = (currentTime.getTime() >= startTime.getTime() + 1500);
		return isFinished;
	}	
}