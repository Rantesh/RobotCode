package org.usfirst.frc.team3131.robot;

import java.util.Date;

import edu.wpi.first.wpilibj.RobotDrive;

public class ForwardCurve3 implements AutoCommand {
	ForwardCurve3(RobotDrive myRobot, Ramp ramp) {
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
		initialized = true;
	}
	
	public void periodic() {
		if (!initialized){
			init();
		}
		ramp.set(.4, .04);
		myRobot.drive(ramp.get(), 0.35);
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
