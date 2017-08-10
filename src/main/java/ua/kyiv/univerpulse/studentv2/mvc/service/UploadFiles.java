package ua.kyiv.univerpulse.studentv2.mvc.service;

import org.springframework.web.multipart.MultipartFile;
import ua.kyiv.univerpulse.studentv2.mvc.domain.FileInfo;
import ua.kyiv.univerpulse.studentv2.mvc.domain.Person;

import java.util.ArrayList;
import java.util.List;

public interface UploadFiles {

    List<FileInfo> uploadFiles(MultipartFile[] files, Person person);

}
