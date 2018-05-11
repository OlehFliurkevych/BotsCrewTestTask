package com.botscrew;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

import com.botscrew.entity.DegreeEnum;
import com.botscrew.entity.DepartmentEntity;
import com.botscrew.entity.LectorEntity;


public class App 
{
	
    public static void main( String[] args ) throws InterruptedException
    {
    	EntityManagerFactory emf=Persistence.createEntityManagerFactory("testtask");
    	EntityManager em=emf.createEntityManager();
    	FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);
    	em.getTransaction().begin();
    	

    	
    	//Add departmens

    	List<DepartmentEntity> departmens=new ArrayList<>();
    	DepartmentEntity depart_1=new DepartmentEntity("Applied Math", "Kaleniuk Petro");
    	DepartmentEntity depart_2=new DepartmentEntity("Menegment", "Vozna Svitlana");
    	DepartmentEntity depart_3=new DepartmentEntity("Applied physics", "Kan Oliver");
    	DepartmentEntity depart_4=new DepartmentEntity("Philology", "Topulko Vasyl");
    	DepartmentEntity depart_5=new DepartmentEntity("International relations", "Hnativ Roman");
    	departmens.add(depart_1);
    	departmens.add(depart_2);
    	departmens.add(depart_3);
    	departmens.add(depart_4);
    	departmens.add(depart_5);
    	
    	//Add lectors
    	
    	List<LectorEntity> lectors=new ArrayList<>();
    	for(int i=0;i<25;i++){
    		for (int j=0;j<departmens.size();j++) {
    			Random rand=new Random();
    			LectorEntity assist=new LectorEntity("Assist_"+(i+1),
    					DegreeEnum.ASSIST,
    					(int)(1500+Math.random()*2500),
    					departmens.get(rand.nextInt(departmens.size())));
    			lectors.add(assist);
			}
    	}

    	for(int i=0;i<15;i++){
    		for (int j=0;j<departmens.size();j++) {
    			Random rand=new Random();
    			LectorEntity assocprof=new LectorEntity("Associate professor_"+(i+1),
    					DegreeEnum.ASSOCPROF,
    					(int)(2000+Math.random()*3500),
    					departmens.get(rand.nextInt(departmens.size())));
    			lectors.add(assocprof);
			}
    	}

    	for(int i=0;i<10;i++){
    		for (int j=0;j<departmens.size();j++) {
    			Random rand=new Random();
    			LectorEntity prof=new LectorEntity("Professor_"+(i+1),
    					DegreeEnum.PROF,
    					(int)(3500+Math.random()*5500),
    					departmens.get(rand.nextInt(departmens.size())));
    			lectors.add(prof);
			}
    	}
    	
    	List<LectorEntity> lectors_1=new ArrayList<>();
    	for(int i=0;i<40;i++){
    		lectors_1.add(lectors.get(i+5));
    	}
    	List<LectorEntity> lectors_2=new ArrayList<>();
    	for(int i=0;i<41;i++){
    		lectors_2.add(lectors.get(i+6));
    	}
    	List<LectorEntity> lectors_3=new ArrayList<>();
    	for(int i=0;i<32;i++){
    		lectors_3.add(lectors.get(i+9));
    	}
    	List<LectorEntity> lectors_4=new ArrayList<>();
    	for(int i=0;i<25;i++){
    		lectors_4.add(lectors.get(i+5));
    	}
    	List<LectorEntity> lectors_5=new ArrayList<>();
    	for(int i=0;i<36;i++){
    		lectors_5.add(lectors.get(i+8));
    	}
    	
    	//Add lectors to departmens
    	
    	depart_1.setLectors(lectors_1);
    	depart_2.setLectors(lectors_2);
    	depart_3.setLectors(lectors_3);
    	depart_4.setLectors(lectors_4);
    	depart_5.setLectors(lectors_5);
    	
    	for(int i=0;i<lectors.size();i++){
    		System.out.println(lectors.get(i));
    	}
    	
    	for(int i=0;i<departmens.size();i++){
    		System.out.println(departmens.get(i));
    	}
    	
    	//Save departments and lectors to database
    	for (DepartmentEntity departmentEntity : departmens) {
			em.persist(departmentEntity);
		}
    	
    	for (LectorEntity lectorEntity : lectors) {
			em.persist(lectorEntity);
		}
    	em.getTransaction().commit();
    	boolean exist=true;
    	
