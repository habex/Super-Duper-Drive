package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Update;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/home")
public class HomeController {

    private NoteService noteService;
    private CredentialService credentialService;

    public HomeController(NoteService noteService, CredentialService credentialService) {
        this.noteService = noteService;
        this.credentialService = credentialService;
    }

    @GetMapping
    public String getHomePage() {
        return "home";
    }


    @PostMapping(path = "/note")
    public String postNote(Note note, Model model) {
        this.noteService.addNote(note);
        model.addAttribute("notes", this.noteService.getNotes());
        return "home";
    }

    @PutMapping
    public void update(Note note) {
        noteService.update(note);
    }

    @DeleteMapping
    public void delete(Note note) {
        noteService.delete(note);
    }

    @PostMapping(path = "/credential")
    public String postCredential(Credential credential, Model model) {

        this.credentialService.addCredential(credential);
        model.addAttribute("credentials", this.credentialService.getCredentials());
        System.out.println(credential.toString());
        return "home";
    }
}
