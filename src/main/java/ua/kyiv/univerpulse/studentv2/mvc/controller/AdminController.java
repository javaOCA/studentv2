package ua.kyiv.univerpulse.studentv2.mvc.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.kyiv.univerpulse.studentv2.mvc.dto.FacultyDto;
import ua.kyiv.univerpulse.studentv2.mvc.dto.PersonDto;
import ua.kyiv.univerpulse.studentv2.mvc.service.RegistrationService;
import ua.kyiv.univerpulse.studentv2.mvc.service.ResultService;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

@Controller
public class AdminController {

    private static final Logger logger = Logger.getLogger(AdminController.class);

    private RegistrationService registrationService;
    private ResultService resultService;
    @Autowired
    public AdminController(RegistrationService registrationService, ResultService resultService) {
        this.registrationService = registrationService;
        this.resultService = resultService;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String getAdminPage() {
        return "admin";
    }

    @RequestMapping(value = "/admin/faculties", method = RequestMethod.GET)
    public String getFacultiesPage(Model model) {
        model.addAttribute("faculties",
                registrationService.getAllFaculty().stream()
                .sorted((f1, f2) -> f1.getId().compareTo(f2.getId()))
                .collect(Collectors.toList()));
        model.addAttribute("faculty", new FacultyDto());
        return "faculties";
    }

    @RequestMapping(value = "/admin/faculties/update", method = RequestMethod.POST)
    public String updateFacultiesPage(@ModelAttribute("faculty") FacultyDto facultyDto, Model model) {
        if(logger.isDebugEnabled())
            logger.debug("Update entity Faculty in AdminController: " + facultyDto);
        registrationService.updateFaculty(facultyDto);
        model.addAttribute("faculties",
                registrationService.getAllFaculty().stream()
                .sorted((f1, f2) -> f1.getId().compareTo(f2.getId()))
                .collect(Collectors.toList()));
        return "faculties";
    }

    @RequestMapping(value = "/admin/faculties/insert", method = RequestMethod.POST)
    public String insertFacultiesPage(Model model, HttpServletRequest request) {
        FacultyDto dto = new FacultyDto();
        dto.setName(request.getParameter("u_name"));
        dto.setEvaluationDate(request.getParameter("u_evaluationDate"));
        dto.setPassingScore(Integer.valueOf(request.getParameter("u_passingScore").trim()));
        dto.setNumberOfStudents(Integer.valueOf(request.getParameter("u_numberOfStudents")));
        if(logger.isDebugEnabled())
            logger.debug("Insert entity Faculty in AdminController: " + dto);
        registrationService.saveFaculty(dto);
        model.addAttribute("faculties",
                registrationService.getAllFaculty().stream()
                .sorted((f1, f2) -> f1.getId().compareTo(f2.getId()))
                .collect(Collectors.toList()));
        model.addAttribute("faculty", new FacultyDto());
        return "faculties";
    }

    @RequestMapping(value = "/admin/entrants", method = RequestMethod.GET)
    public String getEntrantsPage(Model model) {
        model.addAttribute("entrants", resultService.getAllPersonWithoutAdmin());
        model.addAttribute("person", new PersonDto());
        return "entrants";
    }
}
