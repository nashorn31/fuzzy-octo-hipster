package eMail;

import hibernateentitysets.Deficiencies;

import javax.mail.MessagingException;
import javax.mail.Session;

public class SendEmail {
	public static void sendDeficiencyMail(Deficiencies deficiency) {
		Session mailSession = MailUtila.getGMailSession(
				"maengel.dhbw.karlsruhe@googlemail.com",
				"Gng6;g7h5KJE\"[)m3Yqk");

		String message = "Guten Tag,\r\n\r\nes wurde ein neuer Mangel von \""
				+ deficiency.getReportingUser()
				+ "\" gemeldet.\r\n\r\nTicket Nummer: " + deficiency.getId()
				+ "\r\nRaumnummer: " + deficiency.getRoom().getRoomnumber()
				+ "\r\nKategorie: " + deficiency.getCategory()
				+ "\r\nBeschreibung: " + deficiency.getDescription()
				+ "\r\n\r\nViele Grüße\r\nIhr DHBW-App Team";

		String subject = "Neues Ticket mit der Nummer " + deficiency.getId();

		try {
			MailUtila.postMail(mailSession, "johzi@web.de", subject, message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}
}
