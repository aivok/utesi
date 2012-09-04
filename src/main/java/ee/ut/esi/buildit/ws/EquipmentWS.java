package ee.ut.esi.buildit.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

import ee.ut.esi.buildit.ws.model.EquipmentsType;

/**
 * wsdl on kättesaadav /EquipmentWSService?wsdl
 * test on kättesaadav /EquipmentWSService?Tester
 */
@WebService
public class EquipmentWS extends GeneralEnpoint {
	
	@WebMethod
	public EquipmentsType getEquipments() {
		return equipments;
	}
}
