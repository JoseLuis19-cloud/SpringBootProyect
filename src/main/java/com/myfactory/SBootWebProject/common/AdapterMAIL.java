
package com.myfactory.SBootWebProject.common;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import java.util.ArrayList;
import java.io.*;
import javax.activation.FileDataSource;
import javax.activation.DataHandler;

/**
 * <p>
 * Titulo: SingleAdapterMAIL
 * </p>
 * <p>
 * Descripcion: Clase adaptadora que proporciona los metodos necesarios para
 * el envio de mail
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Empresa:
 * </p>
 * 
 * @author Xurxo
 * @version 1.0
 */

public class AdapterMAIL {

    /**
     * Metodo encargado de preparar la cabecera del correo a enviar
     * 
     * @param smtp_host
     *                String con la direccion del host del servidor de correo
     * @param direccionOrigen
     *                String con la direccion email que aparecero como remitente
     * @param direccionDestino
     *                String con la direccion a la que enviar el correo
     * @param asunto
     *                String con el asunto que tendro el correo enviado
     * @return Message El mensaje a enviar con la cabecera preparada
     * @throws IOException
     * @throws AddressException
     * @throws MessagingException
     */
    protected static Message preparaCabecera(String smtp_host, String direccionOrigen,
	    String[] direccionesDestino, String asunto) throws IOException, AddressException,
	    MessagingException {
	Properties props = new Properties();
	props.put("mail.smtp.host", smtp_host);
	Session session = Session.getDefaultInstance(props, null);

	Message msg = new MimeMessage(session);

	InternetAddress[] addr = new InternetAddress[direccionesDestino.length];
	for (int i = 0; i < direccionesDestino.length; i++) {
	    addr[i] = new InternetAddress(direccionesDestino[i]);
	}
	msg.addRecipients(Message.RecipientType.TO, addr);

	InternetAddress from_addr = new InternetAddress(direccionOrigen);
	msg.setFrom(from_addr);

	msg.setSubject(asunto);

	return msg;
    }

    /**
     * Motodo encargado de enviar un correo sin ficheros adjuntos
     * 
     * @param smtp_host
     *                String con la direccion del host del servidor de correo
     * @param direccionOrigen
     *                String con la direccion email que aparecero como remitente
     * @param direccionesDestino
     *                String[] con la direccion a la que enviar el correo
     * @param asunto
     *                String con el asunto que tendro el correo enviado
     * @param mensaje
     *                String con el cuerpo que tendro el correo enviado
     * @return void
     * @throws IOException
     * @throws AddressException
     * @throws MessagingException
     */
    public static void enviaMail(String smtp_host, String direccionOrigen,
	    String[] direccionesDestino, String asunto, String mensaje) throws IOException,
	    AddressException, MessagingException {
	Message msg = preparaCabecera(smtp_host, direccionOrigen, direccionesDestino, asunto);
	msg.setContent(mensaje, "text/plain");
	Transport.send(msg);
    }

    /**
     * Motodo encargado de enviar un mensaje con ficheros adjuntos
     * 
     * @author Jorge Vilarioo Sanchez
     * @param smtp_host
     *                String con la direccion del host del servidor de correo
     * @param direccionOrigen
     *                String con la direccion email que aparecero como remitente
     * @param direccionesDestino
     *                String[] con la direccion a la que enviar el correo
     * @param asunto
     *                String con el asunto que tendro el correo enviado
     * @param mensaje
     *                String con el cuerpo que tendro el correo enviado
     * @param adjuntos
     *                ArrayList con los ficheros a adjuntar. Cada uno de los
     *                elmentos de este ArrayList seran de tipo java.io.File
     * @return void
     * @throws IOException
     * @throws AddressException
     * @throws MessagingException
     */
    public static void enviaConAdjuntos(String smtp_host, String direccionOrigen,
	    String[] direccionesDestino, String asunto, String mensaje, ArrayList<File> adjuntos)
	    throws IOException, AddressException, MessagingException {
	Message msg = preparaCabecera(smtp_host, direccionOrigen, direccionesDestino, asunto);

	MimeMultipart mp = new MimeMultipart();

	MimeBodyPart text = new MimeBodyPart();
	text.setDisposition(Part.INLINE);
	text.setContent(mensaje, "text/plain");
	mp.addBodyPart(text);

	for (File file : adjuntos) {
	    MimeBodyPart file_part = new MimeBodyPart();
	    FileDataSource fds = new FileDataSource(file);
	    DataHandler dh = new DataHandler(fds);
	    file_part.setFileName(file.getName());
	    file_part.setDisposition(Part.ATTACHMENT);
	    file_part.setDescription("Attached file: " + file.getName());
	    file_part.setDataHandler(dh);
	    mp.addBodyPart(file_part);
	}

	msg.setContent(mp);
	Transport.send(msg);
    }
}// Fin -clase
