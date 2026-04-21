package com.javaweb.projectjavaweb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import com.javaweb.projectjavaweb.model.Bookmark;
import com.javaweb.projectjavaweb.model.User;
import com.javaweb.projectjavaweb.model.Blog;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

@Repository
public class BookmarkRepository {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Transactional
    public void save(Bookmark bookmark) {
        entityManager.persist(bookmark);
    }
    
    @Transactional
    public boolean existsByUserAndBlog(User user, Blog blog) {
         String jpql = "SELECT COUNT(b) FROM Bookmark b WHERE b.user = :user AND b.blog = :blog";
         Long count = entityManager.createQuery(jpql, Long.class)
           .setParameter("user", user)
           .setParameter("blog", blog)
           .getSingleResult();
         return count > 0; // count มีค่ามากกว่า 0 แสดงว่ามีการ bookmark บล็อกนี้โดยผู้ใช้นั้นแล้ว
         //count เท่ากับหรือน้อยกว่า 0 แสดงว่ายังไม่มีการ bookmark ซึ่งจะคืนค่า
    }
    
    @Transactional
    public List<Bookmark> findByUser(User user) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Bookmark> criteriaQuery = criteriaBuilder.createQuery(Bookmark.class);
        Root<Bookmark> root = criteriaQuery.from(Bookmark.class);
        
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("user"), user));
        
        TypedQuery<Bookmark> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }
    
    
    @Transactional
    public Optional<Bookmark> findById(Long id) { // เพิ่มเมธอด findById()
        return Optional.ofNullable(entityManager.find(Bookmark.class, id));
    }
    
    @Transactional
    public void delete(Bookmark bookmark) { // เพิ่มเมธอด delete()
        entityManager.remove(bookmark);
    }
}

