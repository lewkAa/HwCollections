package ru.netology.domain;

import java.util.HashMap;

public class Game {

    private HashMap<String, Player> playerMap = new HashMap<>();

    public HashMap<String, Player> getPlayerMap() {
        return playerMap;
    }

    public void register(Player... newPlayers) {
        for (Player newPlayer : newPlayers) {
            boolean isDuplicate = false;
            for (Player existingPlayer : playerMap.values()) {
                if (existingPlayer.getId() == newPlayer.getId() || existingPlayer.getName().equals(newPlayer.getName())) {
                    isDuplicate = true;
                    break;
                }
            }
            if (isDuplicate) {
                throw new AlreadyExistsException(
                        "Игрок с id=" + newPlayer.getId() + " или name=" + newPlayer.getName() + " уже существует."
                );
            } else {
                playerMap.put(newPlayer.getName(), newPlayer);
            }
        }
    }

    public Player findByName(String playerName) {
        for (Player registeredPlayer : playerMap.values()) {
            if (registeredPlayer.getName().equals(playerName)) {
                return registeredPlayer;
            }
        }
        return null;
    }

    public int round(String playerName1, String playerName2) {
        Player player1 = findByName(playerName1);
        Player player2 = findByName(playerName2);

        if (player1 == null || player2 == null) {
            throw new NotRegisteredException("Один из игроков не найден.");
        }
        if (player1.getStrength() > player2.getStrength()) {
            return 1;
        } else if (player1.getStrength() < player2.getStrength()) {
            return 2;
        } else {
            return 0;
        }
    }
}
