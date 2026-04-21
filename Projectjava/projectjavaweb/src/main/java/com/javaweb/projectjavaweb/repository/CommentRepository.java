package com.javaweb.projectjavaweb.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.javaweb.projectjavaweb.model.Comment;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Optional;

@Repository
public class CommentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    //ค้นหาความคิดเห็นโดยใช้ ID ของบล็อก
    @Transactional
    public List<Comment> findByBlogId(Long blogId) {
        // สร้างคำสั่ง SQL เพื่อค้นหาความคิดเห็นที่เกี่ยวข้องกับบล็อกที่มี ID ตรงกับค่าที่ระบุ
        Query q = entityManager.createQuery("SELECT c FROM Comment c WHERE c.blog.id = :blogId");
        // กำหนดค่าพารามิเตอร์ blogId เพื่อค้นหา
        q.setParameter("blogId", blogId);
        // คืนค่ารายการความคิดเห็นที่ได้จากการค้นหา
        return q.getResultList();
    }
    
    @Transactional
    // เพิ่มเมธอดเพื่อดึงชื่อของผู้ใช้ที่เขียน comment จากฐานข้อมูล
    public String findUserNameByComment(Comment comment) {
        return comment.getUser().getName();
    }

    @Transactional
    public void save(Comment comment) {
        entityManager.persist(comment);
    }

    @Transactional
    // เมธอดสำหรับค้นหาความคิดเห็นโดยใช้ ID ของความคิดเห็น
    public Optional<Comment> findById(Long id) {
     // ค้นหาความคิดเห็นจากฐานข้อมูลโดยใช้ EntityManager
     Comment comment = entityManager.find(Comment.class, id);
     // สร้าง Optional เพื่อรับค่าของ Comment ที่ค้นพบ หรือ null ถ้าไม่พบ
     return Optional.ofNullable(comment);
    }

    
    //เมธอดสำหรับลบความคิดเห็นจากฐานข้อมูล
    @Transactional
    public void delete(Comment comment) {
        entityManager.remove(comment);
    }
}
