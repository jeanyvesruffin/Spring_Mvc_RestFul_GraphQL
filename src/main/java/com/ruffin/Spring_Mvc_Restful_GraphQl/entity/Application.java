package com.ruffin.Spring_Mvc_Restful_GraphQl.entity;

import javax.persistence.*;

/**
 * Classe representant l'Entité == application
 * @author admin
 * 
 */


@Entity
public class Application {

	// id est la cle primaire
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "application_id")
	private Integer id;

	@Column(name = "app_name", nullable = false)
	private String name;

	@Column(length = 2000)
	private String description;

	private String owner;

	public Application() {
		super();
	}


	public Application(String name, String description, String owner) {
		super();
		this.name = name;
		this.description = description;
		this.owner = owner;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
	@Override
	public String toString() {
		return "Application{" +
				"id=" + id +
				", name='" + name + '\'' +
				", owner=" + owner +
				", description='" + description + '\'' +
				'}';
	}
}
