package mk.ukim.finki.wp.lab.Service;

import mk.ukim.finki.wp.lab.model.Player;

import java.util.List;

public interface PlayerService
{
    List<Player> listPlayers();
    Player findByPlayerId(String playerId);
}
