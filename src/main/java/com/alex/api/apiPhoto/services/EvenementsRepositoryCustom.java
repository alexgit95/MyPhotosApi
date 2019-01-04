package main.java.com.alex.api.apiPhoto.services;

import java.util.Date;
import java.util.List;

import main.java.com.alex.api.apiPhoto.model.Evenements;

public interface EvenementsRepositoryCustom {
	
	
	List<Evenements> findAllValidatedEvents();
	List<Evenements> findAllValidatedEventsBetweenTwoDates(Date start, Date end);
	
	List<Evenements> findAllNoValidatedEvents();
	Evenements findOneNoValidatedEvents();
	
	void updateEventsTitle(String newName, String id);
	

}
