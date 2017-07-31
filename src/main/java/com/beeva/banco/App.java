package com.beeva.banco;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.beeva.banco.dao.ClienteDAO;
import com.beeva.banco.dao.CuentaDAO;
import com.beeva.banco.impl.ClienteDAOImp;
import com.beeva.banco.impl.CuentaDAOImp;
import com.beeva.banco.model.Cliente;
import com.beeva.banco.model.Cuenta;
import com.beeva.banco.model.TipoCuenta;
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
       
        Cliente c = new Cliente();
        CuentaDAO ctdao = (CuentaDAO) context.getBean(CuentaDAOImp.class);
        MongoUtil mongoUtil = new MongoUtil();
		MongoClient mongoClient = mongoUtil.mongoConnect();
        BasicDBObject d1 = new BasicDBObject();
        Cuenta cuenta = new Cuenta();
        Calendar cal = Calendar.getInstance();
 		int dia = cal.get(Calendar.DAY_OF_WEEK);
        List<Cliente> clients = cdao.getClientes();
        int opc=0;
        String aux ;
        Scanner sc = new Scanner(System.in);
        BasicDBObject d = new BasicDBObject();
        while(opc!=9){
        	System.out.println("");
        	 System.out.println("1. Crear Usuario: ");
        	 System.out.println("2. Listar usuarios: ");
        	 System.out.println("3. Crear Cuenta : ");
        	 System.out.println("4. Retirar : ");
        	 System.out.println("5. Deposito ");
        	 System.out.println("9. Salir");
        	 opc =sc.nextInt();
        	 switch(opc){
        	 case 1:
        		 System.out.println("inserte el nombre: ");
        		 aux=sc.next();
        		 c.setNombre(aux);
        		 System.out.println("inserte el apellido: ");
        		 aux=sc.next();
        		 c.setApellido(aux);
        		 cdao.save(c);
        		 
        	        d.put("mensaje","Se ha insertado un usuario");
        	        d.put("Id", c.getIdcliente());
        	        d.put("fecha", mongoUtil.fecha());
        	        d.put("nombre", c.getNombre());
        	        d.put("apellido", c.getApellido());
        	        if( mongoUtil.doRegistro(mongoClient, d) == true){
        	     	   System.out.println("Registro Guardado con Exito !!");
        	        }else{
        	     	   System.out.println("ptm");
        	        }
          		 break;
        	 case 2: 
        		 System.out.println("elegiste 2");
        		 System.out.println("listar clientes");
        		 clients = cdao.getClientes();
        		 System.out.println("Clientes Existentes");
        		 if(clients.isEmpty()){
        			 System.out.println("No hya clientes Guardados :");
        		 }else{
        		 for(Cliente cl :clients){
        			
        			 System.out.println("Id: " + cl.getIdcliente() + " nombre: " + cl.getNombre()+ " apellido: " + cl.getApellido()+ " "); 
        		 }
        		  }
        		 break;
        	 case 3:
        		  System.out.println("Crear Cuenta");
        		  System.out.println("inserte el tipo de cuenta: " + " 1. Ahorros "+ " 2. Cheques");
         		 	aux=sc.next();
         		 	 cuenta.setIdtipo_cuenta(Integer.parseInt(aux));
	         		 System.out.println("inserte el balance: ");
	         		 aux=sc.next();
	         		 cuenta.setBalance(new BigDecimal(Integer.parseInt(aux)));
	         		System.out.println("Inserte el id el cliente: ");
	         		clients = cdao.getClientes();
	         		if(clients.isEmpty()){
	         			System.out.println("No hay usuarios");
	         		}else{
	         		System.out.println("Seleccione un cliente existente: ");
	         		for(Cliente cl :clients){
	        			 
	        			 System.out.println("Id: " + cl.getIdcliente() + " nombre: " + cl.getNombre()+ " apellido: " + cl.getApellido()+ " "); 
	        		 }
                     aux = sc.next();
                     cuenta.setIdcliente(Integer.parseInt(aux));
                     ctdao.save(cuenta);
                    	d1.put("mensaje","Se ha insertado una Cuenta");
	     	            d1.put("Id", cuenta.getIdcuenta());
	     	            d1.put("fecha", mongoUtil.fecha());
	     	            d1.put("IdCliente", cuenta.getIdcliente());
	     	            d1.put("Tipo Cuenta", cuenta.getIdtipo_cuenta());
	     	            d1.put("balance",cuenta.getBalance().toString());
     	            if( mongoUtil.doRegistro(mongoUtil.mongoConnect(), d1) == true){
     	     	       System.out.println("Registro Guardado");
     	             }else{
     	     	     System.out.println("No se pudo  : ");
     	            }
	         		}
        		 break;
        	 case 4:
        		 System.out.println("Retiro");
        		 System.out.println("Seleccione un cliente de la siguiente lista por id: ");
        		 clients = cdao.getClientes();
        		 if(clients.isEmpty()){
        			 System.out.println("No hay usuarios ");
        		 }else{
        		 for(Cliente cl :clients){
        			 
        			 System.out.println("Id: " + cl.getIdcliente() + " nombre: " + cl.getNombre()+ " apellido: " + cl.getApellido()+ " "); 
        		 }
                 aux = sc.next();
            
                 System.out.println("Ha seleccionado al cliente "+ " Id: " +cdao.getCliente(Integer.parseInt(aux)).getIdcliente() + " nombre: " + cdao.getCliente(Integer.parseInt(aux)).getNombre()+" apellido: "+cdao.getCliente(Integer.parseInt(aux)).getApellido());
                 Cuenta cuentaCliente = cdao.getCuenta(Integer.parseInt(aux));
                 int nn = cuentaCliente.getIdcuenta();
                 TipoCuenta tp = cdao.getTipoCuenta(Integer.parseInt(aux));
                 System.out.println("La cuenta es de tipo " + tp.getNombre()+" con balance " + cuentaCliente.getBalance().toString() );
                 System.out.println("Cuanto deseas retirar : " );
                 aux = sc.next();
                 BigDecimal bg = new BigDecimal(aux);
                if(cuentaCliente.getIdtipo_cuenta()==2){
                	if(dia == 1 || dia == 7){
             			System.out.println("No puedes retirar en fin  de Semana ");
             			System.out.println(" su saldo es : " + cuentaCliente.getBalance().toPlainString());
             		}else{
             			ctdao.retiro(bg, cuentaCliente.getIdcuenta());
             			System.out.println(" su saldo es : " + cuentaCliente.getBalance().toPlainString());
             		}
                }else if(cuentaCliente.getIdtipo_cuenta()==1){
                	if(cuentaCliente.getBalance().doubleValue()<5000){
                		System.out.println("No se puede Retirar tu saldo es menor de 5000");
                	}else{
                		ctdao.retiro(bg, cuentaCliente.getIdcuenta());
                		System.out.println(" su saldo es : " + cdao.getCuenta(nn).getBalance().toString());
                		
                	}
                	
                }
        		 }
        		 break;
        	 case 5:
        		 System.out.println("Deposito");
        		 System.out.println("Seleccione un cliente de la siguiente lista por id: ");
        		 clients = cdao.getClientes();
        		 if(clients.isEmpty()== true){
        			 System.out.println("No hay clientes");
        		 }else{
        		 
        		 for(Cliente cl :clients){
        			 
        			 System.out.println("Id: " + cl.getIdcliente() + " nombre: " + cl.getNombre()+ " apellido: " + cl.getApellido()+ " "); 
        		 }
                 aux = sc.next();
            
                 System.out.println("Ha seleccionado al cliente "+ " Id: " +cdao.getCliente(Integer.parseInt(aux)).getIdcliente() + " nombre: " + cdao.getCliente(Integer.parseInt(aux)).getNombre()+" apellido: "+cdao.getCliente(Integer.parseInt(aux)).getApellido());
                 
                 Cuenta cuentaCliente2 = cdao.getCuenta(Integer.parseInt(aux));
                 int idC = cuentaCliente2.getIdcliente();
                 int n = cuentaCliente2.getIdcuenta();
                 TipoCuenta tp1 = cdao.getTipoCuenta(Integer.parseInt(aux));
                 System.out.println("La cuenta es de tipo " + tp1.getNombre()+" con balance " + cuentaCliente2.getBalance().toString() );
                 System.out.println("Cuanto deseas Depositar : " );
                 aux = sc.next();
                 BigDecimal bg1 = new BigDecimal(aux);
                 if(bg1.doubleValue()>0){
                	 if(cdao.deposito(bg1, idC)==true){
                		 System.out.println("Deposito Exitoso");
                		 System.out.println("Su saldo es : "+ cdao.getCuenta(n).getBalance().toString());
                	 }else{
                		 System.out.println("Deposito no echo");
                	 }
                 }else{
                	 System.out.println("Inserte otra cantidad");
                  }
        		 }
        		 break;
        		 
        	default:
        		System.out.println("Inserte opcion valida ");
        	 
        	 }
        }
        sc.close();
       
    }
}
