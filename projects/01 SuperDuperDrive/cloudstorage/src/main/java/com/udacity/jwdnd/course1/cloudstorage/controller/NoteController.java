package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/note")
public class NoteController {

    private NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }


    @PostMapping
    public String postNote(Note note, Model model) {

        if(note.getNoteId() == null){
            this.noteService.addNote(note);
            model.addAttribute("message", "Note added successfully !");
        }else {
            this.noteService.update(note);
            model.addAttribute("message", "Note updated successfully !");
        }

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
        model.addAttribute("notes", this.noteService.findAll());
        return "home";
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("message", "HOME PAGE!");
    }
}

