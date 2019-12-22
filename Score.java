import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.Gdx;

public class Score extends Group {

  Label nameLabel;
  Label scoreLabel;

  private int score = 0;

  public Score() {
    setName("scoreLabel");

    BitmapFont font = new BitmapFont(Gdx.files.internal("data/fonts/movie-star.fnt"), false);
    // TODO: make two labels with different LabelStyles and add Labels to group
    nameLabel = new Label("HANS", new LabelStyle(font, Color.RED));
    scoreLabel = new Label("0", new LabelStyle(font, Color.WHITE));

    setPosition(10, Gdx.graphics.getHeight() - 2 * nameLabel.getHeight() - 10);

    nameLabel.setPosition(0, 5f + scoreLabel.getHeight());
    nameLabel.getStyle().fontColor = Color.RED;
    scoreLabel.getStyle().fontColor = Color.WHITE;
    addActor(nameLabel);
    addActor(scoreLabel);
  }

  // adds points to scoreLabel
  public void add(int points) {
    score += points;
    scoreLabel.setText(String.valueOf(score));
  }
}
