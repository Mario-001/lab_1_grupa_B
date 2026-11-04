package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository
{
    List<Player> findAll();
    Optional<Player> findByPlayerId(String playerId);
}
