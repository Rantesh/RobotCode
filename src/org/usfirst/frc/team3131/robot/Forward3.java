package org.usfirst.frc.team3131.robot;

import java.util.Date;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.RobotDrive;

public class Forward3 implements AutoCommand{
	Forward3(RobotDrive myRobot, Ramp ramp) {
		this.myRobot = myRobot;
		this.ramp = ramp;
	}
	
	RobotDrive myRobot; 
	private Date startTime;
	public boolean isFinished = false;
	private Ramp ramp = new Ramp(-.5, .04);
	private boolean initialized;
	
	public void init() {
		startTime = new Date();
		ramp.reset();
		initialized = true;
	}
	
	public void periodic() {
		if (!initialized){
			init();
		}
		ramp.set(.4, .04);
		myRobot.drive(ramp.get(), 0);
	}

	public boolean finished() {
		if (!initialized){
			return false;
		}
		if (isFinished) {
			return true;
		}
		Date currentTime = new Date();
		isFinished = (currentTime.getTime() >= startTime.getTime() + 1500);
		return isFinished;
	}
}
