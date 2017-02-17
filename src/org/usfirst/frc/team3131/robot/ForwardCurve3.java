package org.usfirst.frc.team3131.robot;

import edu.wpi.first.wpilibj.RobotDrive;

public class ForwardCurve3 extends TimedAutoCommand {
	ForwardCurve3(RobotDrive myRobot, int milliseconds) {
		super(milliseconds);
		this.myRobot = myRobot;	
	}
	
	RobotDrive myRobot;
	
	public void init(){
	}
	
	public void periodicStuff() {
		myRobot.drive(-0.4, 0.7);
	}
}
