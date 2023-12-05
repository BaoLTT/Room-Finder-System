package com.roomfindingsystem.controller.admin;

import com.roomfindingsystem.entity.NewsEntity;
import com.roomfindingsystem.entity.UserEntity;
import com.roomfindingsystem.service.NewsService;
import com.roomfindingsystem.service.UserService;
import com.roomfindingsystem.service.impl.GcsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Controller
@RequestMapping("/admin")
public class NewsManageController {
    @Autowired
    private NewsService newsService;

    @Autowired
    private GcsService gcsService;

    @Autowired
    private UserService userService;


    @GetMapping("/newsList")
    public String viewNews(Model model){
        model.addAttribute("newsList", newsService.viewAll() );
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userService.findByEmail(email).get();

        model.addAttribute("user", user);
        model.addAttribute("userService",userService);
        return "admin/list_news";
    }

    @GetMapping("newsList/insert")
    public String viewNewNews(Model model)
    {   String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userService.findByEmail(email).get();
        model.addAttribute("user", user);
        return "admin/insert_news";
    }
    @PostMapping("news/save")
    public String saveNews(Model model, @RequestParam(name = "title",required = false , defaultValue = "") String title,
                                   @RequestParam(name = "content",required = false , defaultValue = "") String content,  @RequestParam(name = "file", required = false) MultipartFile file,
                                   @RequestParam(name = "status", required = false) String status) throws IOException {
        NewsEntity NewsEntity = new NewsEntity();
        String imgLink = null;

        // Lấy thời gian hiện tại
        long timestamp = System.currentTimeMillis();

        // Chuyển định dạng thời gian thành chuỗi
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String formattedTimestamp = dateFormat.format(new Date(timestamp));

        if (!file.isEmpty()) {

            //        Handle Image
            byte[] imageBytes = file.getBytes();
            gcsService.uploadImage("rfs_bucket", "News/news_"+formattedTimestamp+".jpg", imageBytes);
            imgLink = "/rfs_bucket/News/"+"news_"+formattedTimestamp+".jpg";
        }
        NewsEntity.setImgLink(imgLink);
        NewsEntity.setTitle(title);
        NewsEntity.setContent(content);
        NewsEntity.setCreatedDate(LocalDate.now());

        final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = null;
        if (userService.findByEmail(currentUserName).isPresent()) {
            user = userService.findByEmail(currentUserName).get();
        }

        assert user != null;
        NewsEntity.setCreatedBy(user.getUserId());

        NewsEntity.setStatus("0");

        newsService.save(NewsEntity);
        return "redirect:/admin/newsList";
    }

    @GetMapping("newsList/update/{id}")
    public String viewFormUpdateSlider(Model model, @PathVariable("id") int id){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userService.findByEmail(email).get();
        NewsEntity news = newsService.getNewsById(id);
        if(news.getStatus()==null) news.setStatus("Không hoạt động");
        if(news.getStatus().equals("1")){
            news.setStatus("Hoạt động");
        } else {
            news.setStatus("Không hoạt động");
        };
        model.addAttribute("news", news);
        model.addAttribute("user", user);
        return "admin/edit_news";
    }

    @PostMapping("news/update")
    public String updateSlider(@ModelAttribute("news") NewsEntity NewsEntity, @RequestParam(name = "title",required = false , defaultValue = "") String title,
                               @RequestParam(name = "content",required = false , defaultValue = "") String content,  @RequestParam(name = "file", required = false) MultipartFile file,
                               @RequestParam(name = "status", required = false) String status) throws IOException {

        String imgLink = null;
        long timestamp = System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String formattedTimestamp = dateFormat.format(new Date(timestamp));
        
        if (!file.isEmpty()) {
            //        Handle Image
            byte[] imageBytes = file.getBytes();
            gcsService.uploadImage("rfs_bucket", "News/news_"+formattedTimestamp+".jpg", imageBytes);
            imgLink = "/rfs_bucket/News/"+"news_"+formattedTimestamp+".jpg";
        }
        NewsEntity.setImgLink(imgLink);

        if(status.equals("Hoạt động")){
            NewsEntity.setStatus("1");
        } else {
            NewsEntity.setStatus("0");
        };

        NewsEntity.setContent(content);
        NewsEntity.setTitle(title);
//        NewsEntity.setSliderid(NewsEntity.getSliderid());
        newsService.update(NewsEntity);
        return "redirect:/admin/newsList";
    }


    @GetMapping("/newsList/delete/{id}")
    String deleteSlider(@PathVariable("id") int id){
        newsService.deleteById(id);
        return "redirect:/admin/newsList";
    }

    @GetMapping("/newsList/deleteImage/{id}")
    public String deleteImage(@PathVariable(name = "id") Integer id){
        NewsEntity slider = newsService.getNewsById(id);
        slider.setImgLink(null);
        newsService.save(slider);

        return "redirect:/admin/newsList/update/"+id;
    }
}
