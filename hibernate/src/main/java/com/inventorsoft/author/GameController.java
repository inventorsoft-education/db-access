package com.inventorsoft.author;

import com.inventorsoft.domain.model.Game;
import com.inventorsoft.domain.service.GameService;
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
public class GameController {

    GameService gameService;

    @GetMapping(value = "/games")
    public ResponseEntity<List<Game>> getAllGames() {
        return ResponseEntity.ok(gameService.getAll());
    }

    @GetMapping(value = "/games/{id}")
    public ResponseEntity<Game> getGamesById(@PathVariable Integer id) {
        return ResponseEntity.ok(gameService.getById(id));
    }


}
