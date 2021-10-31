package com.karenhoffman.api.entity;


import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "movies")
public class Movie {

	@Id @GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String id;
	@OneToOne
	private Image img;
	private MultipartFile file;
	private String title;
	@Temporal(TemporalType.DATE)
	private Date creation;
	private Integer qualification;
	@ManyToOne
	private Genre genre;
	@ManyToMany(mappedBy = "movies")
	private List<DisneyCharacter> characters;
	
	
	public Movie() {
		
	}
	
	
	
	public Movie(String id, String title, Date creation, Integer qualification, List<DisneyCharacter> characters) {
		super();
		this.id = id;
		this.title = title;
		this.creation = creation;
		this.qualification = qualification;
		this.characters = characters;
	}

	

	public MultipartFile getFile() {
		return file;
	}



	public void setFile(MultipartFile file) {
		this.file = file;
	}



	public Genre getGenre() {
		return genre;
	}



	public void setGenre(Genre genre) {
		this.genre = genre;
	}



	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public Image getImg() {
		return img;
	}
	public void setImg(Image img) {
		this.img = img;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getCreation() {
		return creation;
	}
	public void setCreation(Date creation) {
		this.creation = creation;
	}
	public Integer getQualification() {
		return qualification;
	}
	public void setQualification(Integer qualification) {
		this.qualification = qualification;
	}
	public List<DisneyCharacter> getCharacters() {
		return characters;
	}
	public void setCharacters(List<DisneyCharacter> characters) {
		this.characters = characters;
	}
	
	
	
}
