import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class DesktopApplication {

  public static void main(String Args[]) {
    ArkanoidGame game = new ArkanoidGame();
    new LwjglApplication(game, "Arkanoid", 600, 800);
  }
}
