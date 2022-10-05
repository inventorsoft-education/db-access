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
import javax.persistence.Table;

@Entity
@Getter
@Setter
@ToString(exclude = {"id"})
@Table(name = "teams")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@NoArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    Integer id;

    @NonNull
    @Column(nullable = false)
    String name;

    @NonNull
    @Column(nullable = false)
    String pilot1;

    @NonNull
    @Column(nullable = false)
    String pilot2;
}

