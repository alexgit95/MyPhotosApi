package main.java.com.alex.api.apiPhoto.controller;

import java.text.ParseException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import main.java.com.alex.api.apiPhoto.model.Repertoire;
import main.java.com.alex.api.apiPhoto.services.RepertoiresRepositoryCustom;

@Controller
@CrossOrigin
@RequestMapping("/api/v1/repos")
public class RepertoiresController {
	@Autowired
	private RepertoiresRepositoryCustom repositoryRepertoires;
	
	@RequestMapping( value="/racine", method = RequestMethod.GET)
	public @ResponseBody List<Repertoire> getReposRacine() throws ParseException {
		
		return repositoryRepertoires.getAllRepertoireRacine();

	}
	
	@RequestMapping( value="/directory/{cheminParent}", method = RequestMethod.GET)
	public @ResponseBody List<Repertoire> getSousRepo(@PathVariable String cheminParent) throws ParseException {
		cheminParent=new String(Base64.getDecoder().decode(cheminParent));
		return repositoryRepertoires.getAllSousRepertoire(cheminParent);

	}

}
