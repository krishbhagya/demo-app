package com.example.demo.controller;

import com.example.demo.domain.Spice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SpiceServiceController {

    private static Map<Integer, Spice> spiceRepo = new HashMap<>();
   Logger logger = LoggerFactory.getLogger(SpiceServiceController.class);

    static {
        Spice chiliPowder = new Spice();
        chiliPowder.setName("Chili powder");
        chiliPowder.setSpecialName("Meat curry");
        chiliPowder.setSpiceId(1);

        spiceRepo.put(chiliPowder.getSpiceId(), chiliPowder);

        Spice curryPowder = new Spice();
        curryPowder.setName("Curry Powder");

        spiceRepo.put(curryPowder.getSpiceId(), curryPowder);

    }

    @RequestMapping(value="/demo")
    public ResponseEntity<Object> getSpice(){
        return new ResponseEntity<>(spiceRepo.values(), HttpStatus.OK);

    }

    @RequestMapping(value="/demo", method= RequestMethod.POST)
    public ResponseEntity<Object>createSpice(@RequestBody Spice spice){
        spiceRepo.put(spice.getSpiceId(), spice);
       logger.info("************* Spice details: " + spice.getName());

        return new ResponseEntity<>("Spice is created successfully", HttpStatus.CREATED);

    }

    @RequestMapping(value="/demo/{spiceId}", method= RequestMethod.PUT)
    public ResponseEntity<Object> updateSpice(@PathVariable("spiceId") int spiceId, @RequestBody Spice spice) {
        spiceRepo.remove(spiceId);
        spice.setSpiceId(spiceId);

        spiceRepo.put(spiceId, spice);

        return new ResponseEntity<>("Product is updated successsfully", HttpStatus.OK);
    }

    @RequestMapping(value="/demo/{spiceId}", method= RequestMethod.DELETE)
    public ResponseEntity<Object> deleteSpice(@PathVariable("spiceId") int spiceId){
        spiceRepo.remove(spiceId);
        return new ResponseEntity<>("Product is deleted successsfully", HttpStatus.OK);

    }
}
