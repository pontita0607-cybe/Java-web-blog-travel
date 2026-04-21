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
public class DamPondtypeController {
	
	@Autowired
    BlogRepository repo;
    
    @GetMapping("/dampondType")
    public String showDamPondBlogs(Model model, HttpSession session) {
    	if (session.getAttribute("user") != null) {
    		User user = (User) session.getAttribute("user");
            model.addAttribute("name", user.getName());
            model.addAttribute("email", user.getEmail());
           // ดึงข้อมูลบล็อกที่มีประเภทเป็น "ทะเล" จากฐานข้อมูล
           List<Blog> dampondBlogs = repo.findByType("เขื่อน");
        
           // ส่งข้อมูลไปยัง seaType.jsp
           model.addAttribute("dampondBlogs", dampondBlogs);
        
           return "dampondType";
    	}else {
    		List<Blog> dampondBlogs = repo.findByType("เขื่อน");
   		     // ส่งข้อมูลไปยัง seaType.jsp
            model.addAttribute("dampondBlogs", dampondBlogs);
            return "dampondType";
   	     }
    }
}



