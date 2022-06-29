/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ernesto.views;

import com.ernesto.controller.ActorController;
import com.ernesto.model.Actor;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author Ernest.Castro
 */
public class Program {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Locale.setDefault(Locale.US);
        Scanner scan = new Scanner(System.in);
        ActorController controller = new ActorController();
        int opcao =10;
        
        System.out.println("====================================================");
        System.out.println("=====================WELCOME========================");
        System.out.println("====================================================");
        System.out.println();
        while(opcao!=0){            
        System.out.println("1- Get all actors");
        System.out.println("2- Get the actor's information by name");
        System.out.println("3- Get the actor's information by id");
        System.out.println("4- Add information from an actor ");
        System.out.println("5- Update an actor's information");
        System.out.println("6- Load actors from txt file");
        System.out.println("7- Save actors from txt file");
        System.out.println("0- exit program");
        System.out.print("Shoose an option between 1 and 7:");
        String enter = "";
        
            try{
                enter = scan.nextLine();
                opcao = Integer.parseInt(enter);
                if(opcao > 0 && opcao <=7){
                    switch(opcao){
                        case 1: 
                            List<Actor> actorList = controller.getAllActors();
                            if(!actorList.isEmpty()){
                                System.out.println();
                                System.out.println("List of actors:");
                                System.out.println("<name>,<actorId>,<height>,<date of birth>");
                                    for(Actor a: actorList){
                                        System.out.println(a.toString());
                                    }
                                System.out.println();
                            } else {
                                System.out.println("\nDont exists any actor!\n");
                            }
                        break;
                        case 2:
                            String nameFind = "";
                            System.out.print("Name actor: ");
                            nameFind = scan.nextLine();
                                try{
                                    List<Actor> getname = controller.getAllActors();
                                    boolean actorFound = controller.hasActors(getname, nameFind);
                                    if(!(actorFound)){
                                        System.out.println("==========================================");
                                        System.out.println("Actor found");
                                        System.out.println("Dont foun any actor with this name: " + nameFind);   
                                        System.out.println("===========================================");
                                    } else {
                                        System.out.println("==========================================");
                                        System.out.println("Actor found");
                                        controller.getAllActorsByName(nameFind);
                                        System.out.println("==========================================");
                                    }
                                }catch(Exception e){
                                    System.out.println(e.getMessage());
                                } 
                        break;
                        case 3:
                            String findId = "";
                            System.out.print("Id actor: ");
                            findId = scan.nextLine();
                                try{
                                    List<Actor> getid = controller.getAllActors();
                                    boolean foundActor = controller.hasActors(getid, Integer.parseInt(findId));
                                    if(!(foundActor)){
                                        System.out.println("==========================================");
                                        System.out.println("Actor found");
                                        System.out.println("Dont foun any actor with id: " + findId);   
                                        System.out.println("===========================================");
                                    }else {
                                        System.out.println("==========================================");
                                        System.out.println("Actor found");
                                        controller.getAllActorsById(Integer.parseInt(findId));
                                        System.out.println("==========================================");
                                    }
                                }catch(Exception e){
                                    System.out.println(e.getMessage());
                                }
                        break;
                        case 4:
                            try{
                                String name = "";
                                System.out.print("Name:");
                                name = scan.nextLine();
                                System.out.print("ID: ");
                                Integer id = scan.nextInt();
                                //String heigth = "";
                                System.out.print("Heigt (xx.xx(m)):");
                                Float heigth = scan.nextFloat();
                                scan.nextLine();
                                System.out.print("Birth (xx/Dxxxxber/xxxx) :");
                                String birth = scan.nextLine();
                                controller.insertNewActor(id, name, heigth, birth);
                                }catch(Exception e){
                                System.out.println("Erro -> " + e.getMessage());
                                }
                        break;
                        case 5: 
                             List<Actor>  actorsToUpdate = controller.getAllActors();
                                try {    
                                    if(actorsToUpdate.isEmpty()){
                                        System.out.println();
                                        System.out.println("Dont exist any Actor|\n");
                                    } else{
                                        System.out.println("\n\nList of all actors:");
                                            for(Actor a:actorsToUpdate){
                                                System.out.println( a.toString());
                                            }
                                        String idToUpdate = "";
                                        System.out.println("Insert actorId to update:");
                                        idToUpdate = scan.nextLine(); 
                                        //Name
                                        String nameToUpdate = "";
                                        System.out.println("New Name:");
                                        nameToUpdate = scan.nextLine(); 
                                        //heigth
                                        String heigthToUpdate = ""; 
                                        System.out.println("Heigt (xx.xx(m)):");
                                        heigthToUpdate = scan.nextLine(); 
                                        //birth
                                        String birthToUpdate = "";
                                        System.out.println("birth (xx/Dxxxxber/xxxx) :");
                                        birthToUpdate = scan.nextLine();
                                        //Actor(nameToUpdate,Integer.parseInt(idToUpdate), Float.parseFloat(heigthToUpdate), birthToUpdate);
                                        //actorsToUpdate.add(newActor);
                                        if(controller.updateActor(new Actor(Integer.parseInt(idToUpdate), nameToUpdate, Float.parseFloat(heigthToUpdate), birthToUpdate))){
                                            System.out.println("Actor updated! ");
                                        }else {
                                            System.out.println("Dont exists actors with this id");
                                        }
                                    }
                                }catch(Exception ex){
                                    System.out.println("Error in provided data: " );
                                }
                        break;
                        case 6:
                            String path = "";
                            System.out.println("Give the path to save the file:");
                            path = scan.nextLine();
                            System.out.println("You give the path:" + path);
                            controller.loadTxtFile(path);
                        break;
                        case 7:
                            String pathToSave = "";
                            System.out.println("\n\nGive the path to save the file: ");
                            pathToSave = scan.nextLine();       
                            controller.saveTxtFile(pathToSave);
                            System.out.println("File created: " + pathToSave);
                        break;
                        default:
                        break;
                    }
                }else{
                    if(opcao>7){
                         System.out.println("Invalid entry. enter a number amoung the options");
                    }
                }         
            } catch(NumberFormatException e){
                System.out.println("Invalid entry. enter a number amoung the options [ERRO]-> " + e.getMessage()); 
            }
        }       
        System.out.println();
        System.out.println("Inserted [0] The program is over.");
        scan.close();
    }
}
