import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

public class HorseTest {
    @Mock
    Horse horse= new Horse("WOT", 0.1,1);

    @Test
     void passedToTheConstructorAsTheFirstParameterNull()  {
        assertThrows(IllegalArgumentException.class, () -> {
            new Horse(null, 0.0);
        });



    }
    @Test
    void passedToTheConstructorFirstNullParameterContainMessage(){
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse(null, 0.0);
        });
        assertEquals("Name cannot be null.",exception.getMessage());

    }
    @ParameterizedTest
    @ValueSource(strings = {" ", "   ", "\n", "\n\n", "\t", "\t\t", "\t  \t"})
    void  passedToTheConstructorAsTheFirstParameterEmptyCharacter(String name){

        String expectedMassage = "Name cannot be blank.";
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse(name, 0.2,11);
        });
        assertEquals(expectedMassage, exception.getMessage());
//        String parameterEmpty = " ";
//        String parameterEmptyTab = "        ";
//        String parameterEmptyLine = "                   ";
//
//        assertThrows(IllegalArgumentException.class, () -> {
//            new Horse(parameterEmpty, 0.0) ;
//        });
//        assertThrows(IllegalArgumentException.class, () -> {
//            new Horse(parameterEmptyTab, 0.0) ;
//        });
//        assertThrows(IllegalArgumentException.class, () -> {
//            new Horse(parameterEmptyLine, 0.0) ;
//        });
    }
//    @Test
//    void passedToTheConstructorFirstEmptyParameterContainMessage(){
//        String parameterEmpty = " ";
//        String parameterEmptyTab = "        ";
//        String parameterEmptyLine = "                   ";
//        Throwable exceptionParameterEmpty = assertThrows(IllegalArgumentException.class, () -> {
//            new Horse(parameterEmpty, 0.0);
//            throw new IllegalArgumentException("Name cannot be blank.");
//        });
//        Throwable exceptionParameterEmptyTab = assertThrows(IllegalArgumentException.class, () -> {
//            new Horse(parameterEmptyTab, 0.0);
//            throw new IllegalArgumentException("Name cannot be blank.");
//        });
//        Throwable exceptionParameterEmptyLine = assertThrows(IllegalArgumentException.class, () -> {
//            new Horse(parameterEmptyLine, 0.0);
//            throw new IllegalArgumentException("Name cannot be blank.");
//        });
//        assertEquals("Name cannot be blank.",exceptionParameterEmpty.getMessage());
//        assertEquals("Name cannot be blank.",exceptionParameterEmptyTab.getMessage());
//        assertEquals("Name cannot be blank.",exceptionParameterEmptyLine.getMessage());
//
//    }
    @Test
    void passedToTheConstructorByTheSecondParameterNegativeNumber(){

            assertThrows(IllegalArgumentException.class, () -> {
                new Horse(null, -0.1);
            });

    }
    @Test
    void passingNegativeNumberSecondParameterToTheConstructorContainAMessage(){
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse("Tom", -0.1,1);

        });
        assertEquals("Speed cannot be negative.",exception.getMessage());
    }

    @Test
    void passedToTheConstructorByTheThirdParameterOfNegativeNumber(){
        assertThrows(IllegalArgumentException.class, () -> {
            new Horse(null, 0.0,-1);
        });
    }
    @Test
    void passingNegativeNumberThirdParameterToTheConstructorContainAMessage(){
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse("Test", 0.0,-1);
        });
        assertEquals("Distance cannot be negative.",exception.getMessage());
    }
    @Test
    void methodReturnsStringFirstParameterName (){
        String nameHorse = "Rob";
        final Horse rob = new Horse(nameHorse, 0.1,1);
        assertEquals(rob.getName(),nameHorse);
    }
    @Test
    void methodReturnsDoubleSecondParameterSpeed (){
        double speedHorse = 2.1;
        final Horse rob = new Horse("nameHorse", speedHorse,1);
        assertEquals(rob.getSpeed(),speedHorse);
    }

    @Test
    void methodReturnsIntegerThirdParameterDistance (){
        int distanceHorse = 2;
        final Horse parameterDistance = new Horse("nameHorse", 0.2,distanceHorse);
        assertEquals(parameterDistance.getDistance(),distanceHorse);
        final Horse parameterDistanceEmpty = new Horse("nameHorse", 0.2);
        assertEquals(parameterDistanceEmpty.getDistance(),0);

    }

    @ExtendWith(MockitoExtension.class)


        @Test
        void methodInternallyCallsTheGetRandomDoubleMethodParameters(){
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            Horse myClass = new Horse("WOT", 0.1,1);
            myClass.move();
            when(Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
            }
        }


    @ParameterizedTest
    @ValueSource(doubles = {0.2,0.3,0.4,0,6,15,0,122})
    void methodAssignsTheDistanceValueCalculatedByTheFormula(double fakeRandomValue){
        double min = 0.2;
        double max = 0.9;
        String nameHorse = "Test";
        double speed = 2.5;
        double distance  = 22;
        Horse horse = new Horse(nameHorse, speed, distance);

        double expectedDistance = distance + speed * fakeRandomValue;
        try ( MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)){
            mockedStatic.when(() -> Horse.getRandomDouble(min,max)).thenReturn(fakeRandomValue);

            horse.move();
            assertEquals(expectedDistance,horse.getDistance());
        }
//        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
//            Horse myClass = new Horse("WOT", 0.1,1);
//            myClass.move();
//            when(Horse.getRandomDouble(0.2, 0.9)).thenReturn(horse.getDistance());
//            assertEquals(1,horse.getDistance());
//        }
        }

    }


