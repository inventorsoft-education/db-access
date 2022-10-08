package co.inventorsoft.academy.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
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
@ToString(exclude = {"id"})
@Table(name = "teams")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    Integer id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String pilot1;

    @Column(nullable = false)
    String pilot2;

    public Team(@NonNull String name,
                @NonNull String pilot1,
                @NonNull String pilot2) {
        this.name = name;
        this.pilot1 = pilot1;
        this.pilot2 = pilot2;
    }
}

