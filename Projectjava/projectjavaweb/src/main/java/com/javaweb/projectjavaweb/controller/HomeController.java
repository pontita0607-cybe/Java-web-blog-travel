package com.javaweb.projectjavaweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javaweb.projectjavaweb.model.Blog;
import com.javaweb.projectjavaweb.model.Bookmark;
import com.javaweb.projectjavaweb.model.LikeCount;
import com.javaweb.projectjavaweb.model.User;
import com.javaweb.projectjavaweb.repository.BlogRepository;
import com.javaweb.projectjavaweb.repository.BookmarkRepository;
import com.javaweb.projectjavaweb.repository.LikeRepository;
import com.javaweb.projectjavaweb.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private BlogRepository repo;
    
    @Autowired
    private LikeRepository likeRepository;
    
    @Autowired
    private BookmarkRepository bookmarkRepository;
    
    @GetMapping("/")
    public String redirectToHomeUser(Model model, HttpSession session) {
        if (session.getAttribute("user") != null) {

        	List<Blog> blogList = repo.findTop4ByOrderByViewCountDesc();
        	model.addAttribute("blogList", blogList);

            return "redirect:/homeUser";
        } else {
        	List<Blog> blogList = repo.findTop4ByOrderByViewCountDesc();
        	model.addAttribute("blogList", blogList);

             
            return "homeUser";
        }
    }


    @GetMapping("/homeUser")
    public String showHomeUser(Model model, HttpSession session) {
        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            model.addAttribute("name", user.getName());
            model.addAttribute("email", user.getEmail());
            
            List<Blog> blogList = repo.findTop4ByOrderByViewCountDesc();
            model.addAttribute("blogList", blogList);

            
            return "homeUser";
        } else {
        	List<Blog> blogList = repo.findTop4ByOrderByViewCountDesc();
        	model.addAttribute("blogList", blogList);

            return "homeUser";
        }
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); 
        return "redirect:/homeUser"; 
    }
    
    
  //กดไลค์ตาม id
    @GetMapping("/likeHomeUser/{id}")
    public String likeBlog2(@PathVariable Long id, HttpSession session, RedirectAttributes redirectAttributes) {
        // Check if the user is logged in
        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            Blog blog = repo.findById(id);

            // Check if the blog exists
            if (blog != null) {
                // Check if the user has already liked the blog
                if (!likeRepository.existsByUserAndBlog(user, blog)) {
                    // Increment like count
                    if (blog.getLikeCount() == null) {
                        blog.setLikeCount(1);
                    } else {
                        blog.setLikeCount(blog.getLikeCount() + 1);
                    }
                    repo.save(blog);

                    // Record user's like action
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
        } else {
            redirectAttributes.addFlashAttribute("message", "Please log in to like blogs!");
        }
        return "redirect:/homeUser";
    }
    
   //bookmark 
    @GetMapping("/bookmark3/{id}")
    public String bookmarkBlog(@PathVariable Long id, HttpSession session, RedirectAttributes redirectAttributes) {
        // Check if the user is logged in
        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            
            // Check if the user has already bookmarked this blog
            Blog blog = repo.findById(id);
            
            boolean hasBookmarked = bookmarkRepository.existsByUserAndBlog(user, blog);
            
            if (!hasBookmarked) {
                // If the blog exists and the user hasn't bookmarked it yet
                if (blog != null) {
                    Bookmark bookmark = new Bookmark();
                    bookmark.setUser(user);
                    bookmark.setBlog(blog);
                    bookmarkRepository.save(bookmark);
                    
                    redirectAttributes.addFlashAttribute("message", "You bookmarked this blog!");
                } else {
                    redirectAttributes.addFlashAttribute("message", "Blog not found!");
                }
            } else {
                redirectAttributes.addFlashAttribute("message", "You have already bookmarked this blog!");
            }
        } else {
            redirectAttributes.addFlashAttribute("message", "Please log in to bookmark blogs!");
        }
        return "redirect:/homeUser";
    }
    
    
    
}
