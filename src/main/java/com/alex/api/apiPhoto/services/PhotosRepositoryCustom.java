package main.java.com.alex.api.apiPhoto.services;

import java.util.Date;
import java.util.List;

import main.java.com.alex.api.apiPhoto.model.Evenements;
import main.java.com.alex.api.apiPhoto.model.Photos;

public interface PhotosRepositoryCustom {
	
	List<Photos> findPhotosBetweenTwoDates(Date debut, Date fin);
	List<Photos> findPhotosByEvenement(Evenements evt);
	List<Photos> findPhotosWithoutEvenements();
	List<Photos> findPhotosByGeolocation(double lattitude, double longitude);
	Photos findPhotosById(String id);
	void updateFavoritePhotos(Photos photo);

}
