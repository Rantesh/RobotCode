package org.usfirst.frc.team3131.robot;

import java.util.Date;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Teleop {
	public Teleop(RobotDrive myRobot, TalonSRX flywheelTalon){
		this.myRobot = myRobot;
		this.flywheelTalon = flywheelTalon;
		shooterChooser.addDefault("Two Button", 0);
		shooterChooser.addObject("One Button", 1);
		SmartDashboard.putData("Shooter Chooser", shooterChooser);
	}
	
	private Servo servant = new Servo(5);
	private Date shooterStart;
	private Ramp flywheelRamp = new Ramp(-1, 0, 0.04);
	private RobotDrive myRobot;
	private Joystick stick = new Joystick(1);
	private TalonSRX flywheelTalon;
	private TalonSRX climbTalon = new TalonSRX(3);
	private TalonSRX climbTalon2 = new TalonSRX(4);
	private SendableChooser shooterChooser = new SendableChooser();
	private Servo alligator = new Servo(7);

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

	private void secondShooter() {
		if (stick.getRawButton(6)) {
			flywheelTalon.set(1);
			servant.setAngle(0);
		}
		else {
			flywheelTalon.set(0);
			servant.setAngle(90);
		}
	}

	private void thirdShooter() {
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

	private void climbButton() {
		double climbPower = -deadband(stick.getRawAxis(3),2);
		climbTalon.set(climbPower);
		climbTalon2.set(climbPower);
	}

	private void ballAgitator() {
		if(stick.getRawButton(5)) {
			alligator.set(0);
		}
		else {
			alligator.set(1);
		}
	}
	
	public void teleopPeriodic() {
		myRobot.arcadeDrive(-deadband(stick.getRawAxis(1), 5), -deadband(stick.getRawAxis(4), 5));
		climbButton();
		secondShooter();
		thirdShooter();
		ballAgitator();
//		blue.set(stick.getRawButton(2));
//		red.set(stick.getRawButton(3));
		SmartDashboard.putBoolean("Red", stick.getRawButton(3));
		SmartDashboard.putBoolean("Blue", stick.getRawButton(2));
	}

}