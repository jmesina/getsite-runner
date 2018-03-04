package com.mesina.jose.getsite.getsiterunner.getsite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MesinaFamily on 2/22/18.
 */
@Service
public class GetsiteService {

    private GetsiteRepository getsiteRepository;

    public GetsiteService(GetsiteRepository getsiteRepository) {
        this.getsiteRepository = getsiteRepository;
    }

    public List<Getsite> getAllSites() {
        List<Getsite> getsites = new ArrayList<>();
        getsiteRepository.findAll().forEach(getsites::add);
        return getsites;
    }

    public Getsite getSite(String diid) {
        return getsiteRepository.findOne(diid);
    }

    public void addGetsite(Getsite getsite) {
        getsiteRepository.save(getsite);
    }

    public void updateGetsite(Getsite getsite, String diid) {
        Getsite existingGetsite = getsiteRepository.findOne(diid);
        getsiteRepository.save(getsite);

        if (existingGetsite != null) {
            existingGetsite.setDbname(getsite.getDbname());
            existingGetsite.setFidn(getsite.getFidn());
            existingGetsite.setBackend(getsite.getBackend());
        }

        getsiteRepository.save(existingGetsite);

    }

    public void deleteGetsite(String diid) {
        getsiteRepository.delete(diid);
    }
}
