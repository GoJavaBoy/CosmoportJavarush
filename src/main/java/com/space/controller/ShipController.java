package com.space.controller;

import com.space.model.Ship;
import com.space.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ShipController {
    private ShipService shipService;

    @Autowired(required = true)
    @Qualifier(value = "shipService")
    public void setShipService(ShipService shipService) {
        this.shipService = shipService;
    }

//    @RequestMapping(name = "ships", method = RequestMethod.GET)
//    public String listShips(Model model){
//        model.addAttribute("ship", new Ship());
//        model.addAttribute("listShips", shipService.shipsList());
//        return "ships";
//    }
//
//    @RequestMapping(name = "ships", method = RequestMethod.POST)
//    public String addShip(@ModelAttribute("ship") Ship ship){
//        if (ship.getId()==0){
//            shipService.addShip(ship);
//        } else {
//            shipService.updateShip(ship);
//        }
//        return "ships";
//    }
//
//    @RequestMapping("ships/{id}")
//    public String removeShip(@PathVariable("id") int id){
//        shipService.removeShip(id);
//        return "ships";
//    }
//
//    @RequestMapping("ships/{id}")
//    public String updateShip(@PathVariable("id") int id, Model model){
//        model.addAttribute("ship", shipService.getShipById(id)); //?????
//        model.addAttribute("shipList", shipService.shipsList());
//        return "ships";
//    }

    @RequestMapping("ships/{id}")
    public String dataShip(@PathVariable("id") long id, Model model){
        model.addAttribute("ship", shipService.getShipById(id));
        return "ships";
    }

}
