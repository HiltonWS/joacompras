package com.rhcloud.joacompras.core.dao;


import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.rhcloud.joacompras.core.bean.ItemBean;
import com.rhcloud.joacompras.core.dao.connection.EntityManagerProvider;
import com.rhcloud.joacompras.core.dao.crud.CrudDAO;
import com.rhcloud.joacompras.core.util.Messages;

/**
 * @author Hilton Wichwski Silva
 *
 */
public class ItemDAO extends CrudDAO<ItemBean>{
//******Update personalizado não é chamado Retirado******/

	/* (non-Javadoc)
	 * @see com.rhcloud.joacompras.core.dao.crud.CrudDAO#delete(java.lang.Object[])
	 */
	public void delete(ItemBean... e) {
		try {
			EntityManager em = EntityManagerProvider.getEntityManagerFactory()
					.createEntityManager();
			em.getTransaction().begin();

			StringBuilder s = new StringBuilder();
			s.append("DELETE ItemBean");
			s.append(" WHERE id =" + e[0].getId());

			Query q = em.createQuery(s.toString());
			q.executeUpdate();
			em.getTransaction().commit();
			em.close();
			new Messages().addInfo("Deletado com sucesso!");
		} catch (Exception y) {
			new Messages().addError("Erro ao deletar!");
		}

	}

	/* (non-Javadoc)
	 * @see com.rhcloud.joacompras.core.dao.crud.CrudDAO#insert(java.lang.Object[])
	 */
	public void insert(ItemBean... e) {
		super.update(e);
	}
	
	
}
