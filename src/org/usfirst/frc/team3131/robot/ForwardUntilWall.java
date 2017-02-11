package org.usfirst.frc.team3131.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.RobotDrive;

public class ForwardUntilWall extends TimedAutoCommand {
	ForwardUntilWall(RobotDrive myRobot, AnalogInput ultraSonic) {
		super(1500);
		this.myRobot = myRobot;	
		this.ultraSonic = ultraSonic;
		if (ultraSonic == null) {
			throw new Error("UltraSonic is Null!  ");
		}
	}
	
	RobotDrive myRobot;
	AnalogInput ultraSonic; 
	private Ramp ramp = new Ramp(-.5, .04);
	
	public void init() {
		ramp.reset();
	}
	
	public void periodicStuff() {
		ramp.set(/*Math.min(.5*ultraSonic.getVoltage(), .5) - Disabled temporarily because UltraSonic sensor is broken*/ .4, .04);
		myRobot.drive(ramp.get(), 0);
	}
}