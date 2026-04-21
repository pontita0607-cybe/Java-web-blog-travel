package com.javaweb.projectjavaweb.controller;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaweb.projectjavaweb.model.User;
import com.javaweb.projectjavaweb.repository.RegisterRepository;



@Controller
public class RegisterController {

    @Autowired
    private RegisterRepository repo;
   
  
  
    @GetMapping("/register")
    public String showRegisterForm() {
        return "register"; // Assuming the register.jsp is located in your view directory
    }
    
    
    @PostMapping("/register")
    //การรับค่าพารามิเตอร์ที่ถูกส่งมาจากฟอร์ม HTML ซึ่งในที่นี้คือ name, email, password, confirmPassword, และ type_user
    public String userregister(@RequestParam String name, @RequestParam String email,
                           @RequestParam String password, @RequestParam String confirmPassword,
                           @RequestParam(required = false) String type, Model model) { 
        // ถ้าผู้ใช้ไม่ได้เลือก type_user ให้กำหนดค่าเริ่มต้นเป็น "user"
        if (type == null || type.isEmpty()) {
            type = "user";
        }
        
         // สร้างอ็อบเจกต์ของผู้ใช้ใหม่
        User user = new User();
        user.setName(name); 
        user.setEmail(email);
        user.setPassword(password);
        user.setConfirm_password(confirmPassword);
        user.setType_user(type); // กำหนดประเภทผู้ใช้
        
        // ตรวจสอบว่ารหัสผ่านมีตัวอักษรภาษาไทยหรือไม่
        if (containsThaiCharacters(password)) {
            model.addAttribute("messageFailed", "Password cannot contain Thai characters.");
            return "register";
        }
        // ตรวจสอบว่า password และ confirmPassword ตรงกันหรือไม่
        if (!password.equals(confirmPassword)) {
            model.addAttribute("messageFailed", "Password and Confirm Password do not match.");
            return "register";
        }
        // ตรวจสอบความยาวของ password
        if (password.length() < 8) {
            model.addAttribute("messageFailed", "Password must be at least 8 characters long.");
            return "register";
        }
        // ตรวจสอบว่ามีตัวอักษรพิมพ์เล็กใน password หรือไม่
        if (!password.matches(".*[a-z].*")) {
            model.addAttribute("messageFailed", "Password must contain at least one lowercase letter.");
            return "register";
        }
        // ตรวจสอบว่ามีตัวอักษรพิมพ์ใหญ่ใน password หรือไม่
        if (!password.matches(".*[A-Z].*")) {
            model.addAttribute("messageFailed", "Password must contain at least one uppercase letter.");
            return "register";
        }
        
        
        // ตรวจสอบว่าอีเมลถูกใช้งานแล้วหรือไม่
        if (repo.existsByEmail(email)) {
            model.addAttribute("messageFailed", "Email is already registered.");
            return "register";
        }
        
        
        // ทำการลงทะเบียน
        boolean isRegistered = repo.insertData(user);
        System.out.println("Insert Successful");

        // ตรวจสอบว่าลงทะเบียนสำเร็จหรือไม่และตั้งค่าข้อความที่จะส่งไปยังหน้า register.jsp
        if (isRegistered) {
            model.addAttribute("messageSuccess", "Register Successful");
        } else {
            model.addAttribute("messageFailed", "Register Failed");
        }

        // ส่งผู้ใช้กลับไปยังหน้าลงทะเบียน
        return "register";
        
    }


    // ตรวจสอบรหัสผ่านว่ามีตัวอักษรภาษาไทยหรือไม่
    private boolean containsThaiCharacters(String text) {
        Pattern pattern = Pattern.compile("[ก-๙]");
        return pattern.matcher(text).find();
    }
    
    
}
