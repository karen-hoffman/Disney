package com.karenhoffman.api.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

@Service
public class MailService {
	private static final Logger logger = LoggerFactory.getLogger(MailService.class);
	
	public String sendTextEmail(String destinatario) throws IOException {
		// the sender email should be the same as we used to Create a Single Sender Verification
		    Email from = new Email("noreply@disney.com");
		    String subject = "Confirmación";
		    Email to = new Email(destinatario);
		    Content content = new Content("text/plain", "Confirmación de registro");
		    Mail mail = new Mail(from, subject, to, content);
		
		    SendGrid sg = new SendGrid("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");
		    Request request = new Request();
		    try {
		      request.setMethod(Method.POST);
		      request.setEndpoint("mail/send");
		      request.setBody(mail.build());
		      Response response = sg.api(request);
		      logger.info(response.getBody());
		      return response.getBody();	     
		    } catch (IOException ex) {
		      throw ex;
		    }	   
	}
}