package com.mycompany.app;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * JUnit 5 test class for {@link App}.
 */
@ExtendWith(MockitoExtension.class)
class AppTest {

    private static final String EXPECTED_MESSAGE = "Hello World!";

    @Test
    @DisplayName("Given a new App instance, when getMessage is called, then it should return the expected message")
    void givenNewAppInstance_whenGetMessage_thenReturnExpectedMessage() {
        // Arrange
        App app = new App();

        // Act
        String result = app.getMessage();

        // Assert
        assertNotNull(result);
        assertEquals(EXPECTED_MESSAGE, result);
    }

    @Test
    @DisplayName("Given a new App instance, when getMessage is called multiple times, then it should return the same message each time")
    void givenNewAppInstance_whenGetMessageCalledMultipleTimes_thenReturnSameMessageEachTime() {
        // Arrange
        App app = new App();

        // Act
        String firstCall = app.getMessage();
        String secondCall = app.getMessage();
        String thirdCall = app.getMessage();

        // Assert
        assertEquals(EXPECTED_MESSAGE, firstCall);
        assertEquals(EXPECTED_MESSAGE, secondCall);
        assertEquals(EXPECTED_MESSAGE, thirdCall);
        assertEquals(firstCall, secondCall);
        assertEquals(secondCall, thirdCall);
    }

    @Test
    @DisplayName("Given a new App instance, when main is called with null arguments, then it should print the expected message to stdout")
    void givenNewAppInstance_whenMainCalledWithNullArguments_thenReturnExpectedMessageInStdout() {
        // Arrange
        App app = new App();
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Act
        App.main(null);

        // Assert
        String output = outputStream.toString(StandardCharsets.UTF_8);
        System.setOut(originalOut);
        assertEquals(EXPECTED_MESSAGE + System.lineSeparator(), output);
    }

    @Test
    @DisplayName("Given a new App instance, when main is called with empty arguments array, then it should print the expected message to stdout")
    void givenNewAppInstance_whenMainCalledWithEmptyArguments_thenReturnExpectedMessageInStdout() {
        // Arrange
        App app = new App();
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Act
        App.main(new String[]{});

        // Assert
        String output = outputStream.toString(StandardCharsets.UTF_8);
        System.setOut(originalOut);
        assertEquals(EXPECTED_MESSAGE + System.lineSeparator(), output);
    }

    @Test
    @DisplayName("Given a new App instance, when main is called with non-empty arguments, then it should print the expected message to stdout")
    void givenNewAppInstance_whenMainCalledWithNonEmptyArguments_thenReturnExpectedMessageInStdout() {
        // Arrange
        App app = new App();
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        String[] args = {"arg1", "arg2", "arg3"};

        // Act
        App.main(args);

        // Assert
        String output = outputStream.toString(StandardCharsets.UTF_8);
        System.setOut(originalOut);
        assertEquals(EXPECTED_MESSAGE + System.lineSeparator(), output);
    }

    @Test
    @DisplayName("Given a new App instance, when getMessage is called, then the returned message should not be null or empty")
    void givenNewAppInstance_whenGetMessage_thenReturnNonNullOrEmptyMessage() {
        // Arrange
        App app = new App();

        // Act
        String result = app.getMessage();

        // Assert
        assertNotNull(result);
        assertEquals(false, result.isEmpty());
        assertEquals(false, result.isBlank());
    }

    @Test
    @DisplayName("Given multiple App instances, when getMessage is called on each, then all should return the same message")
    void givenMultipleAppInstances_whenGetMessageCalledOnEach_thenReturnSameMessage() {
        // Arrange
        App app1 = new App();
        App app2 = new App();
        App app3 = new App();

        // Act
        String message1 = app1.getMessage();
        String message2 = app2.getMessage();
        String message3 = app3.getMessage();

        // Assert
        assertEquals(EXPECTED_MESSAGE, message1);
        assertEquals(EXPECTED_MESSAGE, message2);
        assertEquals(EXPECTED_MESSAGE, message3);
        assertEquals(message1, message2);
        assertEquals(message2, message3);
    }
}