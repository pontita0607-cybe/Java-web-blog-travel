package com.javaweb.projectjavaweb.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaweb.projectjavaweb.model.News;
import com.javaweb.projectjavaweb.model.Report;
import com.javaweb.projectjavaweb.model.User;
import com.javaweb.projectjavaweb.repository.ReportRepository;

import jakarta.servlet.http.HttpSession;



@Controller
public class ReportController {
	
		@Autowired
		private ReportRepository repo;
		
		@GetMapping("/report")
		public String report(Model model, HttpSession session) {
			if (session.getAttribute("user") != null) {
		         User user = (User) session.getAttribute("user");
		         model.addAttribute("name", user.getName());
		         model.addAttribute("email", user.getEmail());
		         
			    return "report";
			} else {
				 
			     return "report";
		     }
		}
	
		
		@GetMapping("/showReport")
		public String show(Model model, HttpSession session) {
			 if (session.getAttribute("user") != null) {
		            // หากมีการเข้าสู่ระบบแล้ว
		            User adminUser = (User) session.getAttribute("user");
		            model.addAttribute("name", adminUser.getName());
		            model.addAttribute("email", adminUser.getEmail());
		    List<Report> reportList = repo.showAll();
		    //List<ReplyReport> replyReports = repo.showAll1(); // ดึงข้อมูลตอบกลับ
		    model.addAttribute("reportList", reportList);
		   // model.addAttribute("replyReports", replyReports); // เพิ่ม replyReports ใน model
		    return "showreport";
	
		        } else {
		            // หากยังไม่ได้เข้าสู่ระบบ
		            return "redirect:/login"; // ให้เปลี่ยนเส้นทางไปยังหน้า login
		        }
		    }
		
		
		


		@GetMapping("/addReport") //เพิ่มข้อมูลของการReport
	    public String add(String title, String detail) {
			Report report = new Report();
			report.setTitle(title);
			report.setDetail(detail);
			report.setPort_date(new Date()); // Set current date
	        repo.insertData(report);
	        System.out.println("Insert Successful");
	        return "redirect:/report"; // Redirect back to the forum page
	    }
		
		
		
	
		 @GetMapping("/replyReport/{id}")
		    public String readMore(@PathVariable Long id, Model model) {
		        // ดึงข้อมูลรายงานจากฐานข้อมูลโดยใช้ ID
		        Report report = repo.findById(id);
		        model.addAttribute("report", report);
		        return "replyReport"; // แสดงเนื้อหารายงานในหน้า JSP ที่ชื่อ replyReport.jsp
		    }
		 
		 
		//ลบข้อมูล
		 @GetMapping("/deleteReport/{id}")
		 public String deleteReport(@PathVariable Long id) {
		     // เรียกใช้งานเมธอด deleteById ของ ReportRepository
		     repo.deleteById(id);
		     // ส่ง redirect ไปยังหน้าหลักข่าวหลังจากที่ลบข้อมูลเสร็จสมบูรณ์
		     return "redirect:/showReport";
		 }
		 
//		 @PostMapping("/replyReport")
//		 public String addReply(@RequestParam("replyContent") String message, @RequestParam("reportId") Long reportId) {
//		     // สร้าง ReplyReport object
//		     ReplyReport reply = new ReplyReport();
//		     reply.setMessage(message);
//		     reply.setTime(new Date());
//
//		     // เรียกใช้ method insertReply จาก repo โดยส่งข้อมูล reply และ reportId
//		     repo.insertReply(reply, reportId);
//
//		     // Redirect ไปยังหน้าที่แสดงรายงาน
//		     return "redirect:/showReport";
//		 }


		
//		@GetMapping("/searchReport")//ค้นหา
//		public String search(@RequestParam String keyword, Model model) {
//		    List<Report> searchList = repo.findByTitleContaining(keyword);
//		    model.addAttribute("searchResult", searchList);
//		    return "showSearch"; // ส่งค่า searchResult ไปยังหน้า showSearch.jsp
//		}


//		@GetMapping("/deleteReport")
//		public String deleteReport(@RequestParam Integer id) {
//		    try {
//		        repo.deleteData(id);
//		        return "redirect:/report1";
//		    } catch (Exception e) {
//		        return "Failed to delete report with ID " + id + ". Error: " + e.getMessage();
//		    }
//		}

}
