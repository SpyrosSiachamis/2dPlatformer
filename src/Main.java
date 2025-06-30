import Entities.Player;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Player player = new Player(0,0,true);
        Controller game = new Controller(1000,420,"Game World", false,true, player);
    }
}