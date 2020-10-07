package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/home")
public class NoteController {

    private NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }


    @PostMapping(path = "/note")
    public String postNote(Note note, Model model) {
        this.noteService.addNote(note);
        model.addAttribute("notes", this.noteService.getNotes());
        return "home";
    }

    @PutMapping(path = "/note")
    public String update(Note note) {
        noteService.update(note);
        return "home";
    }

    @DeleteMapping (path = "/note")
    public void delete(Note note) {
        noteService.delete(note);
    }
}
