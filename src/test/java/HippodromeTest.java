import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HippodromeTest {

    @Test
    void passingToTheNullConstructorWillThrowException(){
        assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(null);
        });

    }
    @Test
    void thatWhenPassedToTheNullConstructorTheExceptionWillContainMessage(){
        Throwable exception =  assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(null);
        });
        assertEquals("Horses cannot be null.",exception.getMessage());

    }

    @Test
    void passingToTheEmptyConstructorWillThrowException(){
        List<Horse> horses = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(horses);
        });

    }
    @Test
    void thatWhenPassedToTheEmptyConstructorTheExceptionWillContainMessage(){
        List<Horse> horses = new ArrayList<>();
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(horses);
        });
        assertEquals("Horses cannot be empty.",exception.getMessage());

    }

    @Test
    void getHorses_ReturnsListAllHorsesInOrder(){
        List<Horse> horses = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Horse" + i,i,i));
        }

        Hippodrome hippodrome = new Hippodrome(horses);
        assertNotNull(hippodrome.getHorses());
        assertEquals(30,hippodrome.getHorses().size());
        assertEquals("Horse0",hippodrome.getHorses().get(0).getName());
        assertEquals("Horse10",hippodrome.getHorses().get(10).getName());
        assertEquals("Horse29",hippodrome.getHorses().get(29).getName());

    }
    @Test
    void move_CallsMoveMethodForAllHorses(){
        final List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(Mockito.mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();

        for (Horse hors: horses) {
            Mockito.verify(hors,Mockito.times(1)).move();

        }


    }
    @Test
    void getWinner_ReturnCorrectWinner(){
        Hippodrome hippodrome = new Hippodrome(List.of(
                new Horse("Horse10",1,10),
        new Horse("Horse20",1,20),
        new Horse("Horse30",1,30)

        ));

        assertEquals("Horse30",hippodrome.getWinner().getName());
    }



}




