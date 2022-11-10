package codes.blitz.game.bot;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import codes.blitz.game.message.game.ActionKind;
import codes.blitz.game.message.game.Direction;
import codes.blitz.game.message.game.GameMap;
import codes.blitz.game.message.game.GameMessage;
import codes.blitz.game.message.game.Position;
import codes.blitz.game.message.game.Action;

public class Bot
{
    ArrayList<Position> visitePositionOrder;
    int destination;
    public Bot()
    {
        System.out.println("Initializing your super duper mega bot.");
        visitePositionOrder = new ArrayList<Position>();
        destination = 0;
    }

    public Action getNextAction(GameMessage gameMessage)
    {
        Action action;
        System.out.println("Destination: " + destination);
        
        if (gameMessage.spawnLocation() == null) {
            defineVisitOrderPort(gameMessage);

            action = new Action(ActionKind.SPAWN, visitePositionOrder.get(0));
            return action;  
        }
        if(gameMessage.currentTick() > 0) {
        System.out.println(gameMessage.currentLocation()==visitePositionOrder.get(destination));
        System.out.println(gameMessage.currentLocation());
        System.out.println(visitePositionOrder.get(destination));
    }

        if (isPositionEqual(gameMessage.currentLocation(), visitePositionOrder.get(destination))) {
            System.out.println("docking succesful");
            destination++;
            action = new Action(ActionKind.DOCK);
        } else {
            action = new Action(ActionKind.SAIL, moveToNextPort(gameMessage));
        }

        System.out.println("Action: " + action);

        return action;
    }

    private void defineVisitOrderPort(GameMessage gameMessage)
    {
        //double[][] distance = distanceEntreChaquePoint(gameMessage); ici on calcul la distance entre chaque port qu'on met dans un matrice
        //int [] chemin = shortestPathLength(distance,gameMessage); ici on calcul le plus court chemin pour parcourir tous les ports

        for (int i = 0; i < gameMessage.map().ports().size(); i++) {
            visitePositionOrder.add(gameMessage.map().ports().get(i));  // ici on ajouterait les ports dans l'ordre du plus court chemin
        }

        visitePositionOrder.add(visitePositionOrder.get(0)); //On veut revenir au point de départ
    }

    private double[][] distanceEntreChaquePoint(GameMessage gameMessage){
        double[][] distance = new double[gameMessage.map().ports().size()][gameMessage.map().ports().size()];
        for (int i = 0; i < gameMessage.map().ports().size(); i++) {
            for (int j = 0; j < gameMessage.map().ports().size(); j++) {
                if (i != j) {
                    //TODO ça serait encore plus cool si on prenait en considération les obstacles
                    double distanceXi = gameMessage.map().ports().get(i).row();
                    double distanceYi = gameMessage.map().ports().get(i).column();

                    double distanceXj = gameMessage.map().ports().get(j).row();
                    double distanceYj = gameMessage.map().ports().get(j).column();

                    distance[i][j] = Math.sqrt(Math.pow(distanceXi - distanceXj, 2) + Math.pow(distanceYi - distanceYj, 2));
                }
            }
        }

        return distance;
    }

    /**
     * Ici, il faut faire un algo qui prendre un tableau de double avec les distance entre chaque ports
     * Exemple : distance[1][2] est la distance entre le port 1 et le port 2
     * Et avec cet information là il faut trouver c'est quoi la séquence de port la plus optimal pour parcourir tous les ports
     * */
    public int[] shortestPathLength(double[][] distance, GameMessage gameMessage) {
        int nombreNoeud = gameMessage.map().ports().size();
        int [] meilleurChemin = new int[nombreNoeud+1];
        double meilleurCheminCouts = 1000000000;

        return new int[2];
    }


    /**
     * Ici, il faut faire un algo qui détermine le chemin qui nous permet de nous rendre le plus proche du port suivant
     * en prenant en compte les obstacles et les marée.
     * */
    public Direction moveToNextPort(GameMessage gameMessage)
    {
        Direction[] possibleDirections = Direction.values();

        Position target = visitePositionOrder.get(destination);
        Position currentLocation = gameMessage.currentLocation();

        if(target.row() > currentLocation.row()) {
            if (target.column() > currentLocation.column()) {
                return Direction.SE;
            } else if (target.column() < currentLocation.column()) {
                return Direction.SW;
            } else {
                return Direction.S;
            }
        }
        else if(target.row() < currentLocation.row())
        {
            if (target.column() > currentLocation.column()) {
                return Direction.NE;
            } else if (target.column() < currentLocation.column()) {
                return Direction.NW;
            } else {
                return Direction.N;
            }
        } else if (target.column() > currentLocation.column()) {
            return Direction.E;
        } else if (target.column() < currentLocation.column()) {
            return Direction.W;
        }

        return possibleDirections[gameMessage.currentTick() % possibleDirections.length];
    }

    private boolean isPositionEqual(Position p1, Position p2)
    {

        return p1.row() == p2.row() && p1.column() == p2.column();
    }

}