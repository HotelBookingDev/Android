package sf.hotel.com.hotel_client.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Queue;

import sf.hotel.com.data.entity.LoginResult;
import sf.hotel.com.data.utils.LogUtils;


/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/12.
 */
public class SplashActivity extends Activity {
    private Queue<AssetSplash> mSplashList = new LinkedList<AssetSplash>();

    protected boolean notShowSplash = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);

        if (notShowSplash) {
            onSplashStop();
            return;
        }

        FrameLayout frame = new FrameLayout(this);

        ImageView image = new ImageView(this);

        image.setScaleType(ImageView.ScaleType.FIT_CENTER);
        frame.addView(image,
                new FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.MATCH_PARENT,
                        FrameLayout.LayoutParams.MATCH_PARENT));

        frame.setBackgroundColor(getBackgroundColor());
        String[] paths = null;
        try {
            paths = getAssets().list("splash");
        } catch (IOException e) {
        }
        if (paths == null || paths.length == 0) {
            onSplashStop();
        } else {
            for (String path : paths) {
                mSplashList.add(new AssetSplash(image, "splash/" + path));
            }
            setContentView(frame);
        }
    }

    /**
     * 背景颜色 Color.WHITE
     */
    protected int getBackgroundColor() {
        return Color.WHITE;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!isFinishing())
            playSplash();
    }

    private void playSplash() {
        AssetSplash splash = mSplashList.poll();
        if (splash == null) {
            onSplashStop();
            return;
        }
        SplashListener listener = new SplashListener() {
            @Override
            public void onStop() {
                playSplash();
            }
        };
        splash.play(this, listener);
    }


    private static class AssetSplash {
        String path;
        ImageView image;

        AssetSplash(ImageView image, String path) {
            this.path = path;
            this.image = image;
        }

        void play(final Activity context, final SplashListener listener) {
            new AsyncTask<Void, Void, Bitmap>() {
                @Override
                protected Bitmap doInBackground(Void... params) {
                    try {
                        InputStream in = context.getAssets().open(path);
                        Bitmap bm = BitmapFactory.decodeStream(in);
                        return bm;
                    } catch (IOException e) {
                        LogUtils.w("decode asset image failed: " + path);
                        return null;
                    }
                }

                @Override
                protected void onPostExecute(Bitmap result) {
                    if (result == null) {
                        listener.onStop();
                    } else {
                        image.setImageBitmap(result);
                        Animation anim = getSplashAnimation();
                        anim.setAnimationListener(new Animation.AnimationListener() {

                            @Override
                            public void onAnimationStart(Animation animation) {
                                image.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                image.setVisibility(View.INVISIBLE);
                                listener.onStop();
                            }
                        });

                        image.startAnimation(anim);
                    }
                }
            }.execute();
        }

        Animation getSplashAnimation() {
            Animation fadeIn = new AlphaAnimation(0.0F, 1.0F);
            fadeIn.setInterpolator(new DecelerateInterpolator());
            fadeIn.setDuration(500L);

            Animation fadeOut = new AlphaAnimation(1.0F, 0.0F);
            fadeOut.setInterpolator(new AccelerateInterpolator());
            fadeOut.setStartOffset(1500L);
            fadeOut.setDuration(500L);

            AnimationSet animation = new AnimationSet(false);
            animation.addAnimation(fadeIn);
            animation.addAnimation(fadeOut);
            return animation;
        }
    }

    interface SplashListener {
        void onStop();
    }

    protected void onSplashStop() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        this.finish();
    }
}
