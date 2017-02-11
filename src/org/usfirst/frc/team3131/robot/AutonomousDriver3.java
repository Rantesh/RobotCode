package org.usfirst.frc.team3131.robot;

import edu.wpi.first.wpilibj.RobotDrive;

public class AutonomousDriver3 implements AutonomousDriver{
	
	AutonomousDriver3(RobotDrive myRobot){
		this.myRobot = myRobot;
		forward = new Forward3(myRobot, ramp);
		forwardCurve = new ForwardCurve3(myRobot, ramp);
		stop = new Stop(myRobot);
		backCurve = new BackCurve3(myRobot, ramp);
		backStraight = new BackStraight(myRobot, ramp);
	}
	
	private RobotDrive myRobot;
	private Ramp ramp = new Ramp(-.5, .04);
	private Forward3 forward;
	private ForwardCurve3 forwardCurve;
	private Stop stop;
	private BackCurve3 backCurve;
	private BackStraight backStraight;
	
	public void autonomousInit(){		
	}
	
	public void autonomousPeriodic(){
		if (!forward.finished()) {
			forward.periodic();
		}
		if (!forwardCurve.finished()) {
			forwardCurve.periodic();
		}
		if (!stop.finished()) {
			stop.periodic();
		}
		if (!backCurve.finished()) {
			backCurve.periodic();
		}
		if (!backStraight.finished()) {
			backStraight.periodic();
		}
	}
}
