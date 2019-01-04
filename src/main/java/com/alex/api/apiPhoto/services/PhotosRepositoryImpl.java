package main.java.com.alex.api.apiPhoto.services;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import main.java.com.alex.api.apiPhoto.model.Photos;

@Component
public class PhotosRepositoryImpl implements PhotosRepositoryCustom {
	
	 private MongoTemplate mongoTemplate;

	@Override
	public List<Photos> findPhotosBetweenTwoDates(Date start, Date end) {
		
		
		
		Query query = new Query(Criteria.where("datePriseVue").gt(start)
				.andOperator(Criteria.where("datePriseVue").lt(end)));
		
		
		List<Photos> find = mongoTemplate.find(query, Photos.class);
		return find;
	}

	@Override
	public List<Photos> findPhotosByEvenement(String idEvt) {
		Query query = new Query(Criteria.where("evt._id").is(new ObjectId(idEvt)));
		List<Photos> find = mongoTemplate.find(query, Photos.class);
		return find;
	}

	@Override
	public List<Photos> findPhotosWithoutEvenements() {
		Query query = new Query(Criteria.where("evt").is(null));
		List<Photos> find = mongoTemplate.find(query, Photos.class);
		return find;
	}

	@Override
	public List<Photos> findPhotosByGeolocation(double lattitude,
			double longitude) {
		Query query = new Query(Criteria.where("longitude").gt(longitude-0.05)
				.andOperator(Criteria.where("lattitude").gt(lattitude-0.05),Criteria.where("lattitude").lt(lattitude+0.05),Criteria.where("longitude").lt(longitude+0.05)));
		List<Photos> find = mongoTemplate.find(query, Photos.class);
		return find;
	}

	@Override
	public Photos findPhotosById(String id) {
		Query query = new Query(Criteria.where("_id").is(id));
		Photos findOne = mongoTemplate.findOne(query, Photos.class);
		return findOne;
	}

	@Override
	public void updateFavoritePhotos(String id) {
		Query query = new Query(Criteria.where("_id").is(id));
		Photos findOne = mongoTemplate.findOne(query, Photos.class);
		findOne.estFavoris=true;
		mongoTemplate.save(findOne);
	}
	
	
	@Autowired
	public PhotosRepositoryImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

}
