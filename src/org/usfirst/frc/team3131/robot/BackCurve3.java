package org.usfirst.frc.team3131.robot;

import edu.wpi.first.wpilibj.RobotDrive;

public class BackCurve3 extends TimedAutoCommand{
	BackCurve3(RobotDrive myRobot, Ramp ramp) {
		super(1500);
		this.myRobot = myRobot;	
		this.ramp = ramp;
	}
	
	RobotDrive myRobot;
	private Ramp ramp;
	
	public void init(){
		ramp.reset();
	}
	
	public void periodicStuff() {
		myRobot.drive(ramp.get(), 0.35);
	}
}
