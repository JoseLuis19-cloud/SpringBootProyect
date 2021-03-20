package com.myfactory.SBootWebProject.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myfactory.SBootWebProject.beanForm.BeanIdUsuario;
import com.myfactory.SBootWebProject.beanForm.BeanMenuUsuarioSession;
import com.myfactory.SBootWebProject.beanForm.BeanSubMenuN1UsuarioSession;
import com.myfactory.SBootWebProject.beanForm.BeanUsuarioSession;

import com.myfactory.SBootWebProject.model.Aviso;
import com.myfactory.SBootWebProject.model.MenusUsuario;
import com.myfactory.SBootWebProject.servicesJPA.ServJPAAviso;
import com.myfactory.SBootWebProject.servicesJPA.ServJPAMenusUsuario;
import com.myfactory.SBootWebProject.servicesJPA.ServJPAUsuario;

@Controller
// @PropertySource("classpath:servicioEmail.properties")
public class AppController {
	@Autowired
	ServJPAUsuario serviciosJPAUsuario;
	@Autowired
	private BeanIdUsuario beanIdUsuario;
	@Autowired
	private ServJPAMenusUsuario servJPAMenusUsuario;
	@Autowired
	public BeanUsuarioSession beanUsuarioSession;
	@Autowired
	public ServJPAAviso servJPAAviso;
	
//	@Value("${path.servicioEmai}")
//	private String servidorEmail;

	@RequestMapping("/")
	public String viewHomePage(Model modelo) {
		BeanMenuUsuarioSession beanMenuPrinUsuario = null;
		BeanSubMenuN1UsuarioSession beanSubMenuN1UsuarioSession = null;
		
		List<BeanSubMenuN1UsuarioSession> listSubMenuUsuSession;

		Iterable<MenusUsuario> menuUsuario = servJPAMenusUsuario.obtenerMenuUsuario( beanIdUsuario.getIdUsuario());
	 	Iterator<MenusUsuario> menuUsu = menuUsuario.iterator();
	 	
 		List<BeanMenuUsuarioSession> listMenuUsuarioSession  = new ArrayList<BeanMenuUsuarioSession>();
		
	 	while(menuUsu.hasNext()){
	 	 // Cargamos Menu Principal que tiene definidos y activados el usuario
	 		MenusUsuario menuUsuarioIter = menuUsu.next();
	 		
 			Iterable<MenusUsuario> subMenuUsuario = servJPAMenusUsuario.obtenerSubMenuUsuario(beanIdUsuario.getIdUsuario(), menuUsuarioIter.getMenu().getIdMenu());
 			
 			listSubMenuUsuSession = new ArrayList<BeanSubMenuN1UsuarioSession>();
 			
 			// System.out.println(menuUsuarioIter.getIdMenu() + " , " + menuUsuarioIter.getMenu().getTextoMenu());
 			
 			// Cargamos los Elementos que corresponden al submenu del elemento de menu principal para el usuario

 			for (MenusUsuario elemenMenusUsuario : subMenuUsuario) {

 			//	System.out.println("----->" + elemenMenusUsuario.getSubMenu1().getIdSubmenuNivel1()  + " , " + elemenMenusUsuario.getSubMenu1().getTextoSubMenuN1() );
 				
 				beanSubMenuN1UsuarioSession = new BeanSubMenuN1UsuarioSession(elemenMenusUsuario.getSubMenu1().getIdSubmenuNivel1(), elemenMenusUsuario.getSubMenu1().getNumOrdenMenu(), elemenMenusUsuario.getSubMenu1().getTextoSubMenuN1(), elemenMenusUsuario.getSubMenu1().getHrefAplicacionN1());
 				
 				listSubMenuUsuSession.add(beanSubMenuN1UsuarioSession);
 		 	}

 	  // Instanciamos de la manera que pueda ser leido la estructura de beans de menu de la pagina Web.	
 		 beanMenuPrinUsuario = new BeanMenuUsuarioSession(menuUsuarioIter.getIdMenu(), menuUsuarioIter.getNumOrden(), menuUsuarioIter.getMenu().getTextoMenu(), menuUsuarioIter.getMenu().getHrefAplicacion(), listSubMenuUsuSession );
 		 listMenuUsuarioSession.add(beanMenuPrinUsuario); 		
	 	}
	 	// CAMBIAR ESTO PRONTO EL 7
	 	beanUsuarioSession.setIdUsuario(new Long(7));
 		beanUsuarioSession.setListBeanMenuUsuarioSession(listMenuUsuarioSession);
 		
 		// Cargar la lista de submenu de cada menu en su Bean de session.
 		// Iterator<BeanMenuUsuarioSession> listBeanUsuSesionIter = listMenuUsuarioSession.iterator();
 		int ind = 0;
 		for (BeanMenuUsuarioSession elemenMenuUsuSession : listMenuUsuarioSession) {
 			beanUsuarioSession.getListBeanMenuUsuarioSession().get(ind).setListBeanSubMenuN1UsuSession(elemenMenuUsuSession.getListBeanSubMenuN1UsuSession());
 			ind ++;
		}
		
 		modelo.addAttribute("idUsuario", beanIdUsuario.getIdUsuario());
 		modelo.addAttribute("opcionesMenuUsuario", listMenuUsuarioSession);
 		
 		// Enviar un email 
 		//enviarEmail();
 		
 		// Enviar un email 
 		leerAvisosUsuario(modelo, beanIdUsuario.getIdUsuario());
 
	return "Desktop";
	}
	
