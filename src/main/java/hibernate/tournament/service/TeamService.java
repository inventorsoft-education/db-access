package hibernate.tournament.service;

import hibernate.tournament.model.Team;
import hibernate.tournament.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class TeamService extends GeneralService<Team, Integer>{
    private TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository){
        super(teamRepository);
        this.teamRepository =  teamRepository;
    }

    @Transactional(readOnly = true)
    public List<Team> getAll() {
        return teamRepository.findAll();
    }

    @Transactional
    public Team createTestAuthor() {
        Team team = new Team();

        team.setName("Barcelona");
        team.setCaptain("Messi");
        team.setCoach("Xavi");

        return teamRepository.save(team);
    }
}
