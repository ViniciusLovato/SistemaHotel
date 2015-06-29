package hotel;

import java.io.*;  
import javax.servlet.*;  
import javax.servlet.http.*; 
import java.util.*; 
import java.lang.Integer;
import static java.util.concurrent.TimeUnit.*;

import org.hibernate.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;


public class ResetSenhaServlet extends HttpServlet {

	private static SessionFactory sessionFactory;

	public void init () {
		//sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	}

	public void destroy(){
		//sessionFactory.close();
	}

	public void doPost (HttpServletRequest request, HttpServletResponse response){

		try{
			if(request.getParameter("email") != null){

				String email, novaSenha;
				email = request.getParameter("email");


				//Obtem o usuário com este email

				//Se existente, altera sua senha
				
				novaSenha = this.geraSenha();

				//Envia email com a nova senha
				this.enviaSenha(email,novaSenha);

				String erro = "Não foi possível enviar o email. É necessário configurar um servidor de email.";

				HttpSession session = request.getSession();
				session.setAttribute("erro",erro);
				session.setAttribute("senha",novaSenha);
				session.setAttribute("email",email);

			}


			RequestDispatcher dispatcher = request.getRequestDispatcher("/login/senha_alterada.jsp");
			dispatcher.forward(request, response);


		}catch(Exception e){
			e.printStackTrace();
		}

	}

	private String geraSenha(){

		return "senhaRandom";

	}



   private void enviaSenha(String emailUsuario, String senha){    

      // Recipient's email ID needs to be mentioned.
      String to = emailUsuario;

      // Sender's email ID needs to be mentioned
      String from = "contato@localhost";

      // Assuming you are sending email from localhost
      String host = "localhost";

      // Get system properties
      Properties properties = System.getProperties();

      // Setup mail server
      properties.setProperty("mail.smtp.host", host);

      // Get the default Session object.
      javax.mail.Session session = javax.mail.Session.getInstance(properties);

      try{
         // Create a default MimeMessage object.
         MimeMessage message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
         message.addRecipient(Message.RecipientType.TO,
                                  new InternetAddress(to));

         // Set Subject: header field
         message.setSubject("Nova Senha - Hotel Pousada Coconut");

         // Now set the actual message
         message.setText("Sua senha foi alterada com sucesso no nosso sistema. Sua nova senha é: " + senha);

         // Send message
         Transport.send(message);
      }catch (MessagingException mex) {
         mex.printStackTrace();
      }
   }
}