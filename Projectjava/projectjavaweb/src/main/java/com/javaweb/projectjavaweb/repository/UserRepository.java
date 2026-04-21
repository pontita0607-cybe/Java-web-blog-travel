package com.javaweb.projectjavaweb.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import com.javaweb.projectjavaweb.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    //การค้นหาผู้ใช้ (User) จากฐานข้อมูลด้วยอีเมล (email)
    public User findByEmail(String email) {
        String jpql = "SELECT u FROM User u WHERE u.email = :email";
        Query query = entityManager.createQuery(jpql, User.class);
        query.setParameter("email", email);
        return (User) query.getSingleResult();
    }

    //การตรวจสอบว่ามีผู้ใช้ (User) ที่มีอีเมลที่ระบุหรือไม่ 
    public boolean existsByEmail(String email) {
        String jpql = "SELECT COUNT(u) FROM User u WHERE u.email = :email";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("email", email);
        Long count = (Long) query.getSingleResult();
        return count > 0;
        //คืนค่าเป็น true ถ้าจำนวนผู้ใช้ที่มีอีเมลที่ตรงกับค่าที่ระบุมากกว่า 0 ซึ่งหมายความว่ามีอีเมลนี้ในฐานข้อมูลแล้ว
        //คืนค่าเป็น false ถ้าจำนวนผู้ใช้ที่มีอีเมลที่ตรงกับค่าที่ระบุเท่ากับ 0 ซึ่งหมายความว่าไม่มีอีเมลนี้ในฐานข้อมูล
    }

    //บันทึกลงฐานข้อมูล User
    @Transactional
    public void save(User user) {
        entityManager.persist(user);
    }
    
    // เพิ่มเมธอด findById(Long id) ที่ดึงข้อมูลผู้ใช้จาก ID
    public Optional<User> findById(Long id) {
        User user = entityManager.find(User.class, id);
        return Optional.ofNullable(user);
    }
}
