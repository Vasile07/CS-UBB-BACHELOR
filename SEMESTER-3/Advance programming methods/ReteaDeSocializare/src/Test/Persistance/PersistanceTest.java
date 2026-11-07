package Test.Persistance;

import Test.AbstractTest;

public interface PersistanceTest extends AbstractTest {
    @Override
    default void runAllTests(){
            addTest();
            getAllTest();
            foundTest();
            updateTest();
            deleteTest();
    }

    void addTest();
    void deleteTest();
    void getAllTest();
    void foundTest();
    void updateTest();
}
