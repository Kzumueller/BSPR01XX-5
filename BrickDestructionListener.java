import com.badlogic.gdx.physics.box2d.*;

// Loesung Aufgabe a)
public class BrickDestructionListener implements ContactListener {

    public void beginContact(Contact contact) {
        var bodyA = contact.getFixtureA().getBody();
        var actorA = bodyA.getUserData();
        var bodyB = contact.getFixtureB().getBody();
        var actorB = bodyB.getUserData();

        if(actorA instanceof Ball && actorB instanceof Brick)
            ((Brick) actorB).doom();

        if(actorB instanceof Ball && actorA instanceof Brick)
            ((Brick) actorA).doom();
    }

    public void endContact(Contact contact) {
    }

    public void postSolve(Contact contact, ContactImpulse impulse) {
    }

    public void preSolve(Contact contact, Manifold oldManifold) {
    }
}
