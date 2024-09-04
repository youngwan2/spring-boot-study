package com.baeksoo.shop.controller;

import com.baeksoo.shop.entity.Item;
import com.baeksoo.shop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    // 상품 리스트 조회
    @GetMapping("/products")
    String products(Model model, @RequestParam int page) {
        int maxPage = 5;
        Page<Item> items = itemService.getListItems(page, maxPage);
        model.addAttribute("items", items);
        return "list.html";
    }

    //  상품 세부 정보 조회
    @GetMapping("products/{id}")
    String detail(@PathVariable Long id, Model model) {
        try {
            Item item = itemService.getItemWithId(id);
            model.addAttribute("product", item);
            return "detail.html";
        } catch (Exception e) {
            return "Error:" + e.getMessage();
        }
    }

    // 상품 추가 페이지
    @GetMapping("/write")
    String write() {
        return "write.html";
    }

    // 상품 추가 POST 요청 처리
    @PostMapping("/products")
    String postProducts(
            @RequestParam(name = "product-name") String name,
            @RequestParam(name = "product-price") String price
    ) {
        itemService.setItem(name, price);
        return "redirect:/products"; //  리디렉트 경로 입력
    }

    // 상품 수정 페이지
    @GetMapping("/products/edit/{id}")
    String update(@PathVariable Long id ,Model model){
        try {
        Item item = itemService.getItemWithId(id);
        model.addAttribute("item",item);
            return "update.html";
        } catch(Exception ex){
            return "Error:" + ex.getMessage();
        }
    }

    // 상품 수정
    @PostMapping("/update")
    String updateProducts(@RequestParam Long id,  @RequestParam String title, @RequestParam String price) {
        try{
            itemService.updateProduct(id, title, price);
            return "redirect:/products";
        }catch (Exception ex){
            System.out.println( "상품 수정에 실패하였습니다."+ ex.getMessage());
            return ex.getMessage();
        }

    }
}

