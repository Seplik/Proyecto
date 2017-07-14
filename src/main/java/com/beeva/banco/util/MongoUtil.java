package com.beeva.banco.util;

import java.net.UnknownHostException;
import java.util.Date;

import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
@Repository
public class MongoUtil {
	private String host;
	private int port;
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	
	public MongoClient mongoConnect(){
		MongoClient mongoClient = null;
		try{
			mongoClient= new MongoClient("localhost",27017);
		}catch(UnknownHostException unk){
			unk.printStackTrace();
		}
		return mongoClient;
	}
	public boolean doRegistro(MongoClient mg, BasicDBObject document){
		     boolean bandera;
		  	  MongoClient mongoClient= mg;   
	          DB db = mongoClient.getDB("Bancolog");
			  DBCollection td = db.getCollection("logCollection");
			 if (td.insert(document)!= null){
				 bandera = true;
			 }else{
				 bandera = false;
			 }
			  mg.close();
		return bandera;
	}
	public Date fecha(){
		Date date = new Date(System.currentTimeMillis());
		return date;
	}

}
