package com.zoo;


import com.zoo.animal.Animal;
import com.zoo.room.Room;

import java.util.Date;

public final class AnimalDetails {
    private Animal animal;
    private Date dateAdded;
    private Room room;
    private Date roomLocatedDate;

    public AnimalDetails(Animal animalName, Date dateAdded){
        this.animal = animalName;
        this.dateAdded = dateAdded;
    }
    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Room getRoom() {
        return room;
    }

    public AnimalDetails setRoom(Room room) {
        this.room = room;
        return this;
    }

    public Date getRoomLocatedDate() {
        return roomLocatedDate;
    }

    public AnimalDetails setRoomLocatedDate(Date roomLocatedDate) {
        this.roomLocatedDate = roomLocatedDate;
        return this;
    }

    @Override
    public String toString() {
        System.out.println("Animal Details: {name:- " + animal.getName() + " ; dateAdded:- " + dateAdded + "}");
        return "Animal Details: {name:- " + animal.getName() + " ; dateAdded:- " + dateAdded + "}";
    }
}
