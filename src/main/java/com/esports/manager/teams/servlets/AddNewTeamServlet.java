package com.esports.manager.teams.servlets;

import com.esports.manager.teams.TeamManagement;
import com.esports.manager.teams.db.TeamRepository;
import com.esports.manager.teams.entities.Team;
import com.esports.manager.userManagement.UserManagement;
import com.esports.manager.userManagement.entities.User;
import com.esports.manager.userManagement.exceptions.InvalidInputException;
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

/**
 * servlet for adding new teams to the system
 * @author Maximilian Rublik
 */
@WebServlet("/teams/addnewteam")
@MultipartConfig(maxFileSize = 1024*1024*10) // 10mb at most
public class AddNewTeamServlet extends HttpServlet {
    private final Logger log = LogManager.getLogger(AddNewTeamServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User loggedinUser = UserManagement.getAuthorizedUser(req.getSession());
        RequestDispatcher rq = req.getRequestDispatcher("/jsp/addnewteam.jsp");
        rq.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User loggedinUser = UserManagement.getAuthorizedUser(req.getSession());
        req.setCharacterEncoding("UTF-8");

        String teamname = req.getParameter("name");
        String slogan = req.getParameter("slogan");
        String tags = req.getParameter("tags");

        Part profilePart = req.getPart("profile");
        Part backgroundPart = req.getPart("background");
        InputStream profileIS = profilePart.getInputStream();
        InputStream backgroundIS = backgroundPart.getInputStream();

        Team newTeam;

        try {
            newTeam = TeamManagement.createTeam(teamname, slogan, tags);
        } catch (InvalidInputException e) {
            // TODO: handle invalid input (Maxi)
            throw new RuntimeException(e);
        }


        TeamRepository.setProfileImage(bufferImage(profileIS), newTeam);
        TeamRepository.setBackgroundImage(bufferImage(backgroundIS), newTeam);

        resp.sendRedirect(getServletContext().getContextPath() + "/teams");
    }

    private byte[] bufferImage (InputStream inputStream) throws IOException {
        // vvv Start Copy https://stackoverflow.com/a/1264737 vvv
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[1024];
        while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        // ^^^ End Copy ^^^
        return buffer.toByteArray();
    }
}
