package com.baeksoo.shop.exception;

public class ItemNotFoundException extends RuntimeException {

    public ItemNotFoundException(String message){
        super(message);
    }
}
