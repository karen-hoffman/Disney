package com.karenhoffman.api.entity;



import java.util.List;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;



@Table(name = "characters")
@Entity
public class DisneyCharacter {
	
	@Id @GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String id;
	private String name;
	private Integer age;
	private Double weight;
	private String history;
	@JoinTable(
	        name = "character_movies",
	        joinColumns = @JoinColumn(name = "FK_CHARACTER", nullable = false),
	        inverseJoinColumns = @JoinColumn(name="FK_MOVIE", nullable = false)
	    )
	@ManyToMany
	private List<Movie> movies;
	
	
	//@OneToOne
	//private Image img;
	
	public DisneyCharacter() {
		
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	/*public Image getImg() {
		return img;
	}
	public void setImg(Image img) {
		this.img = img;
	}*/
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public String getHistory() {
		return history;
	}
	public void setHistory(String history) {
		this.history = history;
	}
	public List<Movie> getMovies() {
		return movies;
	}
	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}
	
	

}
