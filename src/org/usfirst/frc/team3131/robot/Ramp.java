package org.usfirst.frc.team3131.robot;

public class Ramp {
	Ramp(double target, double rate) {
		this.target=target;
		this.rate=rate;
	}
	private double counter=0;
	private double target;
	private double rate;
	public void reset() {
		counter=0;
	}
	public void set(double target,double rate) {
		this.target=target;
		this.rate=rate;
	}
	double get() {
		if (counter<target) {
		counter = counter + rate; 
		}
		else if (counter>target){
			counter = counter - rate;
		}
		return counter;
	}
}