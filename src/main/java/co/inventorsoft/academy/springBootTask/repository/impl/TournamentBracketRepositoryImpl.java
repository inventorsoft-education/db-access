package co.inventorsoft.academy.springBootTask.repository.impl;

import co.inventorsoft.academy.springBootTask.domain.entity.TournamentBracket;
import co.inventorsoft.academy.springBootTask.repository.EntityMapper;
import co.inventorsoft.academy.springBootTask.repository.TournamentBracketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Repository
@Slf4j
public class TournamentBracketRepositoryImpl implements TournamentBracketRepository {

    private static final String SQL__FIND_ALL_TOURNAMENT_BRACKETS = "SELECT id, matches, winner FROM tournament";
    private static final String SQL__ADD_TOURNAMENT_BRACKET =
            "INSERT into tournament(matches, winner) values(?, ?)";

    public List<TournamentBracket> findAll() {
        List<TournamentBracket> tournamentBracketList = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            TournamentBracketRepositoryImpl.TournamentBracketMapper mapper =
                    new TournamentBracketRepositoryImpl.TournamentBracketMapper();
            pstmt = con.prepareStatement(SQL__FIND_ALL_TOURNAMENT_BRACKETS);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                tournamentBracketList.add(mapper.mapRow(rs));
            }
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            assert con != null;
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return tournamentBracketList;
    }

    public TournamentBracket save(TournamentBracket tb) {
        Connection con = null;
        try {
            log.info("add tb1 started");
            con = DBManager.getInstance().getConnection();
            addTournamentBracket(con, tb);
            log.info("add tb1 finished");
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        }
        return tb;
    }

    public void addTournamentBracket(Connection con, TournamentBracket tb) {
        log.info("add tb2 started");
        try (PreparedStatement pstmt = con.prepareStatement(SQL__ADD_TOURNAMENT_BRACKET)) {
            pstmt.setString(1, tb.getMatches());
            pstmt.setString(2, tb.getWinner());
            log.info("all lines added to pstm");
            pstmt.executeUpdate();
            log.info("add tb2 finished");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static class TournamentBracketMapper implements EntityMapper<TournamentBracket> {

        @Override
        public TournamentBracket mapRow(ResultSet rs) {
            try {
                TournamentBracket tournamentBracket = new TournamentBracket();
                tournamentBracket.setId(rs.getInt("id"));
                tournamentBracket.setMatches(rs.getString("matches"));
                tournamentBracket.setWinner(rs.getString("winner"));
                return tournamentBracket;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }

    }

}
