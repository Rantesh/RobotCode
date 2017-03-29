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
	private Encoder encLeft;
	private Encoder encRight;
	private TalonSRX flywheelTalon;
	private AutoCommand[] commands;
	//private Command autonomousCommand;
	private SendableChooser autoChooser;
	private Preferences prefs;
	private SendableChooser encoderChooser;
/*	private DigitalInput enc11 = new DigitalInput(0);
	private DigitalInput enc12 = new DigitalInput(1);
	private DigitalInput enc21 = new DigitalInput(3);
	private DigitalInput enc22 = new DigitalInput(4);
*/	
	
	private double forward1;
	private double forward2;
	private double stop1;
	private double stop2;
	private double backStraight2;
	private double backStraight1;
	private double backCurve1;
	private double backCurve2;
	private double forwardCurve2;
	private double encDist;
	
/*	public static void main(String[] args){
		EmulatorControl.start(3131,Robot.class);
	}*/
	
	private AutoCommand[] getCommandsForAutonomous0() {
		return new AutoCommand[] {
				new Forward(myRobot,(int)forward1),
				new Stop(myRobot, (int)stop1),
				new BackCurve(myRobot, (int)backCurve1),
				new BackStraight(myRobot, (int)backStraight1)
		};
	}
	
	private AutoCommand[] getCommandsForAutonomous1() {
		return new AutoCommand[] {
				new Forward(myRobot, (int)forward2),
				new ForwardCurve3(myRobot, (int)forwardCurve2),
				new Stop(myRobot, (int)stop2),
				new BackCurve3(myRobot, (int)backCurve2),
				new BackStraight(myRobot, (int)backStraight2)
		};
	}
	
	private AutoCommand[] getCommandsForAutonomous2() {
		return new AutoCommand[] {
				new ForwardDistance(myRobot, encLeft, encRight, encDist),
				new Stop(myRobot, (int)stop1),
				new BackCurve(myRobot, (int)backCurve1),
				new BackStraight(myRobot, (int)backStraight1)
		};
	}
	
	private AutoCommand[] getCommandsForAutonomous3() {
		return new AutoCommand[] {
				new Stop(myRobot, (int)stop1),
		};
	}
	
	private AutoCommand[] getCommandsForAutonomous4() {
		return new AutoCommand[] {
				new Forward(myRobot, 4000),
		};
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
    	SmartDashboard.putNumber("Left Encoder Distance", encLeft.getDistance());
    	SmartDashboard.putNumber("Right Encoder Distance", encRight.getDistance());
    	SmartDashboard.putBoolean("Left Encoder Direction", encLeft.getDirection());
    	SmartDashboard.putBoolean("Right Encoder Direction", encRight.getDirection());
	}

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		myRobot = new RobotDrive(1,2);
		flywheelTalon = new TalonSRX(6);
		teleop = new Teleop(myRobot, flywheelTalon);
		autoChooser = new SendableChooser();
		autoChooser.addDefault("Auto Forward", 0);
		autoChooser.addObject("Auto Right", 1);
		autoChooser.addObject("Auto Encoder", 2);
		autoChooser.addObject("Auto Stop", 3);
		autoChooser.addObject("Auto Straight Forward", 4);
		SmartDashboard.putData("Autonomous Chooser", autoChooser);
		encoderChooser = new SendableChooser();
		encoderChooser.addDefault("Competition", 0);
		encoderChooser.addObject("Test", 1);
		SmartDashboard.putData("Encoder Chooser", encoderChooser);
		prefs = Preferences.getInstance();
		
		if ((int)encoderChooser.getSelected() == 0) {
			encLeft = new Encoder(5, 6, false, Encoder.EncodingType.k4X);
			encLeft.setDistancePerPulse(getDistancePerPulse());
			encRight = new Encoder(0, 1, true, Encoder.EncodingType.k4X);  // ports 2 and 3 weren't working
			encRight.setDistancePerPulse(getDistancePerPulse());
		}
		else if ((int)encoderChooser.getSelected() == 1) {
		}
		
	}

	public void autonomousInit() { 
		forward2 = prefs.getDouble("Forward 2", 1500);
		forward1 = prefs.getDouble("Forward 1", 1600);
		stop1 = prefs.getDouble("Stop 1", 2000);
		stop2 = prefs.getDouble("Stop 2", 2000);
		backCurve1 = prefs.getDouble("Back Curve 1", 3500);
		backCurve2 = prefs.getDouble("Back Curve 2", 2000);
		backStraight1 = prefs.getDouble("Back Straight 1", 1500);
		backStraight2 = prefs.getDouble("Back Straight 2", 1500);
		forwardCurve2 = prefs.getDouble("Forward Curve 2", 1500);
		encDist = prefs.getDouble("Encoder Distance", 60);
		
		encLeft.reset();
 		encRight.reset();
		
		if ((int)autoChooser.getSelected() == 0) {
			commands = getCommandsForAutonomous0();
		}
		else if ((int)autoChooser.getSelected() == 1) {
			commands = getCommandsForAutonomous1();
		}
		else if ((int)autoChooser.getSelected() == 2) {
			commands = getCommandsForAutonomous2();
		}
		else if ((int)autoChooser.getSelected() == 3) {
			commands = getCommandsForAutonomous3();
		}
		else if ((int)autoChooser.getSelected() == 4) {
			commands = getCommandsForAutonomous4();
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
		encLeft.reset();
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