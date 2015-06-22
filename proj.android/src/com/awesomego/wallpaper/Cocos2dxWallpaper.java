package com.awesomego.wallpaper;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import org.cocos2dx.lib.Cocos2dxActivity;
import org.cocos2dx.lib.Cocos2dxGLSurfaceView;
import org.cocos2dx.lib.Cocos2dxHelper;
import org.cocos2dx.lib.Cocos2dxHelper.Cocos2dxHelperListener;
import org.cocos2dx.lib.Cocos2dxRenderer;

import android.content.Context;
import android.opengl.GLSurfaceView.Renderer;
import android.os.Bundle;
import android.service.wallpaper.WallpaperService;
import android.view.SurfaceHolder;

public class Cocos2dxWallpaper extends WallpaperService {

    static {
        System.loadLibrary("cocos2dcpp");
    }

    @Override
    public Engine onCreateEngine() {
        return new MyWallpaperEngine();
    }

    protected class MyWallpaperEngine extends Engine implements Cocos2dxHelperListener {
        protected class MyGLSurfaceView extends Cocos2dxGLSurfaceView {
            MyGLSurfaceView(Context context) {
                super(context);
            }

            @Override
            public SurfaceHolder getHolder() {
                return getSurfaceHolder();
            }

            public void onDestroy() {
                super.onDetachedFromWindow();
            }
        }

        protected class MyGLRenderer extends Cocos2dxRenderer {
            @Override
            public void onSurfaceCreated(final GL10 pGL10, final EGLConfig pEGLConfig) {
            }

            @Override
            public void onSurfaceChanged(final GL10 pGL10, final int pWidth, final int pHeight) {
                setScreenWidthAndHeight(pWidth, pHeight);
                super.onSurfaceCreated(null, null);
            }

        }

        private MyGLSurfaceView mGLSurfaceView;

        private boolean rendererHasBeenSet;

        @Override
        public void onCreate(SurfaceHolder surfaceHolder) {
            super.onCreate(surfaceHolder);

            mGLSurfaceView = new MyGLSurfaceView(Cocos2dxWallpaper.this);
            mGLSurfaceView.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
            //mGLSurfaceView.setCocos2dxRenderer(new Cocos2dxRenderer());
            setRenderer(getNewRenderer());
            Cocos2dxHelper.init(Cocos2dxWallpaper.this, this);
        }

        @Override
        public void onVisibilityChanged(boolean visible) {
            super.onVisibilityChanged(visible);

            if (visible) {
                Cocos2dxHelper.onResume();
                mGLSurfaceView.onResume();
            } else {
                Cocos2dxHelper.onPause();
                mGLSurfaceView.onPause();
            }
        }

        @Override
        public void onDestroy() {
            super.onDestroy();

            Cocos2dxHelper.end();
            mGLSurfaceView.onDestroy();
        }

        protected void setRenderer(Renderer renderer) {
            mGLSurfaceView.setCocos2dxRenderer((Cocos2dxRenderer) renderer);
            rendererHasBeenSet = true;
        }

        Renderer getNewRenderer(){
            Cocos2dxRenderer renderer = new MyGLRenderer();
            return renderer;
        }

        @Override
        public void showDialog(String pTitle, String pMessage) {
        }

        @Override
        public void showEditTextDialog(String pTitle, String pMessage, int pInputMode, int pInputFlag, int pReturnType, int pMaxLength) {
        }

        @Override
        public void runOnGLThread(Runnable pRunnable) {
            mGLSurfaceView.queueEvent(pRunnable);
        }
    }

}
