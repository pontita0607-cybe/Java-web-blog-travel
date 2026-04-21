package com.javaweb.projectjavaweb.controller;

import java.io.IOException;
import java.security.Provider.Service;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javaweb.projectjavaweb.model.Blog;
import com.javaweb.projectjavaweb.model.Bookmark;
import com.javaweb.projectjavaweb.model.Comment;
import com.javaweb.projectjavaweb.model.LikeCount;
import com.javaweb.projectjavaweb.model.News;
import com.javaweb.projectjavaweb.model.User;
import com.javaweb.projectjavaweb.repository.BlogRepository;
import com.javaweb.projectjavaweb.repository.BookmarkRepository;
import com.javaweb.projectjavaweb.repository.CommentRepository;
import com.javaweb.projectjavaweb.repository.LikeRepository;
import com.javaweb.projectjavaweb.repository.NewsRepository;
import com.javaweb.projectjavaweb.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class BlogController {
	
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
    
    @Autowired
    private NewsRepository newsRepository;

    
    @GetMapping("/blog")
    public String showBlog(Model model, HttpSession session) {
    	if (session.getAttribute("user") != null) {
    		User user = (User) session.getAttribute("user");
            model.addAttribute("name", user.getName());
            model.addAttribute("email", user.getEmail());
            
            List<Blog> blogList = repo.showAll();
            model.addAttribute("blogList", blogList);
            
           

       
             return "showBlog";
    	}else {
    		 List<Blog> blogList = repo.showAll();
             model.addAttribute("blogList", blogList);
             return "showBlog";
    	}
    }



    @GetMapping("/showEditBlog")
    public String showEditBlog(Model model,HttpSession session) {
    	 if (session.getAttribute("user") != null) {
             // หากมีการเข้าสู่ระบบแล้ว
             User adminUser = (User) session.getAttribute("user");
             model.addAttribute("name", adminUser.getName());
             model.addAttribute("email", adminUser.getEmail());
        List<Blog> blogList = repo.showAll();
        model.addAttribute("blogList", blogList);
        return "blog";
       
            // แสดงหน้า homeAdmin พร้อมข้อมูลผู้ใช้
        } else {
            // หากยังไม่ได้เข้าสู่ระบบ
        	
            return "redirect:/login"; // ให้เปลี่ยนเส้นทางไปยังหน้า login
        }
    }
    
    
    
    //เพิ่มblog

    @GetMapping("/insert")
    public String insertBlog() {
        return "admin_add"; 
    }

    @PostMapping("/blogs")
    public String addNews(@RequestParam("title") String title,
                         @RequestParam("imageData") MultipartFile imageData,
                         @RequestParam("detail") String detail,
                         @RequestParam("writer") String writer,
                         @RequestParam("type") String type,
                         RedirectAttributes redirectAttributes) throws SerialException, SQLException {
        if (imageData.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload.");
            return "redirect:/blog";
        }
        try {
            byte[] bytes = imageData.getBytes();
            Blog blog = new Blog();
            blog.setTitle(title);
            Blob imageBlob = new javax.sql.rowset.serial.SerialBlob(bytes);
            blog.setImageData(imageBlob);
            blog.setDetail(detail);
            blog.setWriter(writer);
            blog.setPost_date(new Date());
            blog.setType(type); // กำหนดประเภทของบล็อก
            repo.save(blog);
            redirectAttributes.addFlashAttribute("message", "News added successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/showEditBlog";
    }
    
    //readMore
    @GetMapping("/blog/{id}")
    public String readMore(@PathVariable Long id, Model model, HttpSession session) {
        // ค้นหาบล็อกที่ต้องการแสดงรายละเอียดโดยใช้ ID
        Blog blogToUpdate = repo.findById(id);
        model.addAttribute("blog", blogToUpdate);
        
        if (session.getAttribute("user") != null) { // ตรวจสอบว่าผู้ใช้เข้าสู่ระบบหรือไม่
            User user = (User) session.getAttribute("user");
            model.addAttribute("name", user.getName());
            model.addAttribute("email", user.getEmail());
            
            // ดึงข้อมูลบล็อกอีกครั้งเพื่อนำไปแสดงผลและอัปเดตจำนวนการเข้าชม
            Blog blog = repo.findById(id);
            model.addAttribute("blog", blog);
            
            if (blogToUpdate != null) {
                blogToUpdate.setViewCount(blogToUpdate.getViewCount() + 1); // เพิ่มจำนวนการเข้าชม
                repo.update(blogToUpdate); // อัปเดตบล็อก
            }

            // ดึงความคิดเห็นที่เกี่ยวข้องกับบล็อกนี้
            List<Comment> commentList = commentRepository.findByBlogId(id);
            model.addAttribute("commentList", commentList);
            
            return "blogReadMore"; // แสดงรายละเอียดบล็อกในหน้า JSP
        } else {
            // ถ้าไม่ได้เข้าสู่ระบบ แสดงเฉพาะรายละเอียดของบล็อก
            Blog blog = repo.findById(id);
            model.addAttribute("blog", blog);
            
            // ดึงความคิดเห็นที่เกี่ยวข้องกับบล็อกนี้
            List<Comment> commentList = commentRepository.findByBlogId(id);
            model.addAttribute("commentList", commentList);
            
            return "blogReadMore"; // แสดงรายละเอียดบล็อกในหน้า JSP
        }
    }

  
    
    //แก้ไขข้อมูล
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
    	Blog blog = repo.findById(id);
        model.addAttribute("blog", blog);
        return "editBlog"; // แสดงหน้าแก้ไขข้อมูลข่าว edit
    }
    
    @PostMapping("/edit/{id}")
    public String editBlog(@PathVariable Long id, @RequestParam("title") String title,
                           @RequestParam("imageData") MultipartFile imageData,
                           @RequestParam("detail") String detail,
                           @RequestParam("writer") String writer,
                           RedirectAttributes redirectAttributes) throws IOException, SQLException {
        if (imageData.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload.");
            return "redirect:/edit/{id}";
        }
        try {
        	Blog blog = repo.findById(id);
            byte[] bytes = imageData.getBytes();
            Blob imageBlob = new javax.sql.rowset.serial.SerialBlob(bytes);
            
            // อัปเดตข้อมูลข่าวด้วยข้อมูลใหม่
            blog.setTitle(title);
            blog.setImageData(imageBlob);
            blog.setDetail(detail);
            blog.setWriter(writer);
            repo.save(blog);
            
            redirectAttributes.addFlashAttribute("message", "News updated successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/showEditBlog";
    }
    
  //ลบข้อมูล
    @GetMapping("/delete/{id}")
    public String deleteNews(@PathVariable Long id) {
        // ลบข้อมูลข่าวจากฐานข้อมูลโดยใช้ ID
        repo.deleteById(id);
        // ส่ง redirect ไปยังหน้าหลักข่าวหลังจากที่ลบข้อมูลเสร็จสมบูรณ์
        return "redirect:/showEditBlog";
    }
   
    
    
    @GetMapping("/deleteimage/{id}")
    public String deleteImage(@PathVariable Long id) {
      // ลบรูปภาพของข่าวจากฐานข้อมูลโดยใช้ ID
      repo.deleteImageById(id);
      // ส่ง redirect ไปยังหน้าแก้ไขข่าวหลังจากที่ลบรูปภาพเสร็จสมบูรณ์
      return "redirect:/edit/{id}";
    }
    

    
    
//    นับจำนวนการเข้าชม Blog
    @GetMapping("/viewCount/{id}")
    public String viewCount(@PathVariable Long id, Model model, HttpSession session) {
        Blog blogToUpdate = repo.findById(id);
        model.addAttribute("blog", blogToUpdate);

        List<Comment> commentList = commentRepository.findByBlogId(id);
        model.addAttribute("commentList", commentList);

        User user = (User) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("name", user.getName());
        }

        List<Blog> blogList = repo.showAll();
        model.addAttribute("blogList", blogList);

        if (blogToUpdate != null) {
            blogToUpdate.setViewCount(blogToUpdate.getViewCount() + 1); // Increment view count
            repo.update(blogToUpdate); // Update the blog
        }

        return "blogReadMoreHomeUser";
    }
    
    
    @GetMapping("/searchBlog")
    public String searchBlog(@RequestParam("keyword") String keyword, Model model) {
        List<Blog> blogList = repo.findByTitle(keyword); // ค้นหาบล็อกที่มีชื่อ (title) ตรงกับคำค้นหา
        List<News> newslist = newsRepository.findByTitleContaining(keyword);// ค้นหาข่าวที่มีชื่อ (title) ประกอบด้วยคำค้นหา

     // นำรายการบล็อกและข่าวที่ค้นหาเจอมาเก็บไว้ใน Model เพื่อนำไปแสดงผลในหน้าเว็บ
        model.addAttribute("blogList", blogList);
        model.addAttribute("newslist", newslist);

        return "showSearch"; // แสดงผลลัพธ์การค้นหาในหน้า JSP ที่คุณต้องการ
    }
 
}