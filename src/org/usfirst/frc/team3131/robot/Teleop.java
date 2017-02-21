package org.usfirst.frc.team3131.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Teleop {
	public Teleop(RobotDrive myRobot, TalonSRX flywheelTalon){
		this.myRobot = myRobot;
		this.flywheelTalon = flywheelTalon;
	}
	
	private Servo servant = new Servo(4);
	private boolean wallButtonPressed;
	private Ramp flywheelRamp = new Ramp(-1, 0);
	private RobotDrive myRobot;
	private Joystick stick = new Joystick(0);
	private TalonSRX flywheelTalon;
	private TalonSRX climbTalon = new TalonSRX(2);
	private TalonSRX climbTalon2 = new TalonSRX(3);
	private Preferences prefs = Preferences.getInstance();
	private double preferenceStuff;
	private Ramp climberRamp = new Ramp(1, 0);
//	private DigitalOutput blue = new DigitalOutput(5);
//	private DigitalOutput red = new DigitalOutput(6);

	private void flyWheelRev(){
		if (stick.getRawButton(6)){
			flywheelTalon.set(flywheelRamp.get());			
		}
		else {
			flywheelTalon.set(0);
			flywheelRamp.reset();
		}
	}

	private void shooterWall() {
		if (stick.getRawButton(5)) {
			servant.setAngle(0);
		}
		else {
			servant.setAngle(90);
		}
	}
	
	private static double deadband (double joystick, int power) {
		if (joystick < 0 && power % 2 == 0) {
			return -Math.pow(joystick, power);
		}
		else {
			return Math.pow(joystick, power);
		}
	}

	private void climbButton(){
		double climbPower = -deadband(stick.getRawAxis(3),3);
		climbTalon.set(climbPower);
		climbTalon2.set(climbPower);
	}

	
	public void teleopPeriodic() {
		myRobot.arcadeDrive(-deadband(stick.getRawAxis(1), 5)*.7, deadband(-stick.getRawAxis(4), 5)*.7);
		climbButton();
		flyWheelRev();
		shooterWall();
//		blue.set(stick.getRawButton(2));
//		red.set(stick.getRawButton(3));
		SmartDashboard.putBoolean("Red", stick.getRawButton(3));
		SmartDashboard.putBoolean("Blue", stick.getRawButton(2));
	}

}