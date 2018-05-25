package com.tidder.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tidder.model.UserEntity;
import com.tidder.service.LoginService;

@Controller("loginController")
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String getIndex(ModelMap model) {
		UserEntity user = loginService.getAuthenticatedUser();
		model.addAttribute("user", user);
		return "index";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String getLogin (ModelMap model) { 
		return "login";
	}
	
	@RequestMapping(value="/loginFailed", method=RequestMethod.GET)
	public String getLoginFailed (ModelMap model) { 
		model.addAttribute("error", "true");
		return "login";
	}
	
	@RequestMapping(value="/createAccount", method=RequestMethod.GET)
	public String getCreateAccount (@ModelAttribute ("userProfile") UserEntity userProfile) {
		return "createAccount";
	}
	
	@RequestMapping(value="/createAccount", method=RequestMethod.POST)
	public String postCreateAccount (@Valid @ModelAttribute("userProfile") UserEntity userProfile, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return "createAccount";
		} else {
			try {
				loginService.createAccount(userProfile);
				return "redirect:login.html";
			} catch (Exception e) {
				model.addAttribute("error", "true");
				return "createAccount";
			}
			
		}
	}
}
