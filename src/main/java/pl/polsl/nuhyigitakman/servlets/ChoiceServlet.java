package pl.polsl.nuhyigitakman.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Locale;

import pl.polsl.nuhyigitakman.model.*;

@WebServlet("/Choice")
public class ChoiceServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Matrices matrices = new Matrices();
        Coder coder = new Coder();
        matrices.plotAlphabets(matrices.getPlot(), matrices.getRemove());
        String userChoice = request.getParameter("userchoice");
        if(userChoice.equals("manual")){
            String keyInput = request.getParameter("keyOne").toUpperCase(Locale.forLanguageTag("en"));
            matrices.plotKey(matrices.getPlotTwo(), keyInput, matrices.getRemove());
            keyInput = request.getParameter("keyTwo").toUpperCase(Locale.forLanguageTag("en"));
            matrices.plotKey(matrices.getPlotThree(), keyInput, matrices.getRemove());
        }
        else{
            matrices.randomizeKey(matrices.getPlotTwo(), matrices.getRemove());
            matrices.randomizeKey(matrices.getPlotThree(), matrices.getRemove());
        }
        String encryptionChoice = request.getParameter("encryption");
        String userText = request.getParameter("userText").toUpperCase(Locale.forLanguageTag("en"));
        String output;
        if(encryptionChoice.equals("encrypt")){
            output = coder.encodeText(userText, true, matrices.getPlot(), matrices.getPlotTwo(), matrices.getPlotThree());
        } else {
            output = coder.encodeText(userText, false, matrices.getPlot(), matrices.getPlotTwo(), matrices.getPlotThree());
        }
        request.setAttribute("output", output);
        request.setAttribute("plotOne", matrices.getPlot());
        request.setAttribute("plotTwo", matrices.getPlotTwo());
        request.setAttribute("plotThree", matrices.getPlotThree());

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/First");

        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Matrices matrices = new Matrices();
        Coder coder = new Coder();
        matrices.plotAlphabets(matrices.getPlot(), matrices.getRemove());
        String userChoice = request.getParameter("userchoice");
        if(userChoice.equals("manual")){
            String keyInput = request.getParameter("keyOne").toUpperCase(Locale.forLanguageTag("en"));
            matrices.plotKey(matrices.getPlotTwo(), keyInput, matrices.getRemove());
            keyInput = request.getParameter("keyTwo").toUpperCase(Locale.forLanguageTag("en"));
            matrices.plotKey(matrices.getPlotThree(), keyInput, matrices.getRemove());
        }
        else{
            matrices.randomizeKey(matrices.getPlotTwo(), matrices.getRemove());
            matrices.randomizeKey(matrices.getPlotThree(), matrices.getRemove());
        }
        String encryptionChoice = request.getParameter("encryption");
        String userText = request.getParameter("userText").toUpperCase(Locale.forLanguageTag("en"));
        String output;
        if(encryptionChoice.equals("encrypt")){
            output = coder.encodeText(userText, true, matrices.getPlot(), matrices.getPlotTwo(), matrices.getPlotThree());
        } else {
            output = coder.encodeText(userText, false, matrices.getPlot(), matrices.getPlotTwo(), matrices.getPlotThree());
        }
        request.setAttribute("output", output);
        request.setAttribute("plotOne", matrices.getPlot());
        request.setAttribute("plotTwo", matrices.getPlotTwo());
        request.setAttribute("plotThree", matrices.getPlotThree());

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/First");

        requestDispatcher.forward(request, response);
    }
}
