package ua.kyiv.univerpulse.studentv2.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class CongratulationController {

    @RequestMapping(value = "/congratulation", method = RequestMethod.GET)
    public ModelAndView congratulationRegistration() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("congratulation");
        return modelAndView;
    }

    @RequestMapping(value = "/congratulation", method = RequestMethod.POST)
    public String returnToHomePage() {
        return "redirect:/exit";
    }

}
