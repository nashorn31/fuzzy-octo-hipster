package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import room.AddDeficienciesToRoom;

/**
 * Servlet implementation class PostDefect
 */
@WebServlet("/PostDeficiencies")
public class DefectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DefectServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String category = request.getParameter("category");
		String description = request.getParameter("description");
		String reportingUser = request.getParameter("reportingUser");
		String roomNumber = request.getParameter("roomNumber");

		if (AddDeficienciesToRoom.addDeficiency(category, description,
				reportingUser, roomNumber)) {
			response.setStatus(200);
		}else{
			response.setStatus(801);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String category = request.getParameter("category");
		String description = request.getParameter("description");
		String reportingUser = request.getParameter("reportingUser");
		String roomNumber = request.getParameter("roomNumber");

		AddDeficienciesToRoom.addDeficiency(category, description,
				reportingUser, roomNumber);

	}

}
