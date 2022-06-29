/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ernesto.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Aluno
 */
public class Actor {
    private Integer actorId;
    private String name;
    private Float heigth;
    private String dtbirth;

    public Actor() {
    }

    public Actor(Integer actorId, String name, Float heigth, String dtbirth) {
        this.actorId = actorId;
        this.name = name;
        this.heigth = heigth;
        this.dtbirth = dtbirth;
    }

    public Integer getActorId() {
        return actorId;
    }

    public void setActorId(Integer actorId) {
        this.actorId = actorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getHeigth() {
        return heigth;
    }

    public void setHeigth(Float heigth) {
        this.heigth = heigth;
    }

    public String getDtbirth() {
        return dtbirth;
    }

    public void setDtbirth(String dtbirth) {
        this.dtbirth = dtbirth;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Actor other = (Actor) obj;
        if (!Objects.equals(this.actorId, other.actorId)) {
            return false;
        }
        return true;
    }
    
     
    
    public void insertNewActor(Actor actor){
        
    }
    @Override
    public String toString() {
        return name + "," + actorId + "," + heigth + "," + dtbirth.replace("/", " ");
    }
    
}
