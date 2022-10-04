package com.example.lab6.controller;

import com.example.lab6.pojo.Wizard;
import com.example.lab6.repository.WizardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WizardController {
    @Autowired
    private WizardService wizardService;

    @RequestMapping(value = "/wizards", method = RequestMethod.GET)
    public ResponseEntity<?> getAllWizards(){
        return ResponseEntity.ok(wizardService.getAll());
    }

    @RequestMapping(value = "/addWizard", method = RequestMethod.POST)
    public ResponseEntity<?> addWizard(@RequestBody Wizard wizard){
        return ResponseEntity.ok(wizardService.createWizard(wizard));
    }

    @RequestMapping(value ="/updateWizard", method = RequestMethod.POST)
    public boolean updateWizard(@RequestBody Wizard wizard) {
        return wizardService.updateWizard(wizard);
    }
    
    @RequestMapping(value = "/deleteWizard", method = RequestMethod.POST)
    public boolean deleteWizard(@RequestBody Wizard wizard){
        return wizardService.deleteWizard(wizard);
    }

}
