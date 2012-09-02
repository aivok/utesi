package ee.ut.esi.buildit.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import ee.ut.esi.buildit.model.Equipment;
import ee.ut.esi.buildit.model.EquipmentRentRequest;

public class EquipmentService {

	private static EquipmentService instance = new EquipmentService();
	private final BossAcceptanceService bossAcceptanceService;
	private final List<EquipmentRentRequest> rentRequests = new CopyOnWriteArrayList<EquipmentRentRequest>();

	private EquipmentService() {
		if (instance == null) {
			instance = this;
		}
		bossAcceptanceService = BossAcceptanceService.getInstance();
	}

	public static EquipmentService getInstance() {
		return instance;
	}

	public List<Equipment> getEquipmentList() {
		return Arrays.asList(new Equipment("tractor", 1), new Equipment("wheelbarrow", 2));
	}

	public List<EquipmentRentRequest> getRentRequests() {
		return Collections.unmodifiableList(rentRequests);
	}

	public boolean isAvailable(Equipment item, Date startDate, Date endDate) {
		return true;
	}

	public void order(Equipment item, Date startDate, Date endDate) {
		EquipmentRentRequest request = new EquipmentRentRequest(item);
		request.setStartDate(startDate);
		request.setEndDate(endDate);
		rentRequests.add(request);
		bossAcceptanceService.askAccept(request);
	}

	public void setEquipmentRentAcceptance(int requestId, boolean accepted) {
		for (EquipmentRentRequest request : rentRequests) {
			if (request.getId() == requestId) {
				rentRequests.remove(request);
				if (accepted) {
					// send...
				}
			}
		}
	}

	public void cancelEquipmentRentRequest(int requestId) {
		for (EquipmentRentRequest request : rentRequests) {
			if (request.getId() == requestId) {
				rentRequests.remove(request);
			}
		}
	}
}
