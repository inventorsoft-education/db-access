package co.inventorsoft.academy.springBootTask.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "team")
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String capitan;

    private String coach;

}
