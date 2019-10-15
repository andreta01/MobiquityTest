package com.mobiquityinc.packer;

import java.util.ArrayList;

/**
 * Class to manage the Package information
 * @author Andreta
 *
 */
public class PackageInventory {

	private float packageWeigh;
	private ArrayList<Pack> pack;

	public PackageInventory() {

	}

	public float getPackageWeigh() {
		return packageWeigh;
	}

	public void setPackageWeigh(float packageWeigh) {
		this.packageWeigh = packageWeigh;
	}

	public ArrayList<Pack> getPack() {
		return pack;
	}

	public void setPack(ArrayList<Pack> pack) {
		this.pack = pack;
	}
}
