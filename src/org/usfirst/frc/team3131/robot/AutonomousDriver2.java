package org.usfirst.frc.team3131.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.RobotDrive;

public class AutonomousDriver2 implements AutonomousDriver{

	AutonomousDriver2(RobotDrive myRobot, AnalogInput ultraSonic) {
		this.myRobot = myRobot;
		this.ultraSonic = ultraSonic;
	}

	RobotDrive myRobot;
	AnalogInput ultraSonic; 
	
	private ForwardUntilWall forward = new ForwardUntilWall(myRobot, ultraSonic);
	private Stop stop = new Stop(myRobot);
	private Ramp ramp = new Ramp(.5, .04);
	private BackCurve curve = new BackCurve(myRobot, ramp);
	private BackStraight back = new BackStraight(myRobot, ramp);
	
	public void autonomousInit() {
	}

	public void autonomousPeriodic() {
		if (!forward.finished()) {
			forward.periodic();
		}
		else if (!stop.finished())	{
			stop.periodic();
		}
		else if (!curve.finished()) {
			curve.periodic();
		}
		else if (!back.finished()) {
			back.periodic();
		}
		else {
			myRobot.drive(0, 0);
		}
	}
	
}
