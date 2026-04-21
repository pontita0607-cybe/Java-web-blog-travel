package com.javaweb.projectjavaweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.javaweb.projectjavaweb.model.Blog;
import com.javaweb.projectjavaweb.model.User;
import com.javaweb.projectjavaweb.repository.BlogRepository;

import jakarta.servlet.http.HttpSession;

import java.util.List;

@Controller
public class SeatypeController {
    
    @Autowired
    BlogRepository repo;
    
    @GetMapping("/seaType")
    public String showSeaBlogs(Model model, HttpSession session) {
        // ดึงข้อมูลบล็อกที่มีประเภทเป็น "ทะเล" จากฐานข้อมูล
    	if (session.getAttribute("user") != null) {
    		User user = (User) session.getAttribute("user");
            model.addAttribute("name", user.getName());
            model.addAttribute("email", user.getEmail());
        List<Blog> seaBlogs = repo.findByType("ทะเล");
        
        // ส่งข้อมูลไปยัง seaType.jsp
        model.addAttribute("seaBlogs", seaBlogs);
        
        return "seaType";
    	}else {
    		List<Blog> seaBlogs = repo.findByType("ทะเล");
   		     // ส่งข้อมูลไปยัง seaType.jsp
            model.addAttribute("seaBlogs", seaBlogs);
            return "seaType";
   	     }
    }

}
