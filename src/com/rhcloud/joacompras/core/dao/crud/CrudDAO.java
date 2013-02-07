package com.rhcloud.joacompras.core.dao.crud;

import java.util.List;

import javax.persistence.EntityManager;

import com.rhcloud.joacompras.core.bean.ItemBean;
import com.rhcloud.joacompras.core.dao.connection.EntityManagerProvider;

public class CrudDAO<E>{ //o <E> significa que essa classe génerica(Generics), ou seja, pode receber qualquer tipo de objeto
	
	private EntityManager em;
	
	public void insert(E... e){
		try{
			em = EntityManagerProvider.getEntityManagerFactory().createEntityManager();
		
			em.getTransaction().begin();
			for (E entity:e){
				em.persist(entity); //persist ï¿½ insert
				
			}
			em.getTransaction().commit();
			
			em.close();
//			new Messages().addInfo("Salvo com sucesso!",null);
		}catch (Exception ex){
//			new Messages().addError("Erro ao salvar",null);
			ex.printStackTrace();
		}
		
	}	
	
	public void update(E... e){
		try{
			em = EntityManagerProvider.getEntityManagerFactory().createEntityManager();

			em.getTransaction().begin();
			for (E entity:e){
				
				em.merge(entity);
				em.refresh(entity);
				
			}
			em.getTransaction().commit();
		
			
			em.close();
//			new Messages().addInfo("Alterado com sucesso!",null);
	}catch (Exception ex){
//		new Messages().addError("Erro ao salvar",null);
	}
	}
	
	public void delete(E... e){
		try{
			em = EntityManagerProvider.getEntityManagerFactory().createEntityManager();
		
			em.getTransaction().begin();
			for (E entity:e){
				Object ent = em.merge(entity);
				em.remove(ent); //remover
				
			}
			em.getTransaction().commit();
		
			em.close();
		
//			new Messages().addInfo("Deletado com sucesso!",null);
			
	}catch (Exception ex){
		ex.printStackTrace();
//		new Messages().addError("Erro ao deletar",null);
	}
	}
	
	public E buscarPorId(Object id, Class<E> clazz){
		em = EntityManagerProvider.getEntityManagerFactory().createEntityManager();
		
		em.getTransaction().begin();
		E e = em.find(clazz, id);
		
		em.getTransaction().commit();
		
		em.close();
		
		return e;
	}
	
	public List<E> listaTodos(Class<?> c){
		EntityManager em = EntityManagerProvider.getEntityManagerFactory().createEntityManager();
		
		String hql = "from "+c.getSimpleName();
		em.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<E> resultado = em.createQuery(hql).getResultList();
		em.close();
		
		return resultado;
	}


	
	public Long getMaxId(Class<?> c){
		EntityManager em = EntityManagerProvider.getEntityManagerFactory().createEntityManager();
		
		String hql = "MAX(ID) from "+c.getSimpleName();
		em.getTransaction().begin();
		Long resultado = (Long) em.createQuery(hql).getResultList().get(0);
		em.close();
		
		return resultado;
	}
	
	public void insert(Boolean msgConfirm, E... e){ //mehtodo com parametro de confirmacaum
		try{
			em = EntityManagerProvider.getEntityManagerFactory().createEntityManager();
		
			em.getTransaction().begin();
			for (E entity:e){
				em.persist(entity); //persist � insert
				
			}
			em.getTransaction().commit();
			
			em.close();
			if (msgConfirm){
//				new Messages().addInfo("Salvo com sucesso!",null);
			}
		}catch (Exception ex){
			if (msgConfirm){
//				new Messages().addError("Erro ao salvar",null);
			}
			ex.printStackTrace();
		}
	}	
	
	public void update(Boolean msgConfirm, E... e){ //mehtodo com parametro de confirmacaum
		try{
			em = EntityManagerProvider.getEntityManagerFactory().createEntityManager();

			em.getTransaction().begin();
			for (E entity:e){
				
				em.merge(entity); //merge � update
				em.refresh(entity);
				
			}
			em.getTransaction().commit();
					
			em.close();
			if (msgConfirm){
//				new Messages().addInfo("Alterado com sucesso!",null);
			}
		}catch (Exception ex){
			ex.printStackTrace();
			if (msgConfirm){
//				new Messages().addError("Erro ao salvar",null);
			}
		}
	}
	
	public void delete(Boolean msgConfirm, E... e){
		try{
			em = EntityManagerProvider.getEntityManagerFactory().createEntityManager();
		
			em.getTransaction().begin();
			for (E entity:e){
				Object ent = em.merge(entity);
				em.remove(ent); //remover
				
			}
			em.getTransaction().commit();
		
			em.close();
			
			if (msgConfirm){
//				new Messages().addInfo("Deletado com sucesso!",null);
			}
			
	}catch (Exception ex){
		ex.printStackTrace();
		if (msgConfirm){
//			new Messages().addError("Erro ao deletar",null);
		}
	}
	}

	
}