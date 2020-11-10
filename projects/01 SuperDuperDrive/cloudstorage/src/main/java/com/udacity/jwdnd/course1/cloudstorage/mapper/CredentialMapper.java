package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {

    @Select("SELECT * FROM CREDENTIALS")
    List<Credential> findAll();

    @Select("SELECT * FROM CREDENTIALS WHERE credentialid = #{credentialId}")
    Credential findById(@Param("credentialId") int credentialId);

    @Insert("INSERT INTO CREDENTIALS (url, username, key, password, userid) VALUES " +
            "(#{url}, #{userName}, #{key}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int addCredential(Credential credential);

    @Update("UPDATE CREDENTIALS set url = #{url}, username = #{userName}, password = #{password} WHERE credentialid = #{credentialId}")
    void update(Credential credential);



    @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{credentialId}")
    int delete(@Param("credentialId") int credentialId);


}
