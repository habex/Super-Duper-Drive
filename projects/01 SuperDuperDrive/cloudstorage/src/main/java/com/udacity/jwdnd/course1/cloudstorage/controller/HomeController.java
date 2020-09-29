package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

        //return  "home";
    }

  /* @PostMapping("/upload")
    public String uploadFile (@RequestParam("file")MultipartFile file, RedirectAttributes attributes){

        // check if file is empty
        if (file.isEmpty()) {
            attributes.addFlashAttribute("message", "Please select a file to upload.");
            return "redirect:/home";
        }

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        attributes.addFlashAttribute(fileName);
        return "/home";
    }*/

}
