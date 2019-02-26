package com.zoo.animal;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractAnimal implements Animal {
    private Set<String> favouriteRoom = new HashSet<>();
    private long id;

    @Override
    public Animal setAnimalId() {
        id = AnimalId.getInstance().getId();
        return this;
    }

    @Override
    public long getAnimalId() {
        return this.id;
    }

    @Override
    public void addFavouriteRoom(String roomName) {
        favouriteRoom.add(roomName);
    }

    @Override
    public Set<String> getFavouriteRoom() {
        return favouriteRoom;
    }
}
