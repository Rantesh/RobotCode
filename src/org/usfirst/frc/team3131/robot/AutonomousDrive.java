package org.usfirst.frc.team3131.robot;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutonomousDrive {
	AutonomousDrive(RobotDrive myRobot){
		this.myRobot = myRobot;
	}

	private int autoLoopCounter;
	private RobotDrive myRobot;
	
	private void runAutoStep(int minCounterValue, int maxCounterValue, double speed, double curve) {
    	if (autoLoopCounter >= minCounterValue && autoLoopCounter < maxCounterValue){
    		myRobot.drive(speed, curve);
    	}
    }
	
	public void autonomousInit(){
		autoLoopCounter = 0;
	}
	
	public void autonomousPeriodic() {
		int time = (int)(50*SmartDashboard.getNumber("Time Moving",1));
    	runAutoStep(0,time,SmartDashboard.getNumber("Power Used",0.3),0);
		runAutoStep(time,time+200,0,0);
		autoLoopCounter++;
		
		
		SmartDashboard.getNumber("Autonomous Mode",1);
    }
}
