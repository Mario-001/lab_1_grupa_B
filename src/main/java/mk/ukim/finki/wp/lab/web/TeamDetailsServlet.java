package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.Service.PlayerService;
import mk.ukim.finki.wp.lab.Service.TeamService;
import mk.ukim.finki.wp.lab.model.Player;
import mk.ukim.finki.wp.lab.model.Team;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet( name = "TeamDetailsServlet ", urlPatterns = "/teamDetails")
public class TeamDetailsServlet extends HttpServlet
{
    private final TeamService teamService;
    private final SpringTemplateEngine springTemplateEngine;

    public TeamDetailsServlet(TeamService teamService, SpringTemplateEngine springTemplateEngine)
    {
        this.teamService = teamService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req,resp);

        WebContext context = new WebContext(webExchange);

        String selected_team_id = req.getParameter("teamId");
        Team team = teamService.findById(Long.parseLong(selected_team_id));

        context.setVariable("team", team);

        springTemplateEngine.process("teamDetails", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String selected_team_id = req.getParameter("teamId");
        String selected_player_id = req.getParameter("teamId");

        try
        {
            teamService.addPlayerToTeam(Long.parseLong(selected_team_id),selected_player_id);

            resp.sendRedirect("/teamDetails?teamId=" + selected_player_id);
        }
        catch ( IllegalArgumentException e )
        {
            resp.sendRedirect("/players?teamId=" + selected_team_id + "&errorMessage=ne cini");
        }

    }
}
