package org.usfirst.frc.team3131.robot;

import edu.wpi.first.wpilibj.RobotDrive;

public class BackCurve extends TimedAutoCommand {
	BackCurve(RobotDrive myRobot, int milliseconds) {
		super(milliseconds);
		this.myRobot = myRobot;	
	}
	
	RobotDrive myRobot;
	private Ramp ramp = new Ramp(0.6, 0, 0.01);
	
	void init(){
		ramp.reset();
	}
	
	public void periodicStuff() {
		myRobot.drive(-ramp.get(), 0.55);
	}
}