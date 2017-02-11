package org.usfirst.frc.team3131.robot;

import edu.wpi.first.wpilibj.RobotDrive;

public class BackStraight extends TimedAutoCommand {
	BackStraight(RobotDrive myRobot, Ramp ramp) {
		super(1500);
		this.myRobot = myRobot;	
		this.ramp = ramp;
	}
	
	RobotDrive myRobot;
	private Ramp ramp;
	
	public void init(){
	}

	public void periodicStuff() {
		myRobot.drive(ramp.get(), 0);
	}
}
