package dja.housecleaning;

import java.util.List;

import other.things.CleaningTool;
import other.things.Van;

public class Ronald {

	public static final Ronald PERSON = new Ronald();
	
	private Ronald() {
	}
	
	public void cleanMyHousePlease (OrderForm orderForm, double money) {
		System.out.println("Ronald: thank you for your order!");
		Harry.PERSON.cleanHouseOrder(orderForm.getAddress(), orderForm.instructions, money);
	}	

	public OrderForm getOrderForm () {
		return new OrderForm();
	}	

	public Van borrowVan (String who) {
		return Garage.PLACE.getVan();
	}

	public List<CleaningTool> borrowTools (String who) {
		return Garage.PLACE.getCleaningTools();
	}
	

	@Override
	public String toString() {
		return "Ronald";
	}
}
