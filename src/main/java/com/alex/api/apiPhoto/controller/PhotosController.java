package main.java.com.alex.api.apiPhoto.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import main.java.com.alex.api.apiPhoto.model.Photos;
import main.java.com.alex.api.apiPhoto.services.PhotosRepositoryCustom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;

@Controller
@RequestMapping("/api/v1/photos")
public class PhotosController {
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
	
	@Autowired
	private PhotosRepositoryCustom repositoryPhotos;
	
	@RequestMapping( value="/date/{datedebut}/{datefin}", method = RequestMethod.GET)
	public @ResponseBody List<Photos> getPhotosByDate(@PathVariable String datedebut,@PathVariable String datefin) throws Base64DecodingException {
		System.out.println("dateDebut:"+datedebut+", dateFin:"+datefin);
		datedebut = new String(Base64.decode(datedebut));
		datefin = new String(Base64.decode(datefin));
		System.out.println("dateDebut:"+datedebut+", dateFin:"+datefin);
		return null;

	}

}
