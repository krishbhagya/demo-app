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
@RequestMapping(path="/spices")
public class SpiceCRUDController {

    @Autowired
    private SpicesRepository spicesRepository;

    Logger logger = LoggerFactory.getLogger(SpiceCRUDController.class);

    @PostMapping() // Map ONLY POST Requests
    public ResponseEntity<Object> addSpice(@RequestBody Spice spice ) {

        try {
            logger.info("Name of the Spice : " + spice.getName());
            spicesRepository.save(spice);

            return new ResponseEntity<>("Product is added successfully", HttpStatus.OK);

        } catch (Exception e) {
            logger.info("Exception in addSpice() : " + e.getMessage());
            return new ResponseEntity<>("There is an error while adding the spice : " + e.getMessage()
                    , HttpStatus.EXPECTATION_FAILED);
        }

    }

    @GetMapping()
    public @ResponseBody Iterable<Spice> getAllSpices() {
        try {
            // This returns a JSON or XML with the spices
            return spicesRepository.findAll();
        } catch(Exception e) {
            logger.info("Exception in getAllSpices() : " + e.getMessage());
            return null;
        }

    }

    @GetMapping(value="/{spiceId}")
    public @ResponseBody Optional<Spice> getSpice(@PathVariable("spiceId") int spiceId) {
        try {
            logger.info("Retrieving Spice ID: " + spiceId);
            // This returns a JSON or XML with the spices
            return spicesRepository.findById(spiceId);

        } catch (Exception e) {
            logger.info("Exception in getSpice() : " + e.getMessage());
            return null;
        }

    }

    @DeleteMapping(value="/{spiceId}")
    public ResponseEntity<Object> deleteSpice(@PathVariable("spiceId") int spiceId){
        try {
            logger.info("Deleting Spice ID: " + spiceId);
            spicesRepository.deleteById(spiceId);
            return new ResponseEntity<>("Product is deleted successfully", HttpStatus.OK);

        } catch (Exception e) {
            logger.info("Exception in deleteSpice() : " + e.getMessage());
            return new ResponseEntity<>("There is an error while deleting the spice : " + e.getMessage()
                    , HttpStatus.EXPECTATION_FAILED);
        }

    }

    @PutMapping(value="/{spiceId}")
    public ResponseEntity<Object> updateSpice(@PathVariable("spiceId") int spiceId, @RequestBody Spice spice) {
        try {
            logger.info("Updating Spice ID: " + spiceId);

            Optional<Spice> optionalSpice = spicesRepository.findById(spiceId);

            if(optionalSpice.isPresent()){
                Spice updateSpice =  optionalSpice.get();
                updateSpice.setSpecialName(spice.getSpecialName());
                updateSpice.setName(spice.getName());
                spicesRepository.save(updateSpice);
                return new ResponseEntity<>("Product is updated successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Invalid SpiceID ", HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            logger.info("Exception in updateSpice() : " + e.getMessage());
            return new ResponseEntity<>("There is an error while updating the spice : " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }


    }
}
