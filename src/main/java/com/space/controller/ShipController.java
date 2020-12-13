package com.space.controller;

import com.space.model.Ship;
import com.space.model.ShipType;
import com.space.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/rest/ships")
public class ShipController {

    private ShipService shipService;

    @Autowired
    public void setShipService(ShipService shipService) {
        this.shipService = shipService;
    }

    @GetMapping("/count")
    public Integer shipsSortedList(@RequestParam(required = false) String name,
                                      @RequestParam(required = false) String planet,
                                      @RequestParam(required = false) ShipType shipType,
                                      @RequestParam(required = false) Long after,
                                      @RequestParam(required = false) Long before,
                                      @RequestParam(required = false) Boolean isUsed,
                                      @RequestParam(required = false) Double minSpeed,
                                      @RequestParam(required = false) Double maxSpeed,
                                      @RequestParam(required = false) Integer minCrewSize,
                                      @RequestParam(required = false) Integer maxCrewSize,
                                      @RequestParam(required = false) Double minRating,
                                      @RequestParam(required = false) Double maxRating){
        return shipService.shipsSortedList(name, planet, shipType, after, before, isUsed, minSpeed, maxSpeed, minCrewSize,
                maxCrewSize, minRating, maxRating).size();
    }

    @GetMapping
    public List<Ship> shipsFromPage(@RequestParam(required = false) String name,
                                    @RequestParam(required = false) String planet,
                                    @RequestParam(required = false) ShipType shipType,
                                    @RequestParam(required = false) Long after,
                                    @RequestParam(required = false) Long before,
                                    @RequestParam(required = false) Boolean isUsed,
                                    @RequestParam(required = false) Double minSpeed,
                                    @RequestParam(required = false) Double maxSpeed,
                                    @RequestParam(required = false) Integer minCrewSize,
                                    @RequestParam(required = false) Integer maxCrewSize,
                                    @RequestParam(required = false) Double minRating,
                                    @RequestParam(required = false) Double maxRating,
                                    @RequestParam(required = false) ShipOrder order,
                                    @RequestParam(required = false) Integer pageNumber,
                                    @RequestParam(required = false) Integer pageSize){

        List<Ship> shipsSortedList = shipService.shipsSortedList(name, planet, shipType, after, before, isUsed,
                minSpeed, maxSpeed, minCrewSize,
                maxCrewSize, minRating, maxRating);
        return shipService.shipsFromPage(shipsSortedList, order, pageNumber, pageSize);
    }

    @PostMapping
    @ResponseBody
    public Ship addShip(@RequestBody Ship ship){
        return shipService.addShip(ship);
    }

    @PostMapping("/{id}")
    @ResponseBody
    public Ship updateShip(@RequestBody Ship ship, @PathVariable Long id){
        return shipService.updateShip(ship, id);
    }

    @DeleteMapping("/{id}")
    public void removeShip(@PathVariable Long id){
        shipService.removeShip(id);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Ship getShipById(@PathVariable Long id){
        return shipService.getShipById(id);
    }
//    @RequestMapping(name = "rest/ships", method = RequestMethod.GET)
//    public List<Ship> listShips(Model model){
////        model.addAttribute("ship", new Ship());
////        model.addAttribute("listShips", shipService.shipsList());
//        return shipService.shipsList(); //"ships";
//    }
////
//    @RequestMapping(name = "ships", method = RequestMethod.POST)
//    public String addShip(@ModelAttribute("ship") Ship ship){
//        if (ship.getId()==0){
//            shipService.addShip(ship);
//        } else {
//            shipService.updateShip(ship);
//        }
//        return "ships";
//    }
////
////    @RequestMapping("rest/ships/{id}")
////    public String removeShip(@PathVariable("id") long id){
////        shipService.removeShip(id);
////        return "ships";
////    }
//
////    @RequestMapping("ships/{id}")
////    public String updateShip(@PathVariable("id") long id, Model model){
////        model.addAttribute("ship", shipService.getShipById(id)); //?????
////        model.addAttribute("shipList", shipService.shipsList());
////        return "ships";
////    }
//
//    @RequestMapping("rest/ships/{id}")
//    public Ship dataShip(@PathVariable("id") long id, Model model){
//     //   model.addAttribute("ship", shipService.getShipById(id));
//
//        return shipService.getShipById(id);
//    }

}
