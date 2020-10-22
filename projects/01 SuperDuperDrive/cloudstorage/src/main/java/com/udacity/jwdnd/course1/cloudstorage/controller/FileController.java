package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

@Controller
@RequestMapping("/file")
public class FileController {

    private FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping(path = "/{fileId}")
    public String getFile(Model model, Integer fileId) {
        model.addAttribute("file", this.fileService.getFile(fileId));
        return "home";
    }

    @PostMapping
    public String handleFileUpload(MultipartFile file, Model model)    {

        try {
            if (file.isEmpty()) {
                model.addAttribute("message",
                        "Please select a file!");
                throw new IOException("File Not FoundException");
            }

            if(this.fileService.getFileByFileName(file.getOriginalFilename()) != null){
                model.addAttribute("message","\""
                        +file.getOriginalFilename() +"\"  exists in the database!!! Please upload another file!");
                throw new IOException("File name exists");
            }

                // Converting MultipartFile this projects File
                File file1 = new File();
                file1.setFileName(file.getOriginalFilename());
                file1.setContentType(file.getContentType());
                file1.setFileSize(file.getSize());
                file1.setFileData(blobUtil(file));
                this.fileService.upLoad(file1);
                model.addAttribute("files", this.fileService.findAll());
                model.addAttribute("message","You successfully uploaded " + file1.getFileName() + " !");
           }catch (IOException | SQLException e){
        }
        model.addAttribute("files", this.fileService.findAll());
        return "home";
    }

    @GetMapping ("/delete/{fileId}")
    public String deleteFile(@PathVariable Integer fileId, Model model) {
        int result = fileService.delete(fileId);

        if(result>=1){
            model.addAttribute("message","File deleted successfully !");
        }else {
            model.addAttribute("message","Unsuccessfully to delete the File !");
        }
        model.addAttribute("files", this.fileService.findAll());
        return "home";
    }


    public Blob blobUtil(MultipartFile file) throws IOException, SQLException {
        Blob blob = new SerialBlob(file.getBytes());
        return blob;
     }
}
