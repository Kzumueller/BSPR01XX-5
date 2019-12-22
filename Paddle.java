import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

public class Paddle extends Box2DActor {

  final float speed = 20;

  public Paddle(World world, TextureRegion region) {
    super(Box2DTools.createKinematicBody(world, 280, 60), region);
    Shape box = Box2DTools.createBoxShape(region.getRegionWidth(), region.getRegionHeight());
    Box2DTools.addFixtureToBody(body, box, 1.05f);
  }

  @Override
  public void act(float delta) {
    super.act(delta);
    if (Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.A))
      body.setLinearVelocity(-speed, 0.0f);
    else if (Gdx.input.isKeyPressed(Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.D))
      body.setLinearVelocity(speed, 0.0f);
    else
      body.setLinearVelocity(0.0f, 0.0f);
  }

}
