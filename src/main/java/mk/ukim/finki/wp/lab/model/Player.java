package mk.ukim.finki.wp.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Player
{
    String playerId;
    String firstName;
    String lastName;
    String position;
    int jerseyNumber;
}
