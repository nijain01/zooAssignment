package com.zoo;


import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.SetMultimap;
import com.zoo.animal.Animal;
import com.zoo.room.Room;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Stream;

public class AnimalInZooBuilderImpl implements AnimalInZooBuilder {

    private final SetMultimap<Class<? extends Animal>, AnimalDetails> animals;

    public AnimalInZooBuilderImpl() {
        animals = LinkedHashMultimap.create();
    }

    /**
     * @return Set of animals in the zoo
     */
    public Set<Entry<Class<? extends Animal>, AnimalDetails>> getAnimals() {
        return animals.entries();
    }

    /**
     * @param animal
     * @param dateAdded
     * @return
     */
    @Override
    public SetMultimap<Class<? extends Animal>, AnimalDetails> build(Animal animal, Date dateAdded) {
        animals.put(animal.getClass(), new AnimalDetails(animal, dateAdded));
        return animals;
    }

    /**
     * @param animal
     * @param room
     * @param roomAllocationDate
     * @return
     */
    public SetMultimap<Class<? extends Animal>, AnimalDetails> shiftAnimalToRoom(Animal animal, Room room, Date roomAllocationDate) {
        if (null == animals) {
            return null;
        }
        Stream<AnimalDetails> animalDetails = animals.get(animal.getClass()).stream().filter(details -> details.getAnimal().getAnimalId() == animal.getAnimalId());
        animalDetails.forEach(details -> details.setRoom(room).setRoomLocatedDate(roomAllocationDate));
        return animals;
    }

    /**
     * @param animal
     * @return Map with entire animals which are still in zoo
     */
    public SetMultimap<Class<? extends Animal>, AnimalDetails> removeAnimalFromRoom(Animal animal) {
        if (null == animals) {
            return null;
        }
        Stream<AnimalDetails> animalDetails = animals.get(animal.getClass()).stream().filter(details -> details.getAnimal().getAnimalId() == animal.getAnimalId());
        animalDetails.forEach(details -> details.setRoom(null));
        return animals;
    }

    /**
     * @return List of Animal Details which are in Zoo but not allocated to any room
     */
    @Override
    public List<AnimalDetails> animalWithoutRoom() {
        Stream<AnimalDetails> animalDetails = animals.values().stream().filter(details -> details.getRoom() == null);
        List<AnimalDetails> animalList = new ArrayList();
        animalDetails.forEach(details -> animalList.add(details));
        return animalList;
    }

    /**
     * @param room
     * @return List of Animal Details which are present in specific Room
     */
    @Override
    public List<AnimalDetails> animalsInSpecificRoom(Room room) {
        Stream<AnimalDetails> animalDetails = animals.values().stream().filter(details -> (details.getRoom() != null) && (details.getRoom() == room));
        List<AnimalDetails> animalDetailsList = new ArrayList<>();
        animalDetails.forEach(details -> animalDetailsList.add(details));
        return animalDetailsList;
    }

    /**
     * @param animal
     * @return List of favourite rooms for animal
     */
    @Override
    public List<String> getFavouriteRoomForAnimal(Animal animal) {
        Stream<AnimalDetails> animalDetails = animals.values().stream().filter(details -> (animal != null) && (details.getAnimal() == animal));
        List<String> animalFavouriteRoomList = new ArrayList<>();
        animalDetails.forEach(details -> details.getAnimal().getFavouriteRoom().forEach(s -> animalFavouriteRoomList.add(s)));
        return animalFavouriteRoomList;
    }

}
