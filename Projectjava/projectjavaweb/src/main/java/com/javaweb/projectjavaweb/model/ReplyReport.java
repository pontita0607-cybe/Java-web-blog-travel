package com.javaweb.projectjavaweb.model;

import java.util.Date;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ReplyReport {

				@Id
				@GeneratedValue(strategy = GenerationType.IDENTITY)
				private Integer id;
				private String message;
				private Date time;
				
				public Date getTime() {
					return time;
				}
				public void setTime(Date time) {
					this.time = time;
				}
				public Integer getId() {
					return id;
				}
				public void setId(Integer id) {
					this.id = id;
				}
				public String getMessage() {
					return message;
				}
				public void setMessage(String message) {
					this.message = message;
				}
				public void setReplyReport(ReplyReport reply) {
					// TODO Auto-generated method stub
					
				}
				

	}