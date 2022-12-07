package pl.polsl.nuhyigitakman.servlets;

import pl.polsl.nuhyigitakman.history.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * History class of the servlet that process the choice of user and add to history.
 *
 * @author Nuh Yigit Akman
 *
 * @version 1.2
 */
@WebServlet("/History")
public class HistoryServlet extends HttpServlet {
    /**
     * history list of performed actions
     */
    private final List<History> histories = new ArrayList<>();

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()){

            HttpSession session=request.getSession(false);
            String userChoice = (String)session.getAttribute("userChoice");
            String keyOne = (String)session.getAttribute("keyOne");
            String keyTwo = (String)session.getAttribute("keyTwo");
            String userInput = (String)session.getAttribute("userInput");
            String output = (String)session.getAttribute("output");

            Date date = new Date();

            History history = new History(userChoice, keyOne,keyTwo,userInput,output,date);
            histories.add(history);
            out.println("Encryption/Decryption: "+session.getAttribute("userChoice"));
            out.println("Key One: "+session.getAttribute("keyOne"));
            out.println("Key Two: "+session.getAttribute("keyTwo"));
            out.println("Input: "+session.getAttribute("userInput"));
            out.println("Output: "+session.getAttribute("output"));
        }
    }
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()){
            if(histories.size()>0){
                Cookie[] cookies = request.getCookies();
                String lastVisit = "never";
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equals("lastVisit")) {
                            lastVisit = cookie.getValue();
                            break;
                        }
                    }
                }
                Cookie cookie = new Cookie("lastVisit", new java.util.Date().toString());
                response.addCookie(cookie);
                for (History history : histories) {
                    out.println("Encryption/Decryption: " + history.getUserChoice());
                    out.println("Key One: " + history.getKeyOne());
                    out.println("Key Two: " + history.getKeyTwo());
                    out.println("Input: " + history.getUserText());
                    out.println("Output: " + history.getOutput());
                    out.println("Operation time: " + history.getDate());
                    out.println("----------------------------------");
                }
                out.println("Your last visit to history page was: " + lastVisit);
            }
            else {
                response.sendError(response.SC_NOT_FOUND, "There is no performed action yet!");
            }

        }
    }

}
