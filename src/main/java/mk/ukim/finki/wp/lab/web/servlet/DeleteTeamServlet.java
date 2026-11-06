package mk.ukim.finki.wp.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Team;
import mk.ukim.finki.wp.lab.service.TeamService;

import java.io.IOException;

@WebServlet( name = "DeleteTeamServlet", urlPatterns = "/teams/delete")
public class DeleteTeamServlet extends HttpServlet
{

    public final TeamService teamService;

    public DeleteTeamServlet(TeamService teamService)
    {
        this.teamService = teamService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String teamid = req.getParameter("teamId");

        teamService.delete(Long.parseLong(teamid));

        resp.sendRedirect("/teams");

    }
}
