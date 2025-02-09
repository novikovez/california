package org.california.dao.competitor;

import org.california.dao.BaseDao;
import org.california.dto.competitor.CompetitorRequestDto;
import org.california.entity.competitor.Competitor;

import java.util.List;
import java.util.Optional;

public interface CompetitorDao extends BaseDao<Competitor, CompetitorRequestDto> {
    Competitor create(CompetitorRequestDto competitorRequestDto);
    Optional<Competitor> show(Long id);
    Optional<Competitor> update(Competitor entity);
    List<Competitor> index();
    boolean delete(Long id);

}
