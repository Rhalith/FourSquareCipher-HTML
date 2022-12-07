package pl.polsl.nuhyigitakman.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Locale;

import pl.polsl.nuhyigitakman.model.*;

/**
 *
 * Main class of the servlet that demonstrate choice of user.
 *
 * @author Nuh Yigit Akman
 *
 * @version 1.2
 */
@WebServlet("/Choice")
public class ChoiceServlet extends HttpServlet {

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
        Matrices matrices = new Matrices();
        Coder coder = new Coder();
        Cookie[] cookies = request.getCookies();
        matrices.plotAlphabets(matrices.getPlot(), matrices.getRemove());
        String userChoice = request.getParameter("userchoice");
        String encryptionChoice = request.getParameter("encryption");
        String userText = request.getParameter("userText").toUpperCase(Locale.forLanguageTag("en"));
        if(userChoice.equals("manual")){
            if(!userText.matches("[a-zA-Z]+(\\s+[a-zA-Z]+)*") || !request.getParameter("keyOne").matches("[a-zA-Z]+(\\s+[a-zA-Z]+)*") || !request.getParameter("keyTwo").matches("[a-zA-Z]+(\\s+[a-zA-Z]+)*")){
                Cookie cookie = new Cookie("error", "Not english letter");
                response.addCookie(cookie);
                response.sendError(response.SC_BAD_REQUEST, "Please enter only english letters!");

            } else if (userText.length() < 2) {
                Cookie cookie = new Cookie("error", "Not enough letter for input");
                response.addCookie(cookie);
                response.sendError(response.SC_BAD_REQUEST,"Please enter more than 2 letters to input");
            }
            else{
                String keyInput = request.getParameter("keyOne").toUpperCase(Locale.forLanguageTag("en"));
                matrices.plotKey(matrices.getPlotTwo(), keyInput, matrices.getRemove());
                keyInput = request.getParameter("keyTwo").toUpperCase(Locale.forLanguageTag("en"));
                matrices.plotKey(matrices.getPlotThree(), keyInput, matrices.getRemove());
                processRequest(request, response, matrices, coder, cookies, encryptionChoice, userText);
            }
        }
        else{
            if(!userText.matches("[a-zA-Z]+(\\s+[a-zA-Z]+)*")){
                Cookie cookie = new Cookie("error", "Not english letter");
                response.addCookie(cookie);
                response.sendError(response.SC_BAD_REQUEST, "Please enter only english letters!");

            } else if (userText.length() < 2) {
                Cookie cookie = new Cookie("error", "Not enough letter for input");
                response.addCookie(cookie);
                response.sendError(response.SC_BAD_REQUEST,"Please enter more than 2 letters to input");
            }
            else{
                matrices.randomizeKey(matrices.getPlotTwo(), matrices.getRemove());
                matrices.randomizeKey(matrices.getPlotThree(), matrices.getRemove());
                processRequest(request, response, matrices, coder, cookies, encryptionChoice, userText);
            }
        }

    }

    /**
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @param matrices matrices class for encrypt/decrypt
     * @param coder coder class for encrypt/decrypt
     * @param cookies cookies for errors
     * @param encryptionChoice choice of encryption or decryption
     * @param userText user's input text
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response, Matrices matrices, Coder coder, Cookie[] cookies, String encryptionChoice, String userText) throws ServletException, IOException {
        String output;
        if(encryptionChoice.equals("encrypt")){
            output = coder.encodeText(userText, true, matrices.getPlot(), matrices.getPlotTwo(), matrices.getPlotThree());
        } else {
            output = coder.encodeText(userText, false, matrices.getPlot(), matrices.getPlotTwo(), matrices.getPlotThree());
        }
        request.setAttribute("userChoice", encryptionChoice);
        request.setAttribute("userInput", userText);
        request.setAttribute("output", output);
        request.setAttribute("keyOne", matrices.getMatrices("one"));
        request.setAttribute("keyTwo", matrices.getMatrices("two"));
        request.setAttribute("cookies", cookies);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Process");

        requestDispatcher.forward(request, response);
    }
}
