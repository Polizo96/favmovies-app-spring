package gr.aueb.cf.moviesapp.controller;

import javax.validation.Valid;


import gr.aueb.cf.moviesapp.dto.RegisterUserDTO;
import gr.aueb.cf.moviesapp.model.User;
import gr.aueb.cf.moviesapp.service.ISecurityService;
import gr.aueb.cf.moviesapp.service.IUserService;
import gr.aueb.cf.moviesapp.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

	private final UserValidator userValidator;

	private final IUserService userService;

	private final ISecurityService securityService;

	@Autowired
	public RegisterController(UserValidator userValidator, IUserService userService,
							  ISecurityService securityService) {
		this.userValidator = userValidator;
		this.userService = userService;
		this.securityService = securityService;
	}

	@GetMapping("/register")
	public String login(Model model) {
		model.addAttribute("userForm", new RegisterUserDTO());
		return "register";
	}

	@PostMapping("/register")
	public String registration(@Valid @ModelAttribute("userForm") RegisterUserDTO userDTO,
			BindingResult bindingResult) {
		userValidator.validate(userDTO, bindingResult);

		if (bindingResult.hasErrors()) {
			return "register";
		}
		User createdUser = userService.registerUser(userDTO);

		// login with the newly created account and redirect to search page
		securityService.autoLogin(createdUser);

		return "redirect:/search";
	}
}