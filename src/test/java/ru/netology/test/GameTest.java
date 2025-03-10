package ru.netology.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.netology.domain.AlreadyExistsException;
import ru.netology.domain.Game;
import ru.netology.domain.NotRegisteredException;
import ru.netology.domain.Player;

import java.util.ArrayList;
import java.util.Arrays;

public class GameTest {

    Player player1 = new Player("Alonso87", 1, 3);
    Player player2 = new Player("Alex", 2, 7);
    Player player3 = new Player("Romanoff", 3, 5);
    Player player4 = new Player("Dendy", 4, 3);
    Player player5 = new Player("donk666", 5, 10);
    Player player6 = new Player("chopper", 6, 6);
    Player player7 = new Player("sh1ro", 1, 8);
    Player player8 = new Player("Dendy", 7, 8);

    @Test
    void shouldRegister() {
        Game service = new Game();
        try {
            service.register(player1, player2, player3, player4, player5, player6, player7);
        } catch (AlreadyExistsException e) {
            System.out.println(e.getMessage());
        }

        ArrayList<Player> expected = new ArrayList<>(Arrays.asList(player1, player2, player3, player4, player5, player6));
        ArrayList<Player> actual = service.getPlayerList();

        Assertions.assertEquals(expected, actual);
    }

@Test
    void shouldNotRegisterSame() {
        Game service = new Game();

        Assertions.assertThrows(AlreadyExistsException.class,() ->
        {service.register(player1, player2, player3, player4, player5, player6, player7);
        });
    }

    @Test
    void shouldNotRegisterSame2() {
        Game service = new Game();

        Assertions.assertThrows(AlreadyExistsException.class,() ->
        {service.register(player1, player2, player3, player4, player5, player6, player8);
        });
    }

    @ParameterizedTest
    @CsvSource({
            "donk666, chopper, 1",
            "Alonso87, Dendy, 0",
            "Romanoff, Alex, 2"

    })
    void shouldRound(String p1, String p2, int expected) {
        Game service = new Game();

        try {
            service.register(player1, player2, player3, player4, player5, player6, player7);
        } catch (AlreadyExistsException e) {
            System.out.println(e.getMessage());
        }

        int actual = service.round(p1, p2);

        Assertions.assertEquals(actual,expected);
    }

    @ParameterizedTest
    @CsvSource({
            "donk666, lewkAa",
            "zer0, lewkAa"
    })
    void shouldNotRound(String p1, String p2) {
        Game service = new Game();

        service.register(player1, player2, player3, player4, player5, player6);

        Assertions.assertThrows(NotRegisteredException.class,() ->{
            service.round(p1,p2);
        });
    }
}
