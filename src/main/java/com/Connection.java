package com;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class Connection {

	
	public static EntityManager getEntityManager() {
		return Persistence.createEntityManagerFactory("postgres").createEntityManager();
	}
}
