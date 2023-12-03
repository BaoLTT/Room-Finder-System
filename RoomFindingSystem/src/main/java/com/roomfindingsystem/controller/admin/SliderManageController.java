package com.roomfindingsystem.controller.admin;

import com.roomfindingsystem.entity.SliderEntity;
import com.roomfindingsystem.entity.UserEntity;
import com.roomfindingsystem.service.SliderService;
import com.roomfindingsystem.service.UserService;
import com.roomfindingsystem.service.impl.GcsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
import java.util.Objects;

@Controller
@RequestMapping("/admin")
public class SliderManageController {
    @Autowired
    private SliderService sliderService;

    @Autowired
    private GcsService gcsService;

    @Autowired
    private UserService userService;


    @GetMapping("/sliderList")
    public String viewSlider(Model model){
        model.addAttribute("sliderList", sliderService.viewAll() );
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userService.findByEmail(email).get();

        model.addAttribute("user", user);
        model.addAttribute("userService",userService);
        return "/admin/list_slider";
    }

    @GetMapping("sliderList/insert")
    public String viewNewSlider(Model model)
    {   String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userService.findByEmail(email).get();
        model.addAttribute("user", user);
        return "/admin/insert_slider";
    }
    @PostMapping("slider/save")
    public String saveSlider(Model model, @RequestParam(name = "title",required = false , defaultValue = "") String title,
                                   @RequestParam(name = "content",required = false , defaultValue = "") String content,  @RequestParam(name = "file", required = false) MultipartFile file,
                                   @RequestParam(name = "status", required = false) String status) throws IOException {
        SliderEntity sliderEntity = new SliderEntity();
        String imgLink = null;

        // Lấy thời gian hiện tại
        long timestamp = System.currentTimeMillis();

        // Chuyển định dạng thời gian thành chuỗi
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String formattedTimestamp = dateFormat.format(new Date(timestamp));

        if (!file.isEmpty()) {
            //        Handle Image
            byte[] imageBytes = file.getBytes();
            gcsService.uploadImage("rfs_bucket", "Slider/slider_"+formattedTimestamp+".jpg", imageBytes);
            imgLink = "/rfs_bucket/Slider/"+"slider_"+formattedTimestamp+".jpg";
        }
        sliderEntity.setImgLink(imgLink);
        sliderEntity.setTitle(title);
        sliderEntity.setContent(content);
        sliderEntity.setCreatedDate(LocalDate.now());

        final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = null;
        if (userService.findByEmail(currentUserName).isPresent()) {
            user = userService.findByEmail(currentUserName).get();
        }

        assert user != null;
        sliderEntity.setCreatedBy(user.getUserId());

        sliderEntity.setStatus("0");

        sliderService.save(sliderEntity);
        return "redirect:/admin/sliderList";
    }

    @GetMapping("sliderList/update/{id}")
    public String viewFormUpdateSlider(Model model, @PathVariable("id") int id){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userService.findByEmail(email).get();
        SliderEntity slider = sliderService.getSliderById(id);
        if(slider.getStatus()==null) slider.setStatus("Không hoạt động");
        if(slider.getStatus().equals("1")){
            slider.setStatus("Hoạt động");
        } else {
            slider.setStatus("Không hoạt động");
        };
        model.addAttribute("slider", slider);
        model.addAttribute("user", user);
        return "/admin/edit_slider";
    }

    @PostMapping("slider/update")
    public String updateSlider(@ModelAttribute("slider") SliderEntity sliderEntity, @RequestParam(name = "title",required = false , defaultValue = "") String title,
                               @RequestParam(name = "content",required = false , defaultValue = "") String content,  @RequestParam(name = "file", required = false) MultipartFile file,
                               @RequestParam(name = "status", required = false) String status) throws IOException {

        String imgLink = null;
        
        if (!file.isEmpty()) {
            //        Handle Image
            byte[] imageBytes = file.getBytes();
            gcsService.uploadImage("rfs_bucket", "Slider/slider_"+sliderEntity.getSliderid()+".jpg", imageBytes);
            imgLink = "/rfs_bucket/Slider/"+"slider_"+sliderEntity.getSliderid()+".jpg";
        }
        sliderEntity.setImgLink(imgLink);

        if(status.equals("Hoạt động")){
            sliderEntity.setStatus("1");
        } else {
            sliderEntity.setStatus("0");
        };

        sliderEntity.setContent(content);
        sliderEntity.setTitle(title);
//        sliderEntity.setSliderid(sliderEntity.getSliderid());
        sliderService.update(sliderEntity);
        return "redirect:/admin/sliderList";
    }


    @GetMapping("/sliderList/delete/{id}")
    String deleteSlider(@PathVariable("id") int id){
        sliderService.deleteById(id);
        return "redirect:/admin/sliderList";
    }

    @GetMapping("/sliderList/deleteImage/{id}")
    public String deleteImage(@PathVariable(name = "id") Integer id){
        SliderEntity slider = sliderService.getSliderById(id);
        slider.setImgLink(null);
        sliderService.save(slider);

        return "redirect:/admin/sliderList/update/"+id;
    }
}
