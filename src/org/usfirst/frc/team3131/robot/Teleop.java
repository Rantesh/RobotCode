package org.usfirst.frc.team3131.robot;

import java.util.Date;
import java.util.Set;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Solenoid;

public class Teleop {
	public Teleop(RobotDrive myRobot, TalonSRX flywheelTalon){
		this.myRobot = myRobot;
	}
		
	
	private RobotDrive myRobot;
	private Joystick stick = new Joystick(0);
	private double highSpeed = 1;
	private double lowSpeed = 0.7;
	private boolean useHighSpeed = true;
	private DigitalInput topLimitSwitch= new DigitalInput(5);
	private DigitalInput bottomLimitSwitch = new DigitalInput(6);
	private TalonSRX armMotor = new TalonSRX(3);

	
	private static double deadband (double joystick, int power) {
		if (joystick < 0 && power % 2 == 0) {
			return -Math.pow(joystick, power);
		}
		else {
			return Math.pow(joystick, power);
		}
	}

	private void speedDrive() {
		if (useHighSpeed) {
			myRobot.arcadeDrive(-highSpeed * deadband(stick.getRawAxis(1), 2), -highSpeed * deadband(stick.getRawAxis(4), 2));
			if (stick.getRawButton(4)) {
				useHighSpeed = false;
			}
		}
		else {
			myRobot.arcadeDrive(-lowSpeed * deadband(stick.getRawAxis(1), 2), -lowSpeed * deadband(stick.getRawAxis(4), 2));
			if (stick.getRawButton(3)) {
				useHighSpeed = true;
			}
		}
	}
	
	private boolean isArmTriggerPressed() {
		return stick.getRawButton(3);	
	}
	
	private void moveArmManipulatorUp() {
		armMotor.set(1); 
	}
	
	private void moveArmManipulatorDown() {
		armMotor.set(-1);	}
	
	private boolean isTopSwitchPressed() {
		return topLimitSwitch.get();
	}
	
	private boolean isBottomSwitchPressed() {
		return bottomLimitSwitch.get();
	}
	
	private void stopArmManipulator() {
		armMotor.set(0);
	}
	
	private void armControl() {
		if (isTopSwitchPressed()) {
			if (isArmTriggerPressed()) {
				moveArmManipulatorDown();
			}
			else {
				stopArmManipulator();
			}
	}
		else if (isBottomSwitchPressed()) {
			if (isArmTriggerPressed()) {
				moveArmManipulatorUp();
			} 
			else {
				stopArmManipulator(); 
			}
		}
		else {
			stopArmManipulator();
		}
	} 
	
	public void teleopPeriodic() {
		armControl();
		speedDrive();
//		blue.set(stick.getRawButton(2));
//		red.set(stick.getRawButton(3));
		SmartDashboard.putBoolean("Red", stick.getRawButton(3));
		SmartDashboard.putBoolean("Blue", stick.getRawButton(2));
	}

}