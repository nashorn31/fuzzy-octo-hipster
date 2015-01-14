package servlets;

import hibernateentitysets.Rooms;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import roomSearch.RoomSearch;
import xml.CreateXML;

/**
 * Servlet implementation class GetRoomInformations
 */
@WebServlet("/GetRoomInformations")
public class GetRoomInformations extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public GetRoomInformations() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Rooms room = RoomSearch.getRoomByName(request.getParameter("RoomID"));

		TransformerFactory transformerFactory = TransformerFactory
				.newInstance();
		try {
			Transformer transformer = transformerFactory.newTransformer();

			Element rootElement = CreateXML.createXMLDocument();

			Document doc = rootElement.getOwnerDocument();

			DOMSource source = new DOMSource(room.toXML(doc, rootElement));
			StreamResult result = new StreamResult(response.getWriter());

			transformer.transform(source, result);
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		response.getWriter();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getParameter("roomID");

		response.setStatus(403);
	}

}
