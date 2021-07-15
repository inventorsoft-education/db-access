package co.inventrosoft.springboottask;

import co.inventrosoft.springboottask.model.Match;
import co.inventrosoft.springboottask.model.Team;
import co.inventrosoft.springboottask.repository.MatchRepositoryJdbcImpl;
import co.inventrosoft.springboottask.repository.TeamRepositoryJdbcImpl;
import co.inventrosoft.springboottask.repository.TournamentRepositoryJdbcImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class SpringBootTask1ApplicationTests {

    @Autowired
    TeamRepositoryJdbcImpl teamRepository;

    @Autowired
    TournamentRepositoryJdbcImpl tournamentRepository;

    @Autowired
    MatchRepositoryJdbcImpl matchRepository;

    @Test
    void contextLoads() {

        //matchRepository.update(a);



    }

}
