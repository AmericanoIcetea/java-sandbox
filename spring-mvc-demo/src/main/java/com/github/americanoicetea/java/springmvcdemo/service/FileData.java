package com.github.americanoicetea.java.springmvcdemo.service;

public class FileData {
    private String contentType;
    private long contentLength;
    private String name;
    private byte[] data;

    public FileData(String contentType, long contentLength, String name, byte[] data) {
        this.contentType = contentType;
        this.contentLength = contentLength;
        this.name = name;
        this.data = data;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public long getContentLength() {
        return contentLength;
    }

    public void setContentLength(long contentLength) {
        this.contentLength = contentLength;
    }

}
