package com.journaldev.spring.controller;

import java.util.ArrayList;
import java.util.Locale;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.journaldev.spring.model.User;
import api.apis.login.AccessLoginDB;
import api.apis.login.LoginBean;
import api.apis.login.RegisterBean;
import api.apis.login.RegisterUserDB;

@Controller
public class HomeController {

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		System.out.println("Home Page Requested, locale = " + locale);
		return "Login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Locale locale, Model model) {
		System.out.println("Login Page Requested, locale = " + locale);
		return "Login";
	}

	@RequestMapping(value = "/home", method = RequestMethod.POST)
	public String home(@Validated LoginBean user, Model model) {
		System.out.println("home Page Requested");
		model.addAttribute("userName", user.getUsername());
		model.addAttribute("uPassword", user.getPassword());
		if (user.getUsername().isEmpty() || user.getPassword().isEmpty()) // Validation
		{
			return "Login";
		} else {

			AccessLoginDB accessLoginDB = new AccessLoginDB();
			String accessValidate = accessLoginDB.readLogin(user); // Connecting to DB and Business logic to verify the
			System.out.println(accessLoginDB.getUserType(user)); // usertype value
			int usertype = accessLoginDB.getUserType(user);
			System.out.println("DB Value ::::" + accessValidate);
			if (accessValidate.equals("SUCCESS") && usertype == 1) // If function returns success then user will be
																	// rooted to Home page
			{
				return "LoginAction";
			} else if (accessValidate.equals("SUCCESS") && usertype == 2) {
				return "LoginActionAdmin";
			} else {

				return "Login";
			}
		}

	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@Validated RegisterBean reg, Model model) {
		System.out.println("inserting profile values to db");
		model.addAttribute("firstName", reg.getFirstName());
		model.addAttribute("lastName", reg.getLastName());
		model.addAttribute("password", reg.getPassword());
		model.addAttribute("contact", reg.getContact());
		model.addAttribute("address", reg.getAddress());
		model.addAttribute("username", reg.getUserName());
		model.addAttribute("username", reg.getEmail());
		model.addAttribute("confirmpassword", reg.getConfirmpassword());
		if ((reg.getUserName().isEmpty() || reg.getPassword().isEmpty())
				&& (reg.getUserName() != reg.getConfirmpassword())) // Validation
		{
			return "RegistrationPage";
		} else {

			RegisterUserDB accessLoginDB = new RegisterUserDB();
			String accessValidate = accessLoginDB.registerUser(reg); // Connecting to DB and Business logic to verify
																		// the
																		// user
			System.out.println("DB Value ::::" + accessValidate);
			if (accessValidate.equals("SUCCESS")) // If function returns success then user will be rooted to Home page
			{
				return "Login";
			} else {

				return "Login";
			}
		}

	}

	@RequestMapping(value = "/viewUsers/{id}", method = RequestMethod.GET)
	public String viewUsers(@PathVariable("id") int id, Model model) {
		System.out.println("View Users Page Requested");
		AccessLoginDB accessLoginDB = new AccessLoginDB();
		ArrayList accessValidate = accessLoginDB.getUsersBasedOnUserType(id);
		model.addAttribute("allUsers", accessValidate);
		return "displayUsers";
	}

	@RequestMapping(value = "/displayUsers/{id}", method = RequestMethod.GET)
	public String viewUsersJson(@PathVariable("id") int id, Model model) {
		System.out.println("View Users Page Requested");
		AccessLoginDB accessLoginDB = new AccessLoginDB();
		ArrayList accessValidate = accessLoginDB.getUsersBasedOnUserType(id);
		model.addAttribute("allUsers", accessValidate);
		return "Hi";

	}

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String profile(@Validated User user, Model model) {
		System.out.println("Registration Page Requested");
		return "RegistrationPage";
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String user(@Validated User user, Model model) {
		System.out.println("User Page Requested");
		model.addAttribute("userName", user.getUserName());
		return "user";
	}
}
