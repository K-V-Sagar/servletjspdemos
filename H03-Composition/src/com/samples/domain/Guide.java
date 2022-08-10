package com.samples.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Guide {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "staff_id", nullable = false)
	private String staffId;
	
	@Column
	private String name;
	
	@Column
	private Integer salary;
	
	public Guide() {}
	
	public Guide(String staffId, String name, Integer salary) {
		this.staffId = staffId;
		this.name = name;
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Guide [id=" + id + ", staffId=" + staffId + ", name=" + name + ", salary=" + salary + "]";
	}
	
}