package co.inventorsoft.academy.springBootTask.domain.mapper;

import co.inventorsoft.academy.springBootTask.domain.dto.TournamentBracketDto;
import co.inventorsoft.academy.springBootTask.domain.entity.TournamentBracket;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TournamentBracketMapper {

    TournamentBracketMapper INSTANCE = Mappers.getMapper(TournamentBracketMapper.class);

    List<TournamentBracketDto> mapListOfTournamentBracketToListOfDto(List<TournamentBracket> tournamentBrackets);

    TournamentBracketDto mapModelToDto(TournamentBracket tournamentBracket);

    TournamentBracket mapDtoToModel(TournamentBracketDto tournamentBracketDto);

}
