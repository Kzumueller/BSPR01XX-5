import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.SnapshotArray;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Lives extends Group {

  int lives = 5;

  private TextureRegion sprite;

  public Lives(TextureRegion region) {
    setName("lives");
    // Aufgabenteil b)
    sprite = region;
  }

  /**
   * draws a tiny sprite for every life the player has
   */
  @Override
  public void draw(Batch batch, float parentAlpha) {
    super.draw(batch, parentAlpha);
    int regionWidth = sprite.getRegionWidth();

    for (int index = 0; index < lives; ++index) {
      batch.draw(sprite, 5f + regionWidth * index, 13f);
    }
  }

  // returns false if no lives remain
  public boolean decrement() {
    return --lives > 0;
  }
}
