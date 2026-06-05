package com.skate.skateshop.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skate.skateshop.model.Producto;
import com.skate.skateshop.model.Transaccion;
import com.skate.skateshop.repository.ProductoRepository;
import com.skate.skateshop.repository.TransaccionRepository;

@Service
public class TransaccionService {

    @Autowired
    private TransaccionRepository transaccionRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public void registrarTransaccion(
            Long productoId,
            Integer cantidad) {

        Producto producto =
                productoRepository.findById(productoId)
                .orElseThrow();

        if (cantidad <= 0) {
            throw new RuntimeException(
                    "Cantidad inválida");
        }

        if (producto.getStock() < cantidad) {
            throw new RuntimeException(
                    "Stock insuficiente");
        }

        double total =
                producto.getPrecio() * cantidad;

        producto.setStock(
                producto.getStock() - cantidad);

        productoRepository.save(producto);

        Transaccion transaccion =
                new Transaccion();

        transaccion.setProducto(producto);

        transaccion.setCantidad(cantidad);

        transaccion.setTotal(total);

        transaccion.setFecha(LocalDate.now());

        transaccionRepository.save(transaccion);
    }

    public List<Transaccion> listar() {
    return transaccionRepository.findAllByOrderByIdDesc();
}
}