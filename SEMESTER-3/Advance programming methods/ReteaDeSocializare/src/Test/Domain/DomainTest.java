package Test.Domain;

import Domain.User;
import Test.AbstractTest;

import java.util.Objects;

public interface DomainTest extends AbstractTest {
    @Override
    default void runAllTests() {
        createTest();
        setTest();
    }

    void createTest();

    void setTest();

}
