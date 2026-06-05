package com.skate.skateshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.skate.skateshop.service.ProductoService;
import com.skate.skateshop.service.TransaccionService;

@Controller
@RequestMapping("/ventas")
public class TransaccionController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private TransaccionService transaccionService;

    @GetMapping
    public String formularioVenta(Model model) {

        model.addAttribute(
                "productos",
                productoService.listarProductos());

        model.addAttribute(
                "transacciones",
                transaccionService.listar());

        return "ventas";
    }

    @PostMapping
    public String registrarVenta(

            @RequestParam Long productoId,

            @RequestParam Integer cantidad,

            RedirectAttributes redirectAttributes) {

        try {

            transaccionService.registrarTransaccion(
                    productoId,
                    cantidad);

            redirectAttributes.addFlashAttribute(
                    "success",
                    "Venta registrada correctamente");

        } catch (Exception e) {

            redirectAttributes.addFlashAttribute(
                    "error",
                    e.getMessage());
        }

        return "redirect:/ventas";
    }
}