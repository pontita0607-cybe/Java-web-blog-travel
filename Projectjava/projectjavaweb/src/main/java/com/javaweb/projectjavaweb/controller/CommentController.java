package com.javaweb.projectjavaweb.controller;

import com.javaweb.projectjavaweb.model.Blog;
import com.javaweb.projectjavaweb.model.Comment;
import com.javaweb.projectjavaweb.model.User;
import com.javaweb.projectjavaweb.repository.BlogRepository;
import com.javaweb.projectjavaweb.repository.CommentRepository;
import com.javaweb.projectjavaweb.repository.UserRepository;

import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class CommentController {

	 @Autowired
	    private CommentRepository commentRepository;

	    @Autowired
	    private UserRepository userRepository;

	    @Autowired
	    private BlogRepository blogRepository;
	    
  
		//เมธอดสำหรับเพิ่มความคิดเห็นใหม่ในบล็อก
	    @PostMapping("/addComment")
	    public String addComment(@RequestParam("blogId") Long blogId,
	                             @RequestParam("userId") Long userId,
	                             @RequestParam("comment") String commentContent,
	                             HttpSession session,
	                             RedirectAttributes redirectAttributes) {
	        // ตรวจสอบว่าผู้ใช้เข้าสู่ระบบหรือไม่
	        if (session.getAttribute("user") != null) {
	            // ดึงข้อมูลผู้ใช้จาก session
	            User user = (User) session.getAttribute("user");

	            // ดึงข้อมูลบล็อกและผู้ใช้จากฐานข้อมูล
	            Blog blog = blogRepository.findById(blogId);
	            Optional<User> optionalUser = userRepository.findById(userId);
	            User commentUser = optionalUser.orElse(null);

	            // ตรวจสอบว่าบล็อกและผู้ใช้มีหรือไม่
	            if (blog != null && commentUser != null) {
	                // สร้างอ็อบเจ็กต์ Comment ใหม่
	                Comment comment = new Comment();
	                comment.setBlog(blog);
	                comment.setUser(commentUser);
	                comment.setContent(commentContent);
	                comment.setCommentDate(new Date());

	                // บันทึกความคิดเห็นลงในฐานข้อมูล
	                commentRepository.save(comment);
	                
	                redirectAttributes.addFlashAttribute("message", "Comment added successfully!");
	            } else {
	                redirectAttributes.addFlashAttribute("message", "Blog or user not found!");
	            }
	        } else {
	            redirectAttributes.addFlashAttribute("message", "Please log in to add comments!");
	        }

	        // Redirect กลับไปยังหน้าเดิมหลังจากการเพิ่มความคิดเห็น
	        return "redirect:/blog/" + blogId;

	    }
    
	 // เมธอดสำหรับลบความคิดเห็นที่เกี่ยวข้องด้วย commentId
	    @PostMapping("/deleteComment/{commentId}")
	    public String deleteComment(@PathVariable("commentId") Long commentId,
	                                @RequestParam("blogId") Long blogId,
	                                HttpSession session,
	                                RedirectAttributes redirectAttributes) {
	        // ดึงข้อมูลผู้ใช้ที่เข้าสู่ระบบจาก HttpSession
	        User loggedInUser = (User) session.getAttribute("user");

	        // ตรวจสอบว่าผู้ใช้เข้าสู่ระบบหรือไม่
	        if (loggedInUser != null) {
	            // ค้นหาความคิดเห็นโดยใช้ commentId
	            Optional<Comment> optionalComment = commentRepository.findById(commentId);
	            
	            // ตรวจสอบว่ามีค่าอยู่ในตัวเลือกหรือไม่
	            if (optionalComment.isPresent()) { 
	                Comment comment = optionalComment.get();
	                
	                // ตรวจสอบว่าผู้ใช้ที่เข้าสู่ระบบเป็นเจ้าของความคิดเห็นหรือไม่
	                if (comment.getUser().getId().equals(loggedInUser.getId())) {
	                    // ลบความคิดเห็น
	                    commentRepository.delete(comment);
	                    redirectAttributes.addFlashAttribute("message", "Comment deleted successfully!");
	                } else {
	                    redirectAttributes.addFlashAttribute("message", "You are not authorized to delete this comment!");
	                }
	            } else {
	                redirectAttributes.addFlashAttribute("message", "Comment not found!");
	            }
	        } else {
	            redirectAttributes.addFlashAttribute("message", "Please log in to delete comments!");
	        }
	        // Redirect กลับไปยังหน้าเดิมหลังจากการลบความคิดเห็น
	        return "redirect:/blog/" + blogId;
	    }

}
