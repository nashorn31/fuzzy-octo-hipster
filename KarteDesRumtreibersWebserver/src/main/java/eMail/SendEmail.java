package eMail;

import java.io.IOException;

import hibernateentitysets.Deficiencies;

import javax.mail.MessagingException;
import javax.mail.Session;

import properties.ProjectPropertie;

public class SendEmail {
	public static void sendDeficiencyMail(Deficiencies deficiency) {

		try {
			String transmitter = ProjectPropertie
					.getProperty("mailAdressTransmitter");
			String transmitterPW = ProjectPropertie
					.getProperty("mailPasswordTransmitter");

			System.out.println(transmitter + transmitterPW);

			Session mailSession = MailUtila.getGMailSession(transmitter,
					transmitterPW);

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

			String subject = "Neues Ticket mit der Nummer "
					+ deficiency.getId();

			String mailTo = ProjectPropertie.getProperty("mailTo");
			
			MailUtila.postMail(mailSession, mailTo, subject,
					message);
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}
}
