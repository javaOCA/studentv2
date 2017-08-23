package ua.kyiv.univerpulse.studentv2.mvc.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ua.kyiv.univerpulse.studentv2.mvc.domain.FileInfo;
import ua.kyiv.univerpulse.studentv2.mvc.dto.AddressDto;
import ua.kyiv.univerpulse.studentv2.mvc.dto.MarksDto;
import ua.kyiv.univerpulse.studentv2.mvc.dto.PersonDto;
import ua.kyiv.univerpulse.studentv2.mvc.service.RegistrationService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class FileUploadController {

    private static final Logger logger = Logger.getLogger(FileUploadController.class);

    private RegistrationService registrationService;
    private ServletContext context;
    @Autowired
    public FileUploadController(RegistrationService registrationService, ServletContext context) {
        this.registrationService = registrationService;
        this.context = context;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public ModelAndView getUploadPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("upload");
        return modelAndView;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadMultipleFileHandler(@RequestParam("file") MultipartFile[] files, HttpServletRequest request) {
        if (logger.isDebugEnabled())
            logger.debug("Array files lenghs - " + files.length);
        HttpSession session = request.getSession();
        PersonDto personDto = (PersonDto) session.getAttribute("person");
        AddressDto addressDto = (AddressDto) session.getAttribute("address");
        MarksDto marksDto = (MarksDto) session.getAttribute("marks");
        if (Objects.nonNull(personDto) && Objects.nonNull(addressDto) && Objects.nonNull(marksDto) && files.length != 0) {
            List<FileInfo> uploadedFiles = new ArrayList<>();
            uploadedFiles = registrationService.savePerson(personDto, addressDto, marksDto, files);
            session.setAttribute("files", uploadedFiles);
            session.setAttribute("firstName", personDto.getFirstName());
            session.setAttribute("lastName", personDto.getLastName());
            return "redirect:/congratulation";
         } else {
            return "redirect:/";
        }
    }
}
