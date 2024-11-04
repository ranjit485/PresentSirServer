package com.ranjit.ps.controller;

import com.ranjit.ps.model.Location;
import com.ranjit.ps.model.User;
import com.ranjit.ps.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Presentsir")
public class AppController {

    private final LocationService locationService;

    @Autowired
    public AppController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("home");
        return "welcome";
    }

    @ResponseBody
    @GetMapping("/test")
    public String test() {
        return "Hello World";
    }

    @PostMapping("/login")
    public User login(User user){
        return user;
    }

    @PostMapping("/logout")
    public void logout(){

    }

//    USER SEND LOCATION TO SERVER BUS-Q

    @PostMapping("/{busId}/locations")
    public String addLocation(@PathVariable String busId, @RequestParam double latitude, @RequestParam double longitude) {
        locationService.addLocationToBus(busId, latitude, longitude);
        return "Added location to bus " + busId;
    }

//    USER FETCH BUS LOCATION FROM Q-SERVICE

    @GetMapping("/{busId}/locations")
    public Location getLocation(@PathVariable String busId){
        return locationService.getLocation(busId);
    }

}
