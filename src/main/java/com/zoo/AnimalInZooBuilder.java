package com.zoo;


import com.google.common.collect.SetMultimap;
import com.zoo.animal.Animal;
import com.zoo.room.Room;

import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public interface AnimalInZooBuilder {

    Set<Entry<Class<? extends Animal>, AnimalDetails>> getAnimals();
    SetMultimap<Class<? extends Animal>, AnimalDetails> build(Animal animal, Date dateAdded);
    SetMultimap<Class<? extends Animal>, AnimalDetails> shiftAnimalToRoom(Animal animal, Room room, Date roomAllocationDate);
    /*
    * New function for change of room is not required as it can be achieved by  shiftAnimalToRoom() function
     */
   //AnimalDetails changeRoomForAnimal (AnimalDetails animalDetails, Room newRoom, Date roomAllocationDate);
    SetMultimap<Class<? extends Animal>, AnimalDetails> removeAnimalFromRoom(Animal animal);

    List<AnimalDetails> animalWithoutRoom();

    List <AnimalDetails> animalsInSpecificRoom(Room room);

    List<String> getFavouriteRoomForAnimal(Animal animal);
}
