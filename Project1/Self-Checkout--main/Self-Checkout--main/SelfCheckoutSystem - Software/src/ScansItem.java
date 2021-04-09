import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

import org.lsmr.selfcheckout.*;
import org.lsmr.selfcheckout.devices.*;
import org.lsmr.selfcheckout.devices.listeners.*;
import org.lsmr.selfcheckout.external.*;
import org.lsmr.selfcheckout.products.BarcodedProduct;

public class ScansItem {

	//global variables for ScansItem class
    public Map<Barcode, BarcodedProduct> productList;	//mock database of valid items 
    public  ArrayList<BarcodedProduct> itemList= new ArrayList<>();	//list of all items currently scanned
    public SelfCheckoutStation checkoutStation;	//instance of checkout station
    private boolean isScanned = false;
    
    //constructor 
    public ScansItem(Map<Barcode, BarcodedProduct> pd, SelfCheckoutStation st) {
    	productList = pd;
    	checkoutStation = st;
    	//register overwritten listener to scanners
    	checkoutStation.mainScanner.register(bsl);
    	checkoutStation.handheldScanner.register(bsl);
    }

    //Instance of listener with overwritten barcodeScanned() method
    private BarcodeScannerListener bsl = new BarcodeScannerListener() {
 
        @Override
        public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {
            // Do Nothing
        }

        @Override
        public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {
        }

        //Increment number of scanned item by 1 each time an items bar code is scanned
        @Override
        public void barcodeScanned(BarcodeScanner barcodeScanner, Barcode barcode) {
            // TODO Auto-generated method stub
        	//set flag to notify scann occured
        	isScanned = true;  	    
        }
    };

   
    //main driver function to initiate scan of item
    public void scanItem(BarcodedItem anItem, String scannerType) {
    	//call scan() method with the appropriate scanner in the selfCheckoutStation
    	if(scannerType=="handheld") {
    		 checkoutStation.handheldScanner.scan(anItem);
    	}else {
    		 checkoutStation.mainScanner.scan(anItem);
    	}
    	
    	//if scan is recognized, add item to cart
    	if(isScanned) {
    		//if Barcode is matched to Barcode  in mock database, add product to purchase list
            BarcodedProduct product = productList.get(anItem.getBarcode());
        
            if(product==null) {
            	
            }else {
            	itemList.add(product);
            }       
            isScanned = false;
    	}
    }
    
    //function to calculate total price of cart
    public BigDecimal getTotalPrice() {
    	BigDecimal totalPrice = BigDecimal.ZERO;
    	for(int i=0; i<itemList.size(); i++) {
    		BigDecimal currPrice = itemList.get(i).getPrice();
    		totalPrice = totalPrice.add(currPrice);
    	}
    	return totalPrice;
    }
    
}
