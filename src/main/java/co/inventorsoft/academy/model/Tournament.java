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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString(of = "id")
@Table(name = "tournament")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    Integer id;

    @Column(nullable = false)
    String name;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    Match match;

    @Column(nullable = false)
    LocalDate date;

    public Tournament(@NonNull String name,
                      @NonNull Match match,
                      @NonNull LocalDate date) {
        this.name = name;
        this.match = match;
        this.date = date;
    }
}
