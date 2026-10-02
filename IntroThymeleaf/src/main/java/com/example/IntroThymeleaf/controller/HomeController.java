package com.example.IntroThymeleaf.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    @GetMapping("/")
    public String index(@RequestParam(name = "nombre", required = false, defaultValue = "Invitado") String nombre, Model model) {
        model.addAttribute(
                "mensajeBienvenido",
                "¡Bienvenido a mi aplicacion web en Spring Boot.!"
        );

        model.addAttribute("nombre", nombre);
        return "index";
    }

    @GetMapping("/saludo")
    public String saludo(Model model, HttpSession session) {
        LocalTime now = LocalTime.now();
        int hora = now.getHour();

        String saludo;
        String claseCSS;

        if (hora >= 5 && hora < 12) {
            saludo = "Buenos dias es hora de programar";
            claseCSS = "color:#198754";
        }

        else if (hora >= 12 && hora < 19) {
            saludo = "Buenos tardes es hora de programar";
            claseCSS = "color:#0d6efd";
        }
        else {
            saludo = "Buenas noches a descansar";
            claseCSS = "color:#dc3545";
        }
        session.setAttribute("rol", "Admin");
        model.addAttribute("saludo", saludo);
        model.addAttribute("claseCSS", claseCSS);
        return "saludo";
    }

    @GetMapping("/catalogo")
    public String catalogo(Model model) {
        List<Map<String, Object>> juegos = new ArrayList<>();


        juegos.add(crearJuego("The Legend of Zelda: Tears of the Kingdom", "Aventura", "Nintendo Switch", 69.99, true));
        juegos.add(crearJuego("Elden Ring", "RPG", "Multiplataforma", 59.99, true));
        juegos.add(crearJuego("Bloodborne", "Action RPG", "PlayStation 4", 19.99, false));
        juegos.add(crearJuego("Cyberpunk 2077", "RPG", "PC / Consolas", 29.99, true));


        model.addAttribute("juegos", juegos);
        return "catalogo";
    }


    // Método auxiliar privado (no es un endpoint)
    private Map<String, Object> crearJuego(String titulo, String genero, String plataforma, double precio, boolean disponible) {
        Map<String, Object> juego = new HashMap<>();
        juego.put("titulo", titulo);
        juego.put("genero", genero);
        juego.put("plataforma", plataforma);
        juego.put("precio", precio);
        juego.put("disponible", disponible);
        return juego;
    }


}
