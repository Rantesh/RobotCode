package org.usfirst.frc.team3131.robot;

import java.util.Date;

import edu.wpi.first.wpilibj.RobotDrive;

public class BackCurve {
	BackCurve(RobotDrive myRobot, Ramp ramp) {
		this.myRobot = myRobot;	
		this.ramp = ramp;
	}
	
	RobotDrive myRobot;
	private Date startTime;
	private Date currentTime;
	private Ramp ramp;
	private boolean initialized;
	
	private void init(){
		startTime = new Date();
		ramp.reset();
		initialized = true;
	}
	
	public void periodic() {
		if (!initialized){
			init();
		}
		myRobot.drive(ramp.get(), .4);
		currentTime = new Date();
	}
	public boolean finished() {
		return (currentTime.getTime() >= startTime.getTime() + 2500);
	}	
}
