package org.usfirst.frc.team3131.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.Victor;

public class Teleop {
	public Teleop(RobotDrive myRobot){
		this.myRobot = myRobot;
	}

	private int counter = 0;
	private RobotDrive myRobot;
	private Joystick stick = new Joystick(0);
	private TalonSRX Tally = new TalonSRX (3);
	private TalonSRX Talon = new TalonSRX(6);
	private Victor motor = new Victor(7);
	private Victor loader = new Victor(8);
	
	private static double deadband (double joystick, double range) {
		if (-range < joystick && range > joystick) {
			return 0;
		}
		else if (joystick > 0) {
			return (joystick-1)/(1-range)+1;
		}
		else {
			return (joystick+1)/(1-range)-1;
		}
	}

	private void gunTrigger() {
		if (counter == 0 && deadband(stick.getRawAxis(3),0.1) > 0){
			counter = 1;
		}
		else if (counter < 6){
			Tally.set(.2);
			counter ++;
		}
		else if (counter < 11){
			Tally.set(-.2);
			counter ++;
		}
		else{
			counter = 0;
		}
	}

	private void climbButton(){
		if (stick.getRawButton(3) == true){
			Talon.set(.3);
		}
		else if (stick.getRawButton(3) == true){
			Talon.set(0);
		}
		
	};

	private void flyWheelRev(){
		if (deadband(stick.getRawAxis(3),0.1) == 1){
			motor.set(1/*motorSpeed*/);
			if (true/*motorSpeed == max*/){			//sensor in place here for revving of motor
				loader.set(1);
			}
		}
		else if (deadband(stick.getRawAxis(3),0.1) == 0){
			motor.set(0);
			loader.set(0);
		}
	};

	
	public void teleopPeriodic() {
        myRobot.arcadeDrive(deadband(stick.getRawAxis(5), .1),deadband(stick.getRawAxis(1), .1));
        gunTrigger();
        climbButton();
        flyWheelRev();
    }

}
