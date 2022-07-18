package com.my.employeeproject.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="employee")
public class Employee {


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="firstname")
	private String firstName;
	
	@Column(name="lastname")
	private String lastName;
	
	@Column(name="email")
	private String email;
	
	
	
	// Blank constructors
	public Employee() {
	}
		
	
	public Employee(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;}
	

	// Getter Setter
	public int getId() {
		return id;}
	
	public void setId(int id) {
		this.id = id;}

	public String getFirstName() {
		return firstName;}

	public void setFirstName(String firstName) {
		this.firstName = firstName;}
	
	public String getLastName() {
		return lastName;}

	public void setLastName(String lastName) {
		this.lastName = lastName;}

	public String getEmail() {
		return email;}

	public void setEmail(String email) {
		this.email = email;}

	
	// To string
	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + 
				", lastName=" + lastName +
				", email=" + email + "]";}
	
	
	
}


