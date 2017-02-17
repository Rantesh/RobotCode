package org.usfirst.frc.team3131.robot;

import edu.wpi.first.wpilibj.AnalogInput;
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
//	private Encoder enc;
	private AnalogInput ultraSonic;
	private TalonSRX flywheelTalon;
	private AutoCommand[] commands;
	Command autonomousCommand;
	SendableChooser autoChooser;
	Preferences prefs;
	
	double driveMultiplier;
	double armDownPosition;
	
	private AutoCommand[] getCommandsForAutonomous1() {
		AutoCommand[] commands = new AutoCommand[4];
		commands[0] = new Forward(myRobot, 1600);
		commands[1] = new Stop(myRobot, 2000);
		commands[2] = new BackCurve(myRobot, 2500);
		commands[3] = new BackStraight(myRobot, 1500);
		return commands;
	}
	
	private AutoCommand[] getCommandsForAutonomous2() {
		AutoCommand[] commands = new AutoCommand[5];
		commands[0] = new Forward(myRobot, 1500);
		commands[1] = new ForwardCurve3(myRobot, 1500);
		commands[2] = new Stop(myRobot, 2000);
		commands[3] = new BackCurve3(myRobot, 2000);
		commands[4] = new BackStraight(myRobot, 1500);
		return commands;
	}
	
	
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		myRobot = new RobotDrive(0,1);
		flywheelTalon = new TalonSRX(2);
		teleop = new Teleop(myRobot, flywheelTalon);
		ultraSonic = new AnalogInput(0);
		autoChooser = new SendableChooser();
		autoChooser.addDefault("Auto Forward", 1);
		autoChooser.addObject("Auto Right", 2);
		SmartDashboard.putData("Autonomous Chooser", autoChooser);
		prefs = Preferences.getInstance();
//		enc = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
	}
    
    /**
     * This function is run once each time the robot enters autonomous mode
     */
    public void autonomousInit() { 
    	
    	if ((int)autoChooser.getSelected() == 1) {
    		commands = getCommandsForAutonomous1();
    	}
    	else if ((int)autoChooser.getSelected() == 2){
    		commands = getCommandsForAutonomous2();
    	}
    }
    
    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic(){
    	for(int i=0; i<commands.length; ++i) {
    		if (!commands[i].isFinished()) {
    			commands[i].periodic();
    			return;
    		}
    	}
    	myRobot.drive(0,0);
    
    	SmartDashboard.putNumber("Ultrasonic",ultraSonic.getVoltage());
	}
	/**
     * This function is called once each time the robot enters tele-operated mode
     */
	public void teleopInit(){
/*    	enc.setMaxPeriod(.1);
    	enc.setMinRate(10);
    	enc.setDistancePerPulse(5);
    	enc.setReverseDirection(true);
    	enc.setSamplesToAverage(7);
    	enc.reset();*/	
    } 
	
	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		teleop.teleopPeriodic();
		driveMultiplier = prefs.getDouble("Drive Multiplier", 1.0); //Validate this number!
		armDownPosition = prefs.getDouble("abc", 4);
		SmartDashboard.putNumber("ArmUpPosition", driveMultiplier);
		SmartDashboard.putNumber("abc", armDownPosition);
		/* int count = enc.get();
		double raw = enc.getRaw();
		double distance = enc.getDistance();
		double rate = enc.getRate();
		boolean direction = enc.getDirection();
		boolean stopped = enc.getStopped();*/
    	SmartDashboard.putNumber("Ultrasonic",ultraSonic.getVoltage());
	}
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	
    }
}
