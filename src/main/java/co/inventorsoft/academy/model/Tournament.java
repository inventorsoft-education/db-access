package co.inventorsoft.academy.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.FetchType;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString(of = "id")
@Table(name = "tournament")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@NoArgsConstructor
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    Integer id;

    @NonNull
    @Column(nullable = false)
    String name;

    @NonNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    Match match;

    @NonNull
    @Column(nullable = false)
    LocalDate date;
}
