package com.ruffin.Spring_Mvc_Restful_GraphQl.entity;

import javax.persistence.*;

/**
 * Classe representant l'Entité == release
 * @author admin
 *
 */

@Entity
public class Release {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String releaseDate;
	private String description;

	public Release() {
		super();
	}
	
	public Release(Integer id, String releaseDate, String description) {
		super();
		this.id = id;
		this.releaseDate = releaseDate;
		this.description = description;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
