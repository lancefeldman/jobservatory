/**
 *  Pojo for User name and password information 
 */
package com.cincycomp.vetestproject.gui;

//import javax.persistence.*;
//import org.hibernate.cfg.*;
//import org.hibernate.tool.hbm2ddl.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
// import org.hibernate.Session;
// import org.hibernate.SessionFactory;
// import org.hibernate.cfg.AnnotationConfiguration;
// import java.util.Scanner;

/**
 * @author Lance Feldman - trivial change
 *
 */
@Entity
public class User {

	@Id
	@GeneratedValue
	private Long userId;

	private String password;
	
	private String firstName;
	
	private String lastName;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getUserId(){
	return userId;
	}

	public void setUserId(Long id){
	this.userId = id;
	}

	public String getPassword(){
	return password;
	}

	public void setPassword(String password){
	this.password = password;
	}
}
