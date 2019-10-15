package com.mobiquityinc.packer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.mobiquityinc.exception.APIException;

/**
 * Class to handle a list of package to be sent
 * 
 * @author Andreta
 *
 */
public class Packer {

	private static ArrayList<Float> bestWeigth = new ArrayList<Float>();
	private static ArrayList<Float> bestCost = new ArrayList<Float>();
	private static ArrayList<Integer> bestId = new ArrayList<Integer>();
	private static String response = "";

	private Packer() {
	}

	/**
	 * Method to the file transformation into a Package
	 * 
	 * @param filePath
	 * @return best combination package
	 * @throws APIException
	 */
	public static String pack(String filePath) throws APIException {
		try {
			ArrayList<PackageInventory> packageInventory = PackageTransformer.getPackageTransformer(filePath);
			sendPackage(packageInventory);
		} catch (IOException e) {
			throw new APIException("Error :" + e.getMessage(), e);
		}
		return response;
	}

	/**
	 * Method that handler the packageInventoryList
	 * 
	 * @param packageInventoryList
	 */
	private static void sendPackage(List<PackageInventory> packageInventoryList) {
		for (PackageInventory packageInventory : packageInventoryList) {
			bestWeigth.clear();
			bestCost.clear();
			bestId.clear();
			getBestPackageCombination(packageInventory, packageInventory.getPackageWeigh());
			toStringResponse();
		}
	}

	/**
	 * Method that handler the Best Package Combination
	 * 
	 * @param packageInventory
	 * @param packageWeight
	 */
	private static void getBestPackageCombination(PackageInventory packageInventory, float packageWeight) {
		sumUp(packageInventory.getPack(), packageWeight);
	}

	/**
	 * Method that handler the Recursive Combination
	 * 
	 * @param listPack
	 * @param target
	 */
	static void sumUp(ArrayList<Pack> packList, float target) {
		sumUpRecursive(packList, target, new ArrayList<Float>(), new ArrayList<Float>(), new ArrayList<Integer>());
	}

	/**
	 * Method that handler the weight and cost for each pack
	 * 
	 * @param packList
	 * @param target
	 * @param parcialW
	 * @param parcialC
	 * @param partialI
	 */
	static void sumUpRecursive(ArrayList<Pack> packList, float target, ArrayList<Float> parcialW,
			ArrayList<Float> parcialC, ArrayList<Integer> partialI) {
		int s = 0;

		for (float x : parcialW)
			s += x;
		if (s >= target)
			return;

		bestOption(parcialW, parcialC, partialI);

		for (int i = 0; i < packList.size(); i++) {
			ArrayList<Pack> remaining = new ArrayList<Pack>();

			float n = packList.get(i).getWeight();
			for (int j = i + 1; j < packList.size(); j++)
				remaining.add(packList.get(j));
			ArrayList<Float> partialRecW = new ArrayList<Float>(parcialW);
			partialRecW.add(n);
			ArrayList<Float> partialRecC = new ArrayList<Float>(parcialC);
			partialRecC.add(packList.get(i).getCost());
			ArrayList<Integer> partialRecI = new ArrayList<Integer>(partialI);
			partialRecI.add(packList.get(i).getIntexNumber());

			sumUpRecursive(remaining, target, partialRecW, partialRecC, partialRecI);
		}

	}

	/**
	 * Method that handler the best option
	 * 
	 * @param parcialW
	 * @param parcialC
	 * @param partialRecI
	 */
	private static void bestOption(ArrayList<Float> parcialW, ArrayList<Float> parcialC,
			ArrayList<Integer> partialRecI) {

		float parcialCombWeight = sum(parcialW);
		float currentCombWeight = sum(bestWeigth);
		float parcialCombCost = sum(parcialC);
		float currentCombCost = sum(bestCost);

		if (parcialCombWeight >= currentCombWeight) {
			if (parcialCombCost >= currentCombCost) {
				bestCost = parcialC;
				bestWeigth = parcialW;
				bestId = partialRecI;
			}
			return;
		} else {
			if (parcialCombCost >= currentCombCost) {
				bestCost = parcialC;
				bestWeigth = parcialW;
				bestId = partialRecI;

			}
		}
	}

	/**
	 * Method that sum values form a list
	 * 
	 * @param values
	 * @return
	 */
	private static float sum(ArrayList<Float> values) {
		float sum = 0;
		for (float i : values) {
			sum += i;
		}
		return sum;
	}

	/**
	 * Method to return the String format request
	 */
	private static void toStringResponse() {
		if (!bestId.isEmpty()) {
			for (int i = 0; i < bestId.size(); i++) {
				response = response + bestId.get(i);
				if (i != bestId.size() - 1) {
					response = response + ",";
				}
			}
			response = response + "\n";
		} else {
			response = response + "-\n";
		}
	}

}
