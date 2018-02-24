package com.mesina.jose.getsite.getsiterunner.getsite;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by MesinaFamily on 2/22/18.
 */
@Entity
public class Getsite {

    @Id
    private String diid;
    private String fidn;
    private String dbname;
    private String backend;

    public Getsite(){

    }

    public Getsite(String diid, String fidn, String dbname, String backend) {
        this.diid = diid;
        this.fidn = fidn;
        this.dbname = dbname;
        this.backend = backend;
    }

    public String getDiid() {
        return diid;
    }

    public void setDiid(String diid) {
        this.diid = diid;
    }

    public String getFidn() {
        return fidn;
    }

    public void setFidn(String fidn) {
        this.fidn = fidn;
    }

    public String getDbname() {
        return dbname;
    }

    public void setDbname(String dbname) {
        this.dbname = dbname;
    }

    public String getBackend() {
        return backend;
    }

    public void setBackend(String backend) {
        this.backend = backend;
    }
}
