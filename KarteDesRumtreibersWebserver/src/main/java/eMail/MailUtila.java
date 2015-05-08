package eMail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtila {

	public static Session getGMailSession(String user, String pass) {
		final Properties props = new Properties();

		// Zum Empfangen
		props.setProperty("mail.pop3.host", "pop.gmail.com");
		props.setProperty("mail.pop3.user", user);
		props.setProperty("mail.pop3.password", pass);
		props.setProperty("mail.pop3.port", "995");
		props.setProperty("mail.pop3.auth", "true");
		props.setProperty("mail.pop3.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");

		// Zum Senden
		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.port", "465");
		props.setProperty("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.smtp.socketFactory.fallback", "false");

		return Session.getInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(props
						.getProperty("mail.pop3.user"), props
						.getProperty("mail.pop3.password"));
			}
		});
	}

	public static void postMail(Session session, String recipient,
			String subject, String message) throws MessagingException {
		Message msg = new MimeMessage(session);

		InternetAddress addressTo = new InternetAddress(recipient);
		msg.setRecipient(Message.RecipientType.TO, addressTo);

		msg.setSubject(subject);
		msg.setContent(message, "text/plain");
		Transport.send(msg);
	}
}
