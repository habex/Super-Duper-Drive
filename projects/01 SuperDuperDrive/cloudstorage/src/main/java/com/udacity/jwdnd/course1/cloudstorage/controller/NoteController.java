package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/note")
public class NoteController {

    private NoteService noteService;
    private UserService userService;
    private AuthenticationService authenticationService;
    private CredentialService credentialService;
    private FileService fileService;
    private HashService hashService;

    public NoteController(NoteService noteService, UserService userService, AuthenticationService authenticationService, CredentialService credentialService, FileService fileService, HashService hashService) {
        this.noteService = noteService;
        this.userService = userService;
        this.authenticationService = authenticationService;
        this.credentialService = credentialService;
        this.fileService = fileService;
        this.hashService = hashService;
    }

    @PostMapping
    public String postNote(@ModelAttribute("notes") Note note, Model model) {

        if (note.getNoteId() == null) {
            this.noteService.addNote(note);
            model.addAttribute("message", "Note added successfully !");
        } else {
            this.noteService.update(note);
            model.addAttribute("message", "Note updated successfully !");
        }

        model.addAttribute("activeTab", "notes");
        model.addAttribute("credentials",this.credentialService.findAll());
        model.addAttribute("files",this.fileService.findAll());
        model.addAttribute("notes", this.noteService.findAll());
        return "home";
    }

    @GetMapping("/delete/{noteId}")
    public String deleteNote(@PathVariable Integer noteId, Model model) {
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

