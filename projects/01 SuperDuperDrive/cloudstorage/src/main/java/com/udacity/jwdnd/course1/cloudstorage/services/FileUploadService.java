package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.FileUploadForm;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

//@Service
public class FileUploadService {

    private FileMapper fileMapper;

    public FileUploadService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    @PostConstruct
    public void postConstruct (){
        System.out.println("Creating FileUploadService bean");
    }

    public void upLoadFile (FileUploadForm fileUploadForm){

        File file = new File();


    }
}
