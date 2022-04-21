package danta.service;

import danta.model.dao.CatalogDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CatalogService {
    private final CatalogDao catalogDao;

//    public List<CatalogSummary> getCatalog(ItemSearchForm searchForm) {
//        return catalogDao.searchItem(searchForm);
//    }
}
