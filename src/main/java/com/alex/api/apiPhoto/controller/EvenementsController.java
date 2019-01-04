package main.java.com.alex.api.apiPhoto.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import main.java.com.alex.api.apiPhoto.model.Evenements;
import main.java.com.alex.api.apiPhoto.model.Photos;
import main.java.com.alex.api.apiPhoto.services.EvenementsRepositoryCustom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@CrossOrigin
@RequestMapping("/api/v1/evenements")
public class EvenementsController {
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
	
	@Autowired
	private EvenementsRepositoryCustom repositoryEvenements;
	
	@RequestMapping( value="/validated/ok", method = RequestMethod.GET)
	public @ResponseBody List<Evenements> getAllValidated(){
		System.out.println("validated");
		List<Evenements> findAllValidatedEvents = repositoryEvenements.findAllValidatedEvents();
		return findAllValidatedEvents;
	}
	
	@RequestMapping( value="/validated/no", method = RequestMethod.GET)
	public @ResponseBody List<Evenements> getAllNoValidated(){
		System.out.println("no validated");
		List<Evenements> getAllNoValidated = repositoryEvenements.findAllNoValidatedEvents();
		return getAllNoValidated;
	}
	
	@RequestMapping( value="/date/{datedebut}/{datefin}", method = RequestMethod.GET)
	public @ResponseBody List<Evenements> getEvenementsByDate(@PathVariable String datedebut,@PathVariable String datefin) throws  ParseException {
		System.out.println("dateDebut:"+datedebut+", dateFin:"+datefin);
		datedebut = new String(Base64.getDecoder().decode(datedebut));
		datefin = new String(Base64.getDecoder().decode(datefin));
		System.out.println("dateDebut:"+datedebut+", dateFin:"+datefin);
		Date debut=sdf.parse(datedebut);
		Date fin=sdf.parse(datefin);
		List<Evenements> findPhotosBetweenTwoDates = repositoryEvenements.findAllValidatedEventsBetweenTwoDates(debut, fin);
		return findPhotosBetweenTwoDates;

	}
	
	@RequestMapping( value="/validated/no/one", method = RequestMethod.GET)
	public @ResponseBody Evenements getOneNoValidated(){
		System.out.println("getOneNoValidated");
		return repositoryEvenements.findOneNoValidatedEvents();
		
	}
	
	@RequestMapping( value="/edit/nom/{id}/{name}", method = RequestMethod.POST)
	public @ResponseBody List<Photos> setNom(@PathVariable String id,@PathVariable String name)   {
		System.out.println("setNom");
		name = new String(Base64.getDecoder().decode(name));
		repositoryEvenements.updateEventsTitle(name, id);
		return null;
	}

}
