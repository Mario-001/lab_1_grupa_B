package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Team;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository
{
    List<Team> findAll();
    Optional<Team> findById(Long id);
    Team save(Team team);
}
