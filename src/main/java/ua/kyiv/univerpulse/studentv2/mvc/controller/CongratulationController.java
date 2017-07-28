package ua.kyiv.univerpulse.studentv2.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.kyiv.univerpulse.studentv2.mvc.dto.PersonDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class CongratulationController {

    @RequestMapping(value = "/congratulation", method = RequestMethod.GET)
    public ModelAndView congratulationRegistration(HttpServletRequest request) {
        HttpSession session = request.getSession();
        PersonDto personDto = (PersonDto) session.getAttribute("person");
        session.invalidate();
        ModelAndView model = new ModelAndView();
        model.setViewName("congratulation");
        model.addObject("firstName", personDto.getFirstName());
        model.addObject("lastName", personDto.getLastName());
        return model;
    }

    @RequestMapping(value = "/congratulation", method = RequestMethod.POST)
    public String returnToHomePage() {
        return "redirect:/";
    }

}
