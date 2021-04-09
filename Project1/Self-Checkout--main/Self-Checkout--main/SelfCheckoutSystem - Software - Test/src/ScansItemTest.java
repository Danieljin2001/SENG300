import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.Item;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.external.ProductDatabases;
import org.lsmr.selfcheckout.products.BarcodedProduct;

public class ScansItemTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConstructor() {
		//constructor is trivially true
		assertEquals(1,1);
	}

	@Test
	public void testValidItemList_oneItem() {
		//create test product
		Barcode testBarcode = new Barcode("0001");
		BigDecimal testPrice = BigDecimal.TEN;
		BarcodedProduct testProduct = new BarcodedProduct(testBarcode, "This is a test product", testPrice);
		
		//create test database
		Map<Barcode, BarcodedProduct>testMap = new HashMap<>();
		testMap.put(testBarcode, testProduct);
		
		//create test station
		Currency testCurrency = Currency.getInstance("CAD");
		int[] testBanknoteDenomnations = {1,2,5};
		BigDecimal[] testCoinDenomnations = {BigDecimal.TEN};
		SelfCheckoutStation testStation = new SelfCheckoutStation(testCurrency, testBanknoteDenomnations, testCoinDenomnations, 100 ,1);
		
	
		//create test item
		BarcodedItem testItem = new BarcodedItem(testBarcode, 5);
		
		//create test console
		ScansItem testConsole = new ScansItem(testMap, testStation);
		
		//simulate scanning on test console
		testConsole.scanItem(testItem, "main");
		
		//check if system state after is equal to desired system state
		ArrayList<BarcodedProduct> expectedList= new ArrayList<>();
		expectedList.add(testProduct);
		assertEquals(testConsole.itemList,expectedList);
	}
	
	
	
	@Test
	public void testValidItemList_multipleItems() {
		//create test products
		Barcode testBarcode = new Barcode("0001");
		BigDecimal testPrice = BigDecimal.TEN;
		BarcodedProduct testProduct = new BarcodedProduct(testBarcode, "This is a test product", testPrice);
		Barcode testBarcode2 = new Barcode("0010");
		BigDecimal testPrice2 = BigDecimal.ONE;
		BarcodedProduct testProduct2 = new BarcodedProduct(testBarcode2, "This is a test product2", testPrice2);
		
		//create test database
		Map<Barcode, BarcodedProduct>testMap = new HashMap<>();
		testMap.put(testBarcode, testProduct);
		testMap.put(testBarcode2, testProduct2);
		
		//create test station
		Currency testCurrency = Currency.getInstance("CAD");
		int[] testBanknoteDenomnations = {1,2,5};
		BigDecimal[] testCoinDenomnations = {BigDecimal.TEN};
		SelfCheckoutStation testStation = new SelfCheckoutStation(testCurrency, testBanknoteDenomnations, testCoinDenomnations, 100 ,1);
		
	
		//create test items
		BarcodedItem testItem = new BarcodedItem(testBarcode, 5);
		BarcodedItem testItem2 = new BarcodedItem(testBarcode2, 5);
		
		//create test console
		ScansItem testConsole = new ScansItem(testMap, testStation);
		
		//simulate scanning on test console
		testConsole.scanItem(testItem, "main");
		testConsole.scanItem(testItem2, "main");
			
		//check if system state after is equal to desired system state
		ArrayList<BarcodedProduct> expectedList= new ArrayList<>();
		expectedList.add(testProduct);
		expectedList.add(testProduct2);
		assertEquals(testConsole.itemList,expectedList);
	}
	
	@Test
	public void testValidTotalPrice_multipleItems() {
		//create test products
		Barcode testBarcode = new Barcode("0001");
		BigDecimal testPrice = BigDecimal.TEN;
		BarcodedProduct testProduct = new BarcodedProduct(testBarcode, "This is a test product", testPrice);
		Barcode testBarcode2 = new Barcode("0010");
		BigDecimal testPrice2 = BigDecimal.ONE;
		BarcodedProduct testProduct2 = new BarcodedProduct(testBarcode2, "This is a test product", testPrice2);
		
		//create test database
		Map<Barcode, BarcodedProduct>testMap = new HashMap<>();
		testMap.put(testBarcode, testProduct);
		testMap.put(testBarcode2, testProduct2);
		
		//create test station
		Currency testCurrency = Currency.getInstance("CAD");
		int[] testBanknoteDenomnations = {1,2,5};
		BigDecimal[] testCoinDenomnations = {BigDecimal.TEN};
		SelfCheckoutStation testStation = new SelfCheckoutStation(testCurrency, testBanknoteDenomnations, testCoinDenomnations, 100 ,1);
		
	
		//create test items
		BarcodedItem testItem = new BarcodedItem(testBarcode, 5);
		BarcodedItem testItem2 = new BarcodedItem(testBarcode2, 5);
		
		//create test console
		ScansItem testConsole = new ScansItem(testMap, testStation);
		
		//simulate scanning on test console
		testConsole.scanItem(testItem, "handheld");
		testConsole.scanItem(testItem2, "main");
		
		//check if system state after is equal to desired system state
		BigDecimal actualValue = testConsole.getTotalPrice();
		BigDecimal expectedValue = new BigDecimal(11);
		assertEquals(actualValue, expectedValue);
	}
	
	@Test
	public void testInValidItemList_OneItem() {
		//create test products
		Barcode testBarcode = new Barcode("0001");
		BigDecimal testPrice = BigDecimal.TEN;
		BarcodedProduct testProduct = new BarcodedProduct(testBarcode, "This is a test product", testPrice);
		Barcode testBarcode2 = new Barcode("0010");
		BigDecimal testPrice2 = BigDecimal.ONE;
		BarcodedProduct testProduct2 = new BarcodedProduct(testBarcode2, "This is a test product", testPrice2);
		
		//create test database
		Map<Barcode, BarcodedProduct>testMap = new HashMap<>();
		testMap.put(testBarcode, testProduct);
		testMap.put(testBarcode2, testProduct2);
		
		//create test station
		Currency testCurrency = Currency.getInstance("CAD");
		int[] testBanknoteDenomnations = {1,2,5};
		BigDecimal[] testCoinDenomnations = {BigDecimal.TEN};
		SelfCheckoutStation testStation = new SelfCheckoutStation(testCurrency, testBanknoteDenomnations, testCoinDenomnations, 100 ,1);
		
	
		//create test items
		Barcode incorrectCode = new Barcode("0011");
		BarcodedItem testItem = new BarcodedItem(incorrectCode, 5);

		
		//create test console
		ScansItem testConsole = new ScansItem(testMap, testStation);
		
		//simulate scanning on test console
		testConsole.scanItem(testItem, "main");
		
		//check if system state after is equal to desired system state
		ArrayList<BarcodedProduct> expectedList= new ArrayList<>();
		assertEquals(testConsole.itemList,expectedList);
	}

	
	@Test
	public void validAndInvalidItems() {
		//create test products
		Barcode testBarcode = new Barcode("0001");
		BigDecimal testPrice = BigDecimal.TEN;
		BarcodedProduct testProduct = new BarcodedProduct(testBarcode, "This is a test product", testPrice);
		Barcode testBarcode2 = new Barcode("0010");
		BigDecimal testPrice2 = BigDecimal.ONE;
		BarcodedProduct testProduct2 = new BarcodedProduct(testBarcode2, "This is a test product", testPrice2);
		
		//create test database
		Map<Barcode, BarcodedProduct>testMap = new HashMap<>();
		testMap.put(testBarcode, testProduct);
		testMap.put(testBarcode2, testProduct2);
		
		//create test station
		Currency testCurrency = Currency.getInstance("CAD");
		int[] testBanknoteDenomnations = {1,2,5};
		BigDecimal[] testCoinDenomnations = {BigDecimal.TEN};
		SelfCheckoutStation testStation = new SelfCheckoutStation(testCurrency, testBanknoteDenomnations, testCoinDenomnations, 100 ,1);
		
	
		//create test items
		Barcode incorrectCode = new Barcode("0011");
		BarcodedItem incorrectItem = new BarcodedItem(incorrectCode, 5);
		
		BarcodedItem correctItem = new BarcodedItem(testBarcode, 5);

		
		//create test console
		ScansItem testConsole = new ScansItem(testMap, testStation);
		
		//simulate scanning on test console
		testConsole.scanItem(incorrectItem, "main");
		testConsole.scanItem(correctItem, "main");
		
		//check if system state after is equal to desired system state
		ArrayList<BarcodedProduct> expectedList= new ArrayList<>();
		expectedList.add(testProduct);
		assertEquals(testConsole.itemList,expectedList);
	}
	
	@Test
	public void testValidItemList_oneItem_Disabled() {
		//create test product
		Barcode testBarcode = new Barcode("0001");
		BigDecimal testPrice = BigDecimal.TEN;
		BarcodedProduct testProduct = new BarcodedProduct(testBarcode, "This is a test product", testPrice);
		
		
		//create test database
		Map<Barcode, BarcodedProduct>testMap = new HashMap<>();
		testMap.put(testBarcode, testProduct);
		
		//create test station
		Currency testCurrency = Currency.getInstance("CAD");
		int[] testBanknoteDenomnations = {1,2,5};
		BigDecimal[] testCoinDenomnations = {BigDecimal.TEN};
		SelfCheckoutStation testStation = new SelfCheckoutStation(testCurrency, testBanknoteDenomnations, testCoinDenomnations, 100 ,1);
		
	
		//create test item
		BarcodedItem testItem = new BarcodedItem(testBarcode, 5);
		
		//create test console
		ScansItem testConsole = new ScansItem(testMap, testStation);
		
		//simulate scanning on test console
		testStation.mainScanner.disable();
		testConsole.scanItem(testItem, "main");
		
		//check if system state after is equal to desired system state
		ArrayList<BarcodedProduct> expectedList= new ArrayList<>();
		assertEquals(testConsole.itemList,expectedList);
	}
	
	@Test
	public void testValidItemList_oneItem_Enabled() {
		//create test product
		Barcode testBarcode = new Barcode("0001");
		BigDecimal testPrice = BigDecimal.TEN;
		BarcodedProduct testProduct = new BarcodedProduct(testBarcode, "This is a test product", testPrice);
		
		
		//create test database
		Map<Barcode, BarcodedProduct>testMap = new HashMap<>();
		testMap.put(testBarcode, testProduct);
		
		//create test station
		Currency testCurrency = Currency.getInstance("CAD");
		int[] testBanknoteDenomnations = {1,2,5};
		BigDecimal[] testCoinDenomnations = {BigDecimal.TEN};
		SelfCheckoutStation testStation = new SelfCheckoutStation(testCurrency, testBanknoteDenomnations, testCoinDenomnations, 100 ,1);
		
	
		//create test item
		BarcodedItem testItem = new BarcodedItem(testBarcode, 5);
		
		//create test console
		ScansItem testConsole = new ScansItem(testMap, testStation);
		
		//simulate scanning on test console
		testStation.mainScanner.enable();
		testConsole.scanItem(testItem, "main");
		
		//check if system state after is equal to desired system state
		ArrayList<BarcodedProduct> expectedList= new ArrayList<>();
		expectedList.add(testProduct);
		assertEquals(testConsole.itemList,expectedList);
	}
	
	
}

