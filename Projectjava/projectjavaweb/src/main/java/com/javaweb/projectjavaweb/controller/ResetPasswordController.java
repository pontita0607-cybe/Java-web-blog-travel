package com.javaweb.projectjavaweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaweb.projectjavaweb.model.User;
import com.javaweb.projectjavaweb.repository.UserRepository;


@Controller
public class ResetPasswordController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/resetpassword")
    public String showResetPasswordForm() {
        return "resetpassword";
    }

    @PostMapping("/resetpassword")
    public String resetPassword(@RequestParam("email") String email, 
                                @RequestParam("newPassword") String newPassword, 
                                @RequestParam("confirmNewPassword") String confirmNewPassword,
                                Model model) {
        // ค้นหาผู้ใช้ด้วยอีเมล
        User user = userRepository.findByEmail(email);
        
        // ตรวจสอบว่ามีผู้ใช้หรือไม่
        if (user != null) {
            // ตรวจสอบว่ารหัสผ่านใหม่ตรงกับการยืนยันรหัสผ่านใหม่หรือไม่
            if (newPassword.equals(confirmNewPassword)) {
                // ตั้งค่ารหัสผ่านใหม่
                user.setPassword(newPassword);
                // ตั้งค่า confirmNewPassword ใหม่
                user.setConfirm_password(confirmNewPassword);
                
                // อัพเดตข้อมูลผู้ใช้ในฐานข้อมูล
                userRepository.save(user);
             
                model.addAttribute("successMessage", "Password reset successful.");
                return "redirect:/login";
            } else {
                model.addAttribute("errorMessage", "New passwords do not match.");
                return "resetpassword";
            }
        } else {
            model.addAttribute("errorMessage", "Email not found.");
            return "resetpassword";
        }
    }



}
