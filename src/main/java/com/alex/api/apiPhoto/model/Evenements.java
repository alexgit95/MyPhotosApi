package main.java.com.alex.api.apiPhoto.model;

import java.util.Date;

import org.springframework.data.annotation.Id;

public class Evenements implements Comparable<Evenements>{
	@Id
	public String id;
	public String nom;
	public Date debut;
	public Date fin;
	public boolean valid;
	

	public Evenements() {
		super();
	}


	@Override
	public String toString() {
		return "Evenement [id=" + id + ", nom=" + nom + ", debut=" + debut + ", fin=" + fin + "]";
	}
	@Override
	public int compareTo(Evenements o) {
		return debut.compareTo(o.debut);
	}
	
	
	

}
