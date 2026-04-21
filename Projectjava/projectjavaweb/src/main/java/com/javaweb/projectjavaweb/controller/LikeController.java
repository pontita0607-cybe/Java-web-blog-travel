package com.javaweb.projectjavaweb.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javaweb.projectjavaweb.model.Blog;
import com.javaweb.projectjavaweb.model.LikeCount;
import com.javaweb.projectjavaweb.model.User;
import com.javaweb.projectjavaweb.repository.BlogRepository;
import com.javaweb.projectjavaweb.repository.BookmarkRepository;
import com.javaweb.projectjavaweb.repository.CommentRepository;
import com.javaweb.projectjavaweb.repository.LikeRepository;
import com.javaweb.projectjavaweb.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class LikeController {
	
	@Autowired
    private BlogRepository repo;
    
    @Autowired
    private LikeRepository likeRepository;
    
    
    @Autowired
    private UserRepository userRepository;
	
 // กดไลค์ตาม id
    @GetMapping("/like/{id}")
    public String likeBlog(@PathVariable Long id, HttpSession session, RedirectAttributes redirectAttributes, Model model) {
        // ตรวจสอบว่าผู้ใช้เข้าสู่ระบบแล้วหรือไม่
        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            // ดึงข้อมูลบล็อกตาม id ที่ระบุ
            Blog blog = repo.findById(id);
            // ดึงรายการบล็อกที่ผู้ใช้กดไลค์ไปแล้ว
            List<LikeCount> likeCountList = likeRepository.findByUser(user);

            // ตรวจสอบว่ามีบล็อกอยู่หรือไม่
            if (blog != null) {
                // ตรวจสอบว่าผู้ใช้กดไลค์บล็อกแล้วหรือไม่
                if (!likeRepository.existsByUserAndBlog(user, blog)) {
                    // เพิ่มจำนวนไลค์ของบล็อกขึ้น
                    if (blog.getLikeCount() == null) {
                        blog.setLikeCount(1);
                    } else {
                        blog.setLikeCount(blog.getLikeCount() + 1);
                    }
                    // บันทึกการเปลี่ยนแปลงจำนวนไลค์ในฐานข้อมูล
                    repo.save(blog);

                    // บันทึกการกระทำที่ผู้ใช้ชื่นชอบ
                    LikeCount like = new LikeCount();
                    like.setUser(user);
                    like.setBlog(blog);
                    likeRepository.save(like);

                    redirectAttributes.addFlashAttribute("message", "You liked this blog!");
                } else {
                    redirectAttributes.addFlashAttribute("message", "You already liked this blog!");
                }
            } else {
                redirectAttributes.addFlashAttribute("message", "Blog not found!");
            }
            model.addAttribute("likeCountList", likeCountList); // เพิ่มรายการบล็อกที่ผู้ใช้ได้กดไลค์ไปในโมเดล
        } else {
            redirectAttributes.addFlashAttribute("message", "Please log in to like blogs!");
        }
        return "redirect:/user/likeuser"; // Redirect ไปยังหน้าที่แสดงรายการบล็อกที่ผู้ใช้ได้กดไลค์
    }

    //เก็บ กดไลค์ ของ user นั้นๆ
    @GetMapping("/user/likeuser")
    public String showUserlikeuser(HttpSession session, Model model) {
        if (session.getAttribute("user") != null) {
        	//การดึงข้อมูลผู้ใช้ (User) จาก session โดยใช้ชื่อ "user" เป็น key ซึ่งเก็บข้อมูลของผู้ใช้ที่เข้าสู่ระบบไว้
            User user = (User) session.getAttribute("user");
            
            //List ของ LikeCount object ซึ่งเก็บรายการการกดไลค์ทั้งหมดของผู้ใช้นั้น
            List<LikeCount> likeCountList = likeRepository.findByUser(user);
            
            model.addAttribute("likeCountList", likeCountList);
            model.addAttribute("name", user.getName());
            return "likeuser";
        } else {
            // Handle case when user is not logged in
            return "likeuser"; // Redirect to login page or handle appropriately
        }
    }
    
    
    // ลบ กดไลค์
    @GetMapping("/deleteLike/{id}")
    public String deleteLike(@PathVariable Long id, HttpSession session, RedirectAttributes redirectAttributes) {
        // ตรวจสอบว่าผู้ใช้เข้าสู่ระบบหรือไม่
        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            // ค้นหาข้อมูลการกดไลค์ด้วย id ที่ระบุ
            Optional<LikeCount> optionalLikeCount = likeRepository.findById(id);

            // ตรวจสอบว่ามีข้อมูลการกดไลค์หรือไม่
            if (optionalLikeCount.isPresent()) {
                LikeCount likeCount = optionalLikeCount.get();
                
                // ตรวจสอบว่าผู้ใช้ที่ลบไลค์เป็นเจ้าของไลค์หรือไม่
                if (likeCount.getUser().getId().equals(user.getId())) {
                    // ลบการกดไลค์
                    likeRepository.deleteLike(likeCount);
                    redirectAttributes.addFlashAttribute("message", "like deleted successfully!");
                } else {
                    redirectAttributes.addFlashAttribute("message", "You are not authorized to delete this like!");
                }
            } else {
                redirectAttributes.addFlashAttribute("message", "like not found!");
            }
        } else {
            redirectAttributes.addFlashAttribute("message", "Please log in to delete like!");
        }
        return "redirect:/user/likeuser"; // Redirect ไปยังหน้าที่แสดงรายการบล็อกที่ผู้ใช้ได้กดไลค์ไปแล้ว
    }

    

}
