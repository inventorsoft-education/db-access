package co.inventorsoft.academy.model;


import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@RequiredArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class Team {
    @NonNull
    @ToString.Include(name = "Name:")
    private String name;
    @NonNull
    @ToString.Include(name = "Pilot1:")
    private String pilot1;
    @NonNull
    @ToString.Include(name = "Pilot2:")
    private String pilot2;
    private Integer points = (int) (Math.random() * 10);
}

