package org.usfirst.frc.team3131.robot;

import edu.wpi.first.wpilibj.RobotDrive;

public class BackCurve extends TimedAutoCommand {
	BackCurve(RobotDrive myRobot, Ramp ramp) {
		super(1500);
		this.myRobot = myRobot;	
		this.ramp = ramp;
	}
	
	RobotDrive myRobot;
	private Ramp ramp;
	
	void init(){
		ramp.reset();
	}
	
	public void periodicStuff() {
		myRobot.drive(ramp.get(), -0.35);
	}
}