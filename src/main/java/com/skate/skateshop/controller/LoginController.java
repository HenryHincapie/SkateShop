package com.skate.skateshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.skate.skateshop.model.Usuario;
import com.skate.skateshop.repository.UsuarioRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/login")
    public String mostrarLogin() {

        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){

        session.invalidate();

        return "redirect:/";
    }
    @PostMapping("/login")
    public String procesarLogin(

            @RequestParam String email,
            @RequestParam String password,

            HttpSession session,

            Model model) {

        Usuario usuario =
                usuarioRepository
                .findByEmailAndPassword(
                        email,
                        password);

        if (usuario != null) {

            session.setAttribute(
                    "usuario",
                    usuario);

            return "redirect:/admin";
        }

        model.addAttribute(
                "error",
                "Correo o contraseña incorrectos");

        return "login";
    }
}