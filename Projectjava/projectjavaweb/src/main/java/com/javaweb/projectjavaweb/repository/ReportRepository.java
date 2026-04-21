package com.javaweb.projectjavaweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javaweb.projectjavaweb.model.ReplyReport;
import com.javaweb.projectjavaweb.model.Report;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
public class ReportRepository {
	
	
		
		@PersistenceContext
		private EntityManager em; // ใชเ้รยี กเมธอดจัดการฐานขอ้มลู ที่สร ้างมาให ้แล ้ว
		
		public List<Report> showAll() {
			Query q = em.createQuery("from Report");
			return q.getResultList();
		}
		
		@Transactional
		public Report insertData(Report re) { //Bfnคือคลาส
			em.persist(re); //คำสั่งอินเสิร์จ
			return re;
			
		}
		
		public List<ReplyReport> showAll1() {
			Query q = em.createQuery("from ReplyReport");
			return q.getResultList();
		}
		
		private List<ReplyReport> replies;

	    public List<ReplyReport> getReplies() {
	        return replies;
	    }
		
		public void setReplies(List<ReplyReport> replies) {
	        this.replies = replies;
	    }
		
		@Transactional
	    public ReplyReport insertReply(ReplyReport rr, Integer reportId) {
	        // ค้นหารายงานที่ต้องการตอบกลับจาก reportId
			ReplyReport reply = em.find(ReplyReport.class, reportId);
	        // เชื่อมต่อ relation ระหว่าง ReplyReport และ Report
	        rr.setReplyReport(reply);
	        // บันทึกข้อมูลการตอบกลับ
	        em.persist(rr);
	        return rr;
	    }
		
		public Report findById(Long id) {
            try {
                // ใช้ EntityManager เพื่อค้นหาข้อมูลข่าวโดยใช้ ID
                return em.find(Report.class, id);
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
		        Report report = em.find(Report.class, id);
		        // หากข่าวไม่เท่ากับ null ให้ลบข้อมูล
		        if (report != null) {
		            em.remove(report);
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		        // หากเกิดข้อผิดพลาดในการลบข้อมูลให้ส่ง Exception กลับไป
		        throw new RuntimeException("Failed to delete news with id: " + id);
		    }
		}

		
		public ReplyReport findById1(Long id) {
	        return em.find(ReplyReport.class, id);
	    }
		
		
//		public List<Report> findByTitleContaining(String keyword) { //ค้นหา
//		    Query q = em.createQuery("SELECT r FROM Report r WHERE r.title LIKE :keyword");
//		    q.setParameter("keyword", "%" + keyword + "%");
//		    return q.getResultList();
//		}

		
		
		
//		@Transactional
//	    public void deleteData(Integer id) {
//	        Report report = em.find(Report.class, id);
//	        if (report != null) {
//	            em.remove(report);
//	        } else {
//	            throw new IllegalArgumentException("Report with ID " + id + " not found.");
//	        }
//	    }

//		@Transactional
//		public Report update(Integer id, Integer newLove) {
//			Report forumToUpdate = em.find(Report.class, id);
//		    if (forumToUpdate != null) {
//		        if (newLove != null) {
//		            forumToUpdate.setLove(newLove);
//		        }
//		        em.merge(forumToUpdate);
//		    }
//		    return forumToUpdate;
//		}
		


	}
