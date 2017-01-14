package org.usfirst.frc.team3131.robot;

import edu.wpi.first.wpilibj.DriverStation;
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
	
	private void autoCurve(boolean isReversed){
    	runAutoStep(0,200,-0.5,0);  // Drive forward half speed for approximately 10 seconds
		runAutoStep(200,500,0,0);
		runAutoStep(500,800,0.5,0);
		runAutoStep(800,1000,0,0.5);
		runAutoStep(1000,1200,0.5,0);
		runAutoStep(1200,2000,0,0);
		autoLoopCounter++;
    }

	public void autonomousInit(){
		autoLoopCounter = 0;
	}
	
	public void autonomousPeriodic() {
    	if (DriverStation.getInstance().getAlliance() == DriverStation.Alliance.Blue){
    		autoCurve(false);
    	}
    	else if (DriverStation.getInstance().getAlliance() == DriverStation.Alliance.Red){
    		autoCurve(true);
    	}
    	else {                              // Invalid state
    		myRobot.drive(0.0,0.0);
    		
    	}
    }
}
