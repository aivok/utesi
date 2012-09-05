package ee.ut.esi.buildit.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class BaseDAO {
	
	@PersistenceContext(unitName="PersistenceUnit")
	private EntityManager em;
	
	public EntityManager getEntityManager(){
		return em;
	}

}
