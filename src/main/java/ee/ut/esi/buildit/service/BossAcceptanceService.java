package ee.ut.esi.buildit.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ee.ut.esi.buildit.model.BossAcceptanceRequest;

public class BossAcceptanceService {
	private static final BossAcceptanceService instance = new BossAcceptanceService();
	private final List<BossAcceptanceRequest> list = new ArrayList<BossAcceptanceRequest>();

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
				break;
			}
		}
	}
}
