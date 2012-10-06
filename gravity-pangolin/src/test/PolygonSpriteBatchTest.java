package test;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

import fr.gravity.pangolin.helper.TextureHelper;
import fr.gravity.pangolin.helper.TextureHelper.TextureId;
 
public class PolygonSpriteBatchTest implements ApplicationListener, InputProcessor {
 
    OrthographicCamera camera;
    Texture texture;
    TextureRegion textureRegion;
    PolygonRegion pregion;
    PolygonSpriteBatch pbatch;
 
    boolean switcher = false;
 
    SpriteBatch batch;
 
    FPSLogger fps = new FPSLogger();
 
    Array<PolygonSprite> psprites = new Array<PolygonSprite>();
    Array<Sprite> sprites = new Array<Sprite>();
 
    @Override
    public void create() {      
 
        Gdx.input.setInputProcessor(this);
        camera = new OrthographicCamera(480, 320);
//        texture = new Texture(Gdx.files.internal("tree128x128.png"));
        textureRegion = TextureHelper.getInstance().getTextureRegions(TextureId.PANGOLIN)[0];
        pregion = new PolygonRegion(textureRegion, Gdx.files.internal("images/sprite_pangolin.psh"));
        
        camera.position.x = 400;
        camera.position.y = 240;
 
        pbatch = new PolygonSpriteBatch();
        batch = new SpriteBatch();
 
        int count = 500;
 
        for(int i=0; i<count; i++){
            PolygonSprite sprite = new PolygonSprite(pregion);
            sprite.setPosition(MathUtils.random(-10, 700), MathUtils.random(0, 360));
            sprite.setColor(MathUtils.random(0.6f, 1), MathUtils.random(0.6f, 1), MathUtils.random(0.6f, 1), 1.0f);
 
            psprites.add(sprite);
        }
 
        for(int i=0; i<count; i++) {
            Sprite sprite = new Sprite(textureRegion);
            sprite.setPosition(MathUtils.random(-20, 740), MathUtils.random(0, 400));
            sprite.setColor(MathUtils.random(0.6f, 1), MathUtils.random(0.6f, 1), MathUtils.random(0.6f, 1), 1.0f);
 
            sprites.add(sprite);
        }
    }
 
    @Override
    public void dispose() {
 
    }
 
    @Override
    public void render() {
 
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
//        camera.apply(Gdx.gl10);
        camera.update();
 
        pbatch.setProjectionMatrix(camera.combined);
        batch.setProjectionMatrix(camera.combined);
 
        if(switcher == false) {
            pbatch.begin();
            for(int i=0;i<psprites.size;i++) {
                PolygonSprite sprite = psprites.get(i);
 
                sprite.draw(pbatch);
 
            }
            pbatch.end();
        } else {
 
            batch.begin();
            for(int i=0;i<sprites.size;i++) {
                Sprite sprite = sprites.get(i);
 
                sprite.draw(batch);
            }
            batch.end();
 
        }
        fps.log();
    }
 
    @Override
    public void resize(int width, int height) {
    }
 
    @Override
    public void pause() {
    }
 
    @Override
    public void resume() {
    }
 
    @Override
    public boolean keyDown(int keycode) {
        // TODO Auto-generated method stub
        return false;
    }
 
    @Override
    public boolean keyUp(int keycode) {
        // TODO Auto-generated method stub
        return false;
    }
 
    @Override
    public boolean keyTyped(char character) {
        // TODO Auto-generated method stub
        return false;
    }
 
    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        //if(Gdx.input.justTouched()) {
            switcher = !switcher;
        //}
        return false;
    }
 
    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        // TODO Auto-generated method stub
        return false;
    }
 
    @Override
    public boolean touchDragged(int x, int y, int pointer) {
        // TODO Auto-generated method stub
        return false;
    }
 
    @Override
    public boolean touchMoved(int x, int y) {
        // TODO Auto-generated method stub
        return false;
    }
 
    @Override
    public boolean scrolled(int amount) {
        // TODO Auto-generated method stub
        return false;
    }
}