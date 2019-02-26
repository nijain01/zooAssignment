package com.zoo;


import com.google.common.collect.SetMultimap;
import com.zoo.animal.Animal;
import com.zoo.room.Room;

import java.util.Date;
import java.util.List;

/**
 * Zoo Class
 */
public class Zoo {

    private final AnimalInZooBuilder zooBuilder;

    /**
     *
     * @param zooBuilder
     */
    public Zoo(final AnimalInZooBuilder zooBuilder){
        this.zooBuilder = zooBuilder;
    }

    /**
     *
     * @param animal
     * @param dateAdded
     * @return Map of animal details after adding new animal in zoo
     */
    public SetMultimap<Class<? extends Animal>, AnimalDetails> addAnimalInZoo(Animal animal, Date dateAdded){
        return zooBuilder.build(animal, dateAdded);
    }

    /**
     *
     * @param animal
     * @param room
     * @param roomAllocationDate
     * @return Map of animal details after shifting animal to room
     */
    public SetMultimap<Class<? extends Animal>, AnimalDetails> shiftAnimalToRoom(Animal animal, Room room, Date roomAllocationDate){
        return zooBuilder.shiftAnimalToRoom(animal, room, roomAllocationDate);
    }

    /**
     *
     * @param animal
     * @return Map of animals details after removing animal from Room
     */
    public SetMultimap<Class<? extends Animal>, AnimalDetails> removeAnimalFromRoom(Animal animal){
        return zooBuilder.removeAnimalFromRoom(animal);
    }

    /**
     *
     * @return List of Details for animal Without Room
     */
    public List<AnimalDetails> animalWithoutRoom(){
        return zooBuilder.animalWithoutRoom();
    }

    /**
     *
     * @param room
     * @return List of Details for animals in Specific Room
     */
    public List<AnimalDetails> animalsInSpecificRoom(Room room){
        return zooBuilder.animalsInSpecificRoom(room);
    }

    /**
     *
     * @param animal
     * @return List of Favourite Rooms for Animal
     */
    public List<String> getFavouriteRoomForAnimal(Animal animal){
        return zooBuilder.getFavouriteRoomForAnimal(animal);
    }
}
