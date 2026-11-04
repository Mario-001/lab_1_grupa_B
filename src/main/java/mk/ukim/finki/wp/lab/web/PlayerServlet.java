package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.Service.PlayerService;
import mk.ukim.finki.wp.lab.Service.TeamService;
import mk.ukim.finki.wp.lab.model.Team;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet( name = "PlayerServlet", urlPatterns = "/players")
public class PlayerServlet extends HttpServlet
{
    public final PlayerService playerService;
    private final TeamService teamService;
    private final SpringTemplateEngine springTemplateEngine;

    public PlayerServlet(PlayerService playerService, TeamService teamService, SpringTemplateEngine springTemplateEngine)
    {
        this.playerService = playerService;
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

        context.setVariable("errorMessage", req.getParameter("errorMessage"));

        try
        {
            String teamId = req.getParameter("teamId");
            Team selectedTeam = teamService.findById(Long.parseLong(teamId));

            context.setVariable("players", playerService.listPlayers());
            context.setVariable("selectedTeam", selectedTeam);

        }
        catch ( IllegalArgumentException I )
        {
            resp.sendRedirect("/teams");
        }

        springTemplateEngine.process("playersList.html",context,resp.getWriter());
    }
}
