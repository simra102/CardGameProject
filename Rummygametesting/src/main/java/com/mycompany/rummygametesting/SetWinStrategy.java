/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.rummygametesting;

/**
 * A concrete strategy that checks if a player has a valid set.
 */
public class SetWinStrategy implements WinStrategy {
    @Override
    public boolean checkWin(Player player) {
        return player.hasSet(); // Assuming hasSet is implemented in Player
    }
}
