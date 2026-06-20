package com.tiendaropa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.tiendaropa.model.Usuario;
import com.tiendaropa.service.UsuarioService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

	@Autowired
	UsuarioService service;

	@GetMapping("/login")
	public String login() {

		return "login";

	}

	@PostMapping("/validar")
	public String validar(@RequestParam String usuario, @RequestParam String clave, HttpSession session, Model model) {

		System.out.println("Usuario: " + usuario);
		System.out.println("Clave: " + clave);

		Usuario u = service.login(usuario, clave);

		if (u != null) {

			session.setAttribute("usuario", u);

			return "redirect:/";

		}

		model.addAttribute("error", "Usuario o clave incorrectos");

		return "login";

	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {

		session.invalidate();

		return "redirect:/login";

	}

}