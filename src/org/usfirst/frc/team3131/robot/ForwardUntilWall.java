package org.usfirst.frc.team3131.robot;

import java.util.Date;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.RobotDrive;

public class ForwardUntilWall implements AutoCommand {
	ForwardUntilWall(RobotDrive myRobot, AnalogInput ultraSonic) {
		this.myRobot = myRobot;	
		this.ultraSonic = ultraSonic;
		if (ultraSonic == null) {
			throw new Error("UltraSonic is Null!  ");
		}
	}
	
	RobotDrive myRobot;
	AnalogInput ultraSonic; 
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
		ramp.set(/*Math.min(.5*ultraSonic.getVoltage(), .5) - Disabled temporarily because UltraSonic sensor is broken*/ .4, .04);
		myRobot.drive(ramp.get(), 0);
	}

	public boolean finished() {
		if (!initialized){
			return false;
		}
		if (isFinished) {
			//throw new Error("isFinished == true");
			return true;
		}
		Date currentTime = new Date();
		isFinished = (/*.6 > ultraSonic.getVoltage() || Same as above, enable if the Ultrasonic sensor is working correctly*/ currentTime.getTime() >= startTime.getTime() + 1500);
		return isFinished;
	}
}