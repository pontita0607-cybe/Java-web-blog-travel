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
public class Blog {
	
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)

    private Integer id;
    private String title;
    @Lob
    private Blob imageData;
    private String detail;
    private String writer;
    private String type;
    private Date post_date;
    private Integer likeCount;

    private int viewCount;
   
    
    
    
    
    public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

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

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getPost_date() {
		return post_date;
	}

	public void setPost_date(Date post_date) {
		this.post_date = post_date;
	}
	
	 public Integer getLikeCount() {
	        return likeCount;
	 }

	 public void setLikeCount(Integer likeCount) {
	      this.likeCount = likeCount;
	}

	 public String getImageDataAsBase64() {
		    // ตรวจสอบว่ามีข้อมูลรูปภาพหรือไม่
		    if (imageData != null) {
		        try {
		            // อ่านข้อมูลรูปภาพเป็น byte array
		            byte[] bytes = imageData.getBytes(1, (int) imageData.length());
		            // แปลง byte array เป็นรหัส Base64
		            return Base64.getEncoder().encodeToString(bytes);
		        } catch (Exception e) {
		            // จัดการข้อผิดพลาดในการแปลงข้อมูล
		            e.printStackTrace();
		        }
		    }
		    // ถ้าไม่มีข้อมูลรูปภาพในฐานข้อมูล
		    return null;
		}
}
