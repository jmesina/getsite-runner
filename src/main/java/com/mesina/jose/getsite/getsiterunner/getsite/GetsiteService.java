package com.mesina.jose.getsite.getsiterunner.getsite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by MesinaFamily on 2/22/18.
 */
@Service
public class GetsiteService {

    @Autowired
    private GetsiteRepository getsiteRepository;

    public List<Getsite> getAllSites(){
        return (List<Getsite>) getsiteRepository.findAll();
    }

    public Getsite getSite(String diid) {
        return getsiteRepository.findOne(diid);
    }

    public void addGetsite(Getsite getsite) {
        getsiteRepository.save(getsite);
    }

    public void updateGetsite(Getsite getsite, String diid) {
        getsiteRepository.save(getsite);
    }

    public void deleteGetsite(String diid) {
        getsiteRepository.delete(diid);
    }
}
