package com.mobiquityinc.packer;

/**
 * Class to manage Pack information
 * @author Andreta
 *
 */
public class Pack {

	private int intexNumber;
	private float weight;
	private float cost;
	
	public Pack() {
		
	}

	public int getIntexNumber() {
		return intexNumber;
	}

	public void setIntexNumber(int intexNumber) {
		this.intexNumber = intexNumber;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}
}
