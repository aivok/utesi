package ee.ut.esi.buildit.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import ee.ut.esi.buildit.model.Equipment;
import ee.ut.esi.buildit.model.EquipmentRentRequest;

public class EquipmentService {

	private static final EquipmentService instance = new EquipmentService();
	private final BossAcceptanceService bossAcceptanceService = BossAcceptanceService.getInstance();

	private EquipmentService() {
	}

	public static EquipmentService getInstance() {
		return instance;
	}

	public List<Equipment> getEquipmentList() {
		return Arrays.asList(new Equipment("tractor", 1), new Equipment("wheelbarrow", 2));
	}

	public boolean isAvailable(Equipment item, Date startDate, Date endDate) {
		return true;
	}

	public void order(Equipment item, Date startDate, Date endDate) {
		EquipmentRentRequest request = new EquipmentRentRequest(item);
		request.setStartDate(startDate);
		request.setEndDate(endDate);
		bossAcceptanceService.askAccept(request);
	}
}
