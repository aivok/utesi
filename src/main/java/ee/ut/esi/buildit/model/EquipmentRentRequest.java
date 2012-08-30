package ee.ut.esi.buildit.model;

import java.util.Date;

public class EquipmentRentRequest extends BossAcceptanceRequest {

	private Equipment equipment;
	private Date startDate;
	private Date endDate;

	public EquipmentRentRequest(Equipment equipment) {
		this.equipment = equipment;
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
