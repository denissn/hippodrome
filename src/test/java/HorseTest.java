import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyDouble;

//@Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
class HorseTest {

    @Test
    void constructor_NullName_ThrowsIllegalArgumentException() {
        double speed = 2.5;

        assertThrows(IllegalArgumentException.class, () -> new Horse(null, speed));
    }

    @Test
    void constructor_NullName_ExpectedExceptionMessageNameCannotBeNull() {
        double speed = 2.5;
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, speed));

        String expected = "Name cannot be null.";
        String actual = exception.getMessage();
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n", "\r\n"})
    void constructor_EmptyName_ThrowsIllegalArgumentException(String name) {
        double speed = 2.5;

        assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n", "\r\n"})
    void constructor_EmptyName_ExpectedExceptionMessageNameCannotBeBlank(String name) {
        double speed = 2.5;
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed));

        String expected = "Name cannot be blank.";
        String actual = exception.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    void constructor_NegativeSpeed_ThrowsIllegalArgumentException() {
        String name = "TestHorse";
        double speed = -2.5;

        assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed));
    }

    @Test
    void constructor_NegativeSpeed_ExpectedExceptionMessageSpeedCannotBeNegative() {
        String name = "TestHorse";
        double speed = -2.5;
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed));

        String expected = "Speed cannot be negative.";
        String actual = exception.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    void constructor_NegativeDistance_ThrowsIllegalArgumentException() {
        String name = "TestHorse";
        double speed = 2.5;
        double distance = -1;

        assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, distance));
    }

    @Test
    void constructor_NegativeDistance_ExpectedExceptionMessageDistanceCannotBeNegative() {
        String name = "TestHorse";
        double speed = 2.5;
        double distance = -1;
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, distance));

        String expected = "Distance cannot be negative.";
        String actual = exception.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    void getName_ShouldReturnsCorrectName() {
        String name = "TestHorse";
        double speed = 2.5;
        Horse horse = new Horse(name, speed);

        String expected = "TestHorse";
        String actual = horse.getName();
        assertEquals(expected, actual);
    }

    @Test
    void getSpeed_ShouldReturnsCorrectSpeed() {
        String name = "TestHorse";
        double speed = 2.5;
        Horse horse = new Horse(name, speed);

        double expected = 2.5;
        double actual = horse.getSpeed();
        assertEquals(expected, actual);
    }


    @Test
    void getDistance_ShouldReturnsCorrectDistance() {
        String name = "TestHorse";
        double speed = 2.5;
        double distance = 2.5;
        Horse horse = new Horse(name, speed, distance);

        double expected = 2.5;
        double actual = horse.getDistance();
        assertEquals(expected, actual);
    }

    @Test
    void getDistance_ShouldReturnsCorrectDistanceIfConstructorWithoutDistance() {
        String name = "TestHorse";
        double speed = 2.5;
        Horse horse = new Horse(name, speed);

        double expected = 0;
        double actual = horse.getDistance();
        assertEquals(expected, actual);
    }

    @Test
    void move_ShouldCallGetRandomDoubleWithCorrectParams() {
        String name = "TestHorse";
        double speed = 2.5;
        double distance = 2.5;
        Horse horse = new Horse(name, speed, distance);
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            horse.move();
            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @Test
    void move_ShouldReturnsCorrectDistanceWithMockGetRandomDouble() {
        String name = "TestHorse";
        double speed = 2.5;
        double distance = 1.5;
        double getRandomDoubleFakeResult = 0.5;
        Horse horse = new Horse(name, speed, distance);
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            horseMockedStatic.when(()-> Horse.getRandomDouble(anyDouble(), anyDouble())).thenReturn(getRandomDoubleFakeResult);
            horse.move();
        }
        double expected = distance + speed * getRandomDoubleFakeResult;
        double actual = horse.getDistance();
        assertEquals(expected, actual);
    }
}