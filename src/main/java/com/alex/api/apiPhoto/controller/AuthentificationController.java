package main.java.com.alex.api.apiPhoto.controller;

import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Controller
@CrossOrigin
@RequestMapping("/api/v1/auth")
public class AuthentificationController {
	
	@Value("${localisation.service.binaire}")
	private String urlWSFichiers;
	
	@RequestMapping( value="/isAuth", method = RequestMethod.GET)
	public ResponseEntity<String> isAuthorized(HttpServletRequest request){
		String ip = request.getRemoteAddr();
		System.out.println("isAuthorized "+ip);
		
		RestTemplate restTemplate = new RestTemplate();
		Encoder encoder = Base64.getEncoder();
		String ipb64 = encoder.encodeToString(ip.getBytes());
		String call = urlWSFichiers+"auth/" + ipb64 ;
		System.out.println(call);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setCacheControl(CacheControl.maxAge(1, TimeUnit.SECONDS));
		
		try {
			ResponseEntity<String> response = restTemplate.getForEntity(call, String.class);
			ResponseEntity<String> responseEntity = new ResponseEntity<>(response.getBody(), headers, HttpStatus.OK);
			return responseEntity;
		}catch(HttpClientErrorException he) {
			ResponseEntity<String> responseEntity = new ResponseEntity<>(he.getResponseBodyAsString(), headers, he.getStatusCode());
			return responseEntity;
		}
		
		
		
	}

}
