package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/note")
public class NoteController {


    private UserService userService;
    private AuthenticationService authenticationService;
    private EncryptionService encryptionService;
    private NoteService noteService;
    private CredentialService credentialService;
    private FileService fileService;

    public NoteController(NoteService noteService, UserService userService, AuthenticationService authenticationService, CredentialService credentialService, FileService fileService, HashService hashService, EncryptionService encryptionService) {
        this.noteService = noteService;
        this.userService = userService;
        this.authenticationService = authenticationService;
        this.credentialService = credentialService;
        this.fileService = fileService;
        this.encryptionService = encryptionService;
    }

    @PostMapping
    public String postNote( Authentication authentication, @ModelAttribute("notes") Note note, Model model) {


        Integer userId = userService.getUserByName(authentication.getName()).getUserId();
        this.noteService.addNote(note);
        model.addAttribute("message", "Note " + HomeController.status +" successfully !");

        model.addAttribute("activeTab", "notes");
        model.addAttribute("credentials",this.credentialService.findAll(userId));
        model.addAttribute("files",this.fileService.findAll(userId));
        model.addAttribute("notes", this.noteService.findAll(userId));
        return "home";
    }


    @GetMapping("/delete/{noteId}")
    public String deleteNote(Authentication authentication, @PathVariable Integer noteId, Model model) {


        Integer userId = userService.getUserByName(authentication.getName()).getUserId();
        int result = noteService.delete(noteId);

        if (result >= 1) {
            model.addAttribute("message", "Note deleted successfully !");
        } else {
            model.addAttribute("message", "Unsuccessfully to delete the Note !");
        }
        model.addAttribute("activeTab", "notes");
        model.addAttribute("credentials",this.credentialService.findAll(userId));
        model.addAttribute("files",this.fileService.findAll(userId));
        model.addAttribute("notes", this.noteService.findAll(userId));
        return "home";
    }


}

