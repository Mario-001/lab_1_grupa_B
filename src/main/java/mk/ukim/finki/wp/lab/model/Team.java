package mk.ukim.finki.wp.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Team
{
    Long id;
    String name;
    String city;
    List<Player> players;
}
