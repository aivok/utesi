package ee.ut.esi.buildit.service;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ee.ut.esi.buildit.dao.HireRequestDAO;
import ee.ut.esi.buildit.model.BossAcceptanceRequest;
import ee.ut.esi.buildit.model.HireRequest;

@Stateless
public class BossAcceptanceService {
	
	@EJB
	private HireRequestDAO hireRequestDAO;
	
	@EJB
	private PriceListService equipmentService;
	private final List<BossAcceptanceRequest> list = new CopyOnWriteArrayList<BossAcceptanceRequest>();

	public BossAcceptanceService() {
	}

	public void askAccept(BossAcceptanceRequest obj) {
		list.add(obj);
	}

	public List<? extends BossAcceptanceRequest> getRequests() {
		return hireRequestDAO.getHireRequests();
	}

	public void setResponse(int requestId, boolean accepted) {
		for (BossAcceptanceRequest request : list) {
			if (request.getId() == requestId) {
				list.remove(request);
				if (request instanceof HireRequest) {
					equipmentService.setEquipmentRentAcceptance(requestId, accepted);
				}
				return;
			}
		}
	}
}
