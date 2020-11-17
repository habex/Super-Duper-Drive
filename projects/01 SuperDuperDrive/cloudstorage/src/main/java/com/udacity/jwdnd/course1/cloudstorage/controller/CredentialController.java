package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/credential")
public class CredentialController {

    private NoteService noteService;
    private CredentialService credentialService;
    private FileService fileService;
    private EncryptionService encryptionService;
    private UserService userService;

    public CredentialController(NoteService noteService, CredentialService credentialService, FileService fileService, EncryptionService encryptionService, UserService userService) {
        this.noteService = noteService;
        this.credentialService = credentialService;
        this.fileService = fileService;
        this.encryptionService = encryptionService;
        this.userService = userService;
    }

    @PostMapping
    public String postCredential(Authentication authentication, Credential credential, Model model) {

        Integer userId = userService.getUserByName(authentication.getName()).getUserId();

        if(credential.getUserId() ==null){
            credential.setUserId(userId);
        }
        this.credentialService.addCredential(credential);
        model.addAttribute("message", "Credential " + HomeController.status +" successfully !");

        model.addAttribute("activeTab", "credentials");
        model.addAttribute("credentials", this.credentialService.findAll(userId));
        model.addAttribute("files", this.fileService.findAll(userId));
        model.addAttribute("notes", this.noteService.findAll(userId));
        model.addAttribute("encryptionService", encryptionService);

        return "home";
    }

    @GetMapping(value = "/{credentialId}")
    @ResponseBody
    public Credential getCredential(Authentication authentication,@PathVariable(name = "credentialId") String credentialID ) {
        Integer credentialId = Integer.parseInt(credentialID);
        return credentialService.getCredential(credentialId);
    }

    @GetMapping("/delete/{credentialId}")
    public String deleteNote(Authentication authentication, @PathVariable Integer credentialId, Model model) {
        Integer userId = userService.getUserByName(authentication.getName()).getUserId();
        int result = credentialService.delete(credentialId);

        if (result >= 1) {
            model.addAttribute("message", "Credential deleted successfully !");
        } else {
            model.addAttribute("message", "Unsuccessfully to delete the Credential !");
        }
        model.addAttribute("activeTab", "credentials");
        model.addAttribute("credentials", this.credentialService.findAll(userId));
        model.addAttribute("files", this.fileService.findAll(userId));
        model.addAttribute("notes", this.noteService.findAll(userId));
        return "home";
    }


}
