package com.techelevator.controller;

import com.techelevator.dao.JdbcPotholeInformation;
import com.techelevator.dao.PotholeInformationDAO;
import com.techelevator.model.PotholeInformation;
import com.techelevator.model.Schedule;
import com.techelevator.services.PotholeInformationService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class PotholeInformationController {
      private PotholeInformationService potholeService;
//    private JdbcPotholeInformation jdbcPotholeInformation;

//    public PotholeInformationController(){};
    public PotholeInformationController(PotholeInformationService potholeService) {
        this.potholeService = potholeService;
    }

    @RequestMapping(path="/potholes", method = RequestMethod.GET)
    public List<PotholeInformation> listPotholes() {
        List<PotholeInformation> potholes = potholeService.list();
        return potholes;
    }

    @RequestMapping(path="/potholes/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAnyRole('EMPLOYEE', 'ADMIN')")
    public void deletePothole(@PathVariable int id) {
        potholeService.delete(id);
    }

    @RequestMapping(path="/potholes", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'EMPLOYEE')")
    public void createReport(@Valid @RequestBody PotholeInformation pothole) {
        potholeService.create(pothole);
    }

    @RequestMapping(path="/potholes", method = RequestMethod.PUT)
//    @PreAuthorize("hasRole('EMPLOYEE')")
    public void updateSchedule(@Valid @RequestBody PotholeInformation pothole) {
        potholeService.updateSeverity(pothole);
    }

    @RequestMapping(path="/potholes/{id}", method = RequestMethod.PUT)
//    @PreAuthorize("hasRole('EMPLOYEE')")
    public void updatePicture(@RequestParam("file") MultipartFile file, @PathVariable int id) throws IOException {
        potholeService.updatePicture(file, id);
    }

    @RequestMapping(path="/potholes/list", method = RequestMethod.GET)
    public String listImages() {
        return potholeService.listImages();
    }
}
