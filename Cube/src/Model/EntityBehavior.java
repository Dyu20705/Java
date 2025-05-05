package Model;

public interface EntityBehavior {
    void move(double dx, double dy);
    void attack();
    void takeDamage(int dmg);
    boolean isAlive();
}
