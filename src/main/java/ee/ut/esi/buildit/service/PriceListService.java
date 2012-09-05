package ee.ut.esi.buildit.service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ee.ut.esi.buildit.model.HireRequest.StatusEnum;
import ee.ut.esi.buildit.model.PriceListItem;
import ee.ut.esi.buildit.model.HireRequest;

@Stateless
public class PriceListService {

	@EJB
	private BossAcceptanceService bossAcceptanceService;
	private final List<HireRequest> rentRequests = new CopyOnWriteArrayList<HireRequest>();
	
	@PersistenceContext(unitName="PersistenceUnit")
	EntityManager em;
	
	public  PriceListService() {
	}

	@SuppressWarnings("unchecked")
	public List<PriceListItem> getPriceList() {
//		return Arrays.asList(new PriceListItem("tractor", 1, new BigDecimal(100), RentUnit.HOUR), new PriceListItem("wheelbarrow", 2, new BigDecimal(5), RentUnit.DAY));
		Query query = em.createQuery("SELECT e FROM PriceListItem e");
	    return query.getResultList();
	}

	public List<HireRequest> getRentRequests() {
		return Collections.unmodifiableList(rentRequests);
	}

	public boolean isAvailable(PriceListItem item, Date startDate, Date endDate) {
		return true;
	}

	public void order(PriceListItem item, Date startDate, Date endDate) {
		HireRequest request = new HireRequest(item);
		request.setStartDate(startDate);
		request.setEndDate(endDate);
		request.setStatus(StatusEnum.REQUESTED);
		rentRequests.add(request);
		bossAcceptanceService.askAccept(request);
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
