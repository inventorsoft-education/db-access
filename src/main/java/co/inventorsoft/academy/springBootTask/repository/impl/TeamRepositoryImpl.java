package co.inventorsoft.academy.springBootTask.repository.impl;

import co.inventorsoft.academy.springBootTask.domain.entity.Team;
import co.inventorsoft.academy.springBootTask.repository.EntityMapper;
import co.inventorsoft.academy.springBootTask.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class TeamRepositoryImpl implements TeamRepository {

    private static final String SQL__FIND_ALL_TEAMS = "SELECT * FROM team";
    private static final String SQL__ADD_TEAM = "INSERT into `team`(name, `capitan`, coach) " + "values(?, ?, ?)";

    @Override
    public List<Team> findAll() {
        List<Team> teamList = new ArrayList<Team>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            TeamMapper mapper = new TeamMapper();
            pstmt = con.prepareStatement(SQL__FIND_ALL_TEAMS);
            rs = pstmt.executeQuery();
            while (rs.next())
                teamList.add(mapper.mapRow(rs));
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        }
        return teamList;
    }

    public Team save(Team team) {
        Connection con = null;
        try {
            log.info("add team1 started");
            con = DBManager.getInstance().getConnection();
            addTeam(con, team);
            log.info("add team1 finished");
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        }
        return team;
    }

    public void addTeam(Connection con, Team team) throws SQLException {
        log.info("add team2 started");
        PreparedStatement pstmt = con.prepareStatement(SQL__ADD_TEAM);
        int k = 1;
        pstmt.setString(k++, team.getName());
        pstmt.setString(k++, team.getCapitan());
        pstmt.setString(k, team.getCoach());
        log.info("all lines added to pstm");
        pstmt.executeUpdate();
        pstmt.close();
        log.info("add user2 finished");
    }

    private static class TeamMapper implements EntityMapper<Team> {

        @Override
        public Team mapRow(ResultSet rs) {
            try {
                Team team = new Team();
                team.setId(rs.getInt("id"));
                team.setName(rs.getString("name"));
                team.setCapitan(rs.getString("capitan"));
                team.setCoach(rs.getString("coach"));
                return team;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }

    }

}
