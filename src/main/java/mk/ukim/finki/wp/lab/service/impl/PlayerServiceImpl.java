package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Team;
import mk.ukim.finki.wp.lab.repository.TeamRepository;
import mk.ukim.finki.wp.lab.service.PlayerService;
import mk.ukim.finki.wp.lab.model.Player;
import mk.ukim.finki.wp.lab.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService
{

    public final PlayerRepository playerRepository;
    public final TeamRepository teamRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository, TeamRepository teamRepository)
    {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public List<Player> listPlayers()
    {
        return playerRepository.findAll();
    }

    @Override
    public Player findByPlayerId(String playerId)
    {
        return playerRepository.findByPlayerId(playerId).orElseThrow(() ->
                new IllegalArgumentException("Player not found"));

    }

    //baranje od labs - da se prikazat samo slobodnite igraci sto ne igraat
    //za bilo koj drug tim
    @Override
    public List<Player> listFreePlayers()
    {
        List<Team> teams = teamRepository.findAll();

        List<Player> players = playerRepository.findAll();

        List<Player> availablePlayers = players.stream().filter( player ->
                teams.stream().noneMatch( team -> team.getPlayers().stream().anyMatch( playerInTeam ->
                        playerInTeam.getPlayerId().equals(player.getPlayerId())) )).toList();

        return availablePlayers;
    }
}
