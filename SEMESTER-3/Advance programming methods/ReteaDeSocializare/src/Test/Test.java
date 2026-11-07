package Test;

import Test.Domain.FriendshipTest;
import Test.Domain.MessageTest;
import Test.Domain.UsetTests;
import Test.Persistance.InMemoryRepoTest;

import java.util.ArrayList;
import java.util.List;

public class Test implements AbstractTest{

    List<AbstractTest> listOfTests = new ArrayList<>();
    public Test(){
        //Domain
        listOfTests.add(new UsetTests());
        listOfTests.add(new MessageTest());
        listOfTests.add(new FriendshipTest());
        //Persistance
        listOfTests.add(new InMemoryRepoTest());

    }
    @Override
    public void runAllTests() {
        for(AbstractTest test : listOfTests)
            test.runAllTests();
    }
}
