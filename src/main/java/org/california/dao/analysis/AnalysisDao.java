package org.california.dao.analysis;

import org.california.dao.BaseDao;
import org.california.dto.analysis.AnalysisRequestDto;
import org.california.entity.analysis.Analysis;

import java.util.List;
import java.util.Optional;

public interface AnalysisDao extends BaseDao<Analysis, AnalysisRequestDto> {
    Analysis create(AnalysisRequestDto analysisRequestDto);
    Optional<Analysis> show(Long id);
    Optional<Analysis> update(Analysis entity);
    List<Analysis> index();
    boolean delete(Long id);

}
