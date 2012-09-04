package ee.ut.esi.buildit.service;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import ee.ut.esi.buildit.model.BossAcceptanceRequest;
import ee.ut.esi.buildit.model.EquipmentRentRequest;

public class BossAcceptanceService {
	private static BossAcceptanceService instance = new BossAcceptanceService();
	private final PriceListService equipmentService;
	private final List<BossAcceptanceRequest> list = new CopyOnWriteArrayList<BossAcceptanceRequest>();

	private BossAcceptanceService() {
		if (instance == null) {
			instance = this;
		}
		equipmentService = PriceListService.getInstance();
	}

	public static BossAcceptanceService getInstance() {
		return instance;
	}

	public void askAccept(BossAcceptanceRequest obj) {
		list.add(obj);
	}

	public List<BossAcceptanceRequest> getRequests() {
		return Collections.unmodifiableList(list);
	}

	public void setResponse(int requestId, boolean accepted) {
		for (BossAcceptanceRequest request : list) {
			if (request.getId() == requestId) {
				list.remove(request);
				if (request instanceof EquipmentRentRequest) {
					equipmentService.setEquipmentRentAcceptance(requestId, accepted);
				}
				return;
			}
		}
	}
}
