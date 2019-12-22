import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Box2DActor extends Image {

    protected Body body;

    public Box2DActor(Body body, TextureRegion region) {
        super(region);
        this.body = body;
        setOrigin(region.getRegionWidth() * 0.5f, region.getRegionHeight() * 0.5f);
    }

    public void act(float delta) {
        super.act(delta);
        Vector2 pos = Box2DTools.metersToPixels(body.getPosition());
        setPosition(pos.x - getWidth() * 0.5f, pos.y - getHeight() * 0.5f);
        setRotation(Box2DTools.radToDeg(body.getAngle()));
    }
}
