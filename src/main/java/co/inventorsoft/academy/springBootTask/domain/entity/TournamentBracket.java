package co.inventorsoft.academy.springBootTask.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tournament")
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TournamentBracket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String matches;

    private String winner;

}
