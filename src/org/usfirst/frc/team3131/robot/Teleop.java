package org.usfirst.frc.team3131.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Teleop {
	public Teleop(RobotDrive myRobot){
		this.myRobot = myRobot;
	}
	
	private Ramp flywheelRamp = new Ramp(1,0.02);
	private RobotDrive myRobot;
	private Joystick stick = new Joystick(0);
	private TalonSRX flywheelTalon = new TalonSRX (3);
	private TalonSRX climbTalon = new TalonSRX(2);
	private Preferences prefs = Preferences.getInstance();
	private double preferenceStuff;

	private static double deadband (double joystick, int power) {
		if (joystick < 0 && power % 2 == 0) {
			return -Math.pow(joystick, power);
		}
		else {
			return Math.pow(joystick, power);
		}
	}

	private void climbButton(){
		preferenceStuff = prefs.getDouble("PreferenceTesting", 0.03);
		climbTalon.set(0.7 * stick.getRawAxis(5));
		SmartDashboard.putNumber("Climb power", 0.7 * stick.getRawAxis(5));
/*		if (stick.getRawAxis(5) > 0.3) {
			climbTalon.set(preferenceStuff);
			SmartDashboard.putNumber("Climb power", preferenceStuff);
		}
		else {
			climbTalon.set(0);
			SmartDashboard.putNumber("Climb power", 0);
		}*/
	}

	private void flyWheelRev(){
		//flywheelTalon.set(stick.getRawAxis(3));
		if (stick.getRawAxis(3) == 1){
			flywheelTalon.set(flywheelRamp.get());			
		}
		else {
			flywheelTalon.set(0);
			flywheelRamp.reset();
		}
	}

	
	public void teleopPeriodic() {
		//myRobot.arcadeDrive(deadband(stick.getRawAxis(1), 5), deadband(-stick.getRawAxis(4), 5));
		climbButton();
		flyWheelRev();
	}

}
