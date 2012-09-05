package ee.ut.esi.buildit.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import ee.ut.esi.buildit.model.PriceListItem;

@Stateless
public class PriceListDAO extends BaseDAO{
	
	@SuppressWarnings("unchecked")
	public List<PriceListItem> getPriceList(){
		Query query = getEntityManager().createQuery("SELECT e FROM PriceListItem e");
	    return query.getResultList();
	}
	

}
