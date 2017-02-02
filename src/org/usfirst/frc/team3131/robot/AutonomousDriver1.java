package org.usfirst.frc.team3131.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutonomousDriver1 implements AutonomousDriver {
	AutonomousDriver1(RobotDrive myRobot, AnalogInput ultraSonic, TalonSRX flywheelTalon){
		this.myRobot = myRobot;
		this.ultraSonic = ultraSonic;
		this.flywheelTalon = flywheelTalon;
	}

	private AnalogInput ultraSonic;
	private Ramp bob = new Ramp(1, .04); //It's a motor
	private Ramp frank = new Ramp(.7, .04);
	private int autoLoopCounter;
	private RobotDrive myRobot;
	private TalonSRX flywheelTalon;
	
	private void runAutoStep(double minSeconds, double maxSeconds, double speed, double curve, double sensorRange) {
    	/*if(ultraSonic.getVoltage() >= sensorRange){
    		bob.set(0, .04);
    		myRobot.drive(bob.get(), 0);
    	}
		else */if (autoLoopCounter >= minSeconds*50 && autoLoopCounter < maxSeconds*50){
    		bob.set(speed, .04);
    		myRobot.drive(bob.get(), curve);
    		
    	}
    }
	
	public void autonomousInit(){
		autoLoopCounter = 0;
		frank.reset();
	}
	
	public void autonomousPeriodic() {
    	runAutoStep(0, 1, -.5, 0, .6);
    	runAutoStep(1, 2, 0, 0, 0);
		runAutoStep(2, 4.5, .5, .4, 0);
		runAutoStep(4.5, 5.5, .5, 0, 0);
		runAutoStep(5.5, 15, 0, 0, 0);
		if (autoLoopCounter >= 8*50){
			flywheelTalon.set(frank.get());
		}
		else {
			flywheelTalon.set(0);
		}
		autoLoopCounter++;
		
		SmartDashboard.getNumber("Autonomous Mode",1);
    }
}
