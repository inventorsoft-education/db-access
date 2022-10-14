package co.inventorsoft.academy.springBootTask.domain.mapper;

import co.inventorsoft.academy.springBootTask.domain.dto.TeamDto;
import co.inventorsoft.academy.springBootTask.domain.entity.Team;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TeamMapper {

    TeamMapper INSTANCE = Mappers.getMapper(TeamMapper.class);

    List<TeamDto> mapListOfTeamToListOfDto(List<Team> teams);

    TeamDto mapModelToDto(Team team);

    Team mapDtoToModel(TeamDto teamDto);

}
