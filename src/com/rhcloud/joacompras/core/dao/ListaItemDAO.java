package com.rhcloud.joacompras.core.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.rhcloud.joacompras.core.bean.ItemBean;
import com.rhcloud.joacompras.core.bean.ListaBean;
import com.rhcloud.joacompras.core.bean.ListaItemBean;
import com.rhcloud.joacompras.core.dao.connection.EntityManagerProvider;
import com.rhcloud.joacompras.core.dao.crud.CrudDAO;

public class ListaItemDAO extends CrudDAO<ListaItemBean> {

	public void update(ListaItemBean e) {

		EntityManager em = EntityManagerProvider.getEntityManagerFactory()
				.createEntityManager();

		em.getTransaction().begin();
		Query q = em.createQuery("from ListaItemBean where  item = :iId  and lista = :lId");
		q.setParameter("iId", e.getItem());
		q.setParameter("lId", e.getLista());
		if(q.getResultList().isEmpty()){
			em.persist(e);
		}else{
			q = em.createQuery("update ListaItemBean set quantidade = :qtd where  item = :iId  and lista = :lId");
			q.setParameter("iId", e.getItem());
			q.setParameter("lId", e.getLista());
			q.setParameter("qtd", e.getQuantidade());
			
			q.executeUpdate();
		}
		
		em.getTransaction().commit();

		em.close();

	}

	public void delete(ListaItemBean e) {

		EntityManager em = EntityManagerProvider.getEntityManagerFactory()
				.createEntityManager();

		em.getTransaction().begin();
		Query  q = em.createQuery("delete ListaItemBean where  item = :iId  and lista = :lId");
		q.setParameter("iId", e.getItem());
		q.setParameter("lId", e.getLista());
		new ListaDAO().delete(e.getLista());
		q.executeUpdate();
		
		
		em.getTransaction().commit();

		em.close();
		new ListaDAO().delete(e.getLista());

	}
	public List<ItemBean> buscarItens(ListaBean bean) {
		EntityManager em = EntityManagerProvider.getEntityManagerFactory()
				.createEntityManager();

		em.getTransaction().begin();
		CriteriaQuery<ListaItemBean> criteria = em.getCriteriaBuilder()
				.createQuery(ListaItemBean.class);
		Root<ListaItemBean> ib = criteria.from(ListaItemBean.class);
		criteria.select(ib);
		criteria.where(em.getCriteriaBuilder().equal(ib.get("lista"), bean));
		List<ListaItemBean> lb = em.createQuery(criteria).getResultList();
		List<ItemBean> b = new ArrayList<ItemBean>();
		for (ListaItemBean l : lb) {
			l.getItem().setTempQtd(l.getQuantidade());
			b.add(l.getItem());
		}
		em.getTransaction().commit();

		em.close();

		return b;
	}

}
