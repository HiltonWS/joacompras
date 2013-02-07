package com.rhcloud.joacompras.core.dao.connection;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerProvider {
	
	private static EntityManagerFactory emf;
	
	public EntityManagerProvider(){
	}
	
	public static EntityManagerFactory getEntityManagerFactory(){
		if(emf == null){
			//padrão Singleton (apenas uma instância na memória)
			emf = Persistence.createEntityManagerFactory("joacomprasPU"); 
		}
		return emf;
	}
}