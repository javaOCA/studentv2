package ua.kyiv.univerpulse.studentv2.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.kyiv.univerpulse.studentv2.mvc.dto.AddressDto;
import ua.kyiv.univerpulse.studentv2.mvc.dto.MarksDto;
import ua.kyiv.univerpulse.studentv2.mvc.dto.PersonDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class MarksController {

    @RequestMapping(value = "/marks", method = RequestMethod.GET)
    public String registrationMarks(Model model) {
        if (!model.containsAttribute("marks")) {
            model.addAttribute("marks", new MarksDto());
        }
        return "marks";
    }

    @RequestMapping(value = "/marks", method = RequestMethod.POST)
    public String savePerson(@ModelAttribute("address") @Valid MarksDto marksDto,
                             BindingResult result, RedirectAttributes attributes,
                             HttpServletRequest request) {
        if(result.hasErrors()) {
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.marks", result);
            attributes.addFlashAttribute("marks", marksDto);
            return "redirect:/marks";
        }
        HttpSession session = request.getSession();
        session.setAttribute("marks", marksDto);
//        System.out.println((PersonDto) session.getAttribute("person"));
//        System.out.println((AddressDto) session.getAttribute("address"));
//        System.out.println((MarksDto) session.getAttribute("marks"));
//        session.invalidate();
        return "redirect:/";
    }
}
