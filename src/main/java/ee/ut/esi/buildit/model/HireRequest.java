package ee.ut.esi.buildit.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="purchase_orders")
public class HireRequest extends BossAcceptanceRequest {

	public enum StatusEnum {
		REQUESTED("Requested"), // Hire request has been made site engineer/clerk
		APPROVED("Approved"), // Hire request has been approved by works engineer
		NOT_APPROVED("Not approved"); // Hire request has been disapproved by works engineer/supplier
		
		private String value;
		private StatusEnum(String value){
			this.value = value;
		}
		
		public String getValue() {
			return value;
		}
		
	}

	private int id;
	private PriceListItem priceListItem;
	private Date startDate;
	private Date endDate;
	private StatusEnum status;
	
	public HireRequest(){
	}

	public HireRequest(PriceListItem priceListItem) {
		this.priceListItem = priceListItem;
	}

	@Id
	@Column(name="id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@JoinColumn(name="price_list_id")
	public PriceListItem getPriceListItem() {
		return priceListItem;
	}

	public void setPriceListItem(PriceListItem priceListItem) {
		this.priceListItem = priceListItem;
	}
	
	@Temporal(TemporalType.DATE) 
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	@Temporal(TemporalType.DATE) 
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
//	@Column(name="status")
	@Transient
	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

}
