package sample.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import sample.dao.PlantDAO;
import sample.dto.Plant;

@WebServlet(name = "searchServlet", urlPatterns = {"/searchServlet"})
public class searchServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String keyword = request.getParameter("txtsearch");
            String searchby = request.getParameter("txtsearchby");
            ArrayList<Plant> list = PlantDAO.getPlants(keyword, searchby);
            if (list != null && !list.isEmpty()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet searchServlet</title>");
                out.println("<link rel='stylesheet' href='mycss.css' type='text/css' />");
                out.println("</head>");
                out.println("<body><section>");
                ServletContext context = getServletContext();
                String tmp = context.getInitParameter("countryName");
                out.println("<p>The website is deploying in " + tmp + "</p>");
                ServletConfig servletconfig = getServletConfig();
                String tmp2 = servletconfig.getInitParameter("language");
                out.println("<p>Language is used only in this page:" + tmp2 + "</p>");
                out.println("<table class='producttable order'>");
                out.println("<tr><td>product id</td>"
                        + "<td>name</td>"
                        + "<td>price</td>"
                        + "<td>image</td>"
                        + "<td>detail</td>"
                        + "<td>action</td></tr>");
                for (Plant plant : list) {
                    out.println("<tr>");
                    out.println("<td>" + plant.getId() + "</td>");
                    out.println("<td>" + plant.getName() + "</td>");
                    out.println("<td>" + plant.getPrice() + "</td>");
                    out.println("<td><img class='plantimg' src='" + plant.getImgpath() + "' class='product'/></td>");
                    out.println("<td><a href='#'>view detail</a></td>");
                    out.println("<td class='shopping'><a href='#'>add to your cart</a></td>");
                    out.println("</tr>");
                }
                out.println("</table>");
                out.println("</section>");
                out.println("</body>");
                out.println("</html>");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
