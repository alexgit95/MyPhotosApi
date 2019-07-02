package main.java.com.alex.api.apiPhoto.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import main.java.com.alex.api.apiPhoto.model.Evenements;
import main.java.com.alex.api.apiPhoto.model.Repertoire;
@Component
public class RepertoireRepositoryImpl implements RepertoiresRepositoryCustom {

	
	private MongoTemplate mongoTemplate;
	
	
	@Override
	public List<Repertoire> getAllRepertoireRacine() {
		Query query = new Query(Criteria.where("isRacine").is(true));
		query.with(new Sort(Sort.Direction.ASC,"chemin"));
		List<Repertoire> findAllRepertoire = mongoTemplate.find(query, Repertoire.class);
		return findAllRepertoire;
		
	}

	@Override
	public List<Repertoire> getAllSousRepertoire(String repertoireParent) {
		Query query = new Query(Criteria.where("chemin").regex(repertoireParent));
		query.with(new Sort(Sort.Direction.ASC,"chemin"));
		List<Repertoire> findAllRepertoire = mongoTemplate.find(query, Repertoire.class);
		return findAllRepertoire;
		
	}
	
	@Autowired
	public RepertoireRepositoryImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

}
