import java.awt.*;
/**
 * Implémentation de la classe Balls.
 */
public class Balls extends Event{
    private Point[] tabBalle;
    private int dxTotal = 0;
    private int dyTotal = 0;
    private int[] dxTotallocal;
    private int[] dyTotallocal;
    public Color colorcercle;
    public Color colorinside;
    private int rayon;
    private int nbBalles;
    public int[] dx;
    public int[] dy;
    private EventManager manage;

    /**
     * Constructeur général
     *
     * @param balls
     *          tableau qui contient les balles.
     * @param manager
     *          eventManager
     */

    public Balls(Point[] balls, EventManager manager) {
        super(0);
      nbBalles = balls.length;
      tabBalle = new Point[nbBalles];
      dxTotallocal = new int[nbBalles];
      dyTotallocal = new int[nbBalles];
      // Default White
      colorcercle = Color.WHITE;
      colorinside = Color.WHITE;
      // Default rayon
      rayon = 10;
      manage = manager;
      for (int k =0; k < nbBalles; k++){
        tabBalle[k] = balls[k];
      }
    }
    /**
     * Gère le déplacement des balles
     */
    @Override
    public void execute() {
        for (int i = 0; i < this.getNbBalles(); i++) {
            this.translateBallIndice(this.dx[i], this.dy[i], i);
            int x = this.getBalls()[i].x;
            int y = this.getBalls()[i].y;
            // Changer le 490 par la taille de la window - 10 (la moitie du cercle)
            // Je sais pas comment la recuperer lire la doc
            if (x < this.getRayon() || x > 500 - this.getRayon()/2) {
                this.dx[i] *= -1;
                // On translate 2 fois pour revenir en dedans du cadre
                this.translateBallIndice(this.dx[i] * 2, 0, i);
            }
            if (y > 500 - this.getRayon()|| y < this.getRayon()/2) {
                this.dy[i] *= -1;
                this.translateBallIndice(0, this.dy[i] * 2, i);
            }
        }
        this.setDate(getDate() + 1);
        manage.addEvent(this);
    }

    public int getNbBalles(){
      return nbBalles;
    }

    public int getRayon(){
      return rayon;
    }

    public Point[] getBalls() {
        return this.tabBalle;
    }
    /**
     *Translate toutes les balls de dx suivant x et dy suivant y.
     *
     * @param dx
     *          translation suivant x
     * @param dy
     *          translation suivant y
     */
    public void translate(int dx, int dy){
      dxTotal += dx;
      dyTotal += dy;
      for (int i = 0; i < this.tabBalle.length; i++) {
          this.tabBalle[i].x += dx;
          this.tabBalle[i].y += dy;
      }
    }
    /**
     *Translate la balle d'indice indiceBall, de dx suivant x et dy suivant y.
     *
     * @param dx
     *          translation suivant x
     * @param dy
     *          translation suivant y
     * @param indiceBall
     *          indice de la balle à translater
     */
    public void translateBallIndice(int dx, int dy, int indiceBall){
      if (indiceBall >= nbBalles || indiceBall < 0){
        throw new IllegalArgumentException("Indice n'est pas correct");
      }
      this.tabBalle[indiceBall].x += dx;
      this.tabBalle[indiceBall].y += dy;
      dxTotallocal[indiceBall] += dx;
      dyTotallocal[indiceBall] += dy;
    }
    /**
     * Initialise les balles à leur positon d'origine.
     */
    public void reInit(){
      for (int i = 0; i < this.tabBalle.length; i++) {
        this.tabBalle[i].x -= dxTotal + dxTotallocal[i];
        this.tabBalle[i].y -= dyTotal + dyTotallocal[i];
        dxTotallocal[i] = 0;
        dyTotallocal[i] = 0;
      }
      dxTotal = 0;
      dyTotal = 0;
    }



    @Override
    public String toString() {
        String reponse = "[";
        int longeur = tabBalle.length;

        for (int i = 0; i < longeur - 1 ; i++) {
            reponse  += "(" + tabBalle[i].x  + ", " + tabBalle[i].y + "), ";
        }
        reponse += "(" + tabBalle[longeur - 1].x + ", " + tabBalle[longeur - 1].y + ")]";
        return reponse;
    }
}
