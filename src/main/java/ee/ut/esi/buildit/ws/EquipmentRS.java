package ee.ut.esi.buildit.ws;

import java.io.StringWriter;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 * Nimekiri on kättesaadav /rest/EquipmentRS
 */
@Path("EquipmentRS")
public class EquipmentRS extends GeneralEnpoint {
	
	@GET
	@Produces({"application/xml", "application/json"})
	public String getEquipments() throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(equipments.getClass());
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		
		StringWriter writer = new StringWriter();
		marshaller.marshal(equipments, writer);

		return writer.toString();
	}
}
