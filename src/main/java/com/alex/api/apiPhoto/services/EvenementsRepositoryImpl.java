package main.java.com.alex.api.apiPhoto.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import main.java.com.alex.api.apiPhoto.model.Evenements;
@Component
public class EvenementsRepositoryImpl implements EvenementsRepositoryCustom {

	
	private MongoTemplate mongoTemplate;
	
	@Override
	public List<Evenements> findAllValidatedEvents() {
		Query query = new Query(Criteria.where("valid").is(true));
		List<Evenements> findAllNoValidatedEvents = mongoTemplate.find(query, Evenements.class);
		return findAllNoValidatedEvents;
	}

	@Override
	public List<Evenements> findAllValidatedEventsBetweenTwoDates(Date start, Date end) {
		Query query = new Query(Criteria.where("debut").gt(start)
				.andOperator(Criteria.where("debut").lt(end),Criteria.where("valid").is(true)));
		List<Evenements> findAllValidatedEventsBetweenTwoDates = mongoTemplate.find(query, Evenements.class);
		return findAllValidatedEventsBetweenTwoDates;
	}

	@Override
	public List<Evenements> findAllNoValidatedEvents() {
		Query query = new Query(Criteria.where("valid").is(false));
		List<Evenements> findAllNoValidatedEvents = mongoTemplate.find(query, Evenements.class);
		return findAllNoValidatedEvents;
	}

	@Override
	public Evenements findOneNoValidatedEvents() {
		Query query = new Query(Criteria.where("valid").is(null));
		List<Evenements> findOneNoValidatedEvents = mongoTemplate.find(query, Evenements.class);
		return findOneNoValidatedEvents.get(0);
	}

	@Override
	public void updateEventsTitle(String newName, String id) {
		Query query = new Query(Criteria.where("_id").is(id));
		Evenements findOne = mongoTemplate.findOne(query, Evenements.class);
		findOne.nom=newName;
		findOne.valid=true;
		mongoTemplate.save(findOne);

	}
	
	
	
	@Autowired
	public EvenementsRepositoryImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}


}
