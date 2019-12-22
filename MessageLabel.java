import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.Color;

public class MessageLabel extends Label
{
  public MessageLabel()
  {
    super("", new LabelStyle(new BitmapFont(), Color.WHITE));
    setName("messageLabel");
    LabelStyle style = getStyle();
    style.font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
    style.font.setScale(3.0f);
    addAction(Actions.alpha(0.0f));
  }

  public void show(String msg)
  {
    setText(msg);
    int w = Gdx.graphics.getWidth();
    int h = Gdx.graphics.getHeight();
    TextBounds bounds = getTextBounds();
    setPosition( 0.5f*(w - bounds.width), 0.5f*(h - bounds.height));
    addAction(Actions.fadeIn(1.0f));
  }
}
