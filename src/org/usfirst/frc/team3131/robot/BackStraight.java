package org.usfirst.frc.team3131.robot;

import edu.wpi.first.wpilibj.RobotDrive;

public class BackStraight extends TimedAutoCommand {
	BackStraight(RobotDrive myRobot, int milliseconds) {
		super(milliseconds);
		this.myRobot = myRobot;	
	}
	
	RobotDrive myRobot;
	private Ramp ramp = new Ramp(.4, .4);
	
	public void init(){
	}

	public void periodicStuff() {
		myRobot.drive(ramp.get(), 0);
	}
}
