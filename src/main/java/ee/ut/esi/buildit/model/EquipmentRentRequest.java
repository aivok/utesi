package ee.ut.esi.buildit.model;

import java.util.Date;

public class EquipmentRentRequest extends BossAcceptanceRequest {

	private PriceListItem equipment;
	private Date startDate;
	private Date endDate;

	public EquipmentRentRequest(PriceListItem equipment) {
		this.equipment = equipment;
	}

	public PriceListItem getEquipment() {
		return equipment;
	}

	public void setEquipment(PriceListItem equipment) {
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
