package com.example.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import javax.persistence.*;


@Entity
@Getter
@Setter
@Table(name = "teams")
@Component
@NoArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer id;

    @Column(nullable = false)
    private String teamName;

    @Column(nullable = false)
    private String coach;

    @Column(nullable = false)
    private String captain;

    @Column(nullable = false)
    public Integer points = 0;

    private Integer goals = (int) (Math.random() * 10);

//    public Team() {
//    }

    public Team(String teamName, String coach, String captain) {
        this.teamName = teamName;
        this.coach = coach;
        this.captain = captain;
    }
}
