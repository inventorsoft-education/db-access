package com.inventorsoft.author;

import com.inventorsoft.domain.model.Team;
import com.inventorsoft.domain.service.TeamService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeamController {

    TeamService teamService;

    @GetMapping(value = "/teams")
    public ResponseEntity<List<Team>> getAllTeam() {
        return ResponseEntity.ok(teamService.getAll());
    }

    @GetMapping(value = "/teams/{id}")
    public ResponseEntity<Team> getTeamsById(@PathVariable Integer id) {
        return ResponseEntity.ok(teamService.getById(id));
    }
}
