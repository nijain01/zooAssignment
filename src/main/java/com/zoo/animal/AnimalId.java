package com.zoo.animal;

public final class AnimalId {

    private static long id;
    private AnimalId(){ id = 0l;};

    private static class AnimalIdHelper{
        private static final AnimalId INSTANCE = new AnimalId();
    }

    public static AnimalId getInstance(){
        return AnimalIdHelper.INSTANCE;
    }

    public long getId(){
        return id++;
    }
}
