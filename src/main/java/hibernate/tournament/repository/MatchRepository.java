package hibernate.tournament.repository;

import hibernate.tournament.model.MatchDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<MatchDto, Integer> {
    MatchDto getMatchById(Integer id);
}
