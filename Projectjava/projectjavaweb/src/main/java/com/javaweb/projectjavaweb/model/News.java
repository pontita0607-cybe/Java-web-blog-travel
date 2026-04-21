package com.javaweb.projectjavaweb.model;

import java.sql.Blob;
import java.util.Base64;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;


@Entity
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    @Lob
    private Blob imageData;
    private String detail;
    private Date post_date;
    private String writer;
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
	public Blob getImageData() {
		return imageData;
	}
	public void setImageData(Blob imageData) {
		this.imageData = imageData;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public Date getPost_date() {
		return post_date;
	}
	public void setPost_date(Date post_date) {
		this.post_date = post_date;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	 // เมธอดสำหรับการแปลงข้อมูลรูปภาพใน Blob เป็น Base64
    public String getImageDataAsBase64() {
        if (imageData != null) {
            try {
                byte[] bytes = imageData.getBytes(1, (int) imageData.length());
                return Base64.getEncoder().encodeToString(bytes);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    

    }


    // Getter and Setter methods for each field

    
}
