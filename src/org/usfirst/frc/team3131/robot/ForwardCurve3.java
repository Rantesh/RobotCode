package org.usfirst.frc.team3131.robot;

import edu.wpi.first.wpilibj.RobotDrive;

public class ForwardCurve3 extends TimedAutoCommand {
	ForwardCurve3(RobotDrive myRobot, Ramp ramp) {
		super(1500);
		this.myRobot = myRobot;	
		this.ramp = ramp;
	}
	
	RobotDrive myRobot;
	private Ramp ramp;
	
	public void init(){
	}
	
	public void periodicStuff() {
		ramp.set(.4, .04);
		myRobot.drive(ramp.get(), 0.35);
	}
}
