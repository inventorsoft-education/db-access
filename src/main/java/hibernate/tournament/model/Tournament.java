package hibernate.tournament.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;


@Getter
@Setter
@Entity
@Table(name = "tournament_winners")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @Column(name = "team_name")
    String tournamentWinner;

    public Tournament(String tournamentWinner){
        this.tournamentWinner = tournamentWinner;
    }

}
