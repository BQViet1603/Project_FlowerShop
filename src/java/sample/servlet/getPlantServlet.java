package sample.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import sample.dao.PlantDAO;
import sample.dto.Plant;

@WebServlet(name = "getPlantServlet", urlPatterns = {"/getPlantServlet"})
public class getPlantServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");

        try {
            int pid = Integer.parseInt(request.getParameter("txtpid"));
            Plant p = PlantDAO.getPlant(pid);

            if (p != null) {
                request.setAttribute("plantObj", p);
                request.getRequestDispatcher("viewPlant.jsp").forward(request, response);
            } else {
                response.getWriter().println("Plant not found.");
            }
        } catch (NumberFormatException e) {
            response.getWriter().println("Invalid plant ID format.");
        } catch (ServletException | IOException e) {
            e.printStackTrace(response.getWriter());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(getPlantServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(getPlantServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet to fetch plant details by ID";
    }
}
