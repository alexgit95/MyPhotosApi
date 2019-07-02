package main.java.com.alex.api.apiPhoto.services;

import java.util.List;

import main.java.com.alex.api.apiPhoto.model.Repertoire;

public interface RepertoiresRepositoryCustom {

	
	List<Repertoire> getAllRepertoireRacine();
	List<Repertoire> getAllSousRepertoire(String repertoireParent);
}
