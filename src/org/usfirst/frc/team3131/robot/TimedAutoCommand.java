package org.usfirst.frc.team3131.robot;

import java.util.Date;

public abstract class TimedAutoCommand implements AutoCommand {
	TimedAutoCommand(int totalMilliseconds) {
		this.totalMilliseconds = totalMilliseconds;
	}
	
	boolean isFinished;
	int totalMilliseconds;
	Date startTime;
	boolean isInitialized;
	abstract void init();
	abstract void periodicStuff();

	public void periodic() {
		if (!isInitialized){
			startTime = new Date();
			init();
			isInitialized = true;
		}
		periodicStuff();
	}
	
	public boolean isFinished() {
		if (!isInitialized){
			return false;
		}
		if (isFinished) {
			return true;
		}
		Date currentTime = new Date();
		isFinished = (currentTime.getTime() >= startTime.getTime() + totalMilliseconds);
		return isFinished;
	}
	
}
