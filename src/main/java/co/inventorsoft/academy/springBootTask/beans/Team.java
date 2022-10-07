package co.inventorsoft.academy.springBootTask.beans;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Team {

    private String name;

    private String capitan;

    private String coach;

}
