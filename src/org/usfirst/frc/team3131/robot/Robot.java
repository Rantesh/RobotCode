package org.usfirst.frc.team3131.robot;

import edu.wpi.first.wpilibj.DigitalInput;
//import com.autodesk.bxd.EmulatorControl;
//import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.TalonSRX;
//import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import com.autodesk.bxd.*;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */



public class Robot extends IterativeRobot {
	private RobotDrive myRobot;
	private Teleop teleop;
	private Encoder encRight;
	private TalonSRX flywheelTalon;
	private AutoCommand[] commands;
	//private Command autonomousCommand;
	private SendableChooser<Integer> autoChooser;
	private Preferences prefs;
	private SendableChooser<Integer> encoderChooser;
/*	private DigitalInput enc11 = new DigitalInput(0);
	private DigitalInput enc12 = new DigitalInput(1);
	private DigitalInput enc21 = new DigitalInput(3);
	private DigitalInput enc22 = new DigitalInput(4);
*/	
	
	private double forwardTimeMS;
	private double encoderDistanceInches;
	
/*	public static void main(String[] args){
		EmulatorControl.start(3131,Robot.class);
	}*/
	
	private AutoCommand[] getCommandsForAutoForward() {
		return new AutoCommand[] {
				new Forward(myRobot,(int)forwardTimeMS),
		};
	}
	
	private AutoCommand[] getCommandsForAutoEncoder() {
		return new AutoCommand[] {
				new ForwardDistance(myRobot, encRight, encoderDistanceInches)
		};
	}
	
	private AutoCommand[] getCommandsForAutoStop() {
		return new AutoCommand[] {};
	}
		
	private double getDistancePerPulse() {
		double gear1 = 14;
		double gear2 = 50;
		double gear3 = 16;
		double gear4 = 48;
		double gearRatio = (gear1/gear2)*(gear3/gear4);
		int pulsePerMotorRev = 20;
		double radiusInInches = 3;
		double circumference = 2*Math.PI*radiusInInches;
		return gearRatio * circumference / pulsePerMotorRev;
	}
	
	private void encoderData() {
    	SmartDashboard.putNumber("Right Encoder Distance", encRight.getDistance());
    	SmartDashboard.putBoolean("Right Encoder Direction", encRight.getDirection());
	}

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		myRobot = new RobotDrive(2,1);
		flywheelTalon = new TalonSRX(6);
		teleop = new Teleop(myRobot, flywheelTalon);
		autoChooser = new SendableChooser<Integer>();
		autoChooser.addDefault("Auto Forward", 0);
		autoChooser.addObject("Auto Encoder", 1);
		autoChooser.addObject("Auto Stop", 2);
		SmartDashboard.putData("Autonomous Chooser", autoChooser);
		encoderChooser = new SendableChooser<Integer>();
		encoderChooser.addDefault("Competition", 0);
		encoderChooser.addObject("Test", 1);
		SmartDashboard.putData("Encoder Chooser", encoderChooser);
		prefs = Preferences.getInstance();
		encRight = new Encoder(0, 1, true, Encoder.EncodingType.k4X);  // ports 2 and 3 weren't working
		encRight.setDistancePerPulse(getDistancePerPulse());

		if (encoderChooser.getSelected() == 0) {
			// Use Encoder Objects
		}
		else if (encoderChooser.getSelected() == 1) {
			// Use DIO Objects for testing purposes
		}
	}

	public void autonomousInit() { 
		forwardTimeMS = prefs.getDouble("Forward Time in Milliseconds", 4000);
		encoderDistanceInches = prefs.getDouble("Encoder Distance in Inches", 60);
		
 		encRight.reset();
		commands = getAutoCommands();
	}

	private AutoCommand[] getAutoCommands() {
 		switch (autoChooser.getSelected()) {
 		case 0:
			return getCommandsForAutoForward();
 		case 1:
			return getCommandsForAutoEncoder();
 		case 2:
 		default:
			return getCommandsForAutoStop();
 		}
	}
	
	public void autonomousPeriodic(){
		encoderData();
		for(int i=0; i<commands.length; ++i) {
			if (!commands[i].isFinished()) {
				commands[i].periodic();
				return;
			}
		}
		myRobot.drive(0,0);
	}

	public void teleopInit(){
 		encRight.reset();
    } 
	
	public void teleopPeriodic() {
		teleop.teleopPeriodic();
/*		SmartDashboard.putBoolean("Encoder 1 Signal 1",enc11.get());
		SmartDashboard.putBoolean("Encoder 1 Signal 2",enc12.get());
		SmartDashboard.putBoolean("Encoder 2 Signal 1",enc21.get());
		SmartDashboard.putBoolean("Encoder 2 Signal 2",enc22.get());
*/		encoderData();
	}
    
    public void testPeriodic() {
    	
    }
}