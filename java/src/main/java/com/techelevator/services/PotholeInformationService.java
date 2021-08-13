package com.techelevator.services;

import com.techelevator.dao.JdbcPotholeInformation;
import com.techelevator.model.PotholeInformation;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PotholeInformationService {
    private JdbcPotholeInformation potholeInformation;

//    public PotholeInformationService() {}

    public PotholeInformationService(JdbcPotholeInformation potholeInformation) {
        this.potholeInformation = potholeInformation;
    }

    public List<PotholeInformation> list() {
        return potholeInformation.getPotholes();
    }

    public void delete(int id) { potholeInformation.deletePothole(id); }

    public void create(PotholeInformation pothole) { potholeInformation.createReport(pothole); }

    public void updateSeverity(PotholeInformation pothole) {potholeInformation.updateSeverity(pothole);}

    public void updatePicture(MultipartFile file, int id) throws IOException {
        potholeInformation.updatePicture(file, id);
    }

    public String listImages() { return potholeInformation.listImages(); }
}
