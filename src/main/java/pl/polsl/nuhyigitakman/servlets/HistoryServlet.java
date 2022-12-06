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

@WebServlet("/History")
public class HistoryServlet extends HttpServlet {
    private final List<History> histories = new ArrayList<>();

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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()){
            if(histories.size()>0){
                for (History history : histories) {
                    out.println("Encryption/Decryption: " + history.getUserChoice());
                    out.println("Key One: " + history.getKeyOne());
                    out.println("Key Two: " + history.getKeyTwo());
                    out.println("Input: " + history.getUserText());
                    out.println("Output: " + history.getOutput());
                    out.println("Operation time: " + history.getDate());
                    out.println("----------------------------------");
                }
            }
            else {
                out.println("There is no performed action");
            }

        }
    }

}