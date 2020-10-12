package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {
    @Select("SELECT * FROM NOTES")
    List<Note> findAll();

    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES(#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int addNote(Note note);

    @Select("SELECT * FROM NOTES WHERE noteid = #{noteId}")
    Note findById(@Param("id") int noteId);

    @Update("UPDATE NOTES Set notetitle=#{note}, notedescription = #{noteDescription}  WHERE noteid = #{noteId}" )
    int update(Note note);

    @Delete("DELETE FROM NOTES WHERE noteId = #{noteId}")
    int delete(@Param("noteId") int noteId);
}
