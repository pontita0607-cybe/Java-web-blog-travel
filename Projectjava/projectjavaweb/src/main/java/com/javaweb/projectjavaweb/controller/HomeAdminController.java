package com.javaweb.projectjavaweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.javaweb.projectjavaweb.model.User;
import com.javaweb.projectjavaweb.repository.UserRepository;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeAdminController {
	
	@Autowired
    private UserRepository userRepository;
	
	@GetMapping("/homeAdmin")
    public String showAdmin(Model model, HttpSession session) {
        // เช็คว่ามีการเข้าสู่ระบบหรือไม่
        if (session.getAttribute("user") != null) {
            // หากมีการเข้าสู่ระบบแล้ว
            User adminUser = (User) session.getAttribute("user");
            model.addAttribute("name", adminUser.getName());
            model.addAttribute("email", adminUser.getEmail());
            return "homeAdmin"; // แสดงหน้า homeAdmin พร้อมข้อมูลผู้ใช้
        } else {
            // หากยังไม่ได้เข้าสู่ระบบ
            return "redirect:/login"; // ให้เปลี่ยนเส้นทางไปยังหน้า login
        }
    }

}
