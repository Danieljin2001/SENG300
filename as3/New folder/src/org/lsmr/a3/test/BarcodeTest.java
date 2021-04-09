package org.lsmr.a3.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.IllegalDigitException;
import org.lsmr.selfcheckout.Numeral;
import org.lsmr.selfcheckout.devices.SimulationException;


public class BarcodeTest {
	
	@Test
	public void testBarcodeConstructor() {
		//test when barcode is null
		try {
			Barcode barcode0 = new Barcode(null);
		}
		catch (Exception e) {
			assertFalse("Code is null", e instanceof NullPointerException);
		}
		//test when barcode is length 0
		try {
			Barcode barcode1 = new Barcode("");
		}
		catch (Exception e) {
			assertFalse("Code length cannot be less than 1", e instanceof SimulationException);
		}
		//test when barcode is length 49
		try {
			Barcode barcode2 = new Barcode("1111111122222222333333334444444455555555666666667");
		}
		catch(Exception e) {
			assertFalse("Code length cannot be greater than 48", e instanceof SimulationException);
		}
		//test when barcode is length 1
		try {
			Barcode barcode3 = new Barcode("1");
		}
		catch(Exception e) {
			assertTrue("Code length can be 1", e instanceof SimulationException);
		}
		
		//test when the barcode is greater than length of 5
		try {
			Barcode barcode4 = new Barcode("1234567891");
		}
		catch (Exception e) {
			assertFalse("Code can be greater than 5 in length and less than 49 in length", e instanceof IllegalArgumentException);
		}
		//test when the barcode consists of letters
		try {
			Barcode barcode5 = new Barcode("abcde");
		}
		catch (Exception e) {
			assertFalse("Code must be a string of numbers", e instanceof IllegalDigitException);
		}
		//test when the barcode consists of symbols
		try {
			Barcode barcode6 = new Barcode("!@#$");
		}
		catch (Exception e) {
			assertFalse("Code must be a string of numbers", e instanceof IllegalDigitException);
		}
		//test when the barcode consists of letters and numbers
		try {
			Barcode barcode7 = new Barcode("2b3d1");
		}
		catch (Exception e) {
			assertFalse("Code must be a string of numbers", e instanceof IllegalDigitException);
		}
		
	}
	
	@Test
	public void testDigitCount(){
		Barcode barcode = new Barcode("00000");
		int testResult = barcode.digitCount();
		int expectedResult = 5;
		assertEquals(expectedResult, testResult,0.0);
	}
	
	@Test
	public void testGetDigitAt() {
		Barcode barcode = new Barcode("12345");
		Numeral testResult = barcode.getDigitAt(0);
		Numeral expectedResult = Numeral.one;		
		assertEquals(expectedResult, testResult);
		//test when the index is out of bounds
		try {
			Numeral testResult1 = barcode.getDigitAt(5);
		}
		catch (Exception e) {
			assertFalse("Index is out of bounds", e instanceof IndexOutOfBoundsException);
		}
	}
	
	@Test
	public void testToString() {
		Barcode barcode = new Barcode("12345");
		String testResult = barcode.toString();
		String expectedResult = "12345";
		assertEquals(expectedResult, testResult);
	}
	
	@Test
	public void testEquals() {
		Barcode barcode = new Barcode("12345");
		Barcode barcode1 = new Barcode("1234");
		Barcode barcode2 = new Barcode("78901");
		//test when the barcode is equal
		assertTrue(barcode.equals(barcode));
		//test when the barcode is not the same length
		assertFalse(barcode.equals(barcode1));
		//test when the barcode is same length but different numbers
		assertFalse(barcode.equals(barcode2));
		//test when the barcode is not a barcode
		assertFalse(barcode.equals("not a barcode"));
	}
	
	@Test
	public void testHashCode() {
		Barcode barcode = new Barcode("12345");
		Barcode barcode1 = new Barcode("12345");
		Barcode barcode2 = new Barcode("12222");
		Barcode barcode3 = new Barcode("1");
		
		int expectedResult = barcode.hashCode();
		int testResult = barcode1.hashCode();
		int testResult1  = barcode2.hashCode();
		int testResult2  = barcode3.hashCode();
		
		//testing with same barcodes
		assertEquals(expectedResult, testResult);
		//testing with different barcodes but same length
		assertNotEquals(expectedResult, testResult1);
		//testing with different barcode and different length
		assertNotEquals(expectedResult, testResult2);
	}
	
	
}

