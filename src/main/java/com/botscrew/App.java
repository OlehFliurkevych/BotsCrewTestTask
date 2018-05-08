package com.botscrew;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.botscrew.entity.DegreeEnum;
import com.botscrew.entity.DepartmentEntity;
import com.botscrew.entity.LectorEntity;


public class App 
{
    public static void main( String[] args )
    {
    	EntityManagerFactory emf=Persistence.createEntityManagerFactory("testtask");
    	EntityManager em=emf.createEntityManager();
    	
    	em.getTransaction().begin();
    	
    	List<LectorEntity> lectors=new ArrayList<>();
    	for(int i=0;i<25;i++){
    		LectorEntity assist=new LectorEntity("Assist_"+(i+1),DegreeEnum.ASSIST);
    		lectors.add(assist);
    	}
 //   	em.persist(lectors);
    	for(int i=0;i<15;i++){
    		LectorEntity assocprof=new LectorEntity("Associate professor_"+(i+1),DegreeEnum.ASSOCPROF);
    		lectors.add(assocprof);
    	}
//    	em.persist(lectors);
    	for(int i=0;i<10;i++){
    		LectorEntity prof=new LectorEntity("Professor_"+(i+1),DegreeEnum.PROF);
    		lectors.add(prof);
    	}
//    	em.persist(lectors);
    	List<DepartmentEntity> departmens=new ArrayList<>();
    	DepartmentEntity depart_1=new DepartmentEntity("Applied Math", "Kaleniuk Petro");
    	DepartmentEntity depart_2=new DepartmentEntity("Menegment", "Vozna Svitlana");
    	DepartmentEntity depart_3=new DepartmentEntity("Applied Math", "Kaleniuk Petro");
    	DepartmentEntity depart_4=new DepartmentEntity("Philology", "Topulko Vasyl");
    	DepartmentEntity depart_5=new DepartmentEntity("International relations", "Hnativ Roman");
    	departmens.add(depart_1);
    	departmens.add(depart_2);
    	departmens.add(depart_3);
    	departmens.add(depart_4);
    	departmens.add(depart_5);
//    	for (DepartmentEntity departmentEntity : departmens) {
//    		List<LectorEntity> tempLectors=new ArrayList<>();
//    		tempLectors.addAll(lectors);
//    		for(int j=0;j<25;){
//        		depart_1.setLector(lectors.get(j*(int)Math.random()*2));
//        		tempLectors.remove(j);
//    		}
//		}
    		
    	for(int i=0;i<lectors.size();i++){
    		System.out.println(lectors.get(i));
    	}
    	
    	for(int i=0;i<departmens.size();i++){
    		System.out.println(departmens.get(i));
    	}
    	
    	for (DepartmentEntity departmentEntity : departmens) {
			em.persist(departmentEntity);
		}
    	
//    	boolean exist=true;
//    	while(exist){
//    	System.out.println("__________Welcome to university________");
//    	System.out.println("Enter 0 if you want to exit");
////    	System.out.println("1:Find out who's head of some department");
////    	System.out.println("2:Show department statistic");
////    	System.out.println("3:Show the average salary for department");
////    	System.out.println("Please enter your query : ");
//    	Scanner sc=new Scanner(System.in);
//    	String line=sc.nextLine();
//    	if(sc.hasNextLine()){
//    		if(line.toLowerCase().contains("Who is head of department".toLowerCase())){
//        		
//        	}
//    	}else if(sc.nextLine()=="0"){
//    		exist=false;
//    	}
//    	}
    	em.getTransaction().commit();
    	em.close();
    	emf.close();
    }
    
    public static String cutLine(String line){
    	return line;
    }
}
