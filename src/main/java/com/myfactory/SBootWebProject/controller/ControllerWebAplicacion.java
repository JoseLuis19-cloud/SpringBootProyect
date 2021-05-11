package com.myfactory.SBootWebProject.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.ProcessBuilder.Redirect;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Controller
@RequestMapping("/gestionsistema")
public class ControllerWebAplicacion {
	
	protected static final Logger parentLogger = LogManager.getLogger();
	
	@GetMapping("/autores")
	public String autoresTecnologia(Model modelo) {
		return "Aplicacion/AutorTecnologia";
	}
	
	@RequestMapping(value = "/copsegMySQL", method = RequestMethod.GET)
	public String copSeguridadMySQL(Model modelo) {

	//	buckUpMySQLBBDD();
		
		backup();
		
		return "Aplicacion/copiaSeguridadMYSQL";
	}

 	private static void backupMySQLBBDD2() {
		   try {
			   
		//	   Process exec = Runtime.getRuntime().exec(new String[]{"Terminal.app","/c","C:\\Program Files\\MySQL\\MySQL Server 5.6\\bin\\mysqldump "+fisier.getName()+" > C:\\"+fisier.getName()+".sql;"});

			   
			   String [] cmd = {"/Applications/XAMPP/xamppfiles/bin/mysqldump", 
		                 "-u root", 
		                 "-p",
		                 "springboot" ,
		                 " > /Applications/XAMPP/xamppfiles/bin/mysqldump/prueba1.sql" };
			   
			   
			   String[] cmd2 = {"/usr/bin/open", "-a" , "mysqldump", 
					   "-u root", 
		                 "-p",
		                 "springboot" ,
		                 " > bbb123.sql" ,
					   "/Applications/XAMPP/xamppfiles/bin"};
			   
		  //    Process p = Runtime.getRuntime().exec("/Applications/XAMPP/xamppfiles/bin/mysqldump -u root -p springboot");

			Calendar now = Calendar.getInstance();
			String year =  new SimpleDateFormat("yyyy").format(now.getTime());
			String month = new SimpleDateFormat("MM").format(now.getTime());
			String day = new SimpleDateFormat("DD").format(now.getTime());

				
		      Process p = Runtime.getRuntime().exec(cmd2);
		      
		      
		 //     if (p.waitFor() == 0) {
		    	 // System.out.println("El proceso ha terminado correctamente");
		    	  
		    	  
		    //  }
		      
		      
		 /*     InputStream is = p.getInputStream();
		      FileOutputStream fos = new FileOutputStream("springboot_backup_" + year + month + day + ".sql");
		      byte[] buffer = new byte[1000];

		      int leido = is.read(buffer);
		      while (leido > 0) {
		         fos.write(buffer, 0, leido);
		         leido = is.read(buffer);
		      } */

		  //    fos.close();

		   } catch (Exception e) {
		      e.printStackTrace();
		   }
		}
	
	
	
