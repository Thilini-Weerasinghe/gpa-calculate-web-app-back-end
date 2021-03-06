package com.gpastm.gpa.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Faculty implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String id= UUID.randomUUID().toString();
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ai;
	

	private String name; 
	
	   // --- that edit is not property in the database table. but it not gives a error
		@Transient
		public boolean edit;

}
