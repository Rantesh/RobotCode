package org.usfirst.frc.team3131.robot;

public interface AutoCommand {
	void init();
	void periodic();
	boolean finished();
}
