package com.javaweb.projectjavaweb.repository;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.stereotype.Repository;

import com.javaweb.projectjavaweb.model.Blog;
import com.javaweb.projectjavaweb.model.News;
import com.javaweb.projectjavaweb.model.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;


@Repository
public class BlogRepository {
    
    @PersistenceContext
    private EntityManager em;
    
    public List<Blog> showAll() {
        Query q = em.createQuery("from Blog");
        return q.getResultList();
    }
    
    
    public List<Blog> showEditBlog() {
        Query q = em.createQuery("from Blog");
        return q.getResultList();
    }
    
    @Transactional
    public Blog insertData(Blog blog) {
        em.persist(blog);
        return blog;
    }
    
    @Transactional
    public boolean save(Blog blog) {
        try {
            em.persist(blog);
            return true; // บันทึกสำเร็จ
        } catch (Exception e) {
            e.printStackTrace();
            return false; // ไม่สามารถบันทึกได้
        }
    }
    
    @Transactional
    public Blog findeditById(Long id) {
        return em.find(Blog.class, id);
    }

    @Transactional
    public void saveedit(Blog blog) {
    	em.merge(blog);
    }
    
    @Transactional
    public Blog findById(Long id) {
        try {
            // ใช้ EntityManager เพื่อค้นหาข้อมูลข่าวโดยใช้ ID
            return em.find(Blog.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null; // หากเกิดข้อผิดพลาดในการค้นหาให้ส่งค่า null กลับไป
        }
    }
     
    // เมธอดใหม่เพื่อดึงประเภท (typeBlog) จากฐานข้อมูลตาม id
    public String getTypeBlogById(Long id) {
        Blog blog = findById(id); // เรียกใช้เมธอด findById เพื่อค้นหาบล็อกที่มี ID ตรงกับที่ระบุ
        if (blog != null) { // ถ้าพบบล็อกที่ตรงกับ ID ที่ระบุ
            return blog.getType(); // คืนค่าประเภทของบล็อกนั้น
        }
        return null; // หรือคืนค่าเริ่มต้นที่กำหนดไว้หากไม่พบบล็อกที่ตรงกับ ID ที่ระบุ
    }

    
  //ลบข้อมูล
    @Transactional
    public void deleteById(Long id) {
        try {
            // ค้นหาข้อมูลข่าวโดยใช้ EntityManager
        	Blog blog = em.find(Blog.class, id);
            // หากข่าวไม่เท่ากับ null ให้ลบข้อมูล
            if (blog != null) {
                em.remove(blog);
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
        	Blog blog = em.find(Blog.class, id);
            // หากข่าวไม่เท่ากับ null ให้เซ็ตข้อมูลรูปภาพให้เป็น null
            if (blog != null) {
            	blog.setImageData(null); // เซ็ตข้อมูลรูปภาพให้เป็น null
                em.merge(blog); // บันทึกการเปลี่ยนแปลง
            }
        } catch (Exception e) {
            e.printStackTrace();
            // หากเกิดข้อผิดพลาดในการลบรูปภาพให้ส่ง Exception กลับไป
            throw new RuntimeException("Failed to delete image for news with id: " + id);
        }
    }
    
    
   // เมธอดสำหรับดึงข้อมูลบล็อกที่มีประเภทเป็น "ทะเล"
    public List<Blog> findByType(String type) {
        // สร้างคำสั่ง Query สำหรับค้นหาบล็อกที่มีประเภทตามที่ระบุ
        Query query = em.createQuery("SELECT b FROM Blog b WHERE b.type = :type");
        // กำหนดพารามิเตอร์ของคำสั่ง Query โดยระบุประเภทของบล็อกที่ต้องการค้นหา
        query.setParameter("type", type);
        // คืนค่ารายการบล็อกที่ตรงกับเงื่อนไขการค้นหา
        return query.getResultList();
    }

    
	@Transactional
	public Blog findById(Integer id) {
	    return em.find(Blog.class, id);
	}

	@Transactional
	public void update(Blog blog) {
	    em.merge(blog);
	}
	
	
	// popular
	public List<Blog> findTop4ByOrderByViewCountDesc() {
	    return em.createQuery("FROM Blog b ORDER BY b.viewCount DESC", Blog.class)
	    		.setMaxResults(4)
	    		.getResultList();
	}
	
	@Transactional //ค้นหา
    public List<Blog> findByTitle(String keyword) {
		// สร้างคำสั่ง Query ในภาษา JPQL เพื่อค้นหาบล็อกที่มีชื่อ (title) ประกอบด้วยคำค้นหาที่ระบุ
        Query q = em.createQuery("SELECT b FROM Blog b WHERE b.title LIKE :keyword");
     // กำหนดค่าพารามิเตอร์ให้กับคำสั่ง Query เพื่อค้นหาบล็อกที่มีชื่อใกล้เคียงกับคำค้นหาที่ระบุ % ในการแทนตัวอักษรใด ๆ ที่อาจจะเป็นไปได้
        q.setParameter("keyword", "%" + keyword + "%");
        return q.getResultList();
    } 
	
	
}


