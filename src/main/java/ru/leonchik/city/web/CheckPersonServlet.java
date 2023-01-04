package ru.leonchik.city.web;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.leonchik.city.dao.PersonCheckDao;
import ru.leonchik.city.domain.PersonRequest;
import ru.leonchik.city.domain.PersonResponse;


/*import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;*/
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "CheckPersonServlet", urlPatterns = {"/chp"})
public class CheckPersonServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
//        resp.getWriter().println("GET CheckPerson - called");
        req.setCharacterEncoding("UTF-8");

        String surname = req.getParameter("surname");

        PersonRequest pr = new PersonRequest();

        pr.setSurName(surname);
        pr.setGivenName("Павел");
        pr.setPatronymic("Николаевич");
        pr.setDateOfBirth(LocalDate.of(1995, 3, 18));
        pr.setStreetCode(1);
        pr.setBuilding("10");
        pr.setExtension("2");
        pr.setApartment("121");

        try {
            PersonCheckDao dao = new PersonCheckDao();
            PersonResponse ps = dao.checkPerson(pr);

            if (ps.isRegistered()) {
                resp.getWriter().println("Registered");
            } else {
                resp.getWriter().println("Not registered");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
