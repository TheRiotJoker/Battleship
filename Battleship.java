/*
Author of the program: Marko Jurkic / Rottweil, Germany
https://github.com/TheRiotJoker
Contact: marko.jurkic23@gmail.com
*/
import java.util.Scanner;
import java.util.Random;
import java.awt.*;
import javax.swing.*;
public class Battleship extends JFrame
{
    public static final int width = 1200;
    public static final int height = 600;
    Image doubleBufferImg;
	Graphics doubleBufferGraphics;
    private static final long serialVersionUID = 1L;
    public static showShips[][] interfPlayer = new showShips[10][10];
    public static showShips[][] interfEnemy = new showShips[10][10];
    public static void main (String[] args) throws InterruptedException
    {
        Scanner scan = new Scanner(System.in);
        Random random = new Random();
        int x = 10;
        int y = 50;
        int counter = 0;
        players[] player = new players[2];
        for(int i = 0; i < player.length; i++)
        {
            player[i] = new players();
            player[i].initShipsToPlace();
            player[i].initPlayingField();
            player[i].initField();
            player[i].initializeShipHp();
        }
        for(int i = 0; i < interfPlayer.length; i++)
        {
            for(int j = 0; j < interfPlayer[i].length; j++)
            {
                interfPlayer[i][j] = new showShips();
                interfPlayer[i][j].setDisplayText(""+counter);
                counter++;
                interfPlayer[i][j].setX(x);
                x = x+interfPlayer[i][j].getWidth();
                interfPlayer[i][j].setY(y);
                interfPlayer[i][j].setRGB(133,218,255);
            }
            y = y+interfPlayer[0][0].getHeight();
            x = 10;
        }
        counter = 0;
        y = 50;
        x = 600;
        for(int i = 0; i < interfEnemy.length; i++)
        {
            for(int j = 0; j < interfEnemy[i].length; j++)
            {
                interfEnemy[i][j] = new showShips();
                interfEnemy[i][j].setDisplayText(""+counter);
                counter++;
                interfEnemy[i][j].setX(x);
                x = x+interfEnemy[i][j].getWidth();
                interfEnemy[i][j].setY(y);
                interfEnemy[i][j].setRGB(133,218,255);
                interfEnemy[i][j].setShipPresent(true);
            }
            y = y+interfEnemy[0][0].getHeight();
            x = 600;
        }
        x = 0;
        y = 0;
        Battleship gui = new Battleship();
        gui.setSize(width,height);
        gui.setVisible(true);
        gui.setResizable(false);
        gui.setTitle("Battleship");
        gui.setDefaultCloseOperation(EXIT_ON_CLOSE);
        for(int main = 0; main < player.length; main++) //main loop
        {
            gui.repaint();
            if(main == 0)
            {
                System.out.println("Player "+(main+1)+" places his ships now.");
                player[main].showPlayingField();
            }
            while(player[main].getIsDone() == false)
            {
                for(int i = player[main].getShipsToPlace().length-1; i > 0; i--)
                {
                    if(player[main].getShipsToPlace()[i] > 0)
                    {
                        player[main].setChosenShip(i);
                        break;
                    }
                }
                if(main == 0)
                {
                    System.out.println("Placing ship with the length of: "+player[main].getChosenShip());
                }
                if(main == 0) //deciding the position
                {
                    do
                    {
                        System.out.println("Please input your desired position: ");
                        player[main].setInput(input(scan.next()));
                        if(player[main].getInput() < 0 || player[main].getInput() >= 100)
                        {
                            System.out.println("Invalid input.");
                        }
                    }while(player[main].getInput() < 0 || player[main].getInput() >= 100);
                    System.out.println("Please input your desired rotation.");
                    player[main].setRotation(scan.next());
                }
                else
                {
                    player[main].setInput(random.nextInt(100));
                    if(random.nextInt(2)+1 == 1)
                    {
                        player[main].setRotation("horizontal");
                    }
                    else
                    {
                        player[main].setRotation("vertical");
                    }
                }
                y = convert(player[main].getInput())[0];
                x = convert(player[main].getInput())[1];
                switch(player[main].getChosenShip()) //placing the ships
                {
                    case 5:
                    if(player[main].getRotation().equalsIgnoreCase("horizontal"))
                    {
                        if(x+4 < 10)
                        {
                            if(player[main].getField()[y][x] == 0 && player[main].getField()[y][x+1] == 0 && player[main].getField()[y][x+2] == 0 && player[main].getField()[y][x+3] == 0 && player[main].getField()[y][x+4] == 0)
                            {
                                player[main].setShipsToPlace(5, player[main].getShipsToPlace()[5]-1);
                                player[main].setField(y, x, 55555);
                                player[main].setField(y, x+1, 55555);
                                player[main].setField(y, x+2, 55555);
                                player[main].setField(y, x+3, 55555);
                                player[main].setField(y, x+4, 55555);
                                if(main == 1)
                                {
                                    interfEnemy[y][x].setTypeOfShip("C5");
                                    interfEnemy[y][x+1].setTypeOfShip("C5");
                                    interfEnemy[y][x+2].setTypeOfShip("C5");
                                    interfEnemy[y][x+3].setTypeOfShip("C5");
                                    interfEnemy[y][x+4].setTypeOfShip("C5");
                                }
                                if(main == 0)
                                {
                                    interfPlayer[y][x].setTypeOfShip("C5");
                                    interfPlayer[y][x+1].setTypeOfShip("C5");
                                    interfPlayer[y][x+2].setTypeOfShip("C5");
                                    interfPlayer[y][x+3].setTypeOfShip("C5");
                                    interfPlayer[y][x+4].setTypeOfShip("C5");
                                    interfPlayer[y][x].setRGB(99,245,66);
                                    interfPlayer[y][x+1].setRGB(99,245,66);
                                    interfPlayer[y][x+2].setRGB(99,245,66);
                                    interfPlayer[y][x+3].setRGB(99,245,66);
                                    interfPlayer[y][x+4].setRGB(99,245,66);
                                    interfPlayer[y][x].setDisplayText("_C5_");
                                    interfPlayer[y][x+1].setDisplayText("_C5_");
                                    interfPlayer[y][x+2].setDisplayText("_C5_");
                                    interfPlayer[y][x+3].setDisplayText("_C5_");
                                    interfPlayer[y][x+4].setDisplayText("_C5_");
                                    interfPlayer[y][x].setShipPresent(true);
                                    interfPlayer[y][x+1].setShipPresent(true);
                                    interfPlayer[y][x+2].setShipPresent(true);
                                    interfPlayer[y][x+3].setShipPresent(true);
                                    interfPlayer[y][x+4].setShipPresent(true);
                                    gui.repaint();
                                    player[main].setPlayingField(y, x, "_C5_");
                                    player[main].setPlayingField(y, x+1, "_C5_");
                                    player[main].setPlayingField(y, x+2, "_C5_");
                                    player[main].setPlayingField(y, x+3, "_C5_");
                                    player[main].setPlayingField(y, x+4, "_C5_");
                                    player[main].showPlayingField();
                                }
                            }
                            else
                            {
                                errorMsg(1, main);
                            }
                        }
                        else
                        {
                            errorMsg(2, main);
                        }
                    }
                    else
                    {
                        if(player[main].getRotation().equalsIgnoreCase("vertical"))
                        {
                            if(y+4 < 10)
                            {
                                if(player[main].getField()[y][x] == 0 && player[main].getField()[y+1][x] == 0 && player[main].getField()[y+2][x] == 0 && player[main].getField()[y+3][x] == 0 && player[main].getField()[y+4][x] == 0)
                                {
                                    player[main].setShipsToPlace(5, player[main].getShipsToPlace()[5]-1);
                                    player[main].setField(y, x, 55555);
                                    player[main].setField(y+1, x, 55555);
                                    player[main].setField(y+2, x, 55555);
                                    player[main].setField(y+3, x, 55555);
                                    player[main].setField(y+4, x, 55555);
                                    if(main == 1)
                                    {
                                        interfEnemy[y][x].setTypeOfShip("C5");
                                        interfEnemy[y+1][x].setTypeOfShip("C5");
                                        interfEnemy[y+2][x].setTypeOfShip("C5");
                                        interfEnemy[y+3][x].setTypeOfShip("C5");
                                        interfEnemy[y+4][x].setTypeOfShip("C5");
                                    }
                                    if(main == 0)
                                    {
                                        interfPlayer[y][x].setTypeOfShip("C5");
                                        interfPlayer[y+1][x].setTypeOfShip("C5");
                                        interfPlayer[y+2][x].setTypeOfShip("C5");
                                        interfPlayer[y+3][x].setTypeOfShip("C5");
                                        interfPlayer[y+4][x].setTypeOfShip("C5");
                                        interfPlayer[y][x].setRGB(99,245,66);
                                        interfPlayer[y+1][x].setRGB(99,245,66);
                                        interfPlayer[y+2][x].setRGB(99,245,66);
                                        interfPlayer[y+3][x].setRGB(99,245,66);
                                        interfPlayer[y+4][x].setRGB(99,245,66);
                                        interfPlayer[y][x].setDisplayText("_C5_");
                                        interfPlayer[y+1][x].setDisplayText("_C5_");
                                        interfPlayer[y+2][x].setDisplayText("_C5_");
                                        interfPlayer[y+3][x].setDisplayText("_C5_");
                                        interfPlayer[y+4][x].setDisplayText("_C5_");
                                        interfPlayer[y][x].setShipPresent(true);
                                        interfPlayer[y+1][x].setShipPresent(true);
                                        interfPlayer[y+2][x].setShipPresent(true);
                                        interfPlayer[y+3][x].setShipPresent(true);
                                        interfPlayer[y+4][x].setShipPresent(true);
                                        gui.repaint();
                                        player[main].setPlayingField(y, x, "_C5_");
                                        player[main].setPlayingField(y+1, x, "_C5_");
                                        player[main].setPlayingField(y+2, x, "_C5_");
                                        player[main].setPlayingField(y+3, x, "_C5_");
                                        player[main].setPlayingField(y+4, x, "_C5_");
                                        player[main].showPlayingField();
                                    }
                                }
                                else
                                {
                                    errorMsg(1, main);
                                }
                            }
                            else
                            {
                                errorMsg(2, main);
                            }
                        }
                        else
                        {
                            errorMsg(3, main);
                        }
                    }
                    break;
                    case 4:
                    if(player[main].getRotation().equalsIgnoreCase("horizontal"))
                    {
                        if(x+3 < 10)
                        {
                            if(player[main].getField()[y][x] == 0 && player[main].getField()[y][x+1] == 0 && player[main].getField()[y][x+2] == 0 && player[main].getField()[y][x+3] == 0)
                            {
                                player[main].setShipsToPlace(4, player[main].getShipsToPlace()[4]-1);
                                player[main].setField(y, x, 4444);
                                player[main].setField(y, x+1, 4444);
                                player[main].setField(y, x+2, 4444);
                                player[main].setField(y, x+3, 4444);
                                if(main == 1)
                                {
                                    interfEnemy[y][x].setTypeOfShip("B4");
                                    interfEnemy[y][x+1].setTypeOfShip("B4");
                                    interfEnemy[y][x+2].setTypeOfShip("B4");
                                    interfEnemy[y][x+3].setTypeOfShip("B4");
                                }
                                if(main == 0)
                                {
                                    interfPlayer[y][x].setTypeOfShip("B4");
                                    interfPlayer[y][x+1].setTypeOfShip("B4");
                                    interfPlayer[y][x+2].setTypeOfShip("B4");
                                    interfPlayer[y][x+3].setTypeOfShip("B4");
                                    interfPlayer[y][x].setRGB(99,245,66);
                                    interfPlayer[y][x+1].setRGB(99,245,66);
                                    interfPlayer[y][x+2].setRGB(99,245,66);
                                    interfPlayer[y][x+3].setRGB(99,245,66);
                                    interfPlayer[y][x].setDisplayText("_B4_");
                                    interfPlayer[y][x+1].setDisplayText("_B4_");
                                    interfPlayer[y][x+2].setDisplayText("_B4_");
                                    interfPlayer[y][x+3].setDisplayText("_B4_");
                                    interfPlayer[y][x].setShipPresent(true);
                                    interfPlayer[y][x+1].setShipPresent(true);
                                    interfPlayer[y][x+2].setShipPresent(true);
                                    interfPlayer[y][x+3].setShipPresent(true);
                                    gui.repaint();
                                    player[main].setPlayingField(y, x, "_B4_");
                                    player[main].setPlayingField(y, x+1, "_B4_");
                                    player[main].setPlayingField(y, x+2, "_B4_");
                                    player[main].setPlayingField(y, x+3, "_B4_");
                                    player[main].showPlayingField();
                                }
                            }
                            else
                            {
                                errorMsg(1, main);
                            }
                        }
                        else
                        {
                            errorMsg(2, main);
                        }
                    }
                    else
                    {
                        if(player[main].getRotation().equalsIgnoreCase("vertical"))
                        {
                            if(y+3 < 10)
                            {
                                if(player[main].getField()[y][x] == 0 && player[main].getField()[y+1][x] == 0 && player[main].getField()[y+2][x] == 0 && player[main].getField()[y+3][x] == 0)
                                {
                                    player[main].setShipsToPlace(4, player[main].getShipsToPlace()[4]-1);
                                    player[main].setField(y, x, 4444);
                                    player[main].setField(y+1, x, 4444);
                                    player[main].setField(y+2, x, 4444);
                                    player[main].setField(y+3, x, 4444);
                                    if(main == 1)
                                    {
                                        interfEnemy[y][x].setTypeOfShip("B4");
                                        interfEnemy[y+1][x].setTypeOfShip("B4");
                                        interfEnemy[y+2][x].setTypeOfShip("B4");
                                        interfEnemy[y+3][x].setTypeOfShip("B4");
                                    }   
                                    if(main == 0)
                                    {
                                        interfPlayer[y][x].setTypeOfShip("B4");
                                        interfPlayer[y+1][x].setTypeOfShip("B4");
                                        interfPlayer[y+2][x].setTypeOfShip("B4");
                                        interfPlayer[y+3][x].setTypeOfShip("B4");
                                        interfPlayer[y][x].setRGB(99,245,66);
                                        interfPlayer[y+1][x].setRGB(99,245,66);
                                        interfPlayer[y+2][x].setRGB(99,245,66);
                                        interfPlayer[y+3][x].setRGB(99,245,66);
                                        interfPlayer[y][x].setDisplayText("_B4_");
                                        interfPlayer[y+1][x].setDisplayText("_B4_");
                                        interfPlayer[y+2][x].setDisplayText("_B4_");
                                        interfPlayer[y+3][x].setDisplayText("_B4_");
                                        interfPlayer[y][x].setShipPresent(true);
                                        interfPlayer[y+1][x].setShipPresent(true);
                                        interfPlayer[y+2][x].setShipPresent(true);
                                        interfPlayer[y+3][x].setShipPresent(true);
                                        gui.repaint();
                                        player[main].setPlayingField(y, x, "_B4_");
                                        player[main].setPlayingField(y+1, x, "_B4_");
                                        player[main].setPlayingField(y+2, x, "_B4_");
                                        player[main].setPlayingField(y+3, x, "_B4_");
                                        player[main].showPlayingField();
                                    }
                                }
                                else
                                {
                                    errorMsg(1, main);
                                }
                            }
                            else
                            {
                                errorMsg(2, main);
                            }
                        }
                        else
                        {
                            errorMsg(3, main);
                        }
                    }
                    break;
                    case 3:
                    if(player[main].getRotation().equalsIgnoreCase("horizontal"))
                    {
                        if(x+2 < 10)
                        {
                            if(player[main].getField()[y][x] == 0 && player[main].getField()[y][x+1] == 0 && player[main].getField()[y][x+2] == 0)
                            {
                                player[main].setShipsToPlace(3, player[main].getShipsToPlace()[3]-1);
                                if(main == 1)
                                {
                                    if(player[main].getCPlaced() == false)
                                    {
                                        player[main].setField(y, x, 333);
                                        player[main].setField(y, x+1, 333);
                                        player[main].setField(y, x+2, 333);
                                        interfEnemy[y][x].setTypeOfShip("C3");
                                        interfEnemy[y][x+1].setTypeOfShip("C3");
                                        interfEnemy[y][x+2].setTypeOfShip("C3");
                                        player[1].setCPlaced(true);
                                    }
                                    else
                                    {
                                        player[main].setField(y, x, 3333);
                                        player[main].setField(y, x+1, 3333);
                                        player[main].setField(y, x+2, 3333);
                                        interfEnemy[y][x].setTypeOfShip("S3");
                                        interfEnemy[y][x+1].setTypeOfShip("S3");
                                        interfEnemy[y][x+2].setTypeOfShip("S3");
                                    }
                                }
                                if(main == 0)
                                {
                                    if(player[main].getCPlaced() == false)
                                    {
                                        player[main].setField(y, x, 333);
                                        player[main].setField(y, x+1, 333);
                                        player[main].setField(y, x+2, 333);
                                        interfPlayer[y][x].setTypeOfShip("C3");
                                        interfPlayer[y][x+1].setTypeOfShip("C3");
                                        interfPlayer[y][x+2].setTypeOfShip("C3");
                                        player[main].setCPlaced(true);
                                        
                                    }
                                    else
                                    {
                                        player[main].setField(y, x, 3333);
                                        player[main].setField(y, x+1, 3333);
                                        player[main].setField(y, x+2, 3333);
                                        interfPlayer[y][x].setTypeOfShip("S3");
                                        interfPlayer[y][x+1].setTypeOfShip("S3");
                                        interfPlayer[y][x+2].setTypeOfShip("S3");
                                    }
                                    interfPlayer[y][x].setRGB(99,245,66);
                                    interfPlayer[y][x+1].setRGB(99,245,66);
                                    interfPlayer[y][x+2].setRGB(99,245,66);
                                    interfPlayer[y][x].setDisplayText("_C3_");
                                    interfPlayer[y][x+1].setDisplayText("_C3_");
                                    interfPlayer[y][x+2].setDisplayText("_C3_");
                                    interfPlayer[y][x].setShipPresent(true);
                                    interfPlayer[y][x+1].setShipPresent(true);
                                    interfPlayer[y][x+2].setShipPresent(true);
                                    gui.repaint();
                                    player[main].setPlayingField(y, x, "_C3_");
                                    player[main].setPlayingField(y, x+1, "_C3_");
                                    player[main].setPlayingField(y, x+2, "_C3_");
                                    player[main].showPlayingField();
                                }
                            }
                            else
                            {
                                errorMsg(1, main);
                            }
                        }
                        else
                        {
                            errorMsg(2, main);
                        }
                    }
                    else
                    {
                        if(player[main].getRotation().equalsIgnoreCase("vertical"))
                        {
                            if(y+2 < 10)
                            {
                                if(player[main].getField()[y][x] == 0 && player[main].getField()[y+1][x] == 0 && player[main].getField()[y+2][x] == 0)
                                {
                                    player[main].setShipsToPlace(3, player[main].getShipsToPlace()[3]-1);
                                    if(main == 1)
                                    {
                                        if(player[main].getCPlaced() == false)
                                        {
                                            player[main].setField(y, x, 333);
                                            player[main].setField(y+1, x, 333);
                                            player[main].setField(y+2, x, 333);
                                            interfEnemy[y][x].setTypeOfShip("C3");
                                            interfEnemy[y+1][x].setTypeOfShip("C3");
                                            interfEnemy[y+2][x].setTypeOfShip("C3");
                                            player[main].setCPlaced(true);
                                        }
                                        else
                                        {
                                            player[main].setField(y, x, 3333);
                                            player[main].setField(y+1, x, 3333);
                                            player[main].setField(y+2, x, 3333);
                                            interfEnemy[y][x].setTypeOfShip("S3");
                                            interfEnemy[y+1][x].setTypeOfShip("S3");
                                            interfEnemy[y+2][x].setTypeOfShip("S3");
                                        }
                                    }
                                    if(main == 0)
                                    {
                                        if(player[main].getCPlaced() == false)
                                        {
                                            player[main].setField(y, x, 333);
                                            player[main].setField(y+1, x, 333);
                                            player[main].setField(y+2, x, 333);
                                            interfPlayer[y][x].setTypeOfShip("C3");
                                            interfPlayer[y+1][x].setTypeOfShip("C3");
                                            interfPlayer[y+2][x].setTypeOfShip("C3");
                                            player[main].setCPlaced(true);
                                        }
                                        else
                                        {
                                            player[main].setField(y, x, 3333);
                                            player[main].setField(y+1, x, 3333);
                                            player[main].setField(y+2, x, 3333);
                                            interfPlayer[y][x].setTypeOfShip("S3");
                                            interfPlayer[y+1][x].setTypeOfShip("S3");
                                            interfPlayer[y+2][x].setTypeOfShip("S3");
                                        }
                                        interfPlayer[y][x].setRGB(99,245,66);
                                        interfPlayer[y+1][x].setRGB(99,245,66);
                                        interfPlayer[y+2][x].setRGB(99,245,66);
                                        interfPlayer[y][x].setDisplayText("_C3_");
                                        interfPlayer[y+1][x].setDisplayText("_C3_");
                                        interfPlayer[y+2][x].setDisplayText("_C3_");
                                        interfPlayer[y][x].setShipPresent(true);
                                        interfPlayer[y+1][x].setShipPresent(true);
                                        interfPlayer[y+2][x].setShipPresent(true);
                                        gui.repaint();
                                        player[main].setPlayingField(y, x, "_C3_");
                                        player[main].setPlayingField(y+1, x, "_C3_");
                                        player[main].setPlayingField(y+2, x, "_C3_");
                                        player[main].showPlayingField();
                                    }
                                }
                                else
                                {
                                    errorMsg(1, main);
                                }
                            }
                            else
                            {
                                errorMsg(2, main);
                            }
                        }
                        else
                        {
                            errorMsg(3, main);
                        }
                    }
                    break;
                    case 2:
                    if(player[main].getRotation().equalsIgnoreCase("horizontal"))
                    {
                        if(x+1 < 10)
                        {
                            if(player[main].getField()[y][x] == 0 && player[main].getField()[y][x+1] == 0)
                            {
                                player[main].setShipsToPlace(2, player[main].getShipsToPlace()[2]-1);
                                player[main].setField(y, x, 22);
                                player[main].setField(y, x+1, 22);
                                if(main == 1)
                                {
                                    interfEnemy[y][x].setTypeOfShip("D2");
                                    interfEnemy[y][x+1].setTypeOfShip("D2");
                                }
                                if(main == 0)
                                {
                                    interfPlayer[y][x].setTypeOfShip("D2");
                                    interfPlayer[y][x+1].setTypeOfShip("D2");
                                    interfPlayer[y][x].setRGB(99,245,66);
                                    interfPlayer[y][x+1].setRGB(99,245,66);
                                    interfPlayer[y][x].setDisplayText("_D2_");
                                    interfPlayer[y][x+1].setDisplayText("_D2_");
                                    interfPlayer[y][x].setShipPresent(true);
                                    interfPlayer[y][x+1].setShipPresent(true);
                                    gui.repaint();
                                    player[main].setPlayingField(y, x, "_D2_");
                                    player[main].setPlayingField(y, x+1, "_D2_");
                                    player[main].showPlayingField();
                                }
                                player[main].setIsDone(true);
                            }
                            else
                            {
                                errorMsg(1, main);
                            }
                        }
                        else
                        {
                            errorMsg(2, main);
                        }
                    }
                    else
                    {
                        if(player[main].getRotation().equalsIgnoreCase("vertical"))
                        {
                            if(y+1 < 10)
                            {
                                if(player[main].getField()[y][x] == 0 && player[main].getField()[y+1][x] == 0)
                                {
                                    player[main].setShipsToPlace(2, player[main].getShipsToPlace()[2]-1);
                                    player[main].setField(y, x, 22);
                                    player[main].setField(y+1, x, 22);
                                    if(main == 1)
                                    {
                                        interfEnemy[y][x].setTypeOfShip("D2");
                                        interfEnemy[y+1][x].setTypeOfShip("D2");
                                    }
                                    if(main == 0)
                                    {
                                        interfPlayer[y][x].setTypeOfShip("D2");
                                        interfPlayer[y+1][x].setTypeOfShip("D2");
                                        interfPlayer[y][x].setRGB(99,245,66);
                                        interfPlayer[y+1][x].setRGB(99,245,66);
                                        interfPlayer[y][x].setDisplayText("_D2_");
                                        interfPlayer[y+1][x].setDisplayText("_D2_");
                                        interfPlayer[y][x].setShipPresent(true);
                                        interfPlayer[y+1][x].setShipPresent(true);
                                        gui.repaint();
                                        player[main].setPlayingField(y, x, "_D2_");
                                        player[main].setPlayingField(y+1, x, "_D2_");
                                        player[main].showPlayingField();
                                    }
                                    player[main].setIsDone(true);
                                }
                                else
                                {
                                    errorMsg(1, main);
                                }
                            }
                            else
                            {
                                errorMsg(2, main);
                            }
                        }
                        else
                        {
                            errorMsg(3, main);
                        }
                    }
                    break;
                }
            }
        }
        System.out.println("Phase 1 complete.");
        while(player[0].getPiecesAlive() > 0 || player[1].getPiecesAlive() > 0)
        {
            System.out.println("///////////////////////////////////////////////////////");
            System.out.println("///////////////////CPU BOARD//////////////////////////");
            System.out.println("///////////////////CPU BOARD//////////////////////////");
            System.out.println("///////////////////CPU BOARD//////////////////////////");
            System.out.println("///////////////////////////////////////////////////////");
            player[1].showPlayingField();
            System.out.print("It's your turn to shoot: ");
            do
            {
                do
                {
                    player[0].setShoot(input(scan.next()));
                    if(player[0].getShoot() >= 100 || player[0].getShoot() < 0)
                    {
                        if(player[0].getShoot() >= 100)
                        {
                            System.out.println("Invalid input. Can't be higher than or equal to 100");
                        }
                        else
                        {
                            System.out.println("Invalid input. Can't be lower than 0");
                        }
                    }
                }while(player[0].getShoot() >= 100 || player[0].getShoot() < 0);
                y = convert(player[0].getShoot())[0];
                x = convert(player[0].getShoot())[1];
                if(player[1].getPlayingField()[y][x].equalsIgnoreCase("HIT") || player[1].getPlayingField()[y][x].equalsIgnoreCase("MISS"))
                {
                    System.out.println("You've already shot here.");
                }
                if(player[0].getShoot() < 0 || player[0].getShoot() >= 100)
                {
                    System.out.println("Invalid input.");
                }
            }while(player[1].getPlayingField()[y][x].equalsIgnoreCase("HIT") || player[1].getPlayingField()[y][x].equalsIgnoreCase("MISS"));
            interfEnemy[y][x].setShipPresent(false);
            if(player[1].getField()[y][x] > 0)
            {
                System.out.println("Direct hit!");
                player[1].setPiecesAlive(player[1].getPiecesAlive()-1);
                player[1].setPlayingField(y, x, "HIT");
                interfEnemy[y][x].setRGB(255,100,120);
                interfEnemy[y][x].setDisplayText("HIT");
                switch(player[1].getField()[y][x])
                {
                    case 55555:
                    player[1].decrementShipHp(5);
                    if(player[1].getShipHp(5) == 0)
                    {
                        for(int i = 0; i < interfEnemy.length; i++)
                        {
                            for(int j = 0; j < interfEnemy[i].length; j++)
                            {
                                if(interfEnemy[i][j].getTypeOfShip().equalsIgnoreCase("C5"))
                                {
                                    interfEnemy[i][j].setRGB(180,40,40);
                                    interfEnemy[i][j].setDisplayText("");
                                }
                            }
                        }
                    }
                    break;
                    case 4444:
                    player[1].decrementShipHp(4);
                    if(player[1].getShipHp(4) == 0)
                    {
                        for(int i = 0; i < interfEnemy.length; i++)
                        {
                            for(int j = 0; j < interfEnemy[i].length; j++)
                            {
                                if(interfEnemy[i][j].getTypeOfShip().equalsIgnoreCase("B4"))
                                {
                                    interfEnemy[i][j].setRGB(180,40,40);
                                    interfEnemy[i][j].setDisplayText("");
                                }
                            }
                        }
                    }
                    break;
                    case 3333:
                    player[1].decrementShipHp(3);
                    if(player[1].getShipHp(3) == 0)
                    {
                        for(int i = 0; i < interfEnemy.length; i++)
                        {
                            for(int j = 0; j < interfEnemy[i].length; j++)
                            {
                                if(interfEnemy[i][j].getTypeOfShip().equalsIgnoreCase("S3"))
                                {
                                    interfEnemy[i][j].setRGB(180,40,40);
                                    interfEnemy[i][j].setDisplayText("");
                                }
                            }
                        }
                    }
                    break;
                    case 333:
                    player[1].decrementShipHp(2);
                    if(player[1].getShipHp(2) == 0)
                    {
                        for(int i = 0; i < interfEnemy.length; i++)
                        {
                            for(int j = 0; j < interfEnemy[i].length; j++)
                            {
                                if(interfEnemy[i][j].getTypeOfShip().equalsIgnoreCase("C3"))
                                {
                                    interfEnemy[i][j].setRGB(180,40,40);
                                    interfEnemy[i][j].setDisplayText("");
                                }
                            }
                        }
                    }
                    break;
                    case 22:
                    player[1].decrementShipHp(1);
                    if(player[1].getShipHp(1) == 0)
                    {
                        for(int i = 0; i < interfEnemy.length; i++)
                        {
                            for(int j = 0; j < interfEnemy[i].length; j++)
                            {
                                if(interfEnemy[i][j].getTypeOfShip().equalsIgnoreCase("D2"))
                                {
                                    interfEnemy[i][j].setRGB(180,40,40);
                                    interfEnemy[i][j].setDisplayText("");
                                }
                            }
                        }
                    }
                    break;
                }
                player[1].setField(y, x, 0);
                gui.repaint();
                if(player[1].getPiecesAlive() == 0)
                {
                    break;
                }
            }
            else
            {
                System.out.println("Miss.");
                player[1].setPlayingField(y, x, "MISS");
                interfEnemy[y][x].setRGB(200,100,120);
                interfEnemy[y][x].setDisplayText("MISS");
                gui.repaint();
            }
            System.out.println("///////////////////////////////////////////////////////");
            System.out.println("///////////////////YOUR BOARD/////////////////////////");
            System.out.println("///////////////////YOUR BOARD/////////////////////////");
            System.out.println("///////////////////YOUR BOARD/////////////////////////");
            System.out.println("///////////////////////////////////////////////////////");
            player[0].showPlayingField();
            do
            {
                player[1].setShoot(random.nextInt(100));
                y = convert(player[1].getShoot())[0];
                x = convert(player[1].getShoot())[1];
            }while(player[0].getPlayingField()[y][x].equalsIgnoreCase("HIT") || player[0].getPlayingField()[y][x].equalsIgnoreCase("Miss"));
            if(player[0].getField()[y][x] > 0)
            {
                System.out.println("We've been hit!");
                player[0].setPiecesAlive(player[0].getPiecesAlive()-1);
                player[0].setPlayingField(y, x, "HIT");
                interfPlayer[y][x].setRGB(255,100,120);
                interfPlayer[y][x].setDisplayText("HIT");
                switch(player[0].getField()[y][x])
                {
                    case 55555:
                    player[0].decrementShipHp(5);
                    if(player[0].getShipHp(5) == 0)
                    {
                        for(int i = 0; i < interfPlayer.length; i++)
                        {
                            for(int j = 0; j < interfPlayer[i].length; j++)
                            {
                                if(interfPlayer[i][j].getTypeOfShip().equalsIgnoreCase("C5"))
                                {
                                    interfPlayer[i][j].setRGB(180,40,40);
                                    interfPlayer[i][j].setDisplayText("");
                                }
                            }
                        }
                    }
                    break;
                    case 4444:
                    player[0].decrementShipHp(4);
                    if(player[0].getShipHp(4) == 0)
                    {
                        for(int i = 0; i < interfPlayer.length; i++)
                        {
                            for(int j = 0; j < interfPlayer[i].length; j++)
                            {
                                if(interfPlayer[i][j].getTypeOfShip().equalsIgnoreCase("B4"))
                                {
                                    interfPlayer[i][j].setRGB(180,40,40);
                                    interfPlayer[i][j].setDisplayText("");
                                }
                            }
                        }
                    }
                    break;
                    case 3333:
                    player[0].decrementShipHp(3);
                    if(player[0].getShipHp(3) == 0)
                    {
                        for(int i = 0; i < interfPlayer.length; i++)
                        {
                            for(int j = 0; j < interfPlayer[i].length; j++)
                            {
                                if(interfPlayer[i][j].getTypeOfShip().equalsIgnoreCase("S3"))
                                {
                                    interfPlayer[i][j].setRGB(180,40,40);
                                    interfPlayer[i][j].setDisplayText("");
                                }
                            }
                        }
                    }
                    break;
                    case 333:
                    player[0].decrementShipHp(2);
                    if(player[0].getShipHp(2) == 0)
                    {
                        for(int i = 0; i < interfPlayer.length; i++)
                        {
                            for(int j = 0; j < interfPlayer[i].length; j++)
                            {
                                if(interfPlayer[i][j].getTypeOfShip().equalsIgnoreCase("C3"))
                                {
                                    interfPlayer[i][j].setRGB(180,40,40);
                                    interfPlayer[i][j].setDisplayText("");
                                    interfPlayer[i][j].setTypeOfShip("");
                                }
                            }
                        }
                    }
                    break;
                    case 22:
                    player[0].decrementShipHp(1);
                    if(player[0].getShipHp(1) == 0)
                    {
                        for(int i = 0; i < interfPlayer.length; i++)
                        {
                            for(int j = 0; j < interfPlayer[i].length; j++)
                            {
                                if(interfPlayer[i][j].getTypeOfShip().equalsIgnoreCase("D2"))
                                {
                                    interfPlayer[i][j].setRGB(180,40,40);
                                    interfPlayer[i][j].setDisplayText("");
                                }
                            }
                        }
                    }
                    break;
                }
                player[0].setField(y, x, 0);
                gui.repaint();
                if(player[0].getPiecesAlive() == 0)
                {
                    break;
                }
            }
            else
            {
                System.out.println("The enemy missed.");
                player[0].setPlayingField(y, x, "MISS");
                interfPlayer[y][x].setRGB(200,100,120);
                interfPlayer[y][x].setShipPresent(true);
                interfPlayer[y][x].setDisplayText("MISS");
                gui.repaint();
            }
        }
        if(player[0].getPiecesAlive() == 0)
        {
            System.out.println("The CPU has won.");
            interfEnemy[0][0].setHasWon(true);
        }
        else
        {
            System.out.println("You won!");
            interfPlayer[0][0].setHasWon(true);
        }
        gui.repaint();
        scan.close();
    }
    public static int[] convert(int input)
    {
        int[] change = new int[2];
        change[0] = 0;
        change[1] = 0;
        int temp = input;
        if(input == 0)
        {
            change[0] = 0;
            change[1] = 0;
        }
        else
        {
            change[0] = temp/10;
            temp = temp/10;
            change[1] = input - (temp*10);
        }
        return change;
    }
    static void errorMsg(int x, int on)
    {
        if(on == 0)
        {
            switch(x)
            {
                case 1:
                System.out.println("Place already taken.");
                break;
                case 2:
                System.out.println("Out of bounds.");
                break;
                case 3:
                System.out.println("Wrong input");
                break;
            }
        }
    }
    public static int input(String input)
    {
        boolean passed = false;
        Scanner scan = new Scanner(System.in);
        while(passed == false)
        {
            try
            {
                Integer.parseInt(input);
                passed = true;
            }
            catch(NumberFormatException e)
            {
                passed = false;
            }
            if(passed == false)
            {
                System.out.println("Wrong input. Please try again.");
                input = scan.next();
            }
        }
        return Integer.parseInt(input);
    }
    public void paintComponent(Graphics g)
    {
        Color background = new Color(169,169,169);
        Color fieldColor = new Color(0,0,0);
        Color black = new Color(0,0,0);
        Font shipFont;
        shipFont = new Font("Arial", Font.PLAIN, 15);
        Font endFont = new Font("Times New Roman", Font.BOLD, 20);
        g.setFont(shipFont);
        if(interfEnemy[0][0].getHasWon() == true || interfPlayer[0][0].getHasWon() == true)
        {
            if(interfEnemy[0][0].getHasWon() == true)
            {
                g.setColor(Color.RED);
                g.fillRect(0,0,1200,600);
            }
            else
            {
                g.setColor(Color.GREEN);
                g.fillRect(0,0,1200,600);
            }
        }
        else
        {
            g.setColor(background);
            g.fillRect(0,0,1200,600);
        }
        for(int i = 0; i < interfPlayer.length; i++ )
        {
            for(int j = 0; j < interfPlayer[i].length; j++)
            {
                if(interfEnemy[0][0].getHasWon() == true || interfPlayer[0][0].getHasWon() == true)
                {
                    g.setFont(endFont);
                    if(interfEnemy[0][0].getHasWon() == true)
                    {
                        g.setColor(black);
                        g.drawString("The CPU has won! ",477,575);
                        
                    }
                    else
                    {
                        g.setColor(black);
                        g.drawString("You have won! ",477,575);
                    }
                    g.setFont(shipFont);
                }
                fieldColor = new Color(interfPlayer[i][j].getR(), interfPlayer[i][j].getG(), interfPlayer[i][j].getB());
                g.setColor(fieldColor);
                g.fillRect(interfPlayer[i][j].getX(),interfPlayer[i][j].getY(), interfPlayer[i][j].getWidth(), interfPlayer[i][j].getHeight());
                g.setColor(black);
                g.drawRect(interfPlayer[i][j].getX(), interfPlayer[i][j].getY(), interfPlayer[i][j].getWidth(),interfPlayer[i][j].getHeight());
                fieldColor = new Color(interfEnemy[i][j].getR(),interfEnemy[i][j].getG(), interfEnemy[i][j].getB());
                g.setColor(fieldColor);
                g.fillRect(interfEnemy[i][j].getX(), interfEnemy[i][j].getY(), interfEnemy[i][j].getWidth(), interfEnemy[i][j].getHeight());
                g.setColor(black);
                g.drawRect(interfEnemy[i][j].getX(), interfEnemy[i][j].getY(), interfEnemy[i][j].getWidth(), interfEnemy[i][j].getHeight());
                if(interfPlayer[i][j].getDisplayText().equalsIgnoreCase("") || interfEnemy[i][j].getDisplayText().equalsIgnoreCase(""))
                {
                    if(interfPlayer[i][j].getDisplayText().equalsIgnoreCase(""))
                    {
                        g.setColor(black);
                        g.drawLine(interfPlayer[i][j].getX(), interfPlayer[i][j].getY(), interfPlayer[i][j].getX()+interfPlayer[i][j].getWidth(), interfPlayer[i][j].getY()+interfPlayer[i][j].getHeight());
                        g.drawLine(interfPlayer[i][j].getX()+interfPlayer[i][j].getWidth(), interfPlayer[i][j].getY(), interfPlayer[i][j].getX(), interfPlayer[i][j].getY()+interfPlayer[i][j].getHeight());
                    }
                    else
                    {
                        if(interfEnemy[i][j].getDisplayText().equalsIgnoreCase(""));
                        {
                            g.setColor(black);
                            g.drawLine(interfEnemy[i][j].getX(), interfEnemy[i][j].getY(), interfEnemy[i][j].getX()+interfEnemy[i][j].getWidth(), interfEnemy[i][j].getY()+interfEnemy[i][j].getHeight());
                            g.drawLine(interfEnemy[i][j].getX()+interfEnemy[i][j].getWidth(), interfEnemy[i][j].getY(), interfEnemy[i][j].getX(), interfEnemy[i][j].getY()+interfEnemy[i][j].getHeight());
                        }
                    }
                }
                if(interfPlayer[i][j].getShipPresent() == true)
                {
                    g.drawString(interfPlayer[i][j].getDisplayText(), interfPlayer[i][j].getX()+5, interfPlayer[i][j].getY()+25);
                }
                else
                {
                    g.drawString(interfPlayer[i][j].getDisplayText(),interfPlayer[i][j].getX()+15,interfPlayer[i][j].getY()+15);
                }
                if(interfEnemy[i][j].getShipPresent() == true)
                {
                    g.drawString(interfEnemy[i][j].getDisplayText()+ "",interfEnemy[i][j].getX()+15, interfEnemy[i][j].getY()+15);
                }
                else
                {
                    g.drawString(interfEnemy[i][j].getDisplayText(),interfEnemy[i][j].getX()+5, interfEnemy[i][j].getY()+25);
                }
            }
        }
    }
    public void paint(Graphics g)
    {
        doubleBufferImg = createImage(width, height);
		doubleBufferGraphics = doubleBufferImg.getGraphics();
		paintComponent(doubleBufferGraphics);
		g.drawImage(doubleBufferImg,0,0,this);
    }
}
class showShips
{
    //int yFactor = 0;
    //int xFactor = 0;
    boolean hasWon = false;
    int x = 0;
    int y = 0;
    final int width = 50;
    final int height = 50;
    int r = 133;
    int g = 218;
    int b = 255;
    boolean shipPresent = false;
    String DisplayText = "";
    String typeOfShip = "0";
    void setTypeOfShip(String typeOfShip)
    {
        this.typeOfShip = typeOfShip;
    }
    String getTypeOfShip()
    {
        return typeOfShip;
    }
    boolean getHasWon()
    {
        return hasWon;
    }
    void setHasWon(boolean hasWon)
    {
        this.hasWon = hasWon;
    }
    boolean getShipPresent()
    {
        return shipPresent;
    }
    void setShipPresent(boolean shipPresent)
    {
        this.shipPresent = shipPresent;
    }
    void setDisplayText(String DisplayText)
    {
        this.DisplayText = DisplayText;
    }
    String getDisplayText()
    {
        return DisplayText;
    }
    void setRGB(int r, int g, int b)
    {
        this. r = r;
        this.g = g;
        this.b = b;
    }
    int getR()
    {
        return r;
    }
    int getG()
    {
        return g;
    }
    int getB()
    {
        return b;
    }
    void setX(int x)
    {
        this.x = x;
    }
    int getX()
    {
        return x;
    }
    void setY(int y)
    {
        this.y = y;
    }
    int getY()
    {
        return y;
    }
    int getHeight()
    {
        return height;
    }
    int getWidth()
    {
        return width;
    }
}
class players
{
    int shoot = 0;
    int input = 0;
    int ships = 5;
    int piecesAlive = 17;
    int shots = 0;
    int[] shipsToPlace = new int[6];
    int chosenShip = 0;
    String rotation = "";
    String[][] playingField = new String[10][10];
    int[][] field = new int[10][10];
    int counter = 0;
    boolean isDone = false;
    int[] shipHp = new int[6];
    boolean cPlaced = false;
    boolean getCPlaced()
    {
        return cPlaced;
    }
    void setCPlaced(boolean cPlaced)
    {
        this.cPlaced = cPlaced;
    }
    void setShipHp(int index, int value)
    {
        shipHp[index] = value;
    }
    void decrementShipHp(int index)
    {
        shipHp[index]--;
    }
    int getShipHp(int index)
    {
        return shipHp[index];
    }
    void initializeShipHp()
    {
        shipHp[0] = 0;
        shipHp[5] = 5;
        shipHp[4] = 4;
        shipHp[3] = 3;
        shipHp[2] = 3;
        shipHp[1] = 2;
    }
    void setShoot(int shoot)
    {
        this.shoot = shoot;
    }
    int getShoot()
    {
        return shoot;
    }
    void setIsDone(boolean isDone)
    {
        this.isDone = isDone;
    }
    boolean getIsDone()
    {
        return isDone;
    }
    void showPlayingField()
    {
            System.out.println("");
            for(int i = 0; i < playingField.length; i++)
            {
                for(int j = 0; j < playingField[i].length; j++)
                {
                    System.out.print(playingField[i][j]+" \t ");
                }
                System.out.println("");
                System.out.println("");
            }  
    }
    void initField()
    {
        for(int i = 0; i < field.length; i++)
        {
            for(int j = 0; j < field[i].length; j++)
            {
                field[i][j] = 0;
            }
        }
    }
    void setField(int y, int x, int val)
    {
        field[y][x] = val;
    }
    int[][] getField()
    {
        return field;
    }
    void initPlayingField()
    {
        for(int i = 0; i < playingField.length; i++)
        {
            for(int j = 0; j < playingField[i].length; j++)
            {
                playingField[i][j] = ""+counter;
                counter++;
            }
        }
    }
    String[][] getPlayingField()
    {
        return playingField;
    }
    void setPlayingField(int y, int x, String playingFieldEdit)
    {
        playingField[y][x] = playingFieldEdit;
    }
    void setRotation(String rotation)
    {
        this.rotation = rotation;
    }
    String getRotation()
    {
        return rotation;
    }
    void setInput(int input)
    {
        this.input = input;
    }
    int getInput()
    {
        return input;
    }
    void initShipsToPlace()
    {
        for(int i = 0; i < shipsToPlace.length; i++)
        {
            shipsToPlace[i] = 0;
        }
        shipsToPlace[5] = 1;
        shipsToPlace[4] = 1;
        shipsToPlace[3] = 2;
        shipsToPlace[2] = 1;
    }
    int[] getShipsToPlace()
    {
        return shipsToPlace;
    }
    void setShipsToPlace(int index, int change)
    {
        this.shipsToPlace[index] = change;
    }
    double percentageRatio(int shots)
    {
        return (17/shots) * 100;
    }
    void setShips(int ships)
    {
        this.ships = ships;
    }
    int getShips()
    {
        return ships;
    }
    int getPiecesAlive()
    {
        return piecesAlive;
    }
    void setPiecesAlive(int piecesAlive)
    {
        this.piecesAlive = piecesAlive;
    }
    int getShots()
    {
        return shots;
    }
    void setShots(int shots)
    {
        this.shots = shots;
    }
    void setChosenShip(int chosenShip)
    {
        this.chosenShip = chosenShip;
    }
    int getChosenShip()
    {
        return chosenShip;
    }
}
