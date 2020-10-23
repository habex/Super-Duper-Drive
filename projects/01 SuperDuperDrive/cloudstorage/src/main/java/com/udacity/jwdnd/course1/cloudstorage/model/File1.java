package com.udacity.jwdnd.course1.cloudstorage.model;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.sql.Blob;

public class File1 implements MultipartFile {


    private Integer fileId;
    private long fileSize;
    private Integer userId;
    private Blob fileData;

    private String name;
    private String contentType;
    private Resource resource;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getOriginalFilename() {
        return name;
    }

    @Override
    public String getContentType() {
        return contentType;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public long getSize() {
        return fileSize;
    }

    @Override
    public byte[] getBytes() throws IOException {
        return new byte[0];
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return this.getInputStream();
    }

    @Override
    public Resource getResource() {
        return resource;
    }

    public Integer getFileId() {
        return fileId;
    }

    public long getFileSize() {
        return fileSize;
    }

    public Integer getUserId() {
        return userId;
    }

    public Blob getFileData() {
        return fileData;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setFileData(Blob fileData) {
        this.fileData = fileData;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void transferTo(File file) throws IOException, IllegalStateException {

    }

    @Override
    public void transferTo(Path dest) throws IOException, IllegalStateException {

    }
}
