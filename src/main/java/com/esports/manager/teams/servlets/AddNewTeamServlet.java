package com.esports.manager.teams.servlets;

import com.esports.manager.teams.db.TeamRepository;
import com.esports.manager.teams.entities.Team;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@WebServlet("/teams/addnewteam")
@MultipartConfig(maxFileSize = 1024*1024*10) // 10mb at most
public class AddNewTeamServlet extends HttpServlet {
    private final Logger log = LogManager.getLogger(AddNewTeamServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rq = req.getRequestDispatcher("/jsp/addnewteam.jsp");
        rq.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        //TODO save images to database
        String teamname = req.getParameter("name");
        String slogan = req.getParameter("slogan");
        String tags = req.getParameter("tags");

        Part imagePart = req.getPart("profile");
        InputStream is = imagePart.getInputStream();
        //String background = req.getParameter("background");

        // vvv Start Copy https://stackoverflow.com/a/1264737 vvv
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[1024];
        while ((nRead = is.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        // ^^^ End Copy ^^^
        byte[] image = buffer.toByteArray();

        Team newTeam = new Team(teamname, slogan, tags);

        TeamRepository.createTeam(newTeam);
        TeamRepository.setProfileImage(image, newTeam);
        resp.sendRedirect(getServletContext().getContextPath() + "/teams");
    }
}
