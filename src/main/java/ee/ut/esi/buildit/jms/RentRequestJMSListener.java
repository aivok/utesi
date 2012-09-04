package ee.ut.esi.buildit.jms;

import java.io.Serializable;

import javax.ejb.MessageDriven;
import javax.ejb.ActivationConfigProperty;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;


import ee.ut.esi.buildit.model.EquipmentRentRequest;
import ee.ut.esi.buildit.service.BossAcceptanceService;

@MessageDriven(mappedName="jms/RentRequestQueue", activationConfig={
        @ActivationConfigProperty(
             propertyName="destinationType",
             propertyValue="javax.jms.Queue")
             })
public class RentRequestJMSListener implements MessageListener {
	
	BossAcceptanceService service = BossAcceptanceService.getInstance();
	
	@Override
	public void onMessage(Message message) {
		ObjectMessage om = (ObjectMessage) message;
		Serializable content;
		try {
			content = om.getObject();

			EquipmentRentRequest request = (EquipmentRentRequest)content;
		
			service.askAccept(request);
		} catch (JMSException e) {
			e.printStackTrace();
		}	
	}

}
