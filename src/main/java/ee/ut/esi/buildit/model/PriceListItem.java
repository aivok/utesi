package ee.ut.esi.buildit.model;import java.math.BigDecimal;public class PriceListItem {		private int id;	private String name;	private BigDecimal price;	private RentUnit unit;			public PriceListItem(String name, int id, BigDecimal price, RentUnit unit) {		setName(name);		setId(id);		setPrice(price);		setUnit(unit);	} 	public String getName() {		return name;	}	public void setName(String name) {		this.name = name;	}	public int getId() {		return id;	}	public void setId(int id) {		this.id = id;	}			public BigDecimal getPrice() {		return price;	}	public void setPrice(BigDecimal price) {		this.price = price;	}		public String getPriceStringWithUnit(){		return getPrice()+"EUR/"+getUnit().getValue();	}	public RentUnit getUnit() {		return unit;	}	public void setUnit(RentUnit unit) {		this.unit = unit;	}		public enum RentUnit{				HOUR("h"), DAY("day"), WEEK("week");				private final String value;				RentUnit(String value){			this.value = value;		}				public String getValue() {			return value;		}			}	}