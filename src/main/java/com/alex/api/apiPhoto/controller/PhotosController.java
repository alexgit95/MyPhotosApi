package main.java.com.alex.api.apiPhoto.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import main.java.com.alex.api.apiPhoto.model.Photos;
import main.java.com.alex.api.apiPhoto.services.PhotosRepositoryCustom;


@Controller
@CrossOrigin
@RequestMapping("/api/v1/photos")
public class PhotosController {
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
	
	@Autowired
	private PhotosRepositoryCustom repositoryPhotos;
	@Value("${localisation.service.binaire}")
	private String urlWSFichiers;
	
	@RequestMapping( value="/date/{datedebut}/{datefin}", method = RequestMethod.GET)
	public @ResponseBody List<Photos> getPhotosByDate(@PathVariable String datedebut,@PathVariable String datefin) throws ParseException {
		System.out.println("dateDebut:"+datedebut+", dateFin:"+datefin);
		datedebut = new String(Base64.getDecoder().decode(datedebut));
		datefin = new String(Base64.getDecoder().decode(datefin));
		System.out.println("dateDebut:"+datedebut+", dateFin:"+datefin);
		Date debut=sdf.parse(datedebut);
		Date fin=sdf.parse(datefin);
		List<Photos> findPhotosBetweenTwoDates = repositoryPhotos.findPhotosBetweenTwoDates(debut, fin);
		return findPhotosBetweenTwoDates;

	}
	
	@RequestMapping( value="/geo/{lattitude}/{longitude}", method = RequestMethod.GET)
	public @ResponseBody List<Photos> getPhotosByPlace(@PathVariable double lattitude,@PathVariable double longitude) {
		System.out.println("getPhotosByPlace lat:"+lattitude+", longitude:"+longitude);
		List<Photos> findPhotosByGeolocation = repositoryPhotos.findPhotosByGeolocation(lattitude, longitude);
		return findPhotosByGeolocation;

	}
	@RequestMapping( value="/sansevt", method = RequestMethod.GET)
	public @ResponseBody List<Photos> getPhotosWithoutEvt()  {
		System.out.println("getPhotosWithoutEvt");
		List<Photos> findPhotosWithoutEvenements = repositoryPhotos.findPhotosWithoutEvenements();
		return findPhotosWithoutEvenements;

	}
	@RequestMapping( value="/evt/{id}", method = RequestMethod.GET)
	public @ResponseBody List<Photos> getPhotosByEvt(@PathVariable String id)  {
		System.out.println("getPhotosByEvt");
		List<Photos> findPhotosByEvenement = repositoryPhotos.findPhotosByEvenement(id);
		return findPhotosByEvenement;
	}
	
	@RequestMapping( value="/dir/{chemin}", method = RequestMethod.GET)
	public @ResponseBody List<Photos> getPhotosByDirectory(@PathVariable String chemin)  {
		System.out.println("getPhotosByDirectory");
		chemin=new String(Base64.getDecoder().decode(chemin));
		List<Photos> findPhotosByEvenement = repositoryPhotos.findPhotosByDirectory(chemin);
		return findPhotosByEvenement;
	}
	
	@RequestMapping( value="/fav/{id}", method = RequestMethod.POST)
	public @ResponseBody List<Photos> setFavorite(@PathVariable String id)  {
		System.out.println("setFavorite");
		repositoryPhotos.updateFavoritePhotos(id);
		return null;
	}
	
	@RequestMapping( value="/dir/list/{chemin}", method = RequestMethod.GET)
	public @ResponseBody List<String> getPhotosFilePathByDirectory(@PathVariable String chemin)  {
		System.out.println("getPhotosFilePathByDirectory");
		
		RestTemplate restTemplate = new RestTemplate();
		String call=urlWSFichiers+"list/repo/"+chemin;
		System.out.println(call);
		ResponseEntity<String> response = restTemplate.getForEntity(call, String.class);
		Gson gson = new Gson();
		List<String> fromJson = gson.fromJson(response.getBody(), List.class);
		return fromJson;
	}
	
	
	@RequestMapping(value = "/binary/{id}", method = RequestMethod.GET,produces = MediaType.IMAGE_JPEG_VALUE)

	public ResponseEntity<byte[]> getImageAsResponseEntity(@PathVariable String id, HttpServletRequest request)
			throws IOException {

		Photos findPhotosById = repositoryPhotos.findPhotosById(id);
		if (findPhotosById == null) {
			return null;
		}
		RestTemplate restTemplate = new RestTemplate();
		Encoder encoder = Base64.getEncoder();
		String chemin = encoder.encodeToString(findPhotosById.chemin.getBytes());
		String ip = encoder.encodeToString(request.getRemoteAddr().getBytes());
		String call = urlWSFichiers+"binaire/" + ip + "/" + chemin;
		System.out.println(call);
		ResponseEntity<byte[]> response = restTemplate.getForEntity(call, byte[].class);
		HttpHeaders headers = new HttpHeaders();
		headers.setCacheControl(CacheControl.maxAge(1, TimeUnit.DAYS));
		ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(response.getBody(), headers, HttpStatus.OK);
		return responseEntity;


	}
	
	@RequestMapping(value = "/binary/path/{chemin}", method = RequestMethod.GET,produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> getImageByPath(@PathVariable String chemin, HttpServletRequest request)
			throws IOException {

		Encoder encoder = Base64.getEncoder();
		RestTemplate restTemplate = new RestTemplate();
		String ip = encoder.encodeToString(request.getRemoteAddr().getBytes());
		String call = urlWSFichiers+"binaire/" + ip + "/" + chemin;
		System.out.println(call);
		try {
			ResponseEntity<byte[]> response = restTemplate.getForEntity(call, byte[].class);
			HttpHeaders headers = new HttpHeaders();
			headers.setCacheControl(CacheControl.maxAge(1, TimeUnit.DAYS));
			ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(response.getBody(), headers, HttpStatus.OK);
			return responseEntity;
		}catch(HttpClientErrorException he) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(he.getResponseBodyAsByteArray(), headers, he.getStatusCode());
			return responseEntity;
		}
		
		
		
		
			
		
		

}
	
	
	@RequestMapping( value="/date/fav/{datedebut}/{datefin}", method = RequestMethod.GET)
	public @ResponseBody List<Photos> getPhotosByDateFav(@PathVariable String datedebut,@PathVariable String datefin) throws ParseException {
		System.out.println("dateDebut:"+datedebut+", dateFin:"+datefin);
		datedebut = new String(Base64.getDecoder().decode(datedebut));
		datefin = new String(Base64.getDecoder().decode(datefin));
		System.out.println("dateDebut:"+datedebut+", dateFin:"+datefin);
		Date debut=sdf.parse(datedebut);
		Date fin=sdf.parse(datefin);
		List<Photos> findPhotosBetweenTwoDates = repositoryPhotos.findFavoritesPhotosBetweenTwoDates(debut, fin);
		return findPhotosBetweenTwoDates;

	}
	
	@RequestMapping( value="/fav/delete/{id}", method = RequestMethod.POST)
	public @ResponseBody Photos deleteFavorite(@PathVariable String id)  {
		System.out.println("deleteFavorite");
		Photos deleteFavorite = repositoryPhotos.deleteFavorite(id);
		return deleteFavorite;
	}
	
	
	

}
