package com.wuqin.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface IFileInfoService {
    Integer fileSave(String importNo, InputStream imputStream, String fileType) throws IOException;

    void createSmpsFile(String importNo,String originalFilename);

    void readFileEmail();

    List<String> queryFile(Map reqMap);

    void sendEmail();

    void newFile();
}
