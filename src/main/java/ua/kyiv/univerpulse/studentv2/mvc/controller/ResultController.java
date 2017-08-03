package ua.kyiv.univerpulse.studentv2.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.kyiv.univerpulse.studentv2.mvc.dto.PersonDto;
import ua.kyiv.univerpulse.studentv2.mvc.service.ResultService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Controller
public class ResultController {

    private ResultService resultService;
    @Autowired
    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }

    @RequestMapping(value = "/result", method = RequestMethod.GET)
    public String getResultPerson(HttpServletRequest request, HttpServletResponse response) {
        PersonDto personDto = (PersonDto) request.getSession().getAttribute("person");
        if (Objects.nonNull(personDto)) {
            Integer result = resultService.getTotalScoring(personDto);
            request.setAttribute("total", result);
            request.setAttribute("person", personDto);
            return "result";
        } else {
            return "redirect:/";
        }
    }
}
