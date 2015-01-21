package servlets;

import hibernateentitysets.MeasurePoint;
import hibernateentitysets.Rooms;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import xml.CreateXML;
import databaseconnection.InitEntityManager;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TestServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<MeasurePoint> measurePoints = InitEntityManager.getEntityManager()
				.createQuery("FROM MeasurePoint", MeasurePoint.class)
				.getResultList();
		
		
		Element rootElement = CreateXML.createXMLDocument();
		Document doc = rootElement.getOwnerDocument();

		//
		Element measurePointsRoot = doc.createElement("measurePoints");
		rootElement.appendChild(measurePointsRoot);

		// call the xml parser method 
		for (MeasurePoint room : measurePoints)
			room.toXML(doc, measurePointsRoot);

		//Get writer and document source
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(response.getWriter());

		//Transform the document and write it to the servlet output
		try {
			TransformerFactory.newInstance().newTransformer()
					.transform(source, result);
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
