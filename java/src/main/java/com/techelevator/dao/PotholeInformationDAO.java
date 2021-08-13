package com.techelevator.dao;

import com.techelevator.model.PotholeInformation;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface PotholeInformationDAO {
    List<PotholeInformation> getPotholes();

    void deletePothole(int id);

    void createReport(PotholeInformation pothole);

    void updateSeverity(PotholeInformation pothole);

    void updatePicture(MultipartFile file, int id) throws IOException;

    String listImages();
}
