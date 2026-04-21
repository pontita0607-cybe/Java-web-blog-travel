package com.javaweb.projectjavaweb.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Report {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer id;
		private String title;
		private String detail;
		private Date port_date;
		
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getDetail() {
			return detail;
		}
		public void setDetail(String detail) {
			this.detail = detail;
		}
		public Date getPort_date() {
			return port_date;
		}
		public void setPort_date(Date port_date) {
			this.port_date = port_date;
		}
		

}

