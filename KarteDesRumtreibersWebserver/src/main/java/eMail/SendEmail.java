package eMail;

import java.io.IOException;

import hibernateentitysets.Deficiencies;

import javax.mail.MessagingException;
import javax.mail.Session;

import properties.ProjectPropertie;

/**
 * Send an email with the data of an specific object
 * 
 * @author Johannes
 *
 */
public class SendEmail {

	/**
	 * Send an email to an recipient with account data which is both configured
	 * in the Properties
	 * 
	 * @param deficiency
	 *            The deficiency which for which the email should be sended
	 */
	public static void sendDeficiencyMail(Deficiencies deficiency) {

		// try to send an email, print out exception text if it doesn't work
		try {

			// load data from the propertie
			String transmitter = ProjectPropertie
					.getProperty("mailAdressTransmitter");
			String transmitterPW = ProjectPropertie
					.getProperty("mailPasswordTransmitter");
			String mailTo = ProjectPropertie.getProperty("mailTo");

			// create mail session
			Session mailSession = MailUtila.getGMailSession(transmitter,
					transmitterPW);

			// build message String
			String message = "Guten Tag,\r\n\r\nes wurde ein neuer Mangel von \""
					+ deficiency.getReportingUser()
					+ "\" gemeldet.\r\n\r\nTicket Nummer: "
					+ deficiency.getId()
					+ "\r\nRaumnummer: "
					+ deficiency.getRoom().getRoomnumber()
					+ "\r\nKategorie: "
					+ deficiency.getCategory()
					+ "\r\nBeschreibung: "
					+ deficiency.getDescription()
					+ "\r\n\r\nViele Grüße\r\nIhr DHBW-App Team";

			// build subject string
			String subject = "Neues Ticket mit der Nummer "
					+ deficiency.getId();

			// send mail
			MailUtila.postMail(mailSession, mailTo, subject, message);
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}
}
