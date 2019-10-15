package com.mobiquityinc.packer;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import com.mobiquityinc.exception.APIException;

/**
 * Class to manage the file transformation
 * 
 * @author andrea.ortiz
 *
 */
public class PackageTransformer {

	private static final int LIMIT_CONSTANT = 100;
	private static final String SEPARATOR_4 = ":";
	private static final String SEPARATOR_2 = ")";
	private static final String SEPARATOR_1 = "(";

	private PackageTransformer() {

	}

	/**
	 * Method to get the information of the file and sort the list
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 * @throws APIException 
	 */
	public static ArrayList<PackageInventory> getPackageTransformer(String path) throws IOException, APIException {
		ArrayList<PackageInventory> packageList = new ArrayList<PackageInventory>();
		FileReader reader = null;
		try {
			Scanner scanner = new Scanner(new File(path));
			while (scanner.hasNextLine()) {
				
				String tempPack = scanner.nextLine();
				PackageInventory tempPackage  = packageTranformation(tempPack);
				if (!tempPackage.getPack().isEmpty()) {
					packageList.add(tempPackage);
				}
			}
			scanner.close();
		} catch (Exception e) {
			throw new APIException("Error :" + e.getMessage(), e);
		} finally {
			if (null != reader) {
				reader.close();
			}
		}
		return packageList;
	}

	public static PackageInventory packageTranformation(String tempPack) {
		PackageInventory tempPackage = null;
		int temIntex = tempPack.indexOf(SEPARATOR_4);
		float packageWeigth = Float.parseFloat(tempPack.substring(0, temIntex).replace(" ", ""));
		// Max weight that a package can take is ≤ 100
		if (packageWeigth <= LIMIT_CONSTANT) {
			tempPackage = new PackageInventory();
			tempPackage.setPackageWeigh(Float.parseFloat(tempPack.substring(0, temIntex).replace(" ", "")));
			ArrayList<Pack> packList = new ArrayList<Pack>();
			StringTokenizer cadena = new StringTokenizer(tempPack.substring(temIntex + 1));
			while (cadena.hasMoreTokens()) {
				Pack pack = new Pack();
				String[] temp = cadena.nextToken().replace(SEPARATOR_1, "").replace(SEPARATOR_2, "").split(",");
				pack.setIntexNumber(Integer.parseInt(temp[0]));
				pack.setWeight(Float.parseFloat(temp[1]));
				pack.setCost(Float.parseFloat(temp[2].replaceAll("[^\\dA-Za-z]", "")));
				// Max weight and cost of an item is ≤ 100
				if (pack.getWeight() <= 100 && pack.getCost() <= LIMIT_CONSTANT) {
					packList.add(pack);
				}
			}
			tempPackage.setPack(packList);
			
		}
		return tempPackage;
	}
}
