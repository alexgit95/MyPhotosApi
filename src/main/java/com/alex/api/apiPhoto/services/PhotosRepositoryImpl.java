package main.java.com.alex.api.apiPhoto.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import main.java.com.alex.api.apiPhoto.model.Evenements;
import main.java.com.alex.api.apiPhoto.model.Photos;

public class PhotosRepositoryImpl implements PhotosRepositoryCustom {
	
	 private MongoTemplate mongoTemplate;

	@Override
	public List<Photos> findPhotosBetweenTwoDates(Date debut, Date fin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Photos> findPhotosByEvenement(Evenements evt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Photos> findPhotosWithoutEvenements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Photos> findPhotosByGeolocation(double lattitude,
			double longitude) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Photos findPhotosById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateFavoritePhotos(Photos photo) {
		// TODO Auto-generated method stub

	}
	
	
	@Autowired
	public PhotosRepositoryImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

}
