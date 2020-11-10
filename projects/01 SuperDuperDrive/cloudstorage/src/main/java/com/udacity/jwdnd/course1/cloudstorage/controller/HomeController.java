package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

@Controller
@RequestMapping(value = "/home")
public class HomeController {

    private NoteService noteService;
    private CredentialService credentialService;
    private FileService fileService;

    public HomeController(NoteService noteService, CredentialService credentialService, FileService fileService) {
        this.noteService = noteService;
        this.credentialService = credentialService;
        this.fileService = fileService;
    }

    @GetMapping
    public String getHomePage(Model model) {

        model.addAttribute("credentials",this.credentialService.findAll());
        model.addAttribute("files",this.fileService.findAll());
        model.addAttribute("notes", this.noteService.findAll());

        model.addAttribute("activeTab", "files");
        model.addAttribute("message", "HOME PAGE!");
        return  "home";
    }



    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("activeTab", "notes");
        model.addAttribute("message", "HOME PAGE!");
    }
}
