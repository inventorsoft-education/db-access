package main;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "teams")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    Integer id;

    @Column(nullable = false)
    String team_name;

    @Column(nullable = false)
    String horse;

    @Column(nullable = false)
    String rider;

    @Override
    public String toString() {
        return team_name + ", horse - " + horse + ", rider - " + rider;
    }
}
