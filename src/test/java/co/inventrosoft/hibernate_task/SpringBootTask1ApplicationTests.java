package co.inventrosoft.hibernate_task;

import co.inventrosoft.hibernate_task.model.Match;
import co.inventrosoft.hibernate_task.model.Team;
import co.inventrosoft.hibernate_task.model.Tournament;
import co.inventrosoft.hibernate_task.repository.MatchRepositoryJdbcImpl;
import co.inventrosoft.hibernate_task.repository.hibernate.MatchRepository;
import co.inventrosoft.hibernate_task.repository.hibernate.TeamRepository;
import co.inventrosoft.hibernate_task.repository.TeamRepositoryJdbcImpl;
import co.inventrosoft.hibernate_task.repository.TournamentRepositoryJdbcImpl;
import co.inventrosoft.hibernate_task.repository.hibernate.TournamentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
class SpringBootTask1ApplicationTests {

    @Autowired
    TeamRepositoryJdbcImpl teamRepository;

    @Autowired
    TournamentRepositoryJdbcImpl tournamentRepository;

    @Autowired
    TeamRepository teamRepository1;

    @Autowired
    TournamentRepository tournamentRepository1;

    @Autowired
    MatchRepository matchRepository;

    @Test
    void contextLoads() {
       ArrayList<Team> a = new ArrayList<>();



        Team t1 = new Team("test2212", "das", "daa");
        Team t2 = new Team("test1212", "das", "daa");
        a.add(t1);
        a.add(t2);
        System.out.println(t1);
        System.out.println(t2);
        //teamRepository1.saveAll(a);

        Match m = new Match();
        Tournament t6 = new Tournament();
        tournamentRepository1.save(t6);
        System.out.println(t6);

        Team t3 = teamRepository1.getById(1);
        Team t4 = teamRepository1.getById(2);
        m.setFirstTeam(t3);
        m.setSecondTeam(t4);
        m.setTournament(t6);
        matchRepository.save(m);





    }

}
