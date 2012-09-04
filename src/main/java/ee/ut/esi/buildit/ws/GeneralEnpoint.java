package ee.ut.esi.buildit.ws;

import java.math.BigDecimal;
import java.math.BigInteger;

import ee.ut.esi.buildit.ws.model.EquipmentType;
import ee.ut.esi.buildit.ws.model.EquipmentsType;
import ee.ut.esi.buildit.ws.model.ObjectFactory;

public class GeneralEnpoint {
	protected EquipmentsType equipments;
	
	{
		ObjectFactory factory = new ObjectFactory();
		equipments = factory.createEquipmentsType();

		for (int i = 0; i < 10; i++) {
			EquipmentType item = factory.createEquipmentType();
			item.setId(BigInteger.valueOf(i));
			item.setName("equipment_name");
			item.setSupplierName("supplier_x");
			item.setRentalCost(BigDecimal.TEN);
			equipments.getEquipmentType().add(item);
		}
	}
}
