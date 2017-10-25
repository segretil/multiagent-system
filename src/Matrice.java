import gui.*;
import java.awt.Point;
import java.awt.Color;

public class Matrice {
    private boolean[][] matrice;
    private boolean[][] matriceFutur;
    private int tailleX;
    private int tailleY;
    private int nbVivant;

    public Matrice(int xTotal, int yTotal, Point[] vivants) {
        //On accepte les doublons dans les vivants
        this.matrice = new boolean[xTotal][yTotal];
        this.nbVivant = vivants.length;
        this.tailleX = xTotal;
        this.tailleY = yTotal;
        for (int i = 0; i < xTotal; i++) {
            for (int j = 0; j < yTotal; j++) {
                this.matrice[i][j] = false;
            }
        }
        for (Point vivant : vivants) {
            //Gestion des doublons
            if (this.matrice[vivant.x][vivant.y]) {
                nbVivant --;
            }
            this.matrice[vivant.x][vivant.y] = true;
        }
    }

    private int voisins(int x, int y) {
        // Retourne le nombre de voisins qui sont vivants
        int nbVoisins = 0;
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i < this.tailleX && i >= 0 && j < this.tailleY && j >= 0) {
                    if (this.matrice[i][j]) {
                        nbVoisins ++;
                    }
                }
            }
        }
        if (this.matrice[x][y]) {
            nbVoisins --;
        }
        return nbVoisins;
    }

    private void test(int x, int y) {
        // Teste si la cellules doit changé ou non
        int nbVoisins = voisins(x, y);
        if (this.matrice[x][y]) {
            if (nbVoisins != 3 && nbVoisins != 2) {
                change(x, y);
            }
        } else {
            if (nbVoisins == 3) {
                change(x, y);
            }
        }
    }

    public void etape() {
        // On passe à l'étape suivante
        // On doit tester toutes les cellules pour savoir si ses voisines sont vivantes
        this.matriceFutur = this.copied().matrice;
        for (int i = 0; i < tailleX; i++) {
            for (int j = 0; j < tailleY; j++) {
                test(i, j);
            }
        }
        this.matrice = this.matriceFutur;
    }

    public Point[] getVivant() {
        //Retourne tout les vivants
        Point[] vivants = new Point[this.nbVivant];
        int k = 0;
        for (int i = 0; i < tailleX; i++) {
            for (int j = 0; j < tailleY; j++) {
                if (this.matrice[i][j]) {
                    vivants[k] = new Point(i, j);
                    k++;
                }
            }
        }
        return vivants;
    }

    private void change(int x, int y) {
        // On rend la case vivante si elle était morte
        // OU
        // On rend la case morte si elle était vivante
        if (this.matriceFutur[x][y]) {
            this.matriceFutur[x][y] = false;
            this.nbVivant --;
        } else {
            this.matriceFutur[x][y] = true;
            this.nbVivant ++;
        }
    }

    public Matrice copied() {
        Matrice copie = new Matrice(this.tailleX, this.tailleY, this.getVivant());
        return copie;
    }
}
