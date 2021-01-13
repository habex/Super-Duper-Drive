package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {

    @Select("SELECT * FROM CREDENTIALS WHERE userId =#{userId}")
    List<Credential> findAll(int userId);

    @Select("SELECT * FROM CREDENTIALS WHERE credentialId = #{credentialId}")
    Credential findById(int credentialId);

    @Select("SELECT password FROM CREDENTIALS WHERE credentialid=#{credentialID")
    String getDecryptedPassword(int credentialId);

    @Insert("INSERT INTO CREDENTIALS (url, username,key, password,userid) VALUES " +
            "(#{url}, #{username}, #{key}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int addCredential(Credential credential);

    @Update("UPDATE CREDENTIALS set url = #{url}, username = #{username},key = #{key}, password = #{password}, userId = #{userId} WHERE credentialId = #{credentialId}")
    int update(Credential credential);


    @Delete("DELETE FROM CREDENTIALS WHERE credentialId = #{credentialId}")
    int delete(int credentialId);
}
