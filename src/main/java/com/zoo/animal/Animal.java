package com.zoo.animal;

import java.util.Set;

public interface Animal {

    default String getName(){
        return this.getClass().getSimpleName();
    }

    Animal setAnimalId();

    long getAnimalId();

    void addFavouriteRoom(String roomName);

    Set<String> getFavouriteRoom();

}
