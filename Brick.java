import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Shape;

import com.badlogic.gdx.scenes.scene2d.Action;

public class Brick extends Box2DActor {

    private boolean iAmDoomed = false;

    public void doom() {
        iAmDoomed = true;
    }

    public Brick(Body body, TextureRegion region) {
        super(body, region);
        Shape shape = Box2DTools.createBoxShape(region.getRegionWidth(), region.getRegionHeight());
        Box2DTools.addFixtureToBody(body, shape, 1);
        body.setUserData(this);
    }

    @Override
    public void act(float delta) {
        if(iAmDoomed) {
            body.getWorld().destroyBody(body);
            remove();
        } else {
            super.act(delta);
        }
    }
}
