package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Team;
import mk.ukim.finki.wp.lab.repository.TeamRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryTeamRepository implements TeamRepository
{

    @Override
    public List<Team> findAll()
    {
        return DataHolder.teams;
    }

    @Override
    public Optional<Team> findById(Long id)
    {
        return DataHolder.teams.stream().filter( t -> t.getId().equals(id) ).findFirst();
    }

    @Override
    public Team save(Team team)
    {
        DataHolder.teams.removeIf( t -> t.getId().equals(team.getId()));

        DataHolder.teams.add(team);

        return team;
    }

    @Override
    public void delete(Long id)
    {
        DataHolder.teams.removeIf( t -> t.getId().equals(id));
    }


}
