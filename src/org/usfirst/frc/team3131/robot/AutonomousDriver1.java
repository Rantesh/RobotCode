package org.usfirst.frc.team3131.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutonomousDriver1 implements AutonomousDriver {
	AutonomousDriver1(RobotDrive myRobot, AnalogInput ultraSonic){
		this.myRobot = myRobot;
		this.ultraSonic = ultraSonic;
	}

	private AnalogInput ultraSonic;
	private Ramp bob = new Ramp(1,.04); //It's a motor
	private int autoLoopCounter;
	private RobotDrive myRobot;
	
	private void runAutoStep(int minCounterValue, int maxCounterValue, double speed, double curve) {
    	if (autoLoopCounter >= minCounterValue && autoLoopCounter < maxCounterValue){
    		bob.set(speed,.04);
    		myRobot.drive(bob.get(), curve);
    		
    	}
    }
	
	public void autonomousInit(){
		autoLoopCounter = 0;
	}
	
	public void autonomousPeriodic() {
/*    	runAutoStep(0,50,-0.5 ,.04);
    	runAutoStep(50,210,-0.2,.01);
		runAutoStep(210,10000,0,0);
		autoLoopCounter++;*/
		
		if (ultraSonic.getVoltage() > 0.6) {
			bob.set(-0.45, 0.04);
			//myRobot.drive(-0.45, 0);
		}
		else {
			bob.set(0, 0.04);
			//myRobot.drive(0, 0);
		}
		myRobot.drive(bob.get(), 0);
		
		SmartDashboard.getNumber("Autonomous Mode",1);
    }
}
