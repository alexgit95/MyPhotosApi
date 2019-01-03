package main.java.com.alex.api.apiPhoto.controller;

import main.java.com.alex.api.apiPhoto.model.Shop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/api/photos/test")
public class TestController {
	@RequestMapping( value="t2", method = RequestMethod.GET)
	public @ResponseBody Shop getShopInJSON() {

		Shop shop = new Shop();
		shop.setName("popo");
		shop.setStaffName("coucou");
		
		return shop;

	}
	
	@RequestMapping( value="t1/{name}", method = RequestMethod.GET)
	public @ResponseBody Shop getShopIn(@PathVariable String name) {

		Shop shop = new Shop();
		shop.setName(name);
		shop.setStaffName("coucou");
		
		return shop;

	}
	
	

}
