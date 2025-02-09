package org.california.service.competitor;

import org.california.dao.analysis.AnalysisDao;
import org.california.dao.competitor.CompetitorDao;
import org.california.dto.competitor.CompetitorRequestDto;
import org.california.entity.analysis.Analysis;
import org.california.entity.competitor.Competitor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompetitorServiceImp implements CompetitorService {

    private final CompetitorDao competitorDao;
    private final AnalysisDao analysisDao;

    public CompetitorServiceImp(
            CompetitorDao competitorDao,
            AnalysisDao analysisDao
    ) {
        this.competitorDao = competitorDao;
        this.analysisDao = analysisDao;
    }

    @Override
    public Competitor create(CompetitorRequestDto competitorRequestDto) {
        return this.competitorDao.create(competitorRequestDto);
    }

    @Override
    public Optional<Competitor> show(Long id) {
         return this.competitorDao.show(id);

    }

    @Override
    public List<Competitor> index() {
        return this.competitorDao.index();
    }

    @Override
    public Optional<Competitor> update(CompetitorRequestDto competitorRequestDto) {
        Analysis analysis = this.analysisDao.show(competitorRequestDto.getAnalysisId()).orElse(null);
        Competitor competitor = new Competitor()
                .setId(competitorRequestDto.getId())
                .setAnalysisId(analysis.getId())
                .setSite(competitorRequestDto.getSite())
                .setUrl(competitorRequestDto.getUrl())
                .setPrice(competitorRequestDto.getPrice())
                .setRelevant(competitorRequestDto.isRelevant())
                .setPosition(competitorRequestDto.getPosition())
                .setProductName(competitorRequestDto.getProduct());
        return this.competitorDao.update(competitor);
    }

    @Override
    public boolean delete(Long id) {
        return this.competitorDao.delete(id);
    }

}
