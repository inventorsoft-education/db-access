package co.inventorsoft.academy.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Tournament {
    private String name;
    private int match;
    private Date date;
}
