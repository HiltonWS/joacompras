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
import com.rhcloud.joacompras.core.util.Messages;


/**
 * @author Hilton Wichwski Silva
 *
 */
public class ListaItemDAO extends CrudDAO<ListaItemBean> {

	/* (non-Javadoc)
	 * @see com.rhcloud.joacompras.core.dao.crud.CrudDAO#update(java.lang.Object[])
	 */
	public void update(ListaItemBean... e) {
		try {

			EntityManager em = EntityManagerProvider.getEntityManagerFactory()
					.createEntityManager();
			em.getTransaction().begin();
			Query q = em
					.createQuery("update ListaItemBean set quantidade = :qtd where  item = :iId  and lista = :lId");
			q.setParameter("iId", e[0].getItem());
			q.setParameter("lId", e[0].getLista());
			q.setParameter("qtd", e[0].getQuantidade());
			em.merge(e[0].getLista());
			q.executeUpdate();
			
			em.getTransaction().commit();

			em.close();
			new Messages().addInfo("Salvo com sucesso!");
		} catch (Exception y) {
			new Messages().addError("Erro o salvar!");
		}
	}

	/* (non-Javadoc)
	 * @see com.rhcloud.joacompras.core.dao.crud.CrudDAO#delete(java.lang.Object[])
	 */
	public void delete(ListaItemBean... e) {
		EntityManager em = EntityManagerProvider.getEntityManagerFactory()
				.createEntityManager();

		em.getTransaction().begin();
		Query q = em
				.createQuery("delete ListaItemBean where  item = :iId  and lista = :lId");
		q.setParameter("iId", e[0].getItem());
		q.setParameter("lId", e[0].getLista());
		q.executeUpdate();

		em.getTransaction().commit();

		em.close();
	}

	/**
	 * Busca os itens da lista relacionada
	 * @param bean a lista relacionada
	 * @return uma lista com os itens contidos na "ListaBean" relacionada
	 */
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
