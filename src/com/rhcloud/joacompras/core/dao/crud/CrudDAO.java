package com.rhcloud.joacompras.core.dao.crud;

import java.util.List;

import javax.persistence.EntityManager;

import com.rhcloud.joacompras.core.dao.connection.EntityManagerProvider;
import com.rhcloud.joacompras.core.util.Messages;

public class CrudDAO<E> { // o <E> significa que essa classe génerica(Generics),

	private EntityManager em;

	/**
	 * Persist as entidades
	 * @param e entidade 
	 */
	public void insert(E... e) {
		try {
			em = EntityManagerProvider.getEntityManagerFactory()
					.createEntityManager();

			em.getTransaction().begin();
			for (E entity : e) {
				em.persist(entity);

			}
			em.getTransaction().commit();

			em.close();
			new Messages().addInfo("Salvo com sucesso!", null);
		} catch (Exception ex) {
			new Messages().addError("Erro ao salvar", null);
			ex.printStackTrace();
		}

	}

	/**
	 * Merge as entidades
	 * @param e entidade
	 */
	public void update(E... e) {
		try {
			em = EntityManagerProvider.getEntityManagerFactory()
					.createEntityManager();

			em.getTransaction().begin();
			for (E entity : e) {

				em.merge(entity);

			}
			em.getTransaction().commit();

			em.close();
			new Messages().addInfo("Salvo com sucesso!", null);
		} catch (Exception ex) {
			 new Messages().addError("Erro ao salvar",null);
		}
	}

	/**
	 * Verifica se o item existe e o remove
	 * @param e entidade
	 */
	public void delete(E... e) {
		try {
			em = EntityManagerProvider.getEntityManagerFactory()
					.createEntityManager();

			em.getTransaction().begin();
			for (E entity : e) {
				Object ent = em.merge(entity);
				em.remove(ent); // remover

			}
			em.getTransaction().commit();

			em.close();

			new Messages().addInfo("Deletado com sucesso!", null);

		} catch (Exception ex) {
			ex.printStackTrace();
			// new Messages().addError("Erro ao deletar",null);
		}
	}

	/**
	 * Busca a entidade
	 * @param id da entidade
	 * @param clazz classe da entidade
	 * @return entidade
	 */
	public E buscarPorId(Object id, Class<E> clazz) {
		em = EntityManagerProvider.getEntityManagerFactory()
				.createEntityManager();

		em.getTransaction().begin();
		E e = em.find(clazz, id);

		em.getTransaction().commit();

		em.close();

		return e;
	}

	/**
	 * Lista todas as entidades
	 * @param c classe da entidade
	 * @return Lista de entidades
	 */
	public List<E> listaTodos(Class<?> c) {
		EntityManager em = EntityManagerProvider.getEntityManagerFactory()
				.createEntityManager();

		String hql = "from " + c.getSimpleName();
		em.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<E> resultado = em.createQuery(hql).getResultList();
		em.close();

		return resultado;
	}

	/**
	 * Id maxima da entidade
	 * @param c Classe da entidade
	 * @return id
	 */
	public Long getMaxId(Class<?> c) {
		EntityManager em = EntityManagerProvider.getEntityManagerFactory()
				.createEntityManager();

		String hql = "MAX(ID) from " + c.getSimpleName();
		em.getTransaction().begin();
		Long resultado = (Long) em.createQuery(hql).getResultList().get(0);
		em.close();

		return resultado;
	}

	/**
	 * Persist as entidades
	 * @param msgConfirm ha mensagem de confirmacao true/false
	 * @param e entidade
	 */
	public void insert(Boolean msgConfirm, E... e) { // mehtodo com parametro de
														// confirmacaum
		try {
			em = EntityManagerProvider.getEntityManagerFactory()
					.createEntityManager();

			em.getTransaction().begin();
			for (E entity : e) {
				em.persist(entity);

			}
			em.getTransaction().commit();

			em.close();
			if (msgConfirm) {
				new Messages().addInfo("Salvo com sucesso!", null);
			}
		} catch (Exception ex) {
			if (msgConfirm) {
				new Messages().addError("Erro ao salvar", null);
			}
			ex.printStackTrace();
		}
	}

	/**
	 * Merge as entidades
	 * @param msgConfirm ha mensagem de confirmacao true/false
	 * @param e entidade
	 */
	public void update(Boolean msgConfirm, E... e) { // mehtodo com parametro de
														// confirmacaum
		try {
			em = EntityManagerProvider.getEntityManagerFactory()
					.createEntityManager();

			em.getTransaction().begin();
			for (E entity : e) {

				em.merge(entity);

			}
			em.getTransaction().commit();

			em.close();
			if (msgConfirm) {
				new Messages().addInfo("Salvo com sucesso!", null);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			if (msgConfirm) {
				new Messages().addError("Erro ao salvar", null);
			}
		}
	}

	/**
	 * Deleta as entidades
	 * @param msgConfirm ha mensagem de confirmacao true/false
	 * @param e
	 */
	public void delete(Boolean msgConfirm, E... e) {
		try {
			em = EntityManagerProvider.getEntityManagerFactory()
					.createEntityManager();

			em.getTransaction().begin();
			for (E entity : e) {
				Object ent = em.merge(entity);
				em.remove(ent); // remover

			}
			em.getTransaction().commit();

			em.close();

			if (msgConfirm) {
				new Messages().addInfo("Deletado com sucesso!", null);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			if (msgConfirm) {
				new Messages().addError("Erro ao deletar", null);
			}
		}
	}

}