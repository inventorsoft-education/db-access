import dao.MatchDao;
import dao.TeamDao;
import dao.TournamentDao;
import dao.impl.MatchDaoImpl;
import dao.impl.TeamDaoImpl;
import dao.impl.TournamentDaoImpl;
import entity.Match;
import entity.Team;
import entity.Tournament;
import jdbc.JdbcUtils;

import java.sql.Connection;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        JdbcUtils jdbcUtils=new JdbcUtils();

        TournamentDao tournamentDao=new TournamentDaoImpl(jdbcUtils);
        MatchDao matchDao=new MatchDaoImpl(jdbcUtils);
        TeamDao teamDao=new TeamDaoImpl(jdbcUtils);

        tournamentDao.add(new Tournament(1,3));
        tournamentDao.add(new Tournament(2,3));
        tournamentDao.update(new Tournament(-1,5),1);
        Tournament tournament=tournamentDao.find(1);
        tournamentDao.delete(2);

        matchDao.add(new Match(1,1,1,2,1,"1:0"));
        matchDao.add(new Match(2,1,3,4,1,"1:2"));
        matchDao.add(new Match(3,1,5,6,1,"2:0"));
        matchDao.add(new Match(4,1,7,8,1,"3:0"));
        matchDao.update(new Match(0,1,1,2,1,"4:0"),1);
        List<Match> matches=matchDao.findByTournamentId(1);

        teamDao.add(new Team(1,"A","a","a"));
        teamDao.add(new Team(2,"B","a","a"));
        teamDao.add(new Team(3,"C","a","a"));
        teamDao.add(new Team(4,"D","a","a"));
        teamDao.add(new Team(5,"E","a","a"));
        teamDao.add(new Team(6,"F","a","a"));
        teamDao.add(new Team(7,"G","a","a"));
        teamDao.add(new Team(8,"H","a","a"));
        teamDao.add(new Team(9,"A","a","a"));

        teamDao.delete(9);

        teamDao.findByTournamentId(1).stream().forEach(System.out::println);

    }
}
