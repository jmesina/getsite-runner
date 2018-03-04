package com.mesina.jose.getsite.getsiterunner.getsite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by MesinaFamily on 2/22/18.
 */
@RequestMapping(value = "/getsite", produces = {MediaType.APPLICATION_JSON_VALUE})
@RestController
public class GetsiteController {

    private GetsiteService getsiteService;

    public GetsiteController(GetsiteService getsiteService) {
        this.getsiteService = getsiteService;
    }

    @GetMapping
    public List<Getsite> getAllSites(){
        return getsiteService.getAllSites();
    }

    @RequestMapping(value = "{diid}")
    public Getsite getSite(@PathVariable String diid){
        return getsiteService.getSite(diid);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void addGetsite(@RequestBody Getsite getsite){
        getsiteService.addGetsite(getsite);
    }

    @RequestMapping(value = "{diid}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateGetsite(@RequestBody Getsite getsite, @PathVariable String diid){
        getsiteService.updateGetsite(getsite, diid);
    }

    @RequestMapping(value = "{diid}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGetsite(@PathVariable String diid){
        getsiteService.deleteGetsite(diid);
    }
}
