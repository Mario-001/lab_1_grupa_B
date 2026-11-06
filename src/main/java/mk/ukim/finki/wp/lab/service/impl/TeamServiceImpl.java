package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.service.TeamService;
import mk.ukim.finki.wp.lab.model.Player;
import mk.ukim.finki.wp.lab.model.Team;
import mk.ukim.finki.wp.lab.repository.PlayerRepository;
import mk.ukim.finki.wp.lab.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService
{
    public final PlayerRepository playerRepository;
    public final TeamRepository teamRepository;

    public TeamServiceImpl(PlayerRepository playerRepository, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public List<Team> listTeams()
    {
        return teamRepository.findAll();
    }

    @Override
    public Team findById(Long id)
    {
        return teamRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Team doesn't exist"));
    }

    @Override
    public Team addPlayerToTeam(Long teamId, String playerId)
    {
        Team team = findById(teamId);

        Player player = playerRepository.findByPlayerId(playerId)
                .orElseThrow(() -> new IllegalArgumentException("Player doesn't exist"));

        /**
         * searches for the player in the list of all the teams, if present throw exception.
         */
        Optional<Team> existInTeam = teamRepository
                .findAll()
                .stream()
                .filter( t -> t.getPlayers()
                        .stream()
                        .anyMatch( p -> p.getPlayerId().equals(playerId) ))
                .findFirst();

        /**
         * searches if any of the players in the selected team have the same jersy number
         * as the selected player
         */
        Optional<Player> jersyPlayer = team
                .getPlayers().
                stream()
                .filter( p -> p.getJerseyNumber() == player.getJerseyNumber())
                .findFirst();

        if( existInTeam.isPresent() )
        {
            throw new IllegalArgumentException("Player already plays for "
                    + existInTeam.get().getName());
        }

        if( jersyPlayer.isPresent() )
        {
            throw new IllegalArgumentException("Jersey number " +
                    jersyPlayer.get().getJerseyNumber() + " is already taken in this team");
        }

        team.getPlayers().add(player);

        return teamRepository.save(team);
    }

    @Override
    public void delete(Long id)
    {
        teamRepository.delete(id);
    }
}
