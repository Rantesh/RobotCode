package org.usfirst.frc.team3131.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.RobotDrive;


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
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		myRobot = new RobotDrive(0,1);
		teleop = new Teleop(myRobot);
		auto = new AutonomousDrive(myRobot);
	}
    
    /**
     * This function is run once each time the robot enters autonomous mode
     */
    public void autonomousInit() { //Problems here when power is not cycled between matches.
    	auto.autonomousInit();
    }
    
    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	auto.autonomousPeriodic();
	}
	/**
     * This function is called once each time the robot enters tele-operated mode
     */
    public void teleopInit(){

    } 
    
    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	teleop.teleopPeriodic();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	
    }
}
