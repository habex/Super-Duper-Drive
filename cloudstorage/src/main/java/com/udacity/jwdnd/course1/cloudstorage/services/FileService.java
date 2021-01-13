package com.udacity.jwdnd.course1.cloudstorage.services;


import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class FileService {

    private FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    @PostConstruct
    public void postConstruct (){
        System.out.println("Creating FileService bean");
    }

    public List<File> findAll(Integer userId){
        return fileMapper.getAllFiles(userId);
    }

    public File getFile(Integer fileId){
        return fileMapper.getFile(fileId);
    }


    public File getFileByFileName(String fileName){
        return fileMapper.getFileByFileName(fileName);
    }

    public void upLoad(File file ){
        fileMapper.upload(file);
    }

    public File findFileById(Integer fileId) {
       return fileMapper.findFileById(fileId);
    }

    public int delete(Integer fileId) {
       return fileMapper.delete(fileId);
    }
}
