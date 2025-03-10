package ru.netology.domain;

import java.util.ArrayList;

public class Game {

    ArrayList<Player> playerList = new ArrayList<>();

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public void register(Player... newPlayers) {
        for (Player newPlayer : newPlayers) {
            boolean isDuplicate = false;
            for (Player existingPlayer : playerList) {
                if (existingPlayer.getId() == newPlayer.getId() || existingPlayer.getName().equals(newPlayer.getName())) {
                    isDuplicate = true;
                    break;
                }
            }
            if (!isDuplicate) {
                playerList.add(newPlayer);
            } else {
                throw new AlreadyExistsException("Игрок с id=" + newPlayer.getId() + " или name=" + newPlayer.getName() + " уже существует.");
            }
        }
    }

    public Player findByName(String playerName) {
        for (Player registeredPlayer : playerList) {
            if (registeredPlayer.getName().equals(playerName)) {
                return registeredPlayer;
            }
        }
        return null;
    }

    public int round(String playerName1, String playerName2){
        Player player1 = findByName(playerName1);
        Player player2 = findByName(playerName2);

        if  (player1 == null || player2 == null) {
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
