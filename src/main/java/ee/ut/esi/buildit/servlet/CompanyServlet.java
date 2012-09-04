package ee.ut.esi.buildit.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ee.ut.esi.buildit.model.PriceListItem;
import ee.ut.esi.buildit.service.PriceListService;

public class CompanyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(CompanyServlet.class);
	private static final ThreadLocal<DateFormat> df = new ThreadLocal<DateFormat>() {
		@Override
		protected DateFormat initialValue() {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			sdf.setLenient(false);
			return sdf;
		}
	};
	private PriceListService service;
	private String contextPath;

	@Override
	public void init(ServletConfig config) {
		contextPath = config.getServletContext().getContextPath();
		service = PriceListService.getInstance();
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<PriceListItem> list = service.getPriceList();
		request.setAttribute("equipments", list);
		String action = request.getParameter("action");
		if ("view".equals(action)) {
			PriceListItem item = findItem(list, getIntId(request.getParameter("id")));
			if (item != null) {
				request.setAttribute("item", item);
				request.getRequestDispatcher("/WEB-INF/jsp/company-equipment-view.jsp").include(request, response);
				return;
			}
		} else if ("availability".equals(action)) {
			PriceListItem item = findItem(list, getIntId(request.getParameter("id")));
			if (item != null) {
				request.setAttribute("item", item);
				try {
					Date startDate = df.get().parse(request.getParameter("startDate"));
					Date endDate = df.get().parse(request.getParameter("endDate"));
					request.setAttribute("startDate", request.getParameter("startDate"));
					request.setAttribute("endDate", request.getParameter("endDate"));
					request.setAttribute("isAvailable", service.isAvailable(item, startDate, endDate));
				} catch (ParseException e) {
					log.info("Invalid start/end date format");
				}
				request.getRequestDispatcher("/WEB-INF/jsp/company-equipment-view.jsp").include(request, response);
				return;
			}
		} else if ("order".equals(action)) {
			PriceListItem item = findItem(list, getIntId(request.getParameter("id")));
			if (item != null) {
				try {
					Date startDate = df.get().parse(request.getParameter("startDate"));
					Date endDate = df.get().parse(request.getParameter("endDate"));
					service.order(item, startDate, endDate);
					log.info("Order compleate");
					response.sendRedirect(contextPath + "/");
					return;
				} catch (ParseException e) {
					log.info("Invalid start/end date format");
				}
			}
		} else if ("requests".equals(action)) {
			request.setAttribute("rentRequests", service.getRentRequests());
			request.getRequestDispatcher("/WEB-INF/jsp/company-rent-requests.jsp").include(request, response);
			return;
		} else if ("cancelRentRequest".equals(action)) {
			service.cancelEquipmentRentRequest(getIntId(request.getParameter("id")));
			request.setAttribute("rentRequests", service.getRentRequests());
			request.getRequestDispatcher("/WEB-INF/jsp/company-rent-requests.jsp").include(request, response);
			return;
		}

		request.getRequestDispatcher("/WEB-INF/jsp/company-equipment-list.jsp").include(request, response);
	}

	private int getIntId(String id) {
		try {
			return Integer.parseInt(id);
		} catch (NumberFormatException e) {
			return -1;
		}
	}

	private PriceListItem findItem(List<PriceListItem> list, int id) {
		for (PriceListItem item : list) {
			if (item.getId() == id) {
				return item;
			}
		}
		return null;
	}
}
