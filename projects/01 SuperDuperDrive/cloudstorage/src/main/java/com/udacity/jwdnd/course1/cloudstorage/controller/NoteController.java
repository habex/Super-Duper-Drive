package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/note")
public class NoteController {

    private NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }


    @PostMapping
    public String postNote(Note note, Model model) {
        this.noteService.addNote(note);
        model.addAttribute("notes", this.noteService.findAll());
        return "home";
    }

    @PutMapping
    public String update(Note note) {
        noteService.update(note);
        return "home";
    }

   
   /* @PutMapping(path = "/note/update/{noteId}")
    public String updateNote(@PathVariable("noteId") Integer noteId, Note note,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            note.setNoteId(noteId);
            return "home";
        }

        this.noteService.update(note);
        model.addAttribute("notes", this.noteService.findAll());
        return "home";
    }*/



    @GetMapping ("/delete/{noteId}")
    public String deleteNote(@PathVariable("noteId") Integer noteId, Model model) {
        System.out.println("Note Id : "+noteId);
        Note note = this.noteService.findNoteById(noteId);
        this.noteService.delete(note);
        model.addAttribute("notes", this.noteService.findAll());
        return "home";
    }
}
