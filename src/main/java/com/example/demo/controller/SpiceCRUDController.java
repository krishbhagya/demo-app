package com.example.demo.controller;

import com.example.demo.domain.Spice;
import com.example.demo.repository.SpicesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path="/demo")
public class DatabaseController {

    @Autowired
    private SpicesRepository spicesRepository;

    Logger logger = LoggerFactory.getLogger(DatabaseController.class);

    @PostMapping() // Map ONLY POST Requests
    public @ResponseBody String addNewSpice (@RequestBody Spice spice ) {

        logger.info("************* Spice details: " + spice.getName());
        spicesRepository.save(spice);

        return "Saved";
    }

    @GetMapping()
    public @ResponseBody Iterable<Spice> getAllSpices() {
        // This returns a JSON or XML with the spices
        return spicesRepository.findAll();
    }

    @GetMapping(value="/{spiceId}")
    public @ResponseBody Optional<Spice> getSpice(@PathVariable("spiceId") int spiceId) {
        // This returns a JSON or XML with the spices
        return spicesRepository.findById(spiceId);
    }

    @DeleteMapping(value="/{spiceId}")
    public ResponseEntity<Object> deleteSpice(@PathVariable("spiceId") int spiceId){
        spicesRepository.deleteById(spiceId);
        return new ResponseEntity<>("Product is deleted successfully", HttpStatus.OK);

    }

    @PutMapping(value="/{spiceId}")
    public ResponseEntity<Object> updateSpice(@PathVariable("spiceId") int spiceId, Spice spice) {
        Optional<Spice> optionalSpice = spicesRepository.findById(spiceId);
        Spice updateSpice =  optionalSpice.get();
        if(optionalSpice.isPresent()){
            updateSpice.setSpecialName(spice.getSpecialName());
            updateSpice.setName(spice.getName());
            spicesRepository.save(updateSpice);
            return new ResponseEntity<>("Product is updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid SpiceID ", HttpStatus.BAD_REQUEST);
        }




    }
}
