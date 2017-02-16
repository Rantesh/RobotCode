package org.usfirst.frc.team3131.robot;

public class Ramp {
	Ramp(double target, double counter) {
		this.target=target;
		this.counter=counter;
	}
	private double counter=0;
	private double target;
	private double rate = 0.04;
	public void reset() {
		counter=0;
	}
	public void set(double target) {
		this.target=target;
	}
	public void set(double target, double counter) {
		this.target=target;
		this.counter=counter;
	}
	double get() {
		if (counter<target) {
			counter = counter + rate; 
			if (counter>target){
				counter = target;
			}
		}
		else if (counter>target){
			counter = counter - rate;
			if (counter<target){
				counter = target;
			}
		}
		return counter;
	}
}