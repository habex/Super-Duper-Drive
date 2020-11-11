package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.controller.HomeController;
import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class CredentialService {

    private CredentialMapper credentialMapper;
    private final UserMapper userMapper;
    private final AuthenticationService authenticationService;
    private final UserService userService;
    @Autowired
    private EncryptionService encryptionService;


    public CredentialService(CredentialMapper credentialMapper, UserMapper userMapper, AuthenticationService authenticationService, UserService userService) {
        this.credentialMapper = credentialMapper;
        this.userMapper = userMapper;
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @PostConstruct
    public void postConstruct(){
        System.out.println("Creating CredentialService bean");
    }

    public void addCredential(Credential credential){

        if(credential.getCredentialId() == null){
            HomeController.status = "added";
            credentialMapper.addCredential(credential);
        }else
        {
            HomeController.status = "updated";
            credentialMapper.update(credential);
        }

    }

    public List<Credential> findAll(){
        return credentialMapper.findAll();
    }

    public Credential findNoteById(Integer credentialId){
        return credentialMapper.findById(credentialId);
    }

    public int delete(Integer credentialId){
       return credentialMapper.delete(credentialId);
    }


}
