package com.example.demo.controller;

import com.example.demo.domain.Spice;
import com.example.demo.repository.SpicesRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/demo")
public class DatabaseController {

    private SpicesRepository spicesRepository;

    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody String addNewSpice (@RequestParam String name
            , @RequestParam String specialName ) {

        Spice spice = new Spice();
        spice.setName(name);
        spice.setSpecialName(specialName);
        spicesRepository.save(spice);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Spice> getAllSpices() {
        // This returns a JSON or XML with the spices
        return spicesRepository.findAll();
    }
}
