package main.java.com.alex.api.apiPhoto.services;

import java.util.Date;
import java.util.List;

import main.java.com.alex.api.apiPhoto.model.Photos;

public interface PhotosRepositoryCustom {
	List<Photos> findPhotosBetweenTwoDates(Date start, Date end);
	List<Photos> findPhotosByEvenement(String idEvt);
	List<Photos> findPhotosWithoutEvenements();
	List<Photos> findPhotosByGeolocation(double lattitude, double longitude);
	Photos findPhotosById(String id);
	void updateFavoritePhotos(String id);
	Photos deleteFavorite(String id);
	List<Photos> findFavoritesPhotosBetweenTwoDates(Date start, Date end);

}
