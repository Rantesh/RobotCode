package org.usfirst.frc.team3131.robot;

import edu.wpi.first.wpilibj.RobotDrive;

public class Stop extends TimedAutoCommand {
	Stop(RobotDrive myRobot) {
		super(1500);
		this.myRobot = myRobot;
	}
	
	RobotDrive myRobot;

	public void init() {
	}
	
	public void periodicStuff() {
		myRobot.drive(0, 0);
	}
}