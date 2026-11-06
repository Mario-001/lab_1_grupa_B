package mk.ukim.finki.wp.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.service.TeamService;
import mk.ukim.finki.wp.lab.model.Team;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet( name = "TeamDetailsServlet", urlPatterns = "/teamDetails")
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

        if( selected_team_id == null || selected_team_id.isEmpty() )
        {
            resp.sendRedirect("/teams");
            return;
        }

        Team team = teamService.findById(Long.parseLong(selected_team_id));

        context.setVariable("team", team);

        springTemplateEngine.process("teamDetails.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String selected_team_id = req.getParameter("teamId");
        String selected_player_id = req.getParameter("playerId");

        try
        {
            teamService.addPlayerToTeam(Long.parseLong(selected_team_id),selected_player_id);

            resp.sendRedirect("/teamDetails?teamId=" + selected_team_id);
        }
        catch ( IllegalArgumentException e )
        {
            String ex = URLEncoder.encode(e.getMessage(), StandardCharsets.UTF_8);
            resp.sendRedirect("/players?teamId=" + selected_team_id + "&errorMessage=" + ex);
        }

    }
}
