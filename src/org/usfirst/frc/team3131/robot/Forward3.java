package org.usfirst.frc.team3131.robot;

import edu.wpi.first.wpilibj.RobotDrive;

public class Forward3 extends TimedAutoCommand{
	Forward3(RobotDrive myRobot, Ramp ramp) {
		super(1500);
		this.myRobot = myRobot;
		this.ramp = ramp;
	}
	
	RobotDrive myRobot; 
	private Ramp ramp = new Ramp(-.5, .04);
	
	void init() {
		ramp.reset();
	}
	
	public void periodicStuff() {
		ramp.set(.4, .04);
		myRobot.drive(ramp.get(), 0);
	}
}