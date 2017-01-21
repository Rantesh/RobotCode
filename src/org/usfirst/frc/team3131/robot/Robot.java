package org.usfirst.frc.team3131.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Ultrasonic;
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
	private AutonomousDrive auto;
//	private Encoder enc;
	private AnalogInput ultraSonic;
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		myRobot = new RobotDrive(0,1);
		teleop = new Teleop(myRobot);
		ultraSonic = new AnalogInput(0);
		auto = new AutonomousDrive(myRobot,ultraSonic);
//		enc = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
	}
    
    /**
     * This function is run once each time the robot enters autonomous mode
     */
    public void autonomousInit() { 
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
