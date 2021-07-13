package co.inventrosoft.hibernate_task.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(of = "id")
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    int id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String capitan;

    @Column(nullable = false)
    String coach;

    public Team(String name, String capitan, String coach) {
        this.name = name;
        this.capitan = capitan;
        this.coach = coach;
    }

//    @Override
//    public String toString() {
//        return this.name + ". Capitan - " + this.capitan + ", coach - " + this.coach;
//    }
}
