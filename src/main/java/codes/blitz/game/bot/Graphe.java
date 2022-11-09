package codes.blitz.game.bot;

import java.util.ArrayList;

public class Graphe {

    private ArrayList<ArrayList<Arc>> m_listesAdj;
    private int nombreSommet;

    public Graphe(int i) {
        m_listesAdj = new ArrayList<ArrayList<Arc>>();
        this.nombreSommet = i;
    }

    public void ajouterArc(int i, int j, double poids){
        m_listesAdj.get(i).add(new Arc(j, poids));
    }

    double getPoids(int i, int j){
        double pointsMinimal = 1000000000;

        for (int k = 0; k < m_listesAdj.get(i).size(); k++) {
            if (m_listesAdj.get(i).get(k).getDestination() == j) {
                pointsMinimal = Math.min(pointsMinimal, m_listesAdj.get(i).get(k).getPoids());
            }
        }

        return pointsMinimal;
    }


    double[][] plusCourtChemin(){
        double[][] minimumDistance = new double[nombreSommet][nombreSommet];

        for (int i = 0; i < nombreSommet; i++) {
            for (int j = 0; j < nombreSommet; j++) {
                if(i == j)
                    minimumDistance[i][i]=0;
                else
                    minimumDistance[i][i]=1000000000;
            }
        }

        for (int i = 0; i < m_listesAdj.size(); i++) {
            for (int j = 0; j < m_listesAdj.get(i).size(); j++) {
                minimumDistance[i][j] = m_listesAdj.get(i).get(j).getPoids();
            }
        }

         for (int k = 0; k < nombreSommet; k++) {
             for (int i = 0; i < nombreSommet; i++) {
                 for (int j = 0; j < nombreSommet; j++) {
                    if(minimumDistance[i][j] > minimumDistance[i][k] + minimumDistance[k][j]){
                        minimumDistance[i][j] = minimumDistance[i][k] + minimumDistance[k][j];
                    }
                 }
             }
         }

        return minimumDistance;
     }


    private class Arc
    {
        int destination;
        double poids;
        Arc(int destination, double poids){
            this.destination = destination;
            this.poids = poids;
        }

        double getPoids(){
            return poids;
        }
        int getDestination(){
            return destination;
        }
    };
}
