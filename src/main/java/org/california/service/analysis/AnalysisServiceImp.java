package org.california.service.analysis;

import org.california.dao.analysis.AnalysisDao;
import org.california.dto.analysis.AnalysisRequestDto;
import org.california.entity.analysis.Analysis;
import org.california.exception.analysis.AnalysisDaoException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnalysisServiceImp implements AnalysisService {

    private final AnalysisDao analysisDao;

    public AnalysisServiceImp(AnalysisDao analysisDao) {
        this.analysisDao = analysisDao;
    }

    @Override
    public Analysis create(AnalysisRequestDto analysisRequestDto) {
        return this.analysisDao.create(analysisRequestDto);
    }

    @Override
    public Optional<Analysis> show(Long id) throws AnalysisDaoException {
         return this.analysisDao.show(id);

    }

    @Override
    public List<Analysis> index() {
        return this.analysisDao.index();
    }

    @Override
    public Optional<Analysis> update(AnalysisRequestDto analysisRequestDto) throws AnalysisDaoException {
        Analysis analysis = new Analysis()
                .setId(analysisRequestDto.getId())
                .setPrice(analysisRequestDto.getPrice())
                .setPurchase(analysisRequestDto.getPurchase())
                .setQuantity(analysisRequestDto.getQuantity())
                .setExternalId(analysisRequestDto.getExternalId())
                .setSku(analysisRequestDto.getSku())
                .setProduct(analysisRequestDto.getProductName())
                .setCompetitor(analysisRequestDto.getCompetitors());
        return this.analysisDao.update(analysis);
    }

    @Override
    public boolean delete(Long id) {
        return this.analysisDao.delete(id);
    }

}
