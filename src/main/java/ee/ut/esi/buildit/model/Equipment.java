package ee.ut.esi.buildit.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="price_list")
public class Equipment implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String supplierName;
	private BigDecimal rentalCost;
	private int id;

	public Equipment(){
	}
	
	public Equipment(String name, int id) {
		setName(name);
		setId(id);
	}
	
	@Column(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Id
	@Column(name="id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@Transient
	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	@Column(name="cost")
	public BigDecimal getRentalCost() {
		return rentalCost;
	}

	public void setRentalCost(BigDecimal rentalCost) {
		this.rentalCost = rentalCost;
	}
}
