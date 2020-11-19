package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/home/note")
public class NoteController {


    private UserService userService;
    private NoteService noteService;
    private HomeController homeController;
    private String message = "Notes list";

    public NoteController(UserService userService, NoteService noteService, HomeController homeController) {
        this.userService = userService;
        this.noteService = noteService;
        this.homeController = homeController;
    }

    @PostMapping
    public String postNote( Authentication authentication, @ModelAttribute("notes") Note note, Model model) {


        Integer userId = userService.getUserByName(authentication.getName()).getUserId();
        note.setUserId(userId);
        this.noteService.addNote(note);
        message="Note "  +" successfully "+ HomeController.status +" !";

        homeController.addAttributes(model,userId,"notes",message);

        return "home";
    }


    @GetMapping("/delete/{noteId}")
    public String deleteNote(Authentication authentication, @PathVariable Integer noteId, Model model) {


        Integer userId = userService.getUserByName(authentication.getName()).getUserId();
        int result = noteService.delete(noteId);

        if (result >= 1) {
            message= "Note successfully deleted !";
        } else {
            message="Deleting the Note was unsuccessfully !";
        }

        homeController.addAttributes(model,userId,"notes",message);

        return "home";
    }


}

