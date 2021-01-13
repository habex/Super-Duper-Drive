package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/home")
public class HomeController {

    
    private NoteService noteService;
    private CredentialService credentialService;
    private FileService fileService;
    public static String status;
    private EncryptionService encryptionService;
    private UserService userService;

    public HomeController(NoteService noteService, CredentialService credentialService, FileService fileService, EncryptionService encryptionService, UserService userService) {
        this.encryptionService = encryptionService;
        this.userService = userService;
        status="added";
        this.noteService = noteService;
        this.credentialService = credentialService;
        this.fileService = fileService;
    }

    @GetMapping
    public String getHomePage(Model model, Authentication authentication) {

        Integer userId = userService.getUserByName(authentication.getName()).getUserId();

        addAttributes(model,userId,"files","HOME PAGE");
        return  "home";
    }

    public void addAttributes(Model model, Integer userId, String activeTab,String message) {

        model.addAttribute("credentials",this.credentialService.findAll(userId));
        model.addAttribute("files",this.fileService.findAll(userId));
        model.addAttribute("notes", this.noteService.findAll(userId));
        model.addAttribute("encryptionService", encryptionService);

        model.addAttribute("activeTab", activeTab);
        model.addAttribute("message", message);
    }
}
