package org.california.controller.competitor;

import org.california.dto.competitor.CompetitorRequestDto;
import org.california.entity.competitor.Competitor;
import org.california.exception.analysis.CompetitorDaoException;
import org.california.response.competitor.analysis.*;
import org.california.service.competitor.CompetitorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/competitor")
public class CompetitorController {
    private final CompetitorService competitorService;
    public CompetitorController(CompetitorService competitorService) {
        this.competitorService = competitorService;
    }

    @PostMapping
    public ResponseEntity<CompetitorCreateResponse> store(@RequestBody CompetitorRequestDto competitorRequestDto) {
        try {
            Competitor result = this.competitorService.create(competitorRequestDto);
            if(result.getId() != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(CompetitorCreateResponse.of(true, result));
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(CompetitorCreateResponse.of(false, null));
        } catch (CompetitorDaoException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(CompetitorCreateResponse.of(false, null));
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<CompetitorShowResponse> show(@PathVariable("id") Long id) {
        Optional<Competitor> result = this.competitorService.show(id);
        if(result.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(CompetitorShowResponse.of(true, result.get()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(CompetitorShowResponse.of(false, null));

    }

    @GetMapping
    public ResponseEntity<CompetitorIndexResponse> index() {
        List<Competitor> result = this.competitorService.index();
        if(result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(CompetitorIndexResponse.of(false, null));
        }
        return ResponseEntity.status(HttpStatus.OK).body(CompetitorIndexResponse.of(true, result));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompetitorUpdateResponse> update(@PathVariable("id") Long id, @RequestBody CompetitorRequestDto competitorRequestDto) {
        Competitor competitor = this.competitorService.show(id).orElse(null);
        if(competitor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(CompetitorUpdateResponse.of(false, null));
        }
        competitorRequestDto.setId(id);
        Optional<Competitor> result = this.competitorService.update(competitorRequestDto);
        if(result.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(CompetitorUpdateResponse.of(true, result.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(CompetitorUpdateResponse.of(false, null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CompetitorDeleteResponse> delete(@PathVariable("id") Long id) {
        if(this.competitorService.delete(id)) {
            return ResponseEntity.status(HttpStatus.OK).body(CompetitorDeleteResponse.of(true));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(CompetitorDeleteResponse.of(false));
    }
}
