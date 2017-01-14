package org.usfirst.frc.team3131.robot;

import edu.wpi.first.wpilibj.RobotDrive;

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
    	runAutoStep(0,100,-0.5,0);
		runAutoStep(100,200,0,0);
		autoLoopCounter++;
    }
}
