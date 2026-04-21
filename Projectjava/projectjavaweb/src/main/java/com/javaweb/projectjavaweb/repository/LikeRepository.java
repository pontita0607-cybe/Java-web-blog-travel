package com.javaweb.projectjavaweb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.javaweb.projectjavaweb.model.Blog;
import com.javaweb.projectjavaweb.model.Bookmark;
import com.javaweb.projectjavaweb.model.LikeCount;
import com.javaweb.projectjavaweb.model.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

@Repository
public class LikeRepository {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Transactional
    public void save(LikeCount likeCount) {
        entityManager.persist(likeCount);
    }
    
    @Transactional
    public boolean existsByUserAndBlog(User user, Blog blog) {
         String jpql = "SELECT COUNT(l) FROM LikeCount l WHERE l.user = :user AND l.blog = :blog";
         Long count = entityManager.createQuery(jpql, Long.class)
           .setParameter("user", user)
           .setParameter("blog", blog)
           .getSingleResult();
         return count > 0;
    }
    
    @Transactional
    public List<LikeCount> findByUser(User user) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<LikeCount> criteriaQuery = criteriaBuilder.createQuery(LikeCount.class);
        Root<LikeCount> root = criteriaQuery.from(LikeCount.class);
        
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("user"), user));
        
        TypedQuery<LikeCount> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }
    
    @Transactional
    public Optional<LikeCount> findById(Long id) { // เพิ่มเมธอด findById()
        return Optional.ofNullable(entityManager.find(LikeCount.class, id));
    }
    
    @Transactional
    public void deleteLike(LikeCount likeCount) { // เพิ่มเมธอด delete()
        entityManager.remove(likeCount);
    }
}
