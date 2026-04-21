package com.javaweb.projectjavaweb.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.projectjavaweb.model.News;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
public class NewsRepository {
    
    @PersistenceContext
    private EntityManager em; 
    
    @Transactional
    public boolean save(News news) {
        try {
            em.persist(news);
            return true; // บันทึกสำเร็จ
        } catch (Exception e) {
            e.printStackTrace();
            return false; // ไม่สามารถบันทึกได้
        }
    }

    @Transactional
    public List<News> showAll() {
		Query q = em.createQuery("from News");
		return q.getResultList();
	}



        public News findById(Long id) {
            try {
                // ใช้ EntityManager เพื่อค้นหาข้อมูลข่าวโดยใช้ ID
                return em.find(News.class, id);
            } catch (Exception e) {
                e.printStackTrace();
                return null; // หากเกิดข้อผิดพลาดในการค้นหาให้ส่งค่า null กลับไป
            }
        }
        //ลบข้อมูล
        @Transactional
        public void deleteById(Long id) {
            try {
                // ค้นหาข้อมูลข่าวโดยใช้ EntityManager
                News news = em.find(News.class, id);
                // หากข่าวไม่เท่ากับ null ให้ลบข้อมูล
                if (news != null) {
                    em.remove(news);
                }
            } catch (Exception e) {
                e.printStackTrace();
                // หากเกิดข้อผิดพลาดในการลบข้อมูลให้ส่ง Exception กลับไป
                throw new RuntimeException("Failed to delete news with id: " + id);
            }
        }
        
     // ลบรูปภาพ
   
        @Transactional
        public void deleteImageById(Long id) {
            try {
                // ค้นหาข้อมูลข่าวโดยใช้ EntityManager
                News news = em.find(News.class, id);
                // หากข่าวไม่เท่ากับ null ให้เซ็ตข้อมูลรูปภาพให้เป็น null
                if (news != null) {
                    news.setImageData(null); // เซ็ตข้อมูลรูปภาพให้เป็น null
                    em.merge(news); // บันทึกการเปลี่ยนแปลง
                }
            } catch (Exception e) {
                e.printStackTrace();
                // หากเกิดข้อผิดพลาดในการลบรูปภาพให้ส่ง Exception กลับไป
                throw new RuntimeException("Failed to delete image for news with id: " + id);
            }
        }


        
        @Transactional
        public News findeditById(Long id) {
            return em.find(News.class, id);
        }

        @Transactional
        public void saveedit(News news) {
        	em.merge(news);
        }

        @Transactional //ค้นหา
        public List<News> findByTitleContaining(String keyword) {
            Query q = em.createQuery("SELECT n FROM News n WHERE n.title LIKE :keyword");
            q.setParameter("keyword", "%" + keyword + "%");
            return q.getResultList();
        } 
        
  }


