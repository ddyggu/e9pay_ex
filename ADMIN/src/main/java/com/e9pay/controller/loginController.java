package com.e9pay.controller;

import com.e9pay.repository.LoginRepository;
import com.e9pay.entity.AdminUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Locale;
import java.util.Map;

@Controller
public class loginController {

    @Autowired
    private LoginRepository loginRepository;

    @GetMapping(value="/index")
    public String moveIndex(Model model, Map<String, Object> param) {
        return "index";
    }

    @GetMapping(value="/login")
    public String moveLogin(Model model, Map<String, Object> param) {
        List<AdminUser> adminUsers = loginRepository.findAll();
        AdminUser adminUser = loginRepository.findById("ddyggu").map(user -> { user.setEmpId(user.getEmpId().toUpperCase(Locale.ROOT)); return user;}).get();
        model.addAttribute("user", adminUser);
        return "/auth/login";
    }

}
