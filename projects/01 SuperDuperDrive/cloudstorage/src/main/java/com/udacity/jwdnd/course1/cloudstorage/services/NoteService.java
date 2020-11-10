package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class NoteService {
    private NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    @PostConstruct
    public void postConstruct(){
        System.out.println("Creating NoteService bean");
    }

    public List<Note> findAll(){
        return noteMapper.findAll();
    }

    public void addNote(Note note){
        Note isNoteExist = noteMapper.findById(note.getNoteId());
        if(isNoteExist==null){
            noteMapper.addNote(note);
        }else {
            noteMapper.update(note);
        }
    }


    public Note findById(Integer noteId){
        return noteMapper.findById(noteId);
    }

    public void update(Note note){
        noteMapper.update(note);
    }

    public int delete(Integer noteId){
        return noteMapper.delete(noteId);
    }
}
