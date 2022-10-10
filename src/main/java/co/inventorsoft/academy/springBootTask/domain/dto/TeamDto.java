package co.inventorsoft.academy.springBootTask.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeamDto {

    private String name;

    private String capitan;

    private String coach;

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", capitan='" + capitan + '\'' +
                ", coach='" + coach + '\'' +
                '}';
    }

}