    	while(exist){
    		System.out.println("==================== Welcome to university ===================="+'\n');
    		System.out.println("Please enter your query or enter 0 (zero) to exit."+'\n');
        	Scanner sc=new Scanner(System.in);
        	String line=sc.nextLine();
    		if(line.contains("Who is head of department")){
        		String departHead=cutLine(line,"Who is head of department");
        		TypedQuery<DepartmentEntity> query=em.createQuery("SELECT d FROM DepartmentEntity d WHERE d.departmentName=:name",
        				DepartmentEntity.class)
        				.setParameter("name",departHead.trim());
        		DepartmentEntity depart=(DepartmentEntity)query.getSingleResult();
        		System.out.println("================================================"+'\n');
        		
        		System.out.println("Head of "+depart.getDepartmentName()
        				+" department is "
        				+depart.getHeadOfDepartmentName()+'\n');
        		System.out.println("================================================"+'\n');
        	}else if(line.contains("Show")&&line.substring(line.length()-10, line.length()).trim().equals("statistic")){
        		String departHead=line.substring("Show".length(), line.length()-10).trim();
        		TypedQuery<Long> countAssist=
        				em.createQuery("SELECT count(l) FROM LectorEntity l "
        						+ "WHERE l.department.departmentName=:name and "
        						+ "l.degree=:degree",Long.class)
        				.setParameter("name", departHead)
        				.setParameter("degree", DegreeEnum.ASSIST);
        		Long assists=countAssist.getSingleResult();
        		TypedQuery<Long> countAssocProf=
        				em.createQuery("SELECT count(l) FROM LectorEntity l "
        						+ "WHERE l.department.departmentName=:name and "
        						+ "l.degree=:degree",Long.class)
        				.setParameter("name", departHead)
        				.setParameter("degree", DegreeEnum.ASSOCPROF);
        		Long associatProf=countAssocProf.getSingleResult();
        		TypedQuery<Long> countProf=
        				em.createQuery("SELECT count(l) FROM LectorEntity l "
        						+ "WHERE l.department.departmentName=:name and "
        						+ "l.degree=:degree",Long.class)
        				.setParameter("name", departHead)
        				.setParameter("degree", DegreeEnum.PROF);
        		Long professors=countProf.getSingleResult();
        		System.out.println("================================================"+'\n');
        		System.out.println("assistans - "+assists);
        		System.out.println("associate professors - "+associatProf);
        		System.out.println("professors - "+professors+'\n');
        		System.out.println("================================================"+'\n');
        	}else if(line.contains("Show the average salary for department")){
        		String departName=cutLine(line,"Show the average salary for department");
        		System.out.println(departName);
        		TypedQuery<Double> avgQuery=
        				em.createQuery("SELECT avg(l.salary) FROM LectorEntity l WHERE l.department.departmentName=:name",Double.class)
        				.setParameter("name",departName.trim());
        		Double avg=avgQuery.getSingleResult();
        		System.out.println("================================================"+'\n');
        		System.out.println("The average salary of "+departName+" is "+avg);
        		System.out.println("================================================"+'\n');
        	}else if(line.contains("Show count of employee for")){
        		String departName=cutLine(line,"Show count of employee for");
        		System.out.println(departName);
        		TypedQuery<Long> countEmp=em.createQuery("SELECT count(l) FROM LectorEntity l WHERE l.department.departmentName=:name",Long.class)
        				.setParameter("name", departName.trim());
        		Long count=countEmp.getSingleResult();
        		System.out.println("================================================"+'\n');
        		System.out.println(count);
        		System.out.println("================================================"+'\n');
        	}else if(line.contains("Global search by")){
        		String search=cutLine(line,"Global search by");
        		System.out.println("Went to method");
        		System.out.println("================================================"+'\n');
        		
        		System.out.println(search.trim());
        		
        		QueryBuilder qb=fullTextEntityManager.getSearchFactory()
        				.buildQueryBuilder().forEntity(DepartmentEntity.class).get();
        		Query luceneQuery=qb.keyword().onFields("departmentName","headOfDepartmentName")
        				.matching(search.trim())
        				.createQuery();
        		javax.persistence.Query jpaQuery=fullTextEntityManager
        				.createFullTextQuery(luceneQuery, DepartmentEntity.class);
        		
        		QueryBuilder qbLector=fullTextEntityManager.getSearchFactory()
        				.buildQueryBuilder().forEntity(LectorEntity.class).get();
        		Query luceneQueryLector=qbLector.keyword().onField("lectorName")
        				.matching(search.trim())
        				.createQuery();
        		javax.persistence.Query jpaQueryLector=fullTextEntityManager
        				.createFullTextQuery(luceneQueryLector, LectorEntity.class);
        		List result=jpaQuery.getResultList();
        		result.addAll(jpaQueryLector.getResultList());
        		System.out.println(result+"\n");
        		System.out.println("================================================"+'\n');
        	}else if(line.equals("0")||line.equals("zero")){
        		exist=false;
        	}else{
        		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++"+'\n');
        		System.out.println("You enter wrong query! Please repeat entering!"+'\n');
        		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++"+'\n');
        	}
    	}
    	em.close();
    	emf.close();
    }
    
    public static String cutLine(String lineEntered,String lineSearch){
    	return lineEntered.substring(lineSearch.length(), lineEntered.length());
    }
}
