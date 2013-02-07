package com.rhcloud.joacompras.core.dao;


import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.rhcloud.joacompras.core.bean.ItemBean;
import com.rhcloud.joacompras.core.dao.connection.EntityManagerProvider;
import com.rhcloud.joacompras.core.dao.crud.CrudDAO;
import com.rhcloud.joacompras.core.util.Messages;

public class ItemDAO extends CrudDAO<ItemBean>{
//******Update personalizado não é chamado Retirado******/

	public void delete(ItemBean e) {
		try {
			EntityManager em = EntityManagerProvider.getEntityManagerFactory()
					.createEntityManager();
			em.getTransaction().begin();

			StringBuilder s = new StringBuilder();
			s.append("DELETE ItemBean");
			s.append(" WHERE id =" + e.getId());

			Query q = em.createQuery(s.toString());
			q.executeUpdate();
			em.getTransaction().commit();
			em.close();
			new Messages().addInfo("Deletado com sucesso!");
		} catch (Exception y) {
			new Messages().addError("Erro ao deletar!");
		}

	}

	public void insert(ItemBean... e) {
		super.update(e);
	}
	
	
}
