package be.ehb.herhaling.controllers;

import be.ehb.herhaling.util.DiceLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class diceController {

    @Autowired
    private DiceLogic diceLogic;

    @RequestMapping()
    public String openIndex(ModelMap map){
        map.addAttribute("yahtzee", diceLogic.rollYahtzee());
        return "index";
    }
}
