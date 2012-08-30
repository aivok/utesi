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

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ee.ut.esi.buildit.model.Equipment;
import ee.ut.esi.buildit.service.EquipmentService;

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
	private EquipmentService service;
	private String contextPath;

	@Override
	public void init(ServletConfig config) {
		contextPath = config.getServletContext().getContextPath();
		service = EquipmentService.getInstance();
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Equipment> list = service.getEquipmentList();
		request.setAttribute("equipments", list);
		String action = request.getParameter("action");
		if ("view".equals(action)) {
			Equipment item = findItem(list, getEquipmentId(request.getParameter("id")));
			if (item != null) {
				request.setAttribute("item", item);
				request.getRequestDispatcher("/WEB-INF/jsp/company-equipment-view.jsp").include(request, response);
				return;
			}
		} else if ("availability".equals(action)) {
			Equipment item = findItem(list, getEquipmentId(request.getParameter("id")));
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
			Equipment item = findItem(list, getEquipmentId(request.getParameter("id")));
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
		}
		request.getRequestDispatcher("/WEB-INF/jsp/company-equipment-list.jsp").include(request, response);
	}

	private int getEquipmentId(String id) {
		if (!StringUtils.isEmpty(id) && StringUtils.isNumeric(id)) {
			return Integer.parseInt(id);
		}
		return -1;
	}

	private Equipment findItem(List<Equipment> list, int id) {
		for (Equipment item : list) {
			if (item.getId() == id) {
				return item;
			}
		}
		return null;
	}
}
