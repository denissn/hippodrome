import org.junit.jupiter.api.Test;

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
    void getHorses_ShouldReturnHorsesListInConstructorOrder() {
        List<Horse> horses = List.of(
            new Horse("Bucephalus", 2.4),
            new Horse("Ace of Spades", 2.5),
            new Horse("Zephyr", 2.6),
            new Horse("Blaze", 2.7),
            new Horse("Lobster", 2.8),
            new Horse("Pegasus", 2.9),
            new Horse("Cherry", 3)
        );
        
    }

    @Test
    void move() {
    }

    @Test
    void getWinner() {
    }
}