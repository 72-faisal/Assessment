package com.controller;

import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dao.UserDao;
import com.model.User;
import com.service.Servicess;

@Controller
public class UserController {

	@Autowired
	private UserDao dao;

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/user-register")
	public String showregisterForm(Model model) {
		return "user-register";
	}

	@PostMapping("/register")
	public String registerUser(@RequestParam("fname") String fname, @RequestParam("lname") String lname,
			@RequestParam("gender") String gender, @RequestParam("contact") long contact,
			@RequestParam("address") String address, @RequestParam("email") String email,
			@RequestParam("password") String password, Model model) {
		User user = new User();
		user.setFname(fname);
		user.setLname(lname);
		user.setGender(gender);
		user.setContact(contact);
		user.setAddress(address);
		user.setEmail(email);
		user.setPassword(password);
		dao.insertUser(user);
		model.addAttribute("msg", "Data registered successfully");
		return "user-login";
	}

	@RequestMapping("/user-login")
	public String showLoginForm() {
		return "user-login";
	}

	@PostMapping("/login")
	public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password,
			HttpSession session, Model model) {
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		List<User> userFromDao = dao.userLogin(user);
		if (userFromDao == null) {
			return "user-register";
		} else {
			session.setAttribute("data", userFromDao);
			return "user-home";
		}

	}

	@RequestMapping("/user-home")
	public String userHome() {
		return "user-home";
	}

	@PostMapping("/update")
	public String updateUser(@RequestParam("id") int id, @RequestParam("name") String name,
			@RequestParam("contact") long contact, @RequestParam("address") String address,
			@RequestParam("email") String email, HttpSession session) {
		User user = new User();
		user.setId(id);
		user.setFname(name);
		user.setContact(contact);
		user.setAddress(address);
		user.setEmail(email);
		dao.updateProfile(user);
		session.setAttribute("data", user);
		return "user-home";
	}

	@PostMapping("/changePassword")
	public String changePassword(@RequestParam("id") int id, @RequestParam("op") String oldPassword,
			@RequestParam("np") String newPassword, @RequestParam("cnfpassword") String confirmNewPassword,
			Model model) {
		boolean flag = dao.checkOldPassword(oldPassword, id);
		if (flag) {
			if (newPassword.equals(confirmNewPassword)) {
				dao.updatePassword(newPassword, id);
				return "redirect:/user-home.jsp";
			} else {
				model.addAttribute("msg1", "New password and confirm new password not matched");
				return "user-change-password";
			}
		} else {
			model.addAttribute("msg", "Old password not matched");
			return "user-change-password";
		}
	}

	@PostMapping("/getOTP")
	public String getOTP(@RequestParam("email") String email, Model model) {
		Random r = new Random();
		int num = r.nextInt(999999);
		Servicess s = new Servicess();
		s.sendMail(email, num);
		model.addAttribute("email", email);
		model.addAttribute("otp", num);
		return "user-verify-otp";
	}

	@PostMapping("/verify")
	public String verifyOTP(@RequestParam("email") String email, @RequestParam("otp1") int otp1,
			@RequestParam("otp2") int otp2, Model model) {
		if (otp1 == otp2) {
			model.addAttribute("email", email);
			return "user-new-password";
		} else {
			model.addAttribute("email", email);
			model.addAttribute("otp", otp1);
			model.addAttribute("msg", "OTP not matched");
			return "user-verify-otp";
		}
	}

	@PostMapping("/newPassword")
	public String newPassword(@RequestParam("email") String email, @RequestParam("np") String newPassword,
			@RequestParam("cnfpassword") String confirmNewPassword, Model model) {
		if (newPassword.equals(confirmNewPassword)) {
			dao.updatePassword1(email, newPassword);

			return "redirect:/user-login";
		} else {
			model.addAttribute("msg", "New password and confirm new password not matched");
			return "user-new-password";
		}
	}

	

}
