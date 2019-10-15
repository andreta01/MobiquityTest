package com.mobiquityinc.packer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.Test;

import com.mobiquityinc.exception.APIException;

/**
 * Class to test the implementation
 * 
 * @author Andreta
 *
 */
public class PackerTest {

	private static final String path = "src/test/resources/PackerTest.txt";


	@Test
	public void shouldSendBestPack() throws APIException {
		String response = Packer.pack(path);
		assertNotNull(response);
		String[] pack = response.split("\n");
		assertEquals("4",pack[0]);
		assertEquals("-",pack[1]);
		assertEquals("2,7",pack[2]);
		assertEquals("8,9",pack[3]);
	}
	
	@Test(expected=APIException.class)
	public void shouldThrowsAPIException() throws APIException, IOException {
		when(PackageTransformer.getPackageTransformer(anyString())).thenThrow(new IOException());
		Packer.pack(path);
		fail("Should throw an APIException");
	}
	
	@Test(expected=APIException.class)
	public void shouldThrowsException() throws APIException, IOException {
		when(PackageTransformer.getPackageTransformer(anyString())).thenThrow(new APIException("Test"));
		Packer.pack(path);
		fail("Should throw an APIException");
	}
}
