package com.mobiquityinc.packer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Class to test the file transformation
 * 
 * @author Andreta
 *
 */
public class PackageTransformerTest {

	@Test
	public void shouldGetPackageTransformer() throws Exception {
		PackageInventory tempPackage = new PackageInventory();
		String tempPack = "81 : (1,53.38,€45) ";
		tempPackage = PackageTransformer.packageTranformation(tempPack);
		assertNotNull(tempPackage);
		assertEquals(Float.compare(tempPackage.getPackageWeigh(), 81.0f), 0);
		assertNotNull(tempPackage.getPack());
		assertFalse(tempPackage.getPack().isEmpty());
		for (Pack pack : tempPackage.getPack()) {
			assertEquals(Float.compare(pack.getIntexNumber(), 1f), 0);
			assertEquals(Float.compare(pack.getWeight(), 53.38f), 0);
			assertEquals(Float.compare(pack.getCost(), 45f), 0);
		}
	}

	@Test
	public void shouldValidatePackageTransformerPackageWeight() throws Exception {
		PackageInventory tempPackage = new PackageInventory();
		String tempPack = "340 : (1,3.38,€5) ";
		tempPackage = PackageTransformer.packageTranformation(tempPack);
		assertNull(tempPackage);
	}
	
	@Test
	public void shouldValidatePackageTransformerPackage() throws Exception {
		PackageInventory tempPackage = new PackageInventory();
		String tempPack = "23 : (1,987.38,€5) ";
		tempPackage = PackageTransformer.packageTranformation(tempPack);
		assertNotNull(tempPackage);
		assertTrue(tempPackage.getPack().isEmpty());
	}

	@Test
	public void shouldValidatePackageTransformerPackCost() throws Exception {
		PackageInventory tempPackage = new PackageInventory();
		String tempPack = "41 : (1,5.21,€700) (2,18.32,€7)";
		tempPackage = PackageTransformer.packageTranformation(tempPack);
		assertNotNull(tempPackage);
		assertEquals(Float.compare(tempPackage.getPackageWeigh(), 41f), 0);
		assertNotNull(tempPackage.getPack());
		assertFalse(tempPackage.getPack().isEmpty());
		for (Pack pack : tempPackage.getPack()) {
			assertEquals(Float.compare(pack.getIntexNumber(), 2f), 0);
			assertEquals(Float.compare(pack.getWeight(), 18.32f), 0);
			assertEquals(Float.compare(pack.getCost(), 7f), 0);
		}
	}

	@Test
	public void shouldValidatePackageTransformerPackWeight() throws Exception {
		PackageInventory tempPackage = new PackageInventory();
		String tempPack = "41 : (1,67.44,€60) (2,342.66,€20)";
		tempPackage = PackageTransformer.packageTranformation(tempPack);
		assertNotNull(tempPackage);
		assertEquals(Float.compare(tempPackage.getPackageWeigh(), 41f), 0);
		assertNotNull(tempPackage.getPack());
		assertFalse(tempPackage.getPack().isEmpty());
		for (Pack pack : tempPackage.getPack()) {
			assertEquals(Float.compare(pack.getIntexNumber(), 1f), 0);
			assertEquals(Float.compare(pack.getWeight(), 67.44f), 0);
			assertEquals(Float.compare(pack.getCost(), 60f), 0);
		}

	}
}
