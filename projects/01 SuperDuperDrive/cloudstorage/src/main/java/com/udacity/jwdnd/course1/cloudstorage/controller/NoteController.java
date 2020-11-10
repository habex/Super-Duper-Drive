package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String postNote( @ModelAttribute("notes") Note note, Model model) {

        this.noteService.addNote(note);
        model.addAttribute("message", "Note " + HomeController.status +" successfully !");

        model.addAttribute("activeTab", "notes");
        model.addAttribute("credentials",this.credentialService.findAll());
        model.addAttribute("files",this.fileService.findAll());
        model.addAttribute("notes", this.noteService.findAll());
        return "home";
    }

    @GetMapping("/delete/{noteId}")
    public String deleteNote(@PathVariable Integer noteId,AuthenticationService authenticationService, Model model) {
        int result = noteService.delete(noteId);

        if (result >= 1) {
            model.addAttribute("message", "Note deleted successfully !");
        } else {
            model.addAttribute("message", "Unsuccessfully to delete the Note !");
        }
        model.addAttribute("activeTab", "notes");
        model.addAttribute("credentials",this.credentialService.findAll());
        model.addAttribute("files",this.fileService.findAll());
        model.addAttribute("notes", this.noteService.findAll());
        return "home";
    }


}

