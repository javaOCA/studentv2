package ua.kyiv.univerpulse.studentv2.mvc.service.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.kyiv.univerpulse.studentv2.mvc.domain.FileInfo;
import ua.kyiv.univerpulse.studentv2.mvc.domain.Person;
import ua.kyiv.univerpulse.studentv2.mvc.exception.UploadFileException;
import ua.kyiv.univerpulse.studentv2.mvc.service.UploadFiles;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UploadFilesImpl implements UploadFiles {

    private static final Logger logger = Logger.getLogger(UploadFilesImpl.class);

    @Override
    public List<FileInfo> uploadFiles(MultipartFile[] files, Person person) {
        List<FileInfo> uploadedFiles = new ArrayList<>();
        File uploadFile = null;
        for (int i = 0; i < files.length; i++) {
            FileInfo fileInfo = new FileInfo();
            String[] ext = files[i].getOriginalFilename().split("\\.");
            String extention = "";
            if (ext.length > 1) {
                extention = ext[ext.length - 1];
            }
            try {
                byte[] bytes = files[i].getBytes();
                String path = System.getProperty("catalina.home") + File.separator + "uploadFiles";
                File dir = new File(path);
                if (!dir.exists())
                    dir.mkdirs();
                switch (i) {
                    case 0:
                        uploadFile = new File(dir.getAbsolutePath() + File.separator
                                + person.getId() + "_" + person.getFirstName() + "_" + person.getLastName()
                                + "_certificate." + extention);
                        break;
                    case 1:
                        uploadFile = new File(dir.getAbsolutePath() + File.separator
                                + person.getId() + "_" + person.getFirstName() + "_" + person.getLastName()
                                + "_passport." + extention);
                        break;
                }
                try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadFile))) {
                    stream.write(bytes);
                    stream.flush();
                }
                if (logger.isDebugEnabled())
                    logger.debug("Save to disk file with name: " + uploadFile.getName());
                fileInfo.setOriginalName(files[i].getOriginalFilename());
                fileInfo.setUploadName(uploadFile.getName());
                fileInfo.setSize(uploadFile.length());
                if (logger.isDebugEnabled())
                    logger.debug("Class FileInfo: " + fileInfo);
                uploadedFiles.add(fileInfo);
            } catch (IOException e) {
                logger.error("Error save person files", e);
                throw new UploadFileException(e.getMessage());
            }
        }
        return uploadedFiles;
    }
}
