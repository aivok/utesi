package ee.ut.esi.buildit.model;

import java.util.Date;


public class HireRequest extends BossAcceptanceRequest {

	public enum StatusEnum{
		REQUESTED, //Hire request has been made site engineer/clerk
		APPROVED, //Hire request has been approved by works engineer
		REJECTED, //Hire request has been rejected by works engineer/supplier
		SENT, //Hire request has been sent to supplier
		ACCEPTED, // Hire request has been accepted by supplier
		CANCELLED // Hire request has been cancelled by site engineer/clerk
	}
	
	private int id;
	private PriceListItem priceListItem;
	private Date startDate;
	private Date endDate;
	private StatusEnum status;
	private int referenceNr;
	
	public HireRequest(PriceListItem priceListItem){
		this.priceListItem = priceListItem;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public PriceListItem getPriceListItem() {
		return priceListItem;
	}
	public void setPriceListItem(PriceListItem priceListItem) {
		this.priceListItem = priceListItem;
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
	public StatusEnum getStatus() {
		return status;
	}
	public void setStatus(StatusEnum status) {
		this.status = status;
	}
	public int getReferenceNr() {
		return referenceNr;
	}
	public void setReferenceNr(int referenceNr) {
		this.referenceNr = referenceNr;
	} 
	
	
}
