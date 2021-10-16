package com.company.setting;

import com.company.model.Game;

public class GameService {



    public void start(Game game) {
        ServiceFox serviceFox = new ServiceFox();
        ServiceGoose serviceGoose = new ServiceGoose();
        DrawGameService drawGame = new DrawGameService();

        PlatformService.Create(game);

        System.out.println("---------------------------");
        System.out.println("--- Начальная Отрисовка ---");
        drawGame.draw(game);
        System.out.println("\n---------------------------\n");
//        int Count_Goose = game.getCount_Goose();

        /* Логика */
        int number_of_moves = 50;

        while (number_of_moves > 0 && game.getCount_Goose() > 8) {
            System.out.println("Ход : " + (51 - number_of_moves));

            if (number_of_moves % 2 == 0){
                System.out.println("  ---- Goose ----    ");
                serviceGoose.logic_Goose(game);
            }
            else {
                System.out.println("  ---- Fox ----    ");
                serviceFox.logic_Fox(game);
            }
            drawGame.draw(game);
            number_of_moves --;

        }


        System.out.println("--- Конец игры ---");
    }
}