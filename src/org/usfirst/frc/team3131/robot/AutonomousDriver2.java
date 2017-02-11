package org.usfirst.frc.team3131.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.RobotDrive;

public class AutonomousDriver2 implements AutonomousDriver{

	AutonomousDriver2(RobotDrive myRobot, AnalogInput ultraSonic) {
		this.myRobot = myRobot;
		this.ultraSonic = ultraSonic;
		forward = new ForwardUntilWall(myRobot, ultraSonic);
		stop = new Stop(myRobot);
		curve = new BackCurve(myRobot, ramp);
		back = new BackStraight(myRobot, ramp);
	}

	RobotDrive myRobot;
	AnalogInput ultraSonic; 
	
	private ForwardUntilWall forward;
	private Stop stop;
	private Ramp ramp = new Ramp(-0.5, .04);
	private BackCurve curve;
	private BackStraight back;
	
	public void autonomousInit() {
	}

	public void autonomousPeriodic() {
		if (!forward.isFinished()) {
			forward.periodic();
		}
		else if (!stop.isFinished())	{
			stop.periodic();
		}
		else if (!curve.isFinished()) {
			curve.periodic();
		}
		else if (!back.isFinished()) {
			back.periodic();
		}
		else {
			myRobot.drive(0, 0);
		}
	}
	
}