	@RequestMapping(value = "/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("errorMsg", "Your username and password are invalid.");

        if (logout != null)
            model.addAttribute("msg", "You have been logged out successfully.");

        return "logines";
    }
	
	private void enviarEmail()
	{
	/*	 System.out.println("Buenbas");
		 SimpleMailMessage message = new SimpleMailMessage(); 
	        message.setFrom("jlbuenome.andro@gmail.com");
	        message.setTo("jlbuenome.andro@gmail.com"); 
	        message.setSubject("Cabecera"); 
	        message.setText("Cuerpo del mensaje");
	        emailSender.send(message); */
		
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost("smtp.gmail.com");
	    mailSender.setPort(587);
	    
	    mailSender.setUsername("jlbuenome.andro@gmail.com");
	    mailSender.setPassword("19buenomendez70");
		
		/* Properties props = mailSender.getJavaMailProperties();
		    props.put("mail.transport.protocol", "smtp");
		    props.put("mail.smtp.auth", "true");
		    props.put("mail.smtp.starttls.enable", "true");
		    props.put("mail.debug", "true"); */
		    
		    
		  Properties props = new Properties();
		  props.put("mail.smtp.host", "smtp.gmail.com");
		  props.put("mail.smtp.port", "465");
		  props.put("mail.smtp.auth", "true");
		  props.put("mail.smtp.socketFactory.port", "465");
		  props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		 
		
		   Session session = Session.getInstance(props,
	                new javax.mail.Authenticator() {
	                    protected PasswordAuthentication getPasswordAuthentication() {
	                        return new PasswordAuthentication("jlbuenome.andro@gmail.com", "19buenomendez70");
	                    }
	                });
	        
	        try {

	            Message message = new MimeMessage(session);
	            message.setFrom(new InternetAddress("jlbuenome.andro@gmail.com"));
	            message.setRecipients(
	                    Message.RecipientType.TO,
	                    InternetAddress.parse("jlbuenome.andro@gmail.com")
	            );
	            message.setSubject("Testing Gmail TLS");
	            message.setText("Dear Mail Crawler,"
	                    + "\n\n Please do not spam my email!");

	            Transport.send(message);

	            System.out.println("Done");

	        } catch (MessagingException e) {
	            e.printStackTrace();
	        }

	/*	String mailHost = "localhost";
		String mailFrom = "jlbuenome.andro@gmail.com";
		String[] arrDestinatarios = {"jlbuenome.andro@gmail.com"};
		String mailSubject = "Asunto del email";
		String mailMessage = "Prueba del mensaje del email"; */		
		// Enviamos Mensaje de facturas vencidas el cobro al cliente
	//	try {

	//	AdapterMAIL.enviaMail(mailHost, mailFrom, arrDestinatarios,
	//			mailSubject, mailMessage);
//		}
	//	catch (Exception exp)
//		{
//			System.out.println("Se ha  producido un error al enviar un email");
//		}
 
	}
	
/*	@Bean
	public JavaMailSender getJavaMailSender() {
	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost("smtp.gmail.com");
	    mailSender.setPort(587);
	    
	    mailSender.setUsername("jlbuenome.andro@gmail.com");
	    mailSender.setPassword("19buenomendez70");
	    
	
	    return mailSender;
	} */
	private void leerAvisosUsuario(Model modelo, Long idUsuario)
	{
		Iterable<Aviso> listAviso = servJPAAviso.listAvisos(idUsuario.intValue());
 		modelo.addAttribute("listAvisos", listAviso);
	}

	
}
