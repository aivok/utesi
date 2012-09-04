package ee.ut.esi.buildit.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import ee.ut.esi.buildit.model.PriceListItem;
import ee.ut.esi.buildit.model.HireRequest;
import ee.ut.esi.buildit.model.PriceListItem.RentUnit;

public class PriceListService {

	private static PriceListService instance = new PriceListService();
	private final BossAcceptanceService bossAcceptanceService;
	private final List<HireRequest> rentRequests = new CopyOnWriteArrayList<HireRequest>();

	private PriceListService() {
		if (instance == null) {
			instance = this;
		}
		bossAcceptanceService = BossAcceptanceService.getInstance();
	}

	public static PriceListService getInstance() {
		return instance;
	}

	public List<PriceListItem> getPriceList() {
		return Arrays.asList(new PriceListItem("tractor", 1, new BigDecimal(100), RentUnit.HOUR), new PriceListItem("wheelbarrow", 2, new BigDecimal(5), RentUnit.DAY));
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
