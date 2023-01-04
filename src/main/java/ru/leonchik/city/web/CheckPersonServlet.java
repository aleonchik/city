package ru.leonchik.city.web;

// import java.io.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.leonchik.city.dao.PersonCheckDao;
import ru.leonchik.city.dao.PoolConnectionBuilder;
import ru.leonchik.city.domain.PersonRequest;
import ru.leonchik.city.domain.PersonResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/*import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;*/

/**
 * 1. Init
 * 2. Service
 * 3. Destroy
 */

//@WebServlet(name = "CheckPersonServlet", urlPatterns = {"/chp"}, loadOnStartup = 1)
@WebServlet(name = "CheckPersonServlet", urlPatterns = {"/chp"})
public class CheckPersonServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(CheckPersonServlet.class);
    private PersonCheckDao dao;

    @Override
    public void init() throws ServletException {
//        super.init();
        logger.info("Servlet is created");
        dao = new PersonCheckDao();
        dao.setConnectionBuilder(new PoolConnectionBuilder());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
//        resp.getWriter().println("GET CheckPerson - called");
        req.setCharacterEncoding("UTF-8");

        String surname = req.getParameter("surname");

        PersonRequest pr = new PersonRequest();

        pr.setSurName(req.getParameter("surname"));
        pr.setGivenName(req.getParameter("givenname"));
        pr.setPatronymic(req.getParameter("patronymic"));
        LocalDate dateOfBirth = LocalDate.parse(req.getParameter("dateOfBirth"), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
//        pr.setDateOfBirth(LocalDate.of(1995, 3, 18));
        pr.setDateOfBirth(dateOfBirth);
        pr.setStreetCode(Integer.parseInt(req.getParameter("streetCode")));
        pr.setBuilding(req.getParameter("building"));
        pr.setExtension(req.getParameter("extension"));
        pr.setApartment(req.getParameter("apartment"));

        try {
//            PersonCheckDao dao = new PersonCheckDao();
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
