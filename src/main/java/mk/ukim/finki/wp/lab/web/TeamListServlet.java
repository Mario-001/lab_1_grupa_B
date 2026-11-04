package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.Service.TeamService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet( name = "TeamListServlet", urlPatterns = "/teams")
public class TeamListServlet extends HttpServlet
{
    private final TeamService teamService;
    private final SpringTemplateEngine springTemplateEngine;

    public TeamListServlet(TeamService teamService, SpringTemplateEngine springTemplateEngine)
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

        context.setVariable("teams", teamService.listTeams());

        springTemplateEngine.process("listTeams", context, resp.getWriter());
    }
}
