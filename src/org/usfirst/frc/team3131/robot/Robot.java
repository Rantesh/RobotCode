package org.usfirst.frc.team3131.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


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
	Command autonomousCommand;
	SendableChooser autoChooser;
	Preferences prefs;
	
	double forward1;
	double forward2;
	double stop1;
	double stop2;
	double backStraight2;
	double backStraight1;
	double backCurve1;
	double backCurve2;
	double forwardCurve2;
	double encDist;
	
	private AutoCommand[] getCommandsForAutonomous0() {
		AutoCommand[] commands = new AutoCommand[4];
		commands[0] = new Forward(myRobot,(int)forward1);
		commands[1] = new Stop(myRobot, (int)stop1);
		commands[2] = new BackCurve(myRobot, (int)backCurve1);
		commands[3] = new BackStraight(myRobot, (int)backStraight1);
		return commands;
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
		AutoCommand[] commands = new AutoCommand[1];
		commands[0] = new ForwardDistance(myRobot, encLeft, encRight, encDist);
		return commands;
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
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		myRobot = new RobotDrive(0,1);
		flywheelTalon = new TalonSRX(5);
		teleop = new Teleop(myRobot, flywheelTalon);
		autoChooser = new SendableChooser();
		autoChooser.addDefault("Auto Forward", 0);
		autoChooser.addObject("Auto Right", 1);
		autoChooser.addObject("Auto Encoder", 2);
		SmartDashboard.putData("Autonomous Chooser", autoChooser);
		prefs = Preferences.getInstance();
		encLeft = new Encoder(4, 5, false, Encoder.EncodingType.k2X);
		encLeft.setDistancePerPulse(getDistancePerPulse());
		encRight = new Encoder(0, 1, true, Encoder.EncodingType.k2X);  // ports 2 and 3 weren't working
		encRight.setDistancePerPulse(getDistancePerPulse());
	}
	
	/**
	 * This function is run once each time the robot enters autonomous mode
	 */
	public void autonomousInit() { 
		forward2 = prefs.getDouble("Forward 2", 1500);
		forward1 = prefs.getDouble("Forward 1", 1600);
		stop1 = prefs.getDouble("Stop 1", 2000);
		stop2 = prefs.getDouble("Stop 2", 2000);
		backCurve1 = prefs.getDouble("Back Curve 1", 2500);
		backCurve2 = prefs.getDouble("Back Curve 2", 2000);
		backStraight1 = prefs.getDouble("Back Straight 1", 1500);
		backStraight2 = prefs.getDouble("Back Straight 2", 1500);
		forwardCurve2 = prefs.getDouble("Forward Curve 2", 1500);
		encDist = prefs.getDouble("Encoder Distance", 60);
		
		if ((int)autoChooser.getSelected() == 0) {
			commands = getCommandsForAutonomous0();
		}
		else if ((int)autoChooser.getSelected() == 1) {
			commands = getCommandsForAutonomous1();
		}
		else if ((int)autoChooser.getSelected() == 2) {
			commands = getCommandsForAutonomous2();
		}
	}
	
	/**
	 * This function is called periodically during autonomous
	 */
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
	/**
     * This function is called once each time the robot enters tele-operated mode
     */
	public void teleopInit(){
		encLeft.reset();
 		encRight.reset();
    } 
	
	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		teleop.teleopPeriodic();
		encoderData();
	}
    
	private void encoderData() {
    	SmartDashboard.putNumber("Left Encoder Distance", encLeft.getDistance());
    	SmartDashboard.putNumber("Right Encoder Distance", encRight.getDistance());
    	SmartDashboard.putBoolean("Left Encoder Direction", encLeft.getDirection());
    	SmartDashboard.putBoolean("Right Encoder Direction", encRight.getDirection());
	}
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	
    }
}
