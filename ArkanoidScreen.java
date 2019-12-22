import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class ArkanoidScreen implements Screen {

  World world;
  Stage stage;
  final int playfieldHeight = 720;

  public void addBricks(TextureRegion region, int[][] bricks) {
    Group brickGroup = new Group();
    brickGroup.setName("brickGroup");
    stage.addActor(brickGroup);

    for (int[] coordinates : bricks) {
      Body body = Box2DTools.createStaticBody(world, coordinates[0], playfieldHeight - coordinates[1]);
      Brick brick = new Brick(body, region);
      body.setUserData(brick);
      brickGroup.addActor(brick);
    }
  }

  public void show() {
    stage = new Stage();
    world = new World(new Vector2(0, 0), true);
    TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("data/sprites-packed/pack.atlas"));

    stage.addActor(new Image(new TextureRegion(new Texture(Gdx.files.internal("data/background600x800.png")),
      600, playfieldHeight)));

    Paddle paddle = new Paddle(world, atlas.findRegion("paddleBig"));
    stage.addActor(paddle);

    Ball ball = new Ball(world, atlas.findRegion("ball"));
    stage.addActor(ball);

    makeBorder(-8); // left border
    makeBorder(Gdx.graphics.getWidth()); // right border

    // top border
    Body body = Box2DTools.createStaticBody(world, Gdx.graphics.getWidth() * 0.5f, playfieldHeight + 8);
    Shape box = Box2DTools.createBoxShape(Gdx.graphics.getWidth(), 16);
    Box2DTools.addFixtureToBody(body, box, 1);

    addBricks(atlas.findRegion("yellow"), Level1.yellow);
    addBricks(atlas.findRegion("cyan"), Level1.cyan);
    addBricks(atlas.findRegion("orange"), Level1.orange);
    addBricks(atlas.findRegion("grey"), Level1.grey);
    addBricks(atlas.findRegion("purple"), Level1.purple);
    addBricks(atlas.findRegion("green"), Level1.green);
    addBricks(atlas.findRegion("red"), Level1.red);
    addBricks(atlas.findRegion("blue"), Level1.blue);

    // Loesung Aufgabe a)
    world.setContactListener(new BrickDestructionListener());

    // Loesung Aufgabe b)
    stage.addActor(new Lives(atlas.findRegion("paddleSmall")));

    // Loesung Aufgabe c)
    stage.addActor(new Score());

    // TODO: Loesung Aufgabenteil d)

  }

  public void makeBorder(float x) {
    Body body = Box2DTools.createStaticBody(world, x, Gdx.graphics.getHeight() * 0.5f);
    Shape box = Box2DTools.createBoxShape(16, Gdx.graphics.getHeight());
    Box2DTools.addFixtureToBody(body, box, 1);
  }

  public void render(float delta) {
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    world.step(delta, 8, 6);
    stage.act(delta);
    stage.draw();
  }

  public void dispose() {
  }

  public void pause() {
  }

  public void resume() {
  }

  public void hide() {
  }

  public void resize(int w, int h) {
  }
}
