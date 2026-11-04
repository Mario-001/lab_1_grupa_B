package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Player;
import mk.ukim.finki.wp.lab.model.Team;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder
{
    public static List<Team> teams = null;
    public static List<Player> players = null;

    @PostConstruct
    void init()
    {
        teams = new ArrayList<>();

        teams.add(new Team(1L,"Los Angeles Lakers",
                "Los Angeles, California",new ArrayList<>()));
        teams.add(new Team(2L,"Golden State Warriors",
                "San Francisco, California",new ArrayList<>()));
        teams.add(new Team(3L,"Chicago Bulls",
                "Chicago, Illinois",new ArrayList<>()));
        teams.add(new Team(4L,"Boston Celtics",
                "Boston, Massachusetts",new ArrayList<>()));
        teams.add(new Team(5L,"Miami Heat",
                "Miami, Florida",new ArrayList<>()));

        players = new ArrayList<>();

        players.add(new Player("1","LeBron",
                "James","Small Forward",23));
        players.add(new Player("2","Stephen",
                "Curry","Point Guard",30));
        players.add(new Player("3","Luka",
                "Dončić","Point Guard",0));
        players.add(new Player("4","Jayson",
                "Tatum","Small Forward",0));
        players.add(new Player("5","Nikola",
                "Jokić","Center",15));
        players.add(new Player("6","Giannis",
                "Antetokounmpo","Power Forward",34));
        players.add(new Player("7","Kevin",
                "Durant","Power Forward ",7));
        players.add(new Player("8","Anthony",
                "Edwards","Shooting Guard",5));
        players.add(new Player("9","Devin",
                "Booker","Shooting Guard",1));
        players.add(new Player("10","Joel",
                "Embiid","Center",21));
    }
}
