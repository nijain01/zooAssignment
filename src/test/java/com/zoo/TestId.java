package com.zoo;

public final class TestId {

    private static long id;
    private TestId(){ id = 0l;};

    private static class TestIdHelper{
        private static final TestId INSTANCE = new TestId();
    }

    public static TestId getInstance(){
        return TestIdHelper.INSTANCE;
    }

    public long getId(){
        return id++;
    }
}
