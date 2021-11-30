package hibernate.tournament.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "results")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class MatchDto{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    Integer id;

    @Column(nullable = false)
    String round;

    @Column(nullable = false)
    String teamOne;

    @Column(nullable = false)
    String teamTwo;

    @Column(nullable = false)
    String score;

    public MatchDto(String round, String teamOne, String teamTwo, String score){
        this.round = round;
        this.teamOne = teamOne;
        this.teamTwo = teamTwo;
        this.score = score;
    }
}
