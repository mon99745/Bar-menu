package danta.service.item;

import danta.model.dao.item.CatalogDao;
import danta.model.dto.item.CatalogSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CatalogService {
    private final CatalogDao catalogDao;


    public List<CatalogSummary> getCatalog(ItemSearchForm searchForm) {
        return catalogDao.searchItem(searchForm);
    }
}

