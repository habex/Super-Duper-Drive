package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/credential")
public class CredentialController {

    private NoteService noteService;
    private UserService userService;
    private AuthenticationService authenticationService;
    private CredentialService credentialService;
    private FileService fileService;
    private HashService hashService;


    public CredentialController(NoteService noteService, UserService userService, AuthenticationService authenticationService, CredentialService credentialService, FileService fileService, HashService hashService) {
        this.noteService = noteService;
        this.userService = userService;
        this.authenticationService = authenticationService;
        this.credentialService = credentialService;
        this.fileService = fileService;
        this.hashService = hashService;
    }

    @PostMapping
    public String postCredential(@ModelAttribute("credentials") Credential credential, Model model) {

        this.credentialService.addCredential(credential);
        model.addAttribute("message", "Credential " + HomeController.status +" successfully !");

        model.addAttribute("activeTab", "credentials");
        model.addAttribute("credentials", this.credentialService.findAll());
        model.addAttribute("files", this.fileService.findAll());
        model.addAttribute("notes", this.noteService.findAll());
        return "home";
    }

    @GetMapping("/delete/{credentialId}")
    public String deleteNote(@PathVariable Integer credentialId, Model model) {
        int result = credentialService.delete(credentialId);

        if (result >= 1) {
            model.addAttribute("message", "Credential deleted successfully !");
        } else {
            model.addAttribute("message", "Unsuccessfully to delete the Credential !");
        }
        model.addAttribute("activeTab", "credentials");
        model.addAttribute("credentials", this.credentialService.findAll());
        model.addAttribute("files", this.fileService.findAll());
        model.addAttribute("notes", this.noteService.findAll());
        return "home";
    }

}
