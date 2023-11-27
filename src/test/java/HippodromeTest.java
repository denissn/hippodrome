import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {

    @Test
    void constructor_NullHorsesList_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    void constructor_NullHorsesList_ExpectedExceptionMessageHorsesListCannotBeNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));

        String expected = "Horses cannot be null.";
        String actual = exception.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    void constructor_EmptyHorsesList_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
    }

    @Test
    void constructor_EmptyHorsesList_ExpectedExceptionMessageHorsesListCannotBeEmpty() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));

        String expected = "Horses cannot be empty.";
        String actual = exception.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    void getHorses_ShouldReturnsHorsesListInConstructorOrder() {
        int listHorsesSize = 30;
        List<Horse> horses = new ArrayList<>(listHorsesSize);
        for (int i = 0; i < listHorsesSize; i++) {
            String name = "horse_" + i;
            double speed = 2 + i * 0.01;
            double distance = i * 0.1;
            horses.add(new Horse(name, speed, distance));
        }
        Hippodrome hippodrome = new Hippodrome(horses);

        for (int i = 0; i < listHorsesSize; i++) {
            String expected = "horse_" + i;
            String actual = hippodrome.getHorses().get(i).getName();
            assertEquals(expected, actual);
        }
    }

    @Test
    void move_ShouldCallMoveForEachHorse() {
        int listHorsesSize = 50;
        List<Horse> horses = new ArrayList<>(listHorsesSize);
        for (int i = 0; i < listHorsesSize; i++) {
            horses.add(Mockito.mock(Horse.class));
        }

        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();

        for (Horse horse : horses) {
            Mockito.verify(horse, Mockito.times(1)).move();
        }
    }

    @Test
    void getWinner_ShouldReturnsHorseWithLongestDistance() {
        List<Horse> horses = List.of(
                new Horse("horse_0",3.1,4),
                new Horse("horse_1",2.2,2),
                new Horse("horse_2",2.8,3)
        );
        Hippodrome hippodrome = new Hippodrome(horses);

        double expected = 4;
        double actual = hippodrome.getWinner().getDistance();
        assertEquals(expected, actual );
    }
}