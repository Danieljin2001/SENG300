package org.lsmr.a3.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.Item;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.BarcodeScanner;
import org.lsmr.selfcheckout.devices.SimulationException;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.BarcodeScannerListener;

public class BarcodeScannerTest { 
	private BarcodeScanner barcodeScanner;
	private BarcodedItem barcodedItem;
	private Barcode barcode;
	
	@Before
	public void setup() {
		this.barcodeScanner = new BarcodeScanner();
		this.barcode = new Barcode("12345");
		this.barcodedItem = new BarcodedItem(barcode, 5.0);
	}
	

	@Test
	public void testScan() {
		//testing scan with disabled scanner
		barcodeScanner.disable();
		try {
			barcodeScanner.scan(null);
		}
		catch (Exception e) {
			assertFalse("Exception should not be thrown", e instanceof SimulationException);
		}
		
		//re-enable the scanner
		barcodeScanner.enable();
		
		//testing scan with a barcoded item and with a null item
		try {
			barcodeScanner.scan(barcodedItem);
			barcodeScanner.scan(null);
		}
		catch (Exception e){
			assertTrue("Item is null", e instanceof SimulationException);
		}
		
		
	}
	
	@Test
	public void testNotifyBarcodeScanned() {
		//make a stub of the listener
		BarcodeScannerListener stub = new BarcodeScannerListener() {
			@Override
			public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {
			}

			@Override
			public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {	
			}

			@Override
			public void barcodeScanned(BarcodeScanner barcodeScanner, Barcode barcode) {	
			}
			
		};
		//test the register and notify method of scanner
		barcodeScanner.register(stub);
		barcodeScanner.scan(barcodedItem);		
	}

}
