package com.apress.spring.web;

import com.apress.spring.domain.JournalEntry;
import com.apress.spring.repository.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class JournalController {

    private static final String VIEW_INDEX = "index";

    @Autowired
    JournalRepository repo;

    @RequestMapping(value="/", method = RequestMethod.GET)
    public ModelAndView index(ModelAndView modelAndView) {

        modelAndView.setViewName(VIEW_INDEX);

        List<JournalEntry> list = repo.findAll();

        for (JournalEntry vo : list) {
            System.out.println("tile " + vo.getTitle());
        }

        modelAndView.addObject("journal", list);
        modelAndView.addObject("testval1", "dodo2si");
        return modelAndView;
    }
}