	private static void restoreMySQLBBDD2() {
		   try {
		      Process p = Runtime
		            .getRuntime()
		            .exec("C:/Aplicaciones/wamp/bin/mysql/mysql5.1.36/bin/mysql -u root -ppassword database");

		      OutputStream os = p.getOutputStream();
		      FileInputStream fis = new FileInputStream("\"springboot_backup_20201226.sql");
		      byte[] buffer = new byte[1000];

		      int leido = fis.read(buffer);
		      while (leido > 0) {
		         os.write(buffer, 0, leido);
		         leido = fis.read(buffer);
		      }

		      os.flush();
		      os.close();
		      fis.close();

		   } catch (Exception e) {
		      e.printStackTrace();
		   }
		}  
	
	
	private static void buckUpMySQLBBDD() {
		  
	//  required properties for exporting of db
/*	Properties properties = new Properties();
	properties.setProperty(MysqlExportService.DB_NAME, "springboot");
	properties.setProperty(MysqlExportService.DB_USERNAME, "root");
	properties.setProperty(MysqlExportService.DB_PASSWORD, "");
	properties.setProperty(MysqlExportService.JDBC_CONNECTION_STRING, "jdbc:mysql://localhost:3308/springboot?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");

	       
	//properties relating to email config
	properties.setProperty(MysqlExportService.EMAIL_HOST, "smtp.gmail.com");
	properties.setProperty(MysqlExportService.EMAIL_PORT, "587");
	properties.setProperty(MysqlExportService.EMAIL_USERNAME, "jlbuenome.andro@gmail.com");
	properties.setProperty(MysqlExportService.EMAIL_PASSWORD, "19buenomendez70");
	properties.setProperty(MysqlExportService.EMAIL_FROM, "jlbuenome.andro@gmail.com");
	properties.setProperty(MysqlExportService.EMAIL_TO, "jlbuenome.andro@gmail.com"); */

	//set the outputs temp dir
	
/*	properties.setProperty(MysqlExportService.TEMP_DIR, new File("/Users/UsuarioJoseLuis/Documents/backupsmysql").getPath());
	 properties.setProperty(MysqlExportService.PRESERVE_GENERATED_ZIP, "true");
	
	
	MysqlExportService mysqlExportService = new MysqlExportService(properties);
	//File file1 = mysqlExportService.getGeneratedZipFile();
	// mysqlExportService..clearTempFiles(); */
	
/*	try {
		mysqlExportService.export();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
	} */
	
/*	private static void restoreMySQLBBDD() {
	String sql = null;
	try {
		sql = new String(Files.readAllBytes(Paths.get("/Users/UsuarioJoseLuis/Documents/backupsmysql/26_12_2020_12_28_20_springboot_database_dump.zip")));
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}

	try {
		boolean res = MysqlImportService.builder()
		        .setDatabase("springboot")
		        .setJdbcConnString("jdbc:mysql://localhost:3308/springboot?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC")
		        .setSqlString(sql)
		        .setUsername("root")
		        .setPassword("")
		        .setDeleteExisting(true)
		        .setDropExisting(true)
		        .importDatabase();
	} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} */


	}
	
	
	public static void backup() {
        String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd"));
        String backupPath = String.format("%s/%s.%s", "/Users/UsuarioJoseLuis/Documents", currentDate, "sql");
        File backupFile = new File(backupPath);
       // if (!backupFile.exists()) {
            try {
            backupFile.createNewFile();
         //   String mysqlCom=String.format("/Applications/XAMPP/xamppfiles/bin/mysqldump -u%s -p%s %s","root","","springboot");
            
            String mysqlCom2=String.format("/Applications/XAMPP/xamppfiles/bin/mysqldump -u root -p springboot > /Users/UsuarioJoseLuis/Documents/copseg2.sql");
          //  String[] command = new String[] { "/bin/bash", "-c" , "echo 19mendez70| sudo -S",  mysqlCom};
            
           // String[] command = new String[] { "/bin/bash", "-c" , "ls > /Users/UsuarioJoseLuis/Documents/lista12.txt"};
            
          //  String[] command = new String[] { "/bin/bash", "-c" , "echo 19mendez70| sudo -S /sbin/ifconfig > /Users/UsuarioJoseLuis/Documents/lista15.txt" };
            String[] command2 = new String[] { "/bin/bash", "-c" , "echo 19mendez70| sudo -S ", mysqlCom2 };
   
            
            ProcessBuilder processBuilder = new ProcessBuilder(Arrays.asList(command2));
            processBuilder.redirectError(Redirect.INHERIT);
            processBuilder.redirectOutput(Redirect.to(backupFile));
            Process process = processBuilder.start();
            process.waitFor();
            System.out.println("Se ha realizado la backup de la BBDD SpringBoot");//
			// parentLogger.error("Se ha realizado la backup de la BBDD SpringBoot");
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
 
   // } else {
    //	parentLogger.error("Se realizo la copia de seguridad ya");
   // }
	}
        
        public static void restore() {
            String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd"));
            String backupPath = String.format("%s/%s.%s", "/Users/UsuarioJoseLuis/Documents", currentDate, "sql");
            File backupFile = new File(backupPath);
            if (!backupFile.exists()) {
                try {
                backupFile.createNewFile();
                String mysqlCom=String.format("/Applications/XAMPP/xamppfiles/bin/mysql -u%s -p%s %s","root","","springboot", " < 2020_12_28.sql");
              
                String[] command = new String[] { "/bin/bash", "-c" , "echo 19mendez70 | sudo -S ",  mysqlCom};
                ProcessBuilder processBuilder = new ProcessBuilder(Arrays.asList(command));
                processBuilder.redirectError(Redirect.INHERIT);
                processBuilder.redirectOutput(Redirect.to(backupFile));
                Process process = processBuilder.start();
                process.waitFor();
                System.out.println("Se ha realizado la backup de la BBDD SpringBoot");//
    			parentLogger.error("Se ha realizado la backup de la BBDD SpringBoot");
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } else {
        	parentLogger.error("Se realizo la copia de seguridad ya");
        }
}
	
}
