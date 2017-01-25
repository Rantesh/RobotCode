package org.usfirst.frc.team3131.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Teleop {
	public Teleop(RobotDrive myRobot){
		this.myRobot = myRobot;
	}
	
	private Ramp flywheelRamp = new Ramp(1,0.02);
	private RobotDrive myRobot;
	private Joystick stick = new Joystick(0);
	private TalonSRX flywheelTalon = new TalonSRX (2);
	private TalonSRX climbTalon = new TalonSRX(3);
	
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

	private void climbButton(){
		climbTalon.set(deadband(stick.getRawAxis(5), .05));
		SmartDashboard.putNumber("Climb power", deadband(stick.getRawAxis(5),0.05));
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
        myRobot.arcadeDrive(deadband(stick.getRawAxis(1), .1),deadband(stick.getRawAxis(4), .1));
        climbButton();
        flyWheelRev();
        
    }

}
