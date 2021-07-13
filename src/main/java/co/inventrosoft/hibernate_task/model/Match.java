package co.inventrosoft.hibernate_task.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(of = "id")
@Table(name = "matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    int id;

    //  1/round - name of round. e.g roundCode = 8 -> round name = 1/8
    //  roundCode == number of matches in current round
    //  if roundCode == 1 - this round is final
    @Column
    int roundCode;

    //  for resolving to which match carry the winner
    //  order == sequence number of match in current round
    @Column(name = "match_order")
    int order;

    @ManyToOne(fetch = FetchType.LAZY)
    Team firstTeam;

    @ManyToOne(fetch = FetchType.LAZY)
    Team secondTeam;

    @Column(nullable = false)
    boolean played = false;

    @Column(nullable = true)
    int firstTeamResult;

    @Column(nullable = true)
    int secondTeamResult;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    Tournament tournament;

    public Match(int roundCode, int order, Tournament tournament) {
        this.roundCode = roundCode;
        this.order = order;
        this.tournament = tournament;
    }

    @JsonIgnore
    public String getFirstTeamName() {
        if (firstTeam != null) {
            return firstTeam.getName();
        }
        return null;
    }
    @JsonIgnore
    public String getSecondTeamName() {
        if (secondTeam != null) {
            return secondTeam.getName();
        }
        return null;
    }
    @JsonIgnore
    public int getFirstTeamId() {
        if (firstTeam != null) {
            return firstTeam.getId();
        }
        return 0;
    }
    @JsonIgnore
    public int getSecondTeamId() {
        if (secondTeam != null) {
            return secondTeam.getId();
        }
        return 0;
    }
    @JsonIgnore
    public String getScore() {
        String score = null;
        if (this.played) {
            score = this.firstTeamResult + ":" + this.secondTeamResult;
        }
        return score;
    }
    // lombok is broken here idk
    public boolean getPlayed() {
        return this.played;
    }

    @JsonIgnore
    public Team getWinner() {
        Team winner = null;
        if (this.played) {
            winner = (firstTeamResult > secondTeamResult) ? firstTeam : secondTeam;
        }
        return winner;
    }

    @JsonIgnore
    public void setTeamByOrder(int order, Team team) {
        if (order % 2 == 0) {
            firstTeam = team;
        }
        else {
            secondTeam = team;
        }
    }

    @JsonIgnore
    public boolean isFinal() {
        return this.roundCode == 1;
    }

}
