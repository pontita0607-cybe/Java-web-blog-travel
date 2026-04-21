package com.javaweb.projectjavaweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javaweb.projectjavaweb.model.Blog;
import com.javaweb.projectjavaweb.model.News;
import com.javaweb.projectjavaweb.model.User;
import com.javaweb.projectjavaweb.repository.NewsRepository;
import com.javaweb.projectjavaweb.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.rowset.serial.SerialException;



//อื่น ๆ เช่น import statements

@Controller
public class NewsController {
 @Autowired
 private  NewsRepository  newsRepository ;
 @Autowired
 private UserRepository userRepository;
 
 

 
 @GetMapping("/shownews")
 public String showForm(Model model, HttpSession session) {
	 if (session.getAttribute("user") != null) {
         User user = (User) session.getAttribute("user");
         model.addAttribute("name", user.getName());
         model.addAttribute("email", user.getEmail());
         
	     List<News>newslist=newsRepository.showAll();
	     model.addAttribute("newslist",newslist );
         return "shownews";
	 } else {
		 List<News>newslist=newsRepository.showAll();
	     model.addAttribute("newslist",newslist );
	     return "shownews";
     }
 }
 
 
 //กดไปหนสำหรับลบแก้ไข
 @GetMapping("/showNewsEdit")
 public String showdeit(Model model,HttpSession session) {
	 
	        // เช็คว่ามีการเข้าสู่ระบบหรือไม่
	        if (session.getAttribute("user") != null) {
	            // หากมีการเข้าสู่ระบบแล้ว
	            User adminUser = (User) session.getAttribute("user");
	            model.addAttribute("name", adminUser.getName());
	            model.addAttribute("email", adminUser.getEmail());
	            List<News>newslist=newsRepository.showAll();
        		model.addAttribute("newslist",newslist );
	            return "newsformEdit"; // แสดงหน้า homeAdmin พร้อมข้อมูลผู้ใช้
	        } else {
	            // หากยังไม่ได้เข้าสู่ระบบ
	        	 return "redirect:/login"; // ให้เปลี่ยนเส้นทางไปยังหน้า login
	        }
	    }

 
 
 //เปิดอ่านreadmore
 @GetMapping("/news/{id}")
 public String readMore(@PathVariable Long id, Model model,HttpSession session) {
     // ดึงข้อมูลข่าวจากฐานข้อมูลโดยใช้ ID
	 if (session.getAttribute("user") != null) {
         User user = (User) session.getAttribute("user");
         model.addAttribute("name", user.getName());
         model.addAttribute("email", user.getEmail());
         News news = newsRepository.findById(id);
         model.addAttribute("news", news);
         
	    
         return "readmoreNews"; // แสดงเนื้อหาข่าวในหน้า JSP ที่ชื่อ read_more.jsp
	 } else {
		 News news = newsRepository.findById(id);
	     model.addAttribute("news", news);
	     return "readmoreNews"; // แสดงเนื้อหาข่าวในหน้า JSP ที่ชื่อ read_more.jsp
     }
    
 }
 
 
//ลบข้อมูล
 @GetMapping("/delete2/{id}")
 public String deleteNews(@PathVariable Long id) {
     // ลบข้อมูลข่าวจากฐานข้อมูลโดยใช้ ID
     newsRepository.deleteById(id);
     // ส่ง redirect ไปยังหน้าหลักข่าวหลังจากที่ลบข้อมูลเสร็จสมบูรณ์
     return "redirect:/showNewsEdit";
 }
//ลบรูปภาพ

@GetMapping("/deleteimage1/{id}")
public String deleteImage(@PathVariable Long id) {
  // ลบรูปภาพของข่าวจากฐานข้อมูลโดยใช้ ID
  newsRepository.deleteImageById(id);
  // ส่ง redirect ไปยังหน้าแก้ไขข่าวหลังจากที่ลบรูปภาพเสร็จสมบูรณ์
  return "redirect:/edit/{id}";
}


 //แก้ไขข้อมูล
 @GetMapping("/edit2/{id}")
 public String showEditForm(@PathVariable Long id, Model model) {
     News news = newsRepository.findById(id);
     model.addAttribute("news", news);
     return "editnews"; // แสดงหน้าแก้ไขข้อมูลข่าว edit_news.jsp
 }
 
 @PostMapping("/edit2/{id}")
 public String editNews(@PathVariable Long id, @RequestParam("title") String title,
                        @RequestParam("imageData") MultipartFile imageData,
                        @RequestParam("detail") String detail,
                        @RequestParam("writer") String writer,
                        RedirectAttributes redirectAttributes) throws IOException, SQLException {
     if (imageData.isEmpty()) {
         redirectAttributes.addFlashAttribute("message", "Please select a file to upload.");
         return "redirect:/edit2/{id}";
     }

     try {
         News news = newsRepository.findById(id);
         byte[] bytes = imageData.getBytes();
         Blob imageBlob = new javax.sql.rowset.serial.SerialBlob(bytes);
         
         // อัปเดตข้อมูลข่าวด้วยข้อมูลใหม่
         news.setTitle(title);
         news.setImageData(imageBlob);
         news.setDetail(detail);
         news.setWriter(writer);
         newsRepository.save(news);
         
         redirectAttributes.addFlashAttribute("message", "News updated successfully!");
     } catch (IOException e) {
         e.printStackTrace();
     }

     return "redirect:/showedit";
 }

 
//เพิ่มข้อมูล
 
 @GetMapping("/news")
 public String showForm() {
     return "fromnews"; // Assuming the register.jsp is located in your view directory
 }
 @PostMapping("/news")
 public String addNews(@RequestParam("title") String title,
                       @RequestParam("imageData") MultipartFile imageData,
                       @RequestParam("detail") String detail,
                       @RequestParam("writer") String writer,
                       RedirectAttributes redirectAttributes) throws SerialException, SQLException {
     if (imageData.isEmpty()) {
         redirectAttributes.addFlashAttribute("message", "Please select a file to upload.");
         return "redirect:/new";
     }

     try {
         // อ่านข้อมูลจากไฟล์รูปภาพ
         byte[] bytes = imageData.getBytes();
         
         // สร้างอ็อบเจ็กต์ News
         News news = new News();
         news.setTitle(title);
         Blob imageBlob = new javax.sql.rowset.serial.SerialBlob(bytes);
         news.setImageData(imageBlob); // กำหนดข้อมูลรูปภาพให้กับ Blob
         news.setDetail(detail);
         news.setWriter(writer);
         news.setPost_date(new Date()); // กำหนดวันที่โพสต์เป็นวันปัจจุบัน

         // บันทึกข่าวลงในฐานข้อมูล
         newsRepository.save(news);

         redirectAttributes.addFlashAttribute("message", "News added successfully!");
     } catch (IOException e) {
         e.printStackTrace();
     }

     return "redirect:/news";
 }
 


 
}
