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
	
	ForwardUntilWall forward = new ForwardUntilWall(myRobot, ultraSonic);
	Stop stop = new Stop(myRobot);
	BackCurve curve = new BackCurve(myRobot);
	BackStraight back = new BackStraight(myRobot);
	
	
	@Override
	public void autonomousInit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void autonomousPeriodic() {
		// TODO Auto-generated method stub
		if (!forward.HasHitWall()) {
			forward.Periodic();
		}
		else if (!stop.StopStop())	{
			stop.Periodic();
		}
		else if (!curve.Timer()) {
			curve.Periodic();
		}
		else if (!back.Timer()) {
			back.Periodic();
		}
		else {
			myRobot.drive(0, 0);
		}
	}
	
}
