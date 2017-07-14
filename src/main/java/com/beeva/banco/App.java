package com.beeva.banco;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.beeva.banco.dao.ClienteDAO;
import com.beeva.banco.impl.ClienteDAOImp;
import com.beeva.banco.model.Cliente;
import com.beeva.banco.util.MongoUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        ApplicationContext context =  new ClassPathXmlApplicationContext("core-context.xml");
        
        ClienteDAO cdao = (ClienteDAO)context.getBean(ClienteDAOImp.class);
        MongoUtil mongoUtil = new MongoUtil();
        Cliente c = new Cliente();
        //MongoClient mongoClient = mongoUtil.mongoConnect();
        c.setNombre("Fabiola");
        c.setApellido("Silva");
        cdao.save(c);
        /*
         
        BasicDBObject d = new BasicDBObject();
        d.put("mensaje","Se ha insertado un usuario");
        d.put("fecha", mongoUtil.fecha());
        d.put("nombre", c.getNombre());
        d.put("apellido", c.getApellido());
       
       if( mongoUtil.doRegistro(mongoClient, d) == true){
    	   System.out.println("ahuevo");
       }else{
    	   System.out.println("ptm");
       }*/
        
        
        /*List<Cliente> clientes = cdao.traeClientes();
        for(Cliente ci :clientes){
        	System.out.println(ci.getIdcliente() + " " + ci.getNombre()+ " " + ci.getApellido());
        }
        
        */
        
        
    }
}
