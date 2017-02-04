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
	private AutonomousDriver auto;
//	private Encoder enc;
	private AnalogInput ultraSonic;
	private TalonSRX flywheelTalon;
	Command autonomousCommand;
	SendableChooser autoChooser;
	Preferences prefs;
	
	double driveMultiplier;
	double armDownPosition;
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		myRobot = new RobotDrive(0,1);
		flywheelTalon = new TalonSRX(3);
		teleop = new Teleop(myRobot, flywheelTalon);
		ultraSonic = new AnalogInput(0);
		autoChooser = new SendableChooser();
		autoChooser.addDefault("Defalult Program", 1);
		autoChooser.addObject("Experimental Auto", 2);
		SmartDashboard.putData("Autonomous Chooser", autoChooser);
		prefs = Preferences.getInstance();
		driveMultiplier = prefs.getDouble("Drive Multiplier", 1.0); //Validate this number!
		armDownPosition = prefs.getDouble("abc", 4);
//		enc = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
	}
    
    /**
     * This function is run once each time the robot enters autonomous mode
     */
    public void autonomousInit() { 
    	
    	if ((int)autoChooser.getSelected() == 1) {
    		auto = new AutonomousDriver1(myRobot, ultraSonic, flywheelTalon);
    	}
    	else if ((int)autoChooser.getSelected() == 2) {
    		auto = new AutonomousDriver2(myRobot, ultraSonic);
    	}
    	auto.autonomousInit();
    }
    
    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	auto.autonomousPeriodic();
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
