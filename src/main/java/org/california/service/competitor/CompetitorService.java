package org.california.service.competitor;

import org.california.dto.competitor.CompetitorRequestDto;
import org.california.entity.competitor.Competitor;
import org.california.service.BaseService;

import java.util.List;
import java.util.Optional;

public interface CompetitorService extends BaseService<Competitor, CompetitorRequestDto> {
    Competitor create(CompetitorRequestDto competitorRequestDto);
    Optional<Competitor> show(Long id);
    List<Competitor> index();
    Optional<Competitor> update(CompetitorRequestDto competitorRequestDto);
    boolean delete(Long id);
}
