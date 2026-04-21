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
import com.javaweb.projectjavaweb.model.Bookmark;
import com.javaweb.projectjavaweb.model.User;
import com.javaweb.projectjavaweb.repository.BlogRepository;
import com.javaweb.projectjavaweb.repository.BookmarkRepository;
import com.javaweb.projectjavaweb.repository.CommentRepository;
import com.javaweb.projectjavaweb.repository.LikeRepository;
import com.javaweb.projectjavaweb.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class BookmarkController {
	
	@Autowired
    private BlogRepository repo;
    
    @Autowired
    private LikeRepository likeRepository;
    
    @Autowired
    private BookmarkRepository bookmarkRepository;
    
    @Autowired
    private CommentRepository commentRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    //save boomark
    @GetMapping("/bookmark/{id}")
    public String bookmarkBlog(@PathVariable Long id, HttpSession session, RedirectAttributes redirectAttributes) {
        // Check if the user is logged in
        if (session.getAttribute("user") != null) { // ตรวจสอบว่าผู้ใช้เข้าสู่ระบบหรือไม่
            User user = (User) session.getAttribute("user"); // ดึงข้อมูลผู้ใช้จาก session
            
            // Check if the user has already bookmarked this blog
            Blog blog = repo.findById(id); // ค้นหาบล็อกที่ต้องการ bookmark โดยใช้ ID
            
            boolean hasBookmarked = bookmarkRepository.existsByUserAndBlog(user, blog); // ตรวจสอบว่าผู้ใช้ได้ทำการ bookmark บล็อกนี้แล้วหรือไม่
            
            if (!hasBookmarked) { // ถ้าผู้ใช้ยังไม่ได้ทำการ bookmark บล็อกนี้
                if (blog != null) { // ถ้าบล็อกมีอยู่จริง
                    Bookmark bookmark = new Bookmark(); // สร้าง object Bookmark
                    bookmark.setUser(user); // กำหนดผู้ใช้ให้กับ bookmark
                    bookmark.setBlog(blog); // กำหนดบล็อกให้กับ bookmark
                    bookmarkRepository.save(bookmark); // บันทึก bookmark ลงในฐานข้อมูล
                    
                    redirectAttributes.addFlashAttribute("message", "You bookmarked this blog!"); // เพิ่มข้อความลงใน flash attribute สำหรับแสดงผลที่หน้าถัดไป
                } else {
                    redirectAttributes.addFlashAttribute("message", "Blog not found!"); // บล็อกไม่พบ
                }
            } else {
                redirectAttributes.addFlashAttribute("message", "You have already bookmarked this blog!"); // ผู้ใช้ได้ทำการ bookmark บล็อกนี้แล้ว
            }
        } else {
            redirectAttributes.addFlashAttribute("message", "Please log in to bookmark blogs!"); // กรุณาเข้าสู่ระบบเพื่อทำการ bookmark บล็อก
        }
        return "redirect:/blog"; // redirect กลับไปที่หน้ารายการบล็อก
    }


    //เก็บ bookmarks ของ user นั้นๆ
    @GetMapping("/user/bookmarks")
    public String showUserBookmarks(HttpSession session, Model model) {
        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            List<Bookmark> bookmarkList = bookmarkRepository.findByUser(user);
            model.addAttribute("bookmarkList", bookmarkList);
            model.addAttribute("name", user.getName());
            return "bookmark";
        } else {
            // Handle case when user is not logged in
            return "bookmarks"; // Redirect to login page or handle appropriately
        }
    }
    
    
    // ลบ Bookmark
    @GetMapping("/deleteBookmark/{id}")
    public String deleteBookmark(@PathVariable Long id, HttpSession session, RedirectAttributes redirectAttributes) {
        // ตรวจสอบว่าผู้ใช้เข้าสู่ระบบหรือไม่
        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            Optional<Bookmark> optionalBookmark = bookmarkRepository.findById(id);

            // ตรวจสอบว่ามีบุ๊กมาร์กอยู่หรือไม่
            if (optionalBookmark.isPresent()) {
                Bookmark bookmark = optionalBookmark.get();
                // ตรวจสอบว่าบุ๊กมาร์กเป็นของผู้ใช้ที่เข้าสู่ระบบหรือไม่
                if (bookmark.getUser().getId().equals(user.getId())) {
                    bookmarkRepository.delete(bookmark);
                    redirectAttributes.addFlashAttribute("message", "Bookmark deleted successfully!");
                } else {
                    redirectAttributes.addFlashAttribute("message", "You are not authorized to delete this bookmark!");
                }
            } else {
                redirectAttributes.addFlashAttribute("message", "Bookmark not found!");
            }
        } else {
            redirectAttributes.addFlashAttribute("message", "Please log in to delete bookmarks!");
        }
        return "redirect:/user/bookmarks";
    }

}
