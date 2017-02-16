package org.usfirst.frc.team3131.robot;

import edu.wpi.first.wpilibj.RobotDrive;

public class Stop extends TimedAutoCommand {
	Stop(RobotDrive myRobot, int milliseconds) {
		super(milliseconds);
		this.myRobot = myRobot;
	}
	
	RobotDrive myRobot;

	public void init() {
	}
	
	public void periodicStuff() {
		myRobot.drive(0, 0);
	}
}