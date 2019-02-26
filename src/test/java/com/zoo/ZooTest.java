package com.zoo;

import com.google.common.collect.SetMultimap;
import com.zoo.animal.Animal;
import com.zoo.animal.Elephant;
import com.zoo.animal.Lion;
import com.zoo.animal.Zebra;
import com.zoo.room.Room;
import com.zoo.room.Room1;
import com.zoo.room.Room2;
import org.junit.Test;

import java.util.Date;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ZooTest {

    private SetMultimap<Class<? extends Animal>, AnimalDetails> animals;
    private Zoo zoo;
    private TestId testId = TestId.getInstance();

    /**
     * Zoo Creation
     */
    private void createZoo() {
        AnimalInZooBuilder zooBuilder;
        zooBuilder = new AnimalInZooBuilderImpl();
        zoo = new Zoo(zooBuilder);
    }

    /**
     * Data set up for creating Zoo With Animals
     */
    private void createAnimals(Animal...animalss){
        createZoo();
        for (Animal animal: animalss){
            animals =  zoo.addAnimalInZoo(animal.setAnimalId(),new Date());
        }
    }

    /**
     * Test Shifting Animal In Zoo
     */
   @Test
    public void testShiftingAnimalInZoo(){
       createAnimals(new Lion());
        assertEquals(1, animals.size());
        AnimalDetails details = animals.values().iterator().next();
        zoo.shiftAnimalToRoom(details.getAnimal(), new Room1(), new Date());
        assertEquals("Room1", details.getRoom().getName());
        assertEquals(testId.getId(), details.getAnimal().getAnimalId());
   }

    /**
     * Test to try Shifting the Animal In Zoo If No Animal, Null pointer check
     */
    @Test (expected = NullPointerException.class)
    public void testShiftingAnimalInZooIfNoAnimal(){
        createZoo();
        zoo.shiftAnimalToRoom(null, new Room1(), new Date());
        assertEquals(0, animals.size());
        assertEquals("Room1",animals.values().iterator().next().getRoom().getName());
    }

    /**
     * Test Shifting Animal To New Room
     */
    @Test
    public void testShiftingAnimalToNewRoom(){
        createAnimals(new Lion());
        AnimalDetails details = animals.values().iterator().next();
        zoo.shiftAnimalToRoom(details.getAnimal(), new Room1(), new Date());
        assertEquals("Room1", details.getRoom().getName());
        assertEquals(testId.getId(), details.getAnimal().getAnimalId());
        //Lets change the room for animal
        zoo.shiftAnimalToRoom(details.getAnimal(), new Room2(), new Date());
        assertEquals("Room2", details.getRoom().getName());
    }

    /**
     * Test to Deallocate Room From Animal
     */
    @Test
    public void testDeallocateRoomFromAnimal(){
        createAnimals(new Lion());
        AnimalDetails details = animals.values().iterator().next();
        zoo.shiftAnimalToRoom(details.getAnimal(), new Room1(), new Date());
        assertEquals(testId.getId(), details.getAnimal().getAnimalId());
        zoo.removeAnimalFromRoom(details.getAnimal());
        assertNull(details.getRoom());
    }

    /**
     * Test Add Two Animal In Zoo
     */
    @Test
    public void testAddTwoAnimalInZoo() {
        createAnimals(new Lion(), new Zebra());
        assertEquals(2, animals.size());
        animals.values().forEach(details -> assertEquals(testId.getId(), details.getAnimal().getAnimalId()));
    }


    /**
     * Test Add Three Animal In Zoo
     */
    @Test
    public void testAddThreeAnimalInZoo() {
        createAnimals(new Lion(), new Zebra(), new Elephant());
        assertEquals(3, animals.size());
        animals.values().forEach(details -> assertEquals(testId.getId(), details.getAnimal().getAnimalId()));
    }

    /**
     * Test Add Four Animal In Zoo
     */
    @Test
    public void testAddFourAnimalInZoo() {
        createAnimals(new Lion(), new Elephant(), new Zebra(), new Zebra());
        assertEquals(4, animals.size());
        animals.values().forEach(details -> assertEquals(testId.getId(), details.getAnimal().getAnimalId()));
    }

    /**
     * Test for getting Report For Animal Without Room
     */
    @Test
    public void testReportForAnimalWithoutRoom(){
        createAnimals(new Lion(), new Elephant(), new Zebra(), new Zebra());
        assertEquals(4, animals.size());

        animals.values().forEach(details -> zoo.shiftAnimalToRoom(details.getAnimal(), new Room1(), new Date()));

        //Remove one animal from room
        Iterator<AnimalDetails> iterator= animals.values().iterator();
        AnimalDetails animalDetails = iterator.next();
        zoo.removeAnimalFromRoom(animalDetails.getAnimal());

        animals.values().forEach(details -> assertEquals(testId.getId(), details.getAnimal().getAnimalId()));
        assertEquals(1, zoo.animalWithoutRoom().size());

        //Lets remove one more animal from room
        animalDetails = iterator.next();
        zoo.removeAnimalFromRoom(animalDetails.getAnimal());
        assertEquals(2, zoo.animalWithoutRoom().size());
    }


    @Test
    public void testAnimalsInSpecificRoom(){
        createAnimals(new Lion(), new Elephant(), new Zebra(), new Zebra());
        assertEquals(4, animals.size());
        animals.values().forEach(details -> zoo.shiftAnimalToRoom(details.getAnimal(), new Room1(), new Date()));
        animals.values().forEach(details -> assertEquals(testId.getId(), details.getAnimal().getAnimalId()));
        Room2 room2 = new Room2();

        //Move one animal from room1 to Room2
        Iterator<AnimalDetails> iterator= animals.values().iterator();
        AnimalDetails animalDetails = iterator.next();
        zoo.shiftAnimalToRoom(animalDetails.getAnimal(), room2, new Date());

        assertEquals(1, zoo.animalsInSpecificRoom(room2).size());

        //Lets move one more animal to room2
        animalDetails = iterator.next();
        zoo.shiftAnimalToRoom(animalDetails.getAnimal(), room2, new Date());
        assertEquals(2, zoo.animalsInSpecificRoom(room2).size());
    }

    /**
     * Test to Get Favourite Room For Animal
     */
    @Test
    public void testGetFavouriteRoomForAnimal() {
        createAnimals(new Lion(), new Elephant(), new Zebra(), new Zebra());
        assertEquals(4, animals.size());

        animals.values()
            .forEach(details -> assertEquals(testId.getId(), details.getAnimal().getAnimalId()));

        Iterator<AnimalDetails> iterator= animals.values().iterator();
        AnimalDetails animalDetails = iterator.next();

        Room room = new Room1();
        //Set first room as favourite
        animalDetails.getAnimal().addFavouriteRoom(room.getName());

        //create another room and set as favorite for same animal
        Room room2 = new Room2();
        animalDetails.getAnimal().addFavouriteRoom(room2.getName());
        assertEquals(2, zoo.getFavouriteRoomForAnimal(animalDetails.getAnimal()).size());

        //lets add only one room as favorite for next animal and check
        animalDetails = iterator.next();
        animalDetails.getAnimal().addFavouriteRoom(room2.getName());
        assertEquals(1, zoo.getFavouriteRoomForAnimal(animalDetails.getAnimal()).size());

        //When no favorite room is added for the animal
        animalDetails = iterator.next();
        assertEquals(0, zoo.getFavouriteRoomForAnimal(animalDetails.getAnimal()).size());

    }
}