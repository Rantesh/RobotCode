package org.usfirst.frc.team3131.robot;

import edu.wpi.first.wpilibj.RobotDrive;

public class Forward extends TimedAutoCommand{
	Forward(RobotDrive myRobot, int milliseconds) {
		super(milliseconds);
		this.myRobot = myRobot;
	}
	
	RobotDrive myRobot; 
	private Ramp ramp = new Ramp(0, 0, 0.01);
	
	void init() {
		ramp.reset();
	}
	
	public void periodicStuff() {
		ramp.set(0.55);
		myRobot.drive(ramp.get(), 0);
	}
}