package ee.ut.esi.buildit.service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ee.ut.esi.buildit.dao.HireRequestDAO;
import ee.ut.esi.buildit.dao.PriceListDAO;
import ee.ut.esi.buildit.model.HireRequest.StatusEnum;
import ee.ut.esi.buildit.model.PriceListItem;
import ee.ut.esi.buildit.model.HireRequest;

@Stateless
public class PriceListService {

	@EJB
	private BossAcceptanceService bossAcceptanceService;
	private final List<HireRequest> rentRequests = new CopyOnWriteArrayList<HireRequest>();
	
	@EJB
	PriceListDAO priceListDAO;
	
	@EJB
	HireRequestDAO hireRequestDAO;
	
	public PriceListService() {
	}

	@SuppressWarnings("unchecked")
	public List<PriceListItem> getPriceList() {
		return priceListDAO.getPriceList();
	}

	public List<HireRequest> getRentRequests() {
		return hireRequestDAO.getHireRequests();
	}

	public boolean isAvailable(PriceListItem item, Date startDate, Date endDate) {
		return true;
	}

	public void order(PriceListItem item, Date startDate, Date endDate) {
		HireRequest request = new HireRequest(item);
		request.setStartDate(startDate);
		request.setEndDate(endDate);
		request.setStatus(StatusEnum.REQUESTED);
		hireRequestDAO.save(request);
	}

	public void setEquipmentRentAcceptance(int requestId, boolean accepted) {
		for (HireRequest request : rentRequests) {
			if (request.getId() == requestId) {
				rentRequests.remove(request);
				if (accepted) {
					// send...
				}
			}
		}
	}

	public void cancelEquipmentRentRequest(int requestId) {
		for (HireRequest request : rentRequests) {
			if (request.getId() == requestId) {
				rentRequests.remove(request);
			}
		}
	}
}
