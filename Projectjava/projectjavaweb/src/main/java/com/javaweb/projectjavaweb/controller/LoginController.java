package com.javaweb.projectjavaweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.javaweb.projectjavaweb.model.User;
import com.javaweb.projectjavaweb.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;
   

    
    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // ส่งผู้ใช้ไปยังหน้า login.jsp
    }


    @PostMapping("/login")
 public String login(String email, String password, Model model, HttpSession session) { // รับค่า email และ password จากฟอร์มการเข้าสู่ระบบ
     // ตรวจสอบว่ามีผู้ใช้งานด้วยอีเมลที่ระบุหรือไม่
     boolean userExists = userRepository.existsByEmail(email);
     if (userExists) {
         // หาผู้ใช้จากอีเมลที่ระบุ
         User user = userRepository.findByEmail(email);
         // เช็คว่ารหัสผ่านที่ระบุตรงกับรหัสผ่านของผู้ใช้หรือไม่
         if (user.getPassword().equals(password)) {
             // ตรวจสอบประเภทของผู้ใช้
             if (user.getType_user().equals("user")) {
                 session.setAttribute("user", user);
                 return "redirect:/homeUser";
             } else if (user.getType_user().equals("admin")) {
                 session.setAttribute("user", user);
                 return "redirect:/homeAdmin";
             }
         } else {
             // ถ้ารหัสผ่านไม่ถูกต้อง
             model.addAttribute("errorMessage", "Invalid password");
             return "login";
         }
     } else {
         // ถ้าไม่พบผู้ใช้ด้วยอีเมลที่ระบุ 
         model.addAttribute("errorMessage", "User not found");
         return "login";
     }
     // ในกรณีที่เกิดข้อผิดพลาด ให้กลับไปที่หน้า login
     return "login";
 }

    
    

}