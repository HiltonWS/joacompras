package com.rhcloud.joacompras.core.dao;


import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.rhcloud.joacompras.core.bean.ItemBean;
import com.rhcloud.joacompras.core.dao.connection.EntityManagerProvider;
import com.rhcloud.joacompras.core.dao.crud.CrudDAO;

public class ItemDAO extends CrudDAO<ItemBean> {

	//formulario multipart nao recupera entidade gerenciada
	public void update(ItemBean e) { 
		EntityManager em = EntityManagerProvider.getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		
		StringBuilder s = new StringBuilder();
		s.append("UPDATE ItemBean SET ");
		s.append(e.getImagem() == null?"":"imagem = :i,");
		s.append(" nome = '"+e.getNome());
		s.append("', valor = '"+e.getValor());
		s.append("' WHERE id ="+e.getId());
		
		Query q =em.createQuery(s.toString());
		if(e.getImagem() != null)
		q.setParameter("i", e.getImagem());
		q.executeUpdate();
		em.getTransaction().commit();
		em.close();
		
	}
	
	public void delete(ItemBean e) { 
		try{
		EntityManager em = EntityManagerProvider.getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		
		StringBuilder s = new StringBuilder();
		s.append("DELETE ItemBean");
		s.append(" WHERE id ="+e.getId());
		
		Query q =em.createQuery(s.toString());
		q.executeUpdate();
		em.getTransaction().commit();
		em.close();
		}catch(Exception y){
			y.printStackTrace();
		}
		
	}
	
	@Override
	public void insert(Boolean msgConfirm, ItemBean... e) {
		
		super.update(msgConfirm, e);
	}
	
	@Override
	public void insert(ItemBean... e) {
		
		super.update(e);
	}

}

