package pl.polsl.nuhyigitakman.servlets;

import java.io.*;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Main class of the servlet displaying current time, date and image. Site
 * generated by servlet is refreshed every 10 seconds.
 *
 * @author Gall Anonim
 * @version 1.0
 */
@WebServlet("/First")
public class FirstServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html; charset=ISO-8859-2");
        PrintWriter out = response.getWriter();
        response.setHeader("Refresh", "1");
        String date = new Date().toString();

        out.println("<html>\n<body>\n" + date + "\n<h1>It will be done in final!</h1><br>\n"
                + "I was not have time to finish communicate between model and html");
    }
}