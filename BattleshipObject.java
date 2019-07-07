import java.util.Scanner;
import java.util.Random;
import java.awt.*;
import javax.swing.*;

public class BattleshipObject extends JFrame
{
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
        }
        for(int i = 0; i < interfPlayer.length; i++)
        {
            for(int j = 0; j < interfPlayer[i].length; j++)
            {
                interfPlayer[i][j] = new showShips();
                interfPlayer[i][j].setId(counter);
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
                interfEnemy[i][j].setId(counter);
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
        BattleshipObject gui = new BattleshipObject();
        gui.setSize(1200,600);
        gui.setVisible(true);
        gui.setResizable(false);
        gui.setTitle("Battleship");
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
                        player[main].setInput(scan.nextInt());
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
                                if(main == 0)
                                {
                                    interfPlayer[y][x].setRGB(99,245,66);
                                    interfPlayer[y][x+1].setRGB(99,245,66);
                                    interfPlayer[y][x+2].setRGB(99,245,66);
                                    interfPlayer[y][x+3].setRGB(99,245,66);
                                    interfPlayer[y][x+4].setRGB(99,245,66);
                                    interfPlayer[y][x].setTypeOfShip("_C5_");
                                    interfPlayer[y][x+1].setTypeOfShip("_C5_");
                                    interfPlayer[y][x+2].setTypeOfShip("_C5_");
                                    interfPlayer[y][x+3].setTypeOfShip("_C5_");
                                    interfPlayer[y][x+4].setTypeOfShip("_C5_");
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
                                    if(main == 0)
                                    {
                                        interfPlayer[y][x].setRGB(99,245,66);
                                        interfPlayer[y+1][x].setRGB(99,245,66);
                                        interfPlayer[y+2][x].setRGB(99,245,66);
                                        interfPlayer[y+3][x].setRGB(99,245,66);
                                        interfPlayer[y+4][x].setRGB(99,245,66);
                                        interfPlayer[y][x].setTypeOfShip("_C5_");
                                        interfPlayer[y+1][x].setTypeOfShip("_C5_");
                                        interfPlayer[y+2][x].setTypeOfShip("_C5_");
                                        interfPlayer[y+3][x].setTypeOfShip("_C5_");
                                        interfPlayer[y+4][x].setTypeOfShip("_C5_");
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
                                if(main == 0)
                                {
                                    interfPlayer[y][x].setRGB(99,245,66);
                                    interfPlayer[y][x+1].setRGB(99,245,66);
                                    interfPlayer[y][x+2].setRGB(99,245,66);
                                    interfPlayer[y][x+3].setRGB(99,245,66);
                                    interfPlayer[y][x].setTypeOfShip("_B4_");
                                    interfPlayer[y][x+1].setTypeOfShip("_B4_");
                                    interfPlayer[y][x+2].setTypeOfShip("_B4_");
                                    interfPlayer[y][x+3].setTypeOfShip("_B4_");
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
                                    if(main == 0)
                                    {
                                        interfPlayer[y][x].setRGB(99,245,66);
                                        interfPlayer[y+1][x].setRGB(99,245,66);
                                        interfPlayer[y+2][x].setRGB(99,245,66);
                                        interfPlayer[y+3][x].setRGB(99,245,66);
                                        interfPlayer[y][x].setTypeOfShip("_B4_");
                                        interfPlayer[y+1][x].setTypeOfShip("_B4_");
                                        interfPlayer[y+2][x].setTypeOfShip("_B4_");
                                        interfPlayer[y+3][x].setTypeOfShip("_B4_");
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
                                player[main].setField(y, x, 333);
                                player[main].setField(y, x+1, 333);
                                player[main].setField(y, x+2, 333);
                                if(main == 0)
                                {
                                    interfPlayer[y][x].setRGB(99,245,66);
                                    interfPlayer[y][x+1].setRGB(99,245,66);
                                    interfPlayer[y][x+2].setRGB(99,245,66);
                                    interfPlayer[y][x].setTypeOfShip("_C3_");
                                    interfPlayer[y][x+1].setTypeOfShip("_C3_");
                                    interfPlayer[y][x+2].setTypeOfShip("_C3_");
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
                                    player[main].setField(y, x, 333);
                                    player[main].setField(y+1, x, 333);
                                    player[main].setField(y+2, x, 333);
                                    if(main == 0)
                                    {
                                        interfPlayer[y][x].setRGB(99,245,66);
                                        interfPlayer[y+1][x].setRGB(99,245,66);
                                        interfPlayer[y+2][x].setRGB(99,245,66);
                                        interfPlayer[y][x].setTypeOfShip("_C3_");
                                        interfPlayer[y+1][x].setTypeOfShip("_C3_");
                                        interfPlayer[y+2][x].setTypeOfShip("_C3_");
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
                                if(main == 0)
                                {
                                    interfPlayer[y][x].setRGB(99,245,66);
                                    interfPlayer[y][x+1].setRGB(99,245,66);
                                    interfPlayer[y][x].setTypeOfShip("_D2_");
                                    interfPlayer[y][x+1].setTypeOfShip("_D2_");
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
                                    if(main == 0)
                                    {
                                        interfPlayer[y][x].setRGB(99,245,66);
                                        interfPlayer[y+1][x].setRGB(99,245,66);
                                        interfPlayer[y][x].setTypeOfShip("_D2_");
                                        interfPlayer[y+1][x].setTypeOfShip("_D2_");
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
                player[0].setShoot(scan.nextInt());
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
            }while(player[1].getPlayingField()[y][x].equalsIgnoreCase("HIT") || player[1].getPlayingField()[y][x].equalsIgnoreCase("MISS") || player[0].getShoot() < 0 || player[0].getShoot() >= 100);
            interfEnemy[y][x].setShipPresent(false);
            if(player[1].getField()[y][x] > 0)
            {
                System.out.println("Direct hit!");
                player[1].setPiecesAlive(player[1].getPiecesAlive()-1);
                player[1].setPlayingField(y, x, "HIT");
                player[1].setField(y, x, 0);
                interfEnemy[y][x].setRGB(255,100,120);
                interfEnemy[y][x].setTypeOfShip("HIT");
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
                interfEnemy[y][x].setTypeOfShip("MISS");
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
                player[0].setField(y, x, 0);
                interfPlayer[y][x].setRGB(255,100,120);
                interfPlayer[y][x].setTypeOfShip("HIT");
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
                interfPlayer[y][x].setTypeOfShip("MISS");
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
                System.out.println("Out of bounds.");
                break;
                case 2:
                System.out.println("Place already taken.");
                break;
                case 3:
                System.out.println("Wrong input");
                break;
            }
        }
    }
    public void paint(Graphics g)
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
                if(interfPlayer[i][j].getShipPresent() == true)
                {
                    g.drawString(interfPlayer[i][j].getTypeOfShip(), interfPlayer[i][j].getX()+5, interfPlayer[i][j].getY()+25);
                }
                else
                {
                    g.drawString(interfPlayer[i][j].getId()+"",interfPlayer[i][j].getX()+15,interfPlayer[i][j].getY()+15);
                }
                if(interfEnemy[i][j].getShipPresent() == true)
                {
                    g.drawString(interfEnemy[i][j].getId() + "",interfEnemy[i][j].getX()+15, interfEnemy[i][j].getY()+15);
                }
                else
                {
                    g.drawString(interfEnemy[i][j].getTypeOfShip(),interfEnemy[i][j].getX()+5, interfEnemy[i][j].getY()+25);
                }
            }
        }
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
    int id = 0;
    int r = 133;
    int g = 218;
    int b = 255;
    boolean shipPresent = false;
    String typeOfShip = "";
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
    void setTypeOfShip(String typeOfShip)
    {
        this.typeOfShip = typeOfShip;
    }
    String getTypeOfShip()
    {
        return typeOfShip;
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
    void setId(int id)
    {
        this.id = id;
    }
    int getId()
    {
        return id;
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