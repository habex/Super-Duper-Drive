package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {

    @Select("SELECT * FROM FILES WHERE userId =#{userId}")
    List<File> getAllFiles(Integer userId);

    @Select("SELECT * FROM FILES WHERE fileid = #{fileId}")
    File getFile(Integer fileId);

    @Select("SELECT * FROM FILES WHERE filename = #{fileName}")
    File getFileByFileName(String fileName);

    @Insert("INSERT INTO FILES (filename, contenttype , filesize, userid, filedata) VALUES " +
            "(#{fileName}, #{contentType} , #{fileSize}, #{userId},#{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int upload(File file);

    @Select("SELECT * FROM FILES WHERE fileid = #{fileId}")
    File findFileById(Integer fileId);

    @Delete("DELETE FROM FILES WHERE fileId = #{fileId}")
    int delete(@Param("fileId") int fileId);
}
