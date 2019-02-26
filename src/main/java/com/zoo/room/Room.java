package com.zoo.room;

public interface Room {
    default String getName(){
        return this.getClass().getSimpleName();
    }
}
