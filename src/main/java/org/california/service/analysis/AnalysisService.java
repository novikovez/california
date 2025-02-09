package org.california.service.analysis;

import org.california.dto.analysis.AnalysisRequestDto;
import org.california.entity.analysis.Analysis;
import org.california.service.BaseService;

import java.util.List;
import java.util.Optional;

public interface AnalysisService extends BaseService<Analysis, AnalysisRequestDto> {
    Analysis create(AnalysisRequestDto analysisRequestDto);
    Optional<Analysis> show(Long id);
    List<Analysis> index();
    Optional<Analysis> update(AnalysisRequestDto analysisRequestDto);
    boolean delete(Long id);
}
