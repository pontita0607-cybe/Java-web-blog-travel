package com.javaweb.projectjavaweb.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.javaweb.projectjavaweb.model.Blog;
import com.javaweb.projectjavaweb.model.User;
import com.javaweb.projectjavaweb.repository.BlogRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class TempletypeController {
	@Autowired
    BlogRepository repo;
    
	@GetMapping("/templeType")
	public String showTempleBlogs(Model model, HttpSession session) {
	    // ตรวจสอบว่าผู้ใช้เข้าสู่ระบบหรือไม่
	    if (session.getAttribute("user") != null) {
	        User user = (User) session.getAttribute("user");
	        // เพิ่มข้อมูลของผู้ใช้ลงใน model เพื่อนำไปแสดงผลในหน้า templeType.jsp
	        model.addAttribute("name", user.getName());
	        model.addAttribute("email", user.getEmail());
	    }
	    
	    // ดึงข้อมูลบล็อกที่มีประเภทเป็น "วัด" จากฐานข้อมูล
	    List<Blog> templeBlogs = repo.findByType("วัด");
	    
	    // ส่งข้อมูลบล็อกที่มีประเภทเป็น "วัด" ไปยัง templeType.jsp เพื่อแสดงผล
	    model.addAttribute("templeBlogs", templeBlogs);
	    
	    return "templeType"; // คืนค่าชื่อของ view templeType.jsp เพื่อนำไปแสดงผลในเว็บบราวเซอร์
	}


}

