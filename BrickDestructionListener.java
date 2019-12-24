import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;

// Loesung Aufgabe a)
public class BrickDestructionListener implements ContactListener {

  final static int POINTS_PER_HIT = 20;

  private Group brickGroup;

  private Stage stage;

  private Score score;

  public BrickDestructionListener(Stage stage) {
    this.stage = stage;
    this.brickGroup = stage.getRoot().findActor("brickGroup");
    this.score = stage.getRoot().findActor("scoreLabel");
  }

  /**
   * if the ball hits a brick, this removes the brick
   * and ends the game if there are no more bricks remaining
   *
   * @param contact used to evaluate the fixtures involved in the collision
   */
  public void beginContact(Contact contact) {
    var bodyA = contact.getFixtureA().getBody();
    var actorA = bodyA.getUserData();
    var bodyB = contact.getFixtureB().getBody();
    var actorB = bodyB.getUserData();

    if (actorA instanceof Ball && actorB instanceof Brick) {
      ((Brick) actorB).doom();
      maybeEndGame((Ball) actorA);
      score.add(POINTS_PER_HIT);
    }

    if (actorB instanceof Ball && actorA instanceof Brick) {
      ((Brick) actorA).doom();
      maybeEndGame((Ball) actorB);
      score.add(POINTS_PER_HIT);
    }
  }

  /**
   * if there are no bricks left,
   * this shows a message notifying the player that the game has been won and removes the ball
   *
   * @param ball to remove
   */
  public void maybeEndGame(Ball ball) {
    // the group technically still has one child left when all the bricks have been hit
    // since bricks are removed after this listener finishes up
    if (1 < brickGroup.getChildren().size) return;

    ball.doom();
    var label = (MessageLabel) stage.getRoot().findActor("messageLabel");
    label.show("You made it!");
  }

  public void endContact(Contact contact) {
  }

  public void postSolve(Contact contact, ContactImpulse impulse) {
  }

  public void preSolve(Contact contact, Manifold oldManifold) {
  }
}
