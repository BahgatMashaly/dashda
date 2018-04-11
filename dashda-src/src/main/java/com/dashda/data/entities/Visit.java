package com.dashda.data.entities;
// Generated Apr 4, 2018 2:50:44 PM by Hibernate Tools 5.2.8.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Visit generated by hbm2java
 */
@Entity
@Table(name = "VISIT")
public class Visit implements java.io.Serializable {

	private int id;
	private Doctor doctor;
	private Employee employeeByEmployeeId;
	private Employee employeeBySubordinateId;
	private Date datetime;
	private Byte completed;

	public Visit() {
	}

	public Visit(int id) {
		this.id = id;
	}

	public Visit(int id, Doctor doctor, Employee employeeByEmployeeId, Employee employeeBySubordinateId, Date datetime,
			Byte completed) {
		this.id = id;
		this.doctor = doctor;
		this.employeeByEmployeeId = employeeByEmployeeId;
		this.employeeBySubordinateId = employeeBySubordinateId;
		this.datetime = datetime;
		this.completed = completed;
	}

	@Id

	@Column(name = "ID", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DOCUTOR_ID")
	public Doctor getDoctor() {
		return this.doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMPLOYEE_ID")
	public Employee getEmployeeByEmployeeId() {
		return this.employeeByEmployeeId;
	}

	public void setEmployeeByEmployeeId(Employee employeeByEmployeeId) {
		this.employeeByEmployeeId = employeeByEmployeeId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SUBORDINATE_ID")
	public Employee getEmployeeBySubordinateId() {
		return this.employeeBySubordinateId;
	}

	public void setEmployeeBySubordinateId(Employee employeeBySubordinateId) {
		this.employeeBySubordinateId = employeeBySubordinateId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATETIME", length = 19)
	public Date getDatetime() {
		return this.datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	@Column(name = "COMPLETED")
	public Byte getCompleted() {
		return this.completed;
	}

	public void setCompleted(Byte completed) {
		this.completed = completed;
	}

}