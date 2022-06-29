/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ernesto.controller;

import com.ernesto.exceptions.ExceptionActor;
import com.ernesto.model.Actor;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.input.DataFormat;

/**
 *
 * @author Ernesto.Castro
 */
public class ActorController  {
    
        //private Map<Integer, Actor> map = new HashMap<>();
    private List<Actor> list;
        
    public ActorController(){
        this.list = new ArrayList<Actor>();
    }
        
    
    public boolean insertNewActor(Integer id, String name, Float heigth, String dtBirth) throws ParseException, ExceptionActor{
        if(id >0 && name != null && name.length()>0 && heigth > 0  && dtBirth != null && dtBirth.length()>0){
            SimpleDateFormat formato = new SimpleDateFormat("dd/MMMM/yyyy");
            Date date = formato.parse(dtBirth);
            if(hasActors(list, id)){
                System.out.println("\n Exists actors with this ID.\n");
            } else {
               Actor actor= new Actor(id, name, heigth, formato.format(date));
                actor.insertNewActor(actor);
                list.add(actor);
                System.out.println("\n Actor added successfully!\n");
                return true;
            }
        }
        return false;
    }
        
    public boolean hasActors(List<Actor> actors, Integer id){
        Actor actor = list.stream().filter(x -> x.getActorId().equals(id)).findFirst().orElse(null);
        return actor != null;
    }
        
    public boolean validBirth(String dtbirth){
        for(int i=0;i< dtbirth.length();i++){
            if(!Character.isDigit(dtbirth.charAt(i))){
                if (!(i == 2 || i == 5)){
                    return false;
                }
            }
        }
        return true;
    }
        
    public boolean hasActors(List<Actor> list, String name){
        Actor actor = list.stream().filter(x -> x.getName().equals(name)).findFirst().orElse(null);
        return actor != null;
    }
   
    public List<Actor> getAllActorsById(Integer id){
        Actor actorsList;
        try{ 
            actorsList = null;
                for(Actor actor: this.list){
                    if(Integer.valueOf(id).equals(Integer.valueOf(actor.getActorId()))){
                        actorsList = actor;
                        System.out.println(actorsList);
                    } 
                }
            } catch(Exception e){
                System.out.println(e.getMessage());              
            } 
        return list;
    }  
    
    public List<Actor> getAllActorsByName(String name){
        Actor actorsList;   
        try{ 
            actorsList = null;
            for(Actor actor: this.list){
                if(String.valueOf(name).equals(String.valueOf(actor.getName()))){
                    actorsList = actor;
                    System.out.println(actorsList);
                }
            }
        } catch(Exception e){
            System.out.println(e.getMessage());              
        } 
            return this.list;
    }  
    
    public boolean loadActor(String name, Integer id, Float heigth, String birth){
        for(Actor a: list){
            if(a.getActorId().toString().equals(id.toString())){
                return false;
            }
        }
        list.add(new Actor(id, name, heigth, birth ));
        return true;
    }
   
    public boolean updateActor(Integer id, String name, Float heigth, String dtBirth){
        boolean flag = false;
        Actor a = null;
        for(Actor actor: list){
            if(actor.getActorId().equals(id)){
                this.list.remove(actor);
                a = new Actor(id, name, heigth, dtBirth);
                this.list.add(a);
                
                flag=true;
            }
        }
        return flag;
    }
    
    public void saveTxtFile(String path){
        try(BufferedWriter br = new BufferedWriter(new FileWriter(path))){
            for(Actor a:list){
                String newLine= "";  
                newLine = a.getName() + "," + a.getActorId() + "," + a.getHeigth() + "," + a.getDtbirth().replace("/", " ");
                br.append(newLine + "\n");
            }
        }catch(IOException e){
            System.out.println("[ERRO] saveTxtFileRepository " + e.getMessage());
        }
    }  
        
    public void loadTxtFile(String path){
       try(BufferedReader br = new BufferedReader(new FileReader(path))){
            String line = br.readLine();
            while(line != null){
                String[] vet = line.split(",");
                String name = vet[0];
                Integer actorId = Integer.parseInt(vet[1]);
                Float heigth = Float.parseFloat(vet[2]);
                String birth = vet[3].replace("/", " ");
                Actor act = new Actor(actorId, name, heigth, birth);
                list.add(act);
                line = br.readLine();
            }
        } catch(IOException e){
            System.out.println("Error loadtxtfileRepository: " + e.getMessage());
        }
    }
       
    public List<Actor> getAllActors(){     
        ArrayList<Actor> actorsFound = new ArrayList<Actor>();
        for(Actor actor : this.list){
            actorsFound.add(actor);
        }
        return actorsFound;
    }
    public boolean findAll(){   
        boolean success = false;
        ArrayList<Actor> actorsFound = new ArrayList<Actor>();
        if(!success){
            for(Actor actor : this.list){
                actorsFound.add(actor);
                success = true;
            }
        }
        return success;
    }
    public ArrayList getActors(boolean flag){
        flag = false;
        ArrayList<Actor> actorsFound = new ArrayList<Actor>();
        for(Actor actor : this.list){
            actorsFound.add(actor);
            flag = true;
        }
        return actorsFound;
    }
    
    

    @Override    
    public String toString() {
        return "" + list;
    }   
}
