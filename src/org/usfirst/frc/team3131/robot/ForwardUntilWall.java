package org.usfirst.frc.team3131.robot;

import java.util.Date;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.RobotDrive;

public class ForwardUntilWall {
	ForwardUntilWall(RobotDrive myRobot, AnalogInput ultraSonic) {
	this.myRobot = myRobot;	
	this.ultraSonic = ultraSonic;
	}
	
	RobotDrive myRobot;
	AnalogInput ultraSonic; 
	private Date startTime;
	private Date currentTime;
	private boolean isFinished;
	private Ramp ramp = new Ramp(-.5, .04);
	
	
	public void init() {
		startTime = new Date();
		isFinished = false;
		ramp.reset();
	}
	
	public void periodic() {
		ramp.set(Math.min(-.5*ultraSonic.getVoltage(), .5), .04);
		myRobot.drive(ramp.get(), 0);
		currentTime = new Date();
	}

	public boolean finished() {
		if (.6 > ultraSonic.getVoltage() && currentTime.getTime() >= startTime.getTime() + 1000){
			isFinished = true; 
		}
		else {
			isFinished = false;
		}
		return isFinished;
	}

}
