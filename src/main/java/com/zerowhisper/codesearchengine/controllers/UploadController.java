package com.zerowhisper.codesearchengine.controllers;

import com.zerowhisper.codesearchengine.services.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;


@RestController
public class UploadController {


    private final ProcessService processService;

    @Autowired
    public UploadController(ProcessService processService) {
        this.processService = processService;
    }

    @PostMapping("/uploadZipFile")
    public ResponseEntity<?> uploadZipFile(@RequestParam("zipFile") MultipartFile zipFile) {

        //First Check the file is not empty and it's a zipFile
        if (zipFile.isEmpty() || !Objects.requireNonNull(zipFile.getOriginalFilename()).endsWith(".zip")) {
            return ResponseEntity.badRequest().body("Invalid file type. Please upload a ZIP file.");
        }

        try {
            //Pass the zipFile To upload service to Unzip it and the pass
            System.out.println(zipFile.getOriginalFilename());
            processService.processPKG(zipFile);
            return ResponseEntity.ok().body("Successfully unzipped file.\n FileName: " + zipFile.getOriginalFilename());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    //end Uploading the File Here

}
