package org.usfirst.frc.team3131.robot;

import edu.wpi.first.wpilibj.RobotDrive;

public class AutonomousDriver3 implements AutonomousDriver{
	
	AutonomousDriver3(RobotDrive myRobot){
		forward = new Forward3(myRobot, ramp);
		forwardCurve = new ForwardCurve3(myRobot, ramp);
		stop = new Stop(myRobot);
		backCurve = new BackCurve3(myRobot, ramp);
		backStraight = new BackStraight(myRobot, ramp);
	}
	
	private Ramp ramp = new Ramp(-.5, .04);
	private Forward3 forward;
	private ForwardCurve3 forwardCurve;
	private Stop stop;
	private BackCurve3 backCurve;
	private BackStraight backStraight;
	
	public void autonomousInit(){		
	}
	
	public void autonomousPeriodic(){
		if (!forward.isFinished()) {
			forward.periodic();
		}
		if (!forwardCurve.isFinished()) {
			forwardCurve.periodic();
		}
		if (!stop.isFinished()) {
			stop.periodic();
		}
		if (!backCurve.isFinished()) {
			backCurve.periodic();
		}
		if (!backStraight.isFinished()) {
			backStraight.periodic();
		}
	}
}
