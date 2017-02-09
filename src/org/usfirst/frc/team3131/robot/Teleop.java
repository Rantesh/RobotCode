package org.usfirst.frc.team3131.robot;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Teleop {
	public Teleop(RobotDrive myRobot, TalonSRX flywheelTalon){
		this.myRobot = myRobot;
		this.flywheelTalon = flywheelTalon;
	}
	
	private Ramp flywheelRamp = new Ramp(1, .02);
	private RobotDrive myRobot;
	private Joystick stick = new Joystick(0);
	private TalonSRX flywheelTalon;
	private TalonSRX climbTalon = new TalonSRX(3);
	private Preferences prefs = Preferences.getInstance();
	private double preferenceStuff;
	private Ramp climberRamp = new Ramp(1, .02);
	private DigitalOutput blue = new DigitalOutput(0);
	private DigitalOutput red = new DigitalOutput(1);

	private static double deadband (double joystick, int power) {
		if (joystick < 0 && power % 2 == 0) {
			return -Math.pow(joystick, power);
		}
		else {
			return Math.pow(joystick, power);
		}
	}

	private void climbButton(){
/*		if (stick.getRawButton(1) == stick.getRawButton(4)){
			climberRamp.reset();
			climbTalon.set(0); 	
		}
		else if (stick.getRawButton(4) == true){
			climberRamp.set(1, .02);
			climbTalon.set(climberRamp.get());
		}
		else if (stick.getRawButton(1) == true){
			climberRamp.set(-1, .02);
			climbTalon.set(climberRamp.get());
		}*/
		climbTalon.set(deadband(stick.getRawAxis(5),5));
	}

	private void flyWheelRev(){
		if (stick.getRawAxis(3) == 1){
			flywheelTalon.set(flywheelRamp.get());			
		}
		else {
			flywheelTalon.set(0);
			flywheelRamp.reset();
		}
	}

	
	public void teleopPeriodic() {
		myRobot.arcadeDrive(deadband(-stick.getRawAxis(1), 5)*.7, deadband(-stick.getRawAxis(4), 5)*.7);
		climbButton();
		flyWheelRev();
		blue.set(stick.getRawButton(2));
		red.set(stick.getRawButton(3));
		SmartDashboard.putBoolean("Red", stick.getRawButton(3));
		SmartDashboard.putBoolean("Blue", stick.getRawButton(2));
	}

}