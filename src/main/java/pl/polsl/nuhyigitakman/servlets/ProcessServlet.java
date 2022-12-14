package pl.polsl.nuhyigitakman.servlets;

import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 *
 * Process class of the servlet that process the choice of user and sent to history servlet.
 *
 * @author Nuh Yigit Akman
 *
 * @version 1.2
 */
@WebServlet("/Process")
public class ProcessServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/plain; charset=ISO-8859-2");

        HttpSession session = request.getSession(true);

        session.setAttribute("userChoice",request.getAttribute("userChoice"));
        session.setAttribute("userInput", request.getAttribute("userInput"));
        session.setAttribute("output", request.getAttribute("output"));
        session.setAttribute("keyOne", request.getAttribute("keyOne"));
        session.setAttribute("keyTwo", request.getAttribute("keyTwo"));
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/History");

        requestDispatcher.forward(request, response);

    }
}
