package ee.ut.esi.buildit.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import ee.ut.esi.buildit.model.HireRequest;

@Stateless
public class HireRequestDAO extends BaseDAO {
	
	@SuppressWarnings("unchecked")
	public List<HireRequest> getHireRequests(){
		Query query = getEntityManager().createQuery("SELECT e FROM HireRequest e");
	    return query.getResultList();
	}
	
	public void save(HireRequest hireRequest){
		getEntityManager().persist(hireRequest);
	}
	
	

}
