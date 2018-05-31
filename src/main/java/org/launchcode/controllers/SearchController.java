package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;

import static org.launchcode.models.JobData.findByColumnAndValue;
import static org.launchcode.models.JobData.findByValue;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }


    @RequestMapping(value = "results", method = RequestMethod.GET)
    private String results(Model model, @RequestParam String searchTerm, @RequestParam String searchType) {
        //all isn't working need an if statement to check for all
        if (searchType.equals("all")){
            ArrayList<HashMap<String, String>> jobs = findByValue(searchTerm);
            model.addAttribute("columns", ListController.columnChoices);
            model.addAttribute("jobs", jobs);
            return "search";
        }
        else {
            ArrayList<HashMap<String, String>> jobs = findByColumnAndValue(searchType, searchTerm);
            model.addAttribute("columns", ListController.columnChoices);
            model.addAttribute("jobs", jobs);
            return "search";
        }
    }

//    // TODO #1 - Create handler to process search request and display results
//    The method should take in two parameters, specifying the type of search and the search term.
//    You'll also need to use the correct annotations
//    for the method and parameters.
//After looking up the search results via the JobData class, you'll need to pass them into the search.html view via the model.
//    You'll also need to pass ListController.columnChoices to the view, as the existing search handler does.

//        model.addAttribute("columns", ListController.columnChoices);
//        return "search";
}
