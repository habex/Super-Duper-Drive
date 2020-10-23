package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.File1;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.apache.ibatis.annotations.Param;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;

@Controller
@RequestMapping("/file")
public class FileController {

    private FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

   /* //@RequestMapping(path = "/{fileId}")
    public File getFile(@PathVariable Integer fileId, HttpServletResponse response) {

        File file = fileService.getFile(fileId);

        System.out.println(fileId + "  " + file.getFileName());
        try {
            response.setHeader("Content-Disposition", "inline;filename=\"" + file.getFileName() + "\"");
            OutputStream out = response.getOutputStream();
            response.setContentType(file.getContentType());
            //IOUtils.copy(file.getFileData().getBinaryStream(), out);
            out.flush();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }*/


    @RequestMapping(path = "/{fileId}")
    public ResponseEntity<Resource> serveFile(@PathVariable Integer fileId) throws SQLException {

        File file = fileService.getFile(fileId);

        System.out.println(file.getFileName() + " ==  " +file.getFileData().length);

        byte[] bytes = file.getFileData();
        InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(bytes));
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Disposition", String.format("attachment; filename=" + file.getFileName()));
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(bytes.length)
                .contentType(MediaType.valueOf("application/octet-stream"))
                .body(resource);
    }


    @PostMapping
    public String upload(MultipartFile file, Model model) {

        try {
            if (file.isEmpty()) {
                model.addAttribute("message",
                        "Please select a file!");
                throw new IOException("File Not FoundException");
            }

            if (this.fileService.getFileByFileName(file.getOriginalFilename()) != null) {
                model.addAttribute("message", "\""
                        + file.getOriginalFilename() + "\"  exists in the database!!! Please upload another file!");
                throw new IOException("File name exists");
            }

            // Converting MultipartFile this projects File
            File file1 = new File();
            file1.setFileName(file.getOriginalFilename());
            file1.setContentType(file.getContentType());
            file1.setFileSize(file.getSize());
            file1.setFileData(file.getBytes());
            this.fileService.upLoad(file1);
            model.addAttribute("files", this.fileService.findAll());
            model.addAttribute("message", "You successfully uploaded " + file1.getFileName() + " !");
        } catch (IOException e) {
        }
        model.addAttribute("files", this.fileService.findAll());
        return "home";
    }

    @GetMapping("/delete/{fileId}")
    public String deleteFile(@PathVariable Integer fileId, Model model) {
        int result = fileService.delete(fileId);

        if (result >= 1) {
            model.addAttribute("message", "File deleted successfully !");
        } else {
            model.addAttribute("message", "Unsuccessfully to delete the File !");
        }
        model.addAttribute("files", this.fileService.findAll());
        return "home";
    }


    public Blob blobUtil(MultipartFile file) throws IOException, SQLException {
        Blob blob = new SerialBlob(file.getBytes());
        return blob;
    }
}
