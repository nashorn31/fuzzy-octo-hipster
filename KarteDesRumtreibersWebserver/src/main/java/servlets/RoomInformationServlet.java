package servlets;

import hibernateentitysets.Rooms;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import room.RoomSearch;
import xml.CreateXML;

/**
 * Servlet implementation class GetRoomInformations
 */
@WebServlet("/RoomInformations")
public class RoomInformationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public RoomInformationServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String roomName = request.getParameter("roomName");
		String roomType = request.getParameter("roomType");
		String wing = request.getParameter("wing");
		String floor = request.getParameter("floor");
		String roomNumber = request.getParameter("roomNumber");
		String personCapacity = request.getParameter("personCapacity");
		String beameRequired = request.getParameter("beameRequired");

		List<Rooms> rooms = RoomSearch.getRoomByName(roomName, roomType, wing,
				floor, roomNumber, personCapacity, beameRequired);

		try {

			// Create xml document and root element
			Element rootElement = CreateXML.createXMLDocument();
			Document doc = rootElement.getOwnerDocument();

			//
			Element roomsRoot = doc.createElement("rooms");
			rootElement.appendChild(roomsRoot);

			// call the xml parser method
			for (Rooms room : rooms)
				room.toXML(doc, roomsRoot);

			// Get writer and document source
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(response.getWriter());

			// Transform the document and write it to the servlet output
			TransformerFactory.newInstance().newTransformer()
					.transform(source, result);
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
