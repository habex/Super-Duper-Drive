package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.SQLException;

@Controller
//@RequestMapping("/files")
public class FileController {

    private NoteService noteService;
    private UserService userService;
    private AuthenticationService authenticationService;
    private CredentialService credentialService;
    private FileService fileService;
    private HashService hashService;

    public FileController(NoteService noteService, UserService userService, AuthenticationService authenticationService, CredentialService credentialService, FileService fileService, HashService hashService) {
        this.noteService = noteService;
        this.userService = userService;
        this.authenticationService = authenticationService;
        this.credentialService = credentialService;
        this.fileService = fileService;
        this.hashService = hashService;
    }

    @GetMapping("/files")
    public String getFiles(Model model) {

        model.addAttribute("activeTab", "files");
        model.addAttribute("credentials", this.credentialService.findAll());
        model.addAttribute("files", this.fileService.findAll());
        model.addAttribute("notes", this.noteService.findAll());

        return "home";

    }

    @PostMapping("/files")
    public String upload(MultipartFile file, @ModelAttribute("files") File file1, Model model) {
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

            file1.setFileName(file.getOriginalFilename());
            file1.setContentType(file.getContentType());
            file1.setFileSize(file.getSize());
            file1.setFileData(file.getBytes());
            this.fileService.upLoad(file1);


            model.addAttribute("message", "You successfully uploaded " + file1.getFileName() + " !");
        } catch (IOException e) {
        }

        model.addAttribute("activeTab", "files");
        model.addAttribute("credentials", this.credentialService.findAll());
        model.addAttribute("files", this.fileService.findAll());
        model.addAttribute("notes", this.noteService.findAll());

        return "home";
    }


    @RequestMapping(path = "/files/view/{fileId}")
    public ResponseEntity serveFile(@PathVariable("fileId") Integer fileId, Model model) throws SQLException {

        model.addAttribute("activeTab", "files");
        model.addAttribute("credentials", this.credentialService.findAll());
        model.addAttribute("files", this.fileService.findAll());
        model.addAttribute("notes", this.noteService.findAll());

        File file = fileService.getFile(fileId);
//        byte[] bytes = file.getFileData();
//        InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(bytes));
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Content-Disposition", String.format("attachment; filename=" + file.getFileName()));

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(file.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
                .body(file.getFileData());

        /*return ResponseEntity.ok()
                .headers(headers)
                .contentLength(bytes.length)
                .contentType(MediaType.valueOf("application/octet-stream"))
                .body(resource);*/
    }


    @GetMapping("/files/delete/{fileId}")
    public String deleteFile(@PathVariable Integer fileId, Model model) {
        int result = fileService.delete(fileId);

        if (result >= 1) {
            model.addAttribute("message", "File deleted successfully !");
        } else {
            model.addAttribute("message", "Unsuccessfully to delete the File !");
        }

        model.addAttribute("activeTab", "files");
        model.addAttribute("credentials", this.credentialService.findAll());
        model.addAttribute("files", this.fileService.findAll());
        model.addAttribute("notes", this.noteService.findAll());
        return "home";
    }


}
