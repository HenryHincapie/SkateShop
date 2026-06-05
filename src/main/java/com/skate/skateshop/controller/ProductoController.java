package com.skate.skateshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.skate.skateshop.model.Producto;
import com.skate.skateshop.service.ProductoService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class ProductoController {

    @Autowired
    private ProductoService service;


    @GetMapping("/")
    public String listarProductos(Model model) {

        model.addAttribute(
                "productos",
                service.listarProductos());

        return "index";
    }
    @GetMapping("/productos")
    public String administrarProductos(
            Model model,
            HttpSession session) {

        if(session.getAttribute("usuario") == null) {

            return "redirect:/login";
        }

        model.addAttribute(
                "productos",
                service.listarProductos());

        return "productos";
    }

    @GetMapping("/nuevo")
    public String nuevoProducto(Model model) {

        model.addAttribute(
                "producto",
                new Producto());

        return "nuevo";
    }


    @PostMapping("/guardar")
    public String guardarProducto(

            @Valid
            @ModelAttribute Producto producto,

            BindingResult result,

            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {

            return producto.getId() == null
                    ? "nuevo"
                    : "editar";
        }

        try {

            service.guardarProducto(producto);

            redirectAttributes.addFlashAttribute(
                    "success",
                    "Producto guardado correctamente");

        } catch (Exception e) {

            redirectAttributes.addFlashAttribute(
                    "error",
                    "Error al guardar el producto");

        }

        return "redirect:/productos";
}


    @GetMapping("/editar/{id}")
    public String editarProducto(
            @PathVariable Long id,
            Model model) {

        Producto producto =
                service.obtenerProducto(id);

        model.addAttribute("producto", producto);

        return "editar";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes) {

        try {

            service.eliminarProducto(id);

            redirectAttributes.addFlashAttribute(
                    "success",
                    "Producto eliminado correctamente");

        } catch (Exception e) {

            redirectAttributes.addFlashAttribute(
                    "error",
                    e.getMessage());
        }

        return "redirect:/productos";
    }
}