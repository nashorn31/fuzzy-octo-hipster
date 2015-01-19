package servlets;

import hibernateentitysets.Rooms;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

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

		String searchTerm = request.getParameter("RoomID");
		List<Rooms> rooms;

		if (Pattern.matches("[0-9]+", searchTerm)) {
			rooms = RoomSearch.getRoomByName(searchTerm);
		} else {
			rooms = RoomSearch.getRoomByName(
					searchTerm.substring(1, searchTerm.length() - 1),
					searchTerm.substring(0, 1));
		}

		TransformerFactory transformerFactory = TransformerFactory
				.newInstance();
		try {
			Transformer transformer = transformerFactory.newTransformer();

			Element rootElement = CreateXML.createXMLDocument();

			Document doc = rootElement.getOwnerDocument();

			// Equipment Elements
			Element roomsRoot = doc.createElement("rooms");
			rootElement.appendChild(roomsRoot);

			for (Rooms room : rooms)
				room.toXML(doc, roomsRoot);

			DOMSource source = new DOMSource(doc);
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

		response.setStatus(403);
	}

}
