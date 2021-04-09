import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.Item;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.BarcodeScanner;
import org.lsmr.selfcheckout.devices.ElectronicScale;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.BarcodeScannerListener;
import org.lsmr.selfcheckout.devices.listeners.ElectronicScaleListener;
import org.lsmr.selfcheckout.products.BarcodedProduct;

public class PlacesInBaggingArea {

	//global variables for placesInBaggingArea class
  public SelfCheckoutStation checkoutStation;
  public boolean isPlaced = false;
  public boolean isRemoved = false;
  public boolean isOverloaded = false;
  public ArrayList<Item> items = new ArrayList<>();
  
  public ElectronicScaleListener esl = new ElectronicScaleListener() {
	  	@Override
		public 
		void weightChanged(ElectronicScale scale, double weightInGrams) {
	  		isPlaced = true;
		}

	  	@Override
	  	public
		void overload(ElectronicScale scale) {isOverloaded = true;}

	  	@Override
	  	public
		void outOfOverload(ElectronicScale scale) { isRemoved = true; }

		@Override
		public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {}

		@Override
		public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {}
	  
  };
    
    //constructor 
    public PlacesInBaggingArea(SelfCheckoutStation st) {
    	checkoutStation = st;
    	st.baggingArea.register(esl);
    }

   
    //main driver function to place item into bagging area
    public void placeItem(Item anItem) {
    	checkoutStation.baggingArea.add(anItem);
    	if(isPlaced || isOverloaded) {
    		items.add(anItem);
    		isOverloaded = false;
    		isPlaced = false;
    	}
    }
    
    //main driver function to remove item from bagging area
    public void removeItem(Item anItem) {
    	checkoutStation.baggingArea.remove(anItem);
    	if(isRemoved || isPlaced) {
    		int index = items.indexOf(anItem);
    		items.remove(index);
    		isRemoved = false;
    		isPlaced = false;
    	}
    }
    	
	
}
