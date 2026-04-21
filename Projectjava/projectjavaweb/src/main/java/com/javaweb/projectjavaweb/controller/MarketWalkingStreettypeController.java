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
public class MarketWalkingStreettypeController {
	
	@Autowired
    BlogRepository repo;
    
    @GetMapping("/marketwalkingstreetType")
    public String showMarketWalkingStreetBlogs(Model model, HttpSession session) {
    	if (session.getAttribute("user") != null) {
    		User user = (User) session.getAttribute("user");
            model.addAttribute("name", user.getName());
            model.addAttribute("email", user.getEmail());
        // ดึงข้อมูลบล็อกที่มีประเภทเป็น "ทะเล" จากฐานข้อมูล
        List<Blog> marketwalkingstreetBlogs = repo.findByType("ตลาด");
        
        // ส่งข้อมูลไปยัง seaType.jsp
        model.addAttribute("marketwalkingstreetBlogs", marketwalkingstreetBlogs);
        
        return "marketwalkingstreetType";
    	}else {
    		List<Blog> marketwalkingstreetBlogs = repo.findByType("ตลาด");
   		     // ส่งข้อมูลไปยัง seaType.jsp
            model.addAttribute("marketwalkingstreetBlogs", marketwalkingstreetBlogs);
            return "marketwalkingstreetType";
   	     }
    }

}



