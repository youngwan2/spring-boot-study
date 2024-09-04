package com.baeksoo.shop.service;

import com.baeksoo.shop.exception.ItemNotFoundException;
import com.baeksoo.shop.repository.ItemRepository;
import com.baeksoo.shop.entity.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    // 아이템 목록 조회
    public Page<Item> getListItems(int page, int maxSize) {
        Pageable pageable = PageRequest.of(page, maxSize);
        return itemRepository.findNextPage(pageable);
    }

    // 아이템 저장
    public void setItem(String title, String price) {
        Item item = new Item(title, price);
        itemRepository.save(item);
    }

    // 단일 아이템 조회
    public Item getItemWithId(Long id) {
        Optional<Item> result = itemRepository.findById(id);
//        result.orElseThrow(() -> new ItemNotFoundException("Item Not Found:" + id));
        result.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found with id"+id));

        return result.get();
    }
    //    아이템 수정
    public void updateProduct(Long id, String title, String price){
            System.out.println(id);
        System.out.println(title);
           Optional<Item> result  = itemRepository.findById(id);

           result.orElseThrow(()-> new ItemNotFoundException("현재 검색된 아이템은 존재하지 않습니다.:"+id));

           Item item = result.get();

           item.setTitle(title);
           item.setPrice(price);

           itemRepository.save(item);
    }
}
