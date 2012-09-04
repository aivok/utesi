package ee.ut.esi.buildit.service;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ee.ut.esi.buildit.jms.RentRequestJMSSender;
import ee.ut.esi.buildit.model.Equipment;
import ee.ut.esi.buildit.model.EquipmentRentRequest;

@Stateless
public class EquipmentService {

	@EJB
	private RentRequestJMSSender jmsSender;
	
	@PersistenceContext(unitName="EquipmentServicePersistenceUnit")
	EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Equipment> getEquipmentList() {
		Query query = em.createQuery("SELECT e FROM Equipment e");
	    return query.getResultList();
	}

	public boolean isAvailable(Equipment item, Date startDate, Date endDate) {
		return true;
	}

	public void order(Equipment item, Date startDate, Date endDate) {
		EquipmentRentRequest request = new EquipmentRentRequest(item);
		request.setStartDate(startDate);
		request.setEndDate(endDate);
		jmsSender.sendMessage(request);
	}
}
