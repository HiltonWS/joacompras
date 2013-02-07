package com.rhcloud.joacompras.core.dao.connection;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Hilton Wichwski Silva
 *
 */
public class EntityManagerProvider {
	
	private static EntityManagerFactory emf;
	
	public EntityManagerProvider(){
	}
	
	/**
	 * @return EntityManaFactory com a persistencia
	 */
	public static EntityManagerFactory getEntityManagerFactory(){
		if(emf == null){
			//padrão Singleton (apenas uma instância na memória)
			emf = Persistence.createEntityManagerFactory("joacomprasPU"); 
		}
		return emf;
	}
}