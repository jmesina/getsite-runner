package com.mesina.jose.getsite.getsiterunner.getsite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by MesinaFamily on 2/22/18.
 */
@RestController
public class GetsiteController {

    @Autowired
    private GetsiteService getsiteService;

    @RequestMapping("/getsite")
    public List<Getsite> getAllSites(){
        return getsiteService.getAllSites();
    }

    @RequestMapping("/getsite/{diid}")
    public Getsite getSite(@PathVariable String diid){
        return getsiteService.getSite(diid);
    }

    @RequestMapping(value = "/getsite", method = RequestMethod.POST)
    public void addGetsite(@RequestBody Getsite getsite){
        getsiteService.addGetsite(getsite);
    }

    @RequestMapping(value = "/getsite/{diid}", method = RequestMethod.PUT)
    public void updateGetsite(@RequestBody Getsite getsite, @PathVariable String diid){
        getsiteService.updateGetsite(getsite, diid);
    }

    @RequestMapping(value = "/getsite/{diid}", method = RequestMethod.DELETE)
    public void deleteGetsite(@PathVariable String diid){
        getsiteService.deleteGetsite(diid);
    }
}
