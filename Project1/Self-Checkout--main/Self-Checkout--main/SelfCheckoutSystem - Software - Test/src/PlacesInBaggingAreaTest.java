import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.Item;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;

public class PlacesInBaggingAreaTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPlaceItem() {
		//create test station
		Currency testCurrency = Currency.getInstance("CAD");
		int[] testBanknoteDenomnations = {1,2,5};
		BigDecimal[] testCoinDenomnations = {BigDecimal.TEN};
		SelfCheckoutStation testStation = new SelfCheckoutStation(testCurrency, testBanknoteDenomnations, testCoinDenomnations, 500, 2 );
		
		//create test console
		PlacesInBaggingArea testConsole = new PlacesInBaggingArea(testStation);
		
		//create test item
		Barcode testBarcode = new Barcode("0001");
		Item testItem = new BarcodedItem(testBarcode, 5);	
		
		//place item through console
		testConsole.placeItem(testItem);
		
		//expected result
		ArrayList<Item> expectedList = new ArrayList<>();
		expectedList.add(testItem);
		
		//get actual result
		ArrayList<Item> actualList = testConsole.items;		
		
		//check equals
		assertEquals(actualList, expectedList);
}
	
	@Test
	public void testPlaceMultipleItems() {
		//create test station
		Currency testCurrency = Currency.getInstance("CAD");
		int[] testBanknoteDenomnations = {1,2,5};
		BigDecimal[] testCoinDenomnations = {BigDecimal.TEN};
		SelfCheckoutStation testStation = new SelfCheckoutStation(testCurrency, testBanknoteDenomnations, testCoinDenomnations, 500, 2 );
		
		//create test console
		PlacesInBaggingArea testConsole = new PlacesInBaggingArea(testStation);
		
		//create test items
		Barcode testBarcode = new Barcode("0001");
		Item testItem = new BarcodedItem(testBarcode, 5);
		Barcode testBarcode2 = new Barcode("0002");
		Item testItem2 = new BarcodedItem(testBarcode2, 3);	
		
		//place item through console
		testConsole.placeItem(testItem);
		testConsole.placeItem(testItem2);
		
		//expected result
		ArrayList<Item> expectedList = new ArrayList<>();
		expectedList.add(testItem);
		expectedList.add(testItem2);
		
		//get actual result
		ArrayList<Item> actualList = testConsole.items;		
		
		//check equals
		assertEquals(actualList, expectedList);
}	
	
	@Test
	public void testPlaceOverloadItem() {
		//create test station
		Currency testCurrency = Currency.getInstance("CAD");
		int[] testBanknoteDenomnations = {1,2,5};
		BigDecimal[] testCoinDenomnations = {BigDecimal.TEN};
		SelfCheckoutStation testStation = new SelfCheckoutStation(testCurrency, testBanknoteDenomnations, testCoinDenomnations, 500, 2 );
		
		//create test console
		PlacesInBaggingArea testConsole = new PlacesInBaggingArea(testStation);
		
		//create test item
		Barcode testBarcode = new Barcode("0001");
		Item testItem = new BarcodedItem(testBarcode, 600);	
		
		//place item through console
		testConsole.placeItem(testItem);
		
		//expected result
		ArrayList<Item> expectedList = new ArrayList<>();
		
		//get actual result
		ArrayList<Item> actualList = testConsole.items;		
		
		//check equals
		assertEquals(actualList, expectedList);
}
	
	@Test
	public void testPlaceUnderweightItem() {
		//create test station
		Currency testCurrency = Currency.getInstance("CAD");
		int[] testBanknoteDenomnations = {1,2,5};
		BigDecimal[] testCoinDenomnations = {BigDecimal.TEN};
		SelfCheckoutStation testStation = new SelfCheckoutStation(testCurrency, testBanknoteDenomnations, testCoinDenomnations, 500, 2 );
		
		//create test console
		PlacesInBaggingArea testConsole = new PlacesInBaggingArea(testStation);
		
		//create test item
		Barcode testBarcode = new Barcode("0001");
		Item testItem = new BarcodedItem(testBarcode, 1);	
		
		//place item through console
		testConsole.placeItem(testItem);
		
		//expected result
		ArrayList<Item> expectedList = new ArrayList<>();
		
		//get actual result
		ArrayList<Item> actualList = testConsole.items;		
		
		//check equals
		assertEquals(actualList, expectedList);
}
}
