import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class Box2DTools 
{

  public final static float PIXELS_PER_METER = 16f; 
  static Vector2 v = new Vector2();

  public static float radToDeg(float rad)
  {
    return  rad * (float)(180.0 / java.lang.Math.PI); 
  }

  public static Vector2 metersToPixels(Vector2 v)
  {
    return v.scl( PIXELS_PER_METER );
  }

  public static Vector2 pixelsToMeters(Vector2 v)
  {
    return v.scl( 1.0f/PIXELS_PER_METER );
  }

  public static Shape createCircleShape(float diameter)
  {
    CircleShape circle = new CircleShape();
    circle.setRadius(0.5f*diameter / PIXELS_PER_METER);
    return circle;
  }

  public static Shape createBoxShape(float width, float height)
  {
    PolygonShape box = new PolygonShape();
    box.setAsBox(0.5f*width/PIXELS_PER_METER, 0.5f*height/PIXELS_PER_METER);
    return box;
  }

  public static Body createDynamicBody(World world, float x, float y)
  {
    BodyDef bdef = new BodyDef();
    bdef.type = BodyDef.BodyType.DynamicBody;
    bdef.position.set(pixelsToMeters(v.set(x,y)));
    return world.createBody(bdef);
  }

  public static Body createKinematicBody(World world, float x, float y)
  {
    BodyDef bdef = new BodyDef();
    bdef.type = BodyDef.BodyType.KinematicBody;
    bdef.position.set(pixelsToMeters(v.set(x,y)));
    return world.createBody(bdef);
  }

  public static Body createStaticBody(World world, float x, float y)
  {
    BodyDef bdef = new BodyDef();
    bdef.position.set(pixelsToMeters(v.set(x,y)));
    return world.createBody(bdef);
  }

  public static void addFixtureToBody(Body body, Shape shape, float restitution)
  {
    FixtureDef fd = new FixtureDef();
    fd.density = 10.0f;
    fd.friction = 0f;
    fd.restitution = restitution;
    fd.shape = shape;
    body.createFixture( fd );
  } 
}
