package org.california.controller.analysis;

import org.california.dto.analysis.AnalysisRequestDto;
import org.california.entity.analysis.Analysis;
import org.california.exception.analysis.AnalysisDaoException;
import org.california.response.analysis.*;
import org.california.service.analysis.AnalysisService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/analysis")
public class AnalysisController {
    private final AnalysisService analysisService;
    public AnalysisController(AnalysisService analysisService) {
        this.analysisService = analysisService;
    }

    @PostMapping
    public ResponseEntity<AnalysisCreateResponse> store(@RequestBody AnalysisRequestDto analysisRequestDto) {
        Analysis result = this.analysisService.create(analysisRequestDto);
        if(result.getId() != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(AnalysisCreateResponse.of(true, result));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(AnalysisCreateResponse.of(false, null));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnalysisShowResponse> show(@PathVariable("id") Long id) {
        Optional<Analysis> result = this.analysisService.show(id);
        if(result.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(AnalysisShowResponse.of(true, result.get()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(AnalysisShowResponse.of(false, null));

    }

    @GetMapping
    public ResponseEntity<AnalysisIndexResponse> index() {
        List<Analysis> result = this.analysisService.index();
        if(result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(AnalysisIndexResponse.of(false, null));
        }
        return ResponseEntity.status(HttpStatus.OK).body(AnalysisIndexResponse.of(true, result));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnalysisUpdateResponse> update(@PathVariable("id") Long id, @RequestBody AnalysisRequestDto analysisRequestDto) {
        Analysis analysis = this.analysisService.show(id).orElse(null);
        if(analysis == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(AnalysisUpdateResponse.of(false, null));
        }
        analysisRequestDto.setId(id);
        try {
            Optional<Analysis> result = this.analysisService.update(analysisRequestDto);
            if(result.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(AnalysisUpdateResponse.of(true, result.get()));
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(AnalysisUpdateResponse.of(false, null));
        } catch (AnalysisDaoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(AnalysisUpdateResponse.of(false, null));
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AnalysisDeleteResponse> delete(@PathVariable("id") Long id) {
        if(this.analysisService.delete(id)) {
            return ResponseEntity.status(HttpStatus.OK).body(AnalysisDeleteResponse.of(true));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(AnalysisDeleteResponse.of(false));
    }
}
