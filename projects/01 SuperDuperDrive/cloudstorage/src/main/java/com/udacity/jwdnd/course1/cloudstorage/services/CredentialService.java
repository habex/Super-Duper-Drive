package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class CredentialService {

    private CredentialMapper credentialMapper;

    public CredentialService(CredentialMapper credentialMapper) {
        this.credentialMapper = credentialMapper;
    }

    @PostConstruct
    public void postConstruct(){
        System.out.println("Creating CredentialService bean");
    }

    public void addCredential(Credential credential){
        credentialMapper.addCredential(credential);
    }

    public List<Credential> findAll(){
        return credentialMapper.findAll();
    }

    public Credential findNoteById(Integer credentialId){
        return credentialMapper.findById(credentialId);
    }

    public void update(Credential credential) {
        credentialMapper.update(credential);
    }

    public int delete(Integer credentialId){
       return credentialMapper.delete(credentialId);
    }


}
