package mk.ukim.finki.wp.lab.Service;

import mk.ukim.finki.wp.lab.model.Player;
import mk.ukim.finki.wp.lab.model.Team;

import java.util.List;

public interface TeamService
{
    List<Team> listTeams();
    Team findById(Long id);
    Team addPlayerToTeam(Long teamId, String playerId);
}
