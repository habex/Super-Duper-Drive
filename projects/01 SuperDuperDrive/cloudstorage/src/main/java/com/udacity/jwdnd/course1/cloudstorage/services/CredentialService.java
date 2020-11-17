package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.controller.HomeController;
import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {


    private CredentialMapper credentialMapper;
    private EncryptionService encryptionService;

    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("Creating CredentialService bean");
    }

    public Credential getCredential(Integer credentialId) {
        Credential credential = credentialMapper.findById(credentialId);
        String encryptedPassword = credential.getPassword();
        String key = credential.getKey();
        String plainTextPassword = encryptionService.decryptValue(encryptedPassword, key);
        credential.setPassword(plainTextPassword);
        return credential;
    }

    public int addCredential(Credential credential) {

        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);

        credential.setPassword(encryptionService.encryptValue(credential.getPassword(), encodedKey));
        credential.setKey(encodedKey);

        if (credential.getCredentialId() == null) {
            HomeController.status = "added";
            return credentialMapper.addCredential(credential);
        } else {
            HomeController.status = "updated";
            return credentialMapper.update(credential);
        }

    }

    public List<Credential> findAll(int userId) {
        return credentialMapper.findAll(userId);
    }

    public Credential findNoteById(Integer credentialId) {
        return credentialMapper.findById(credentialId);
    }

    public int delete(Integer credentialId) {
        return credentialMapper.delete(credentialId);
    }

    public String getDecryptedCredentials(Integer credentialId) {
        String decryptedPassword = credentialMapper.getDecryptedPassword(credentialId);
        return decryptedPassword;
    }
}
