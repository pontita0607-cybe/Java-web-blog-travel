package com.javaweb.projectjavaweb.repository;

import org.springframework.stereotype.Repository;

import com.javaweb.projectjavaweb.model.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class RegisterRepository {
	
	@PersistenceContext
	private EntityManager em; 
	
	@Transactional
	public boolean insertData(User userregister) {
	    try {
	        em.persist(userregister);
	        return true; // สำเร็จ
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false; // ไม่สำเร็จ
	    }
	}
	
	// ตรวจสอบว่ามีอีเมลที่ระบุอยู่ในฐานข้อมูลหรือไม่
	public boolean existsByEmail(String email) {
        String jpql = "SELECT COUNT(r) FROM User r WHERE r.email = :email";
        Long count = em.createQuery(jpql, Long.class)
                        .setParameter("email", email)
                        .getSingleResult();
        return count > 0;
    }
	
	

}
