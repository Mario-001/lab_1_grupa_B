package mk.ukim.finki.wp.lab.Service.impl;

import mk.ukim.finki.wp.lab.Service.PlayerService;
import mk.ukim.finki.wp.lab.model.Player;
import mk.ukim.finki.wp.lab.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService
{

    public final PlayerRepository playerRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository)
    {
        this.playerRepository = playerRepository;
    }

    @Override
    public List<Player> listPlayers()
    {
        return playerRepository.findAll();
    }

    @Override
    public Player findByPlayerId(String playerId)
    {
        return playerRepository.findByPlayerId(playerId).orElseThrow(() -> new IllegalArgumentException());

    }
}
