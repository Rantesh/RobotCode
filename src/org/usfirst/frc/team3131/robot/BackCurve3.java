package org.usfirst.frc.team3131.robot;

import edu.wpi.first.wpilibj.RobotDrive;

public class BackCurve3 extends TimedAutoCommand{
	BackCurve3(RobotDrive myRobot, int milliseconds) {
		super(milliseconds);
		this.myRobot = myRobot;	
	}
	
	RobotDrive myRobot;
	private Ramp ramp = new Ramp(1, 0);
	
	public void init(){
		ramp.reset();
	}
	
	public void periodicStuff() {
		myRobot.arcadeDrive(0.5, 0.7);
	}
}
