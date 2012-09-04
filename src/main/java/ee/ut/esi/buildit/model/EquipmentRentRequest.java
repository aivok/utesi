package ee.ut.esi.buildit.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

public class EquipmentRentRequest extends BossAcceptanceRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
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