package com.roomfindingsystem.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.http.HttpStatus;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {
    @RequestMapping("/error")
    public String accessfail(HttpServletRequest request, ModelMap model) {
        model.addAttribute("request",request);
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if(status != null && Integer.valueOf(status.toString())== HttpStatus.SC_NOT_FOUND){
            return "404";
        }


        return "error";
    }
}
