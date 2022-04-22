package danta.service;

import danta.domain.item.ItemEntity;
import danta.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    public Long saveItem(AddItemRequest request) {
        ItemEntity newItem = ItemEntity.builder()
                .name(request.getName())
                .imagePath(request.getImagePath())
                .price(request.getPrice())
                .stockQuantity(request.getStockQuantity())
                .categoryId(request.getCategoryId())
                .build();
        ItemEntity savedItem = itemRepository.save(newItem);

        return savedItem.getItemId();
    }
    public List<ItemEntity> findAll() {
        return itemRepository.findAll();
    }

    public ItemDetails findItem(Long itemId) {
        ItemEntity itemEntity = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다."));

        return new ItemDetails(itemEntity);
    }
}
