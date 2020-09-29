package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.Note;
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

    public HomeController (NoteService noteService){
        this.noteService = noteService;
    }

    @GetMapping
    public String getHomePage (){
        return "home";
    }


    @PostMapping
    public void postNote (Authentication authentication, Note note, Model model){

        this.noteService.addNote(note);
        model.addAttribute("notes",this.noteService.getNotes());
    }

   @PutMapping
   public  void update(Note note){
        noteService.update(note);
   }

   @DeleteMapping
    public void delete(Note note){
        noteService.delete(note);
   }
}
