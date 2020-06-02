package be.ehb.herhaling.controllers;

import be.ehb.herhaling.util.DiceLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class diceController {

    @Autowired //injection, needs to be a bean
    private DiceLogic diceLogic;

    @ModelAttribute("yahtzee")//voor alle requests beschikbaar
    public int[] yahtzee(){
        return diceLogic.rollYahtzee();
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String openIndex(ModelMap map){
        map.addAttribute("yahtzee", diceLogic.rollYahtzee());//enkel binnen deze request beschikbaar
        return "index";
    }
}
