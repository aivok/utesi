package ee.ut.esi.buildit.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.ServiceMode;

import ee.ut.esi.buildit.service.BossAcceptanceService;

public class WorksEngineerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BossAcceptanceService service;

	@Override
	public void init(ServletConfig conf) {
		service = BossAcceptanceService.getInstance();
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		if (id != null) {
			service.setResponse(Integer.parseInt(id), Boolean.parseBoolean(request.getParameter("accept")));
		}
		request.setAttribute("requests", service.getRequests());
		request.getRequestDispatcher("/WEB-INF/jsp/works-engineer-request-list.jsp").include(request, response);
	}
}
