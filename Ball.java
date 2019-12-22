import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

public class Ball extends Box2DActor {

  Vector2 startPosition;  // in physical coordinates
  final float startVelocityX = 11;
  final float startVelocityY = -11;

  public Ball(World world, TextureRegion region) {
    super(Box2DTools.createDynamicBody(world, 400, 400), region);
    setName("ball");
    Shape circle = Box2DTools.createCircleShape(region.getRegionWidth());
    Box2DTools.addFixtureToBody(body, circle, 1);
    body.setBullet(true);
    body.setUserData(this);
    body.setGravityScale(2.0f);
    startPosition = new Vector2(body.getPosition()); // Achtung, getPosition liefert eine Referenz auf die Position; wir wollen uns aber die Startpositon merken, darum ist ein Konstruktoraufruf notwendig
    body.setLinearVelocity(startVelocityX, startVelocityY);
  }

  @Override
  public void act(float delta) {
    super.act(delta);
    if (body.getPosition().y < 0) {
      body.setTransform(startPosition, 0);
      body.setAngularVelocity(0);
      body.setLinearVelocity(startVelocityX, startVelocityY);
      ((Lives) getStage().getRoot().findActor("lives")).decrement();
    }
  }
}
