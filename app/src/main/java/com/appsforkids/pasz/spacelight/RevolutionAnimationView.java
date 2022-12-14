package com.appsforkids.pasz.spacelight;

import android.animation.TimeAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import java.util.Random;

/**
 * Created by zigin on 06.11.2017.
 */

public class RevolutionAnimationView extends View {

    private static class SerpMolot {
        private float x;
        private float y;
        private float scale;
        private float alpha;
        private float speed;
    }

    private float mBaseSpeed;
    private float mBaseSize;
    private long mCurrentPlayTime;

    public static final int SEED = 1337;
    public static final int COUNT = 32;
    public static final int BASE_SPEED_DP_PER_S = 60;


    /** The minimum scale of a SerpMolot */
    public static final float SCALE_MIN_PART = 0.45f;
    /** How much of the scale that's based on randomness */
    public static final float SCALE_RANDOM_PART = 0.55f;
    /** How much of the alpha that's based on the scale of the star */
    private static final float ALPHA_SCALE_PART = 0.5f;
    /** How much of the alpha that's based on randomness */
    private static final float ALPHA_RANDOM_PART = 0.5f;

    private final Random mRnd = new Random(SEED);
    private final SerpMolot[] mSerpMolots = new SerpMolot[COUNT];

    private TimeAnimator mTimeAnimator;
    private Drawable mDrawable;


    /** @see View#View(Context) */
    public RevolutionAnimationView(Context context) {
        super(context);
        init(ContextCompat.getDrawable(getContext(), R.drawable.sm_1));
    }

    /** @see View#View(Context, AttributeSet) */
    public RevolutionAnimationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(ContextCompat.getDrawable(getContext(), R.drawable.sm_1));
    }

    /** @see View#View(Context, AttributeSet, int) */
    public RevolutionAnimationView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(ContextCompat.getDrawable(getContext(), R.drawable.sm_1));
    }

    public void init(Drawable drawable) {
        mDrawable = drawable;
        mBaseSize = Math.max(mDrawable.getIntrinsicWidth(), mDrawable.getIntrinsicHeight()) / 2f;
        mBaseSpeed = BASE_SPEED_DP_PER_S * getResources().getDisplayMetrics().density;


    }

    public void changeImage(Drawable drawable){

        mDrawable = drawable;
    }

    public void changeColorImage(Drawable drawable, int color){

        drawable.setColorFilter(color, android.graphics.PorterDuff.Mode.SRC_IN);
    }

    private void initializeSerpMolot(SerpMolot serpMolot, int viewWidth, int viewHeight) {
        // Set the scale based on a min value and a random multiplier
        serpMolot.scale = SCALE_MIN_PART + SCALE_RANDOM_PART * mRnd.nextFloat();

        // Set X to a random value within the width of the view
        serpMolot.x = viewWidth * mRnd.nextFloat();

        // Set Y - start at the bottom of the view
        serpMolot.y = viewHeight;

        // The value Y is in the center of the serpMolot, add the size
        // to make sure it starts outside of the view bound
        serpMolot.y += serpMolot.scale * mBaseSize;

        //Add a random offset to create a small delay before the serpMolot appears again
        serpMolot.y += viewHeight * mRnd.nextFloat() / 4f;

        // The alpha is determined by the scale of the serpMolot and a random multiplier
        serpMolot.alpha = ALPHA_SCALE_PART * serpMolot.scale + ALPHA_RANDOM_PART * mRnd.nextFloat();

        // The bigger and the brighter serpMolot is faster
        serpMolot.speed = mBaseSpeed * serpMolot.alpha * serpMolot.scale;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        final int viewHight = getHeight();
        for( final SerpMolot serpMolot : mSerpMolots) {
            // Ignore the serpMolot if it's outside of the view bounds
            final float serpMolotSize = serpMolot.scale * mBaseSize;
            if (serpMolot.y + serpMolotSize < 0 || serpMolot.y - serpMolotSize > viewHight) {
                continue;
            }

            //Save the current canvas state
            final int save = canvas.save();

            // Move the canvas to the center of the serpMolot
            canvas.translate(serpMolot.x, serpMolot.y);

            //Rotate the canvas based on how far the serpMolot has moved
            final float progress = (serpMolot.y + serpMolotSize) / viewHight;
            canvas.rotate(360 * progress);


            //Prepare the size and alpha of the drawable
            final int size = Math.round(serpMolotSize);
            mDrawable.setBounds(-size, -size, size, size);
            mDrawable.setAlpha(Math.round(255 * serpMolot.alpha));

            // Draw the serpMolot to the canvas
            mDrawable.draw(canvas);

            // Restore the canvas to it's previous position and rotation
            canvas.restoreToCount(save);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        // The starting position is dependent on the size of the view,
        // which is why the model is initialized here, when the view is measured.
        for (int i = 0; i < mSerpMolots.length; i++) {
            final SerpMolot serpMolot = new SerpMolot();
            initializeSerpMolot(serpMolot, w, h);
            mSerpMolots[i] = serpMolot;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        mTimeAnimator = new TimeAnimator();
        mTimeAnimator.setTimeListener(new TimeAnimator.TimeListener() {
            @Override
            public void onTimeUpdate(TimeAnimator animation, long totalTime, long deltaTime) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    if (!isLaidOut()) {
                        // Ignore all calls before the view has been measured and laid out.
                        return;
                    }
                }
                updateState(deltaTime);
                invalidate();
            }
        });

        startAnimation();

    }


    public void startAnimation(){
        if(mTimeAnimator!=null){
            mTimeAnimator.start();
        }
    }



    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        mTimeAnimator.cancel();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mTimeAnimator.setTimeListener(null);
        }
        mTimeAnimator.removeAllListeners();
        mTimeAnimator = null;
    }

    /**
     * Progress the animation by moving the stars based on the elapsed time
     * @param deltaMs time delta since the last frame, in millis
     */
    private void updateState(float deltaMs) {
        // Converting to seconds since PX/S constants are easier to understand
        final float deltaSeconds = deltaMs / 1000f;
        final int viewWidth = getWidth();
        final int viewHeight = getHeight();

        for (final SerpMolot serpMolot : mSerpMolots) {
            // Move the serpMolot based on the elapsed time and it's speed
            serpMolot.y -= serpMolot.speed * deltaSeconds;

            // If the serpMolot is completely outside of the view bounds after
            // updating it's position, recycle it.
            final float size = serpMolot.scale * mBaseSize;
            if (serpMolot.y + size < 0) {
                initializeSerpMolot(serpMolot, viewWidth, viewHeight);
            }
        }
    }

    /**
     * Pause the animation if it's running
     */
    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public void pause() {
        if (mTimeAnimator != null && mTimeAnimator.isRunning()) {
            // Store the current play time for later.
            mCurrentPlayTime = mTimeAnimator.getCurrentPlayTime();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                mTimeAnimator.pause();
            }
        }
    }

    /**
     * Resume the animation if not already running
     */
    public void resume() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (mTimeAnimator != null && mTimeAnimator.isPaused()) {
                mTimeAnimator.start();
                // Why set the current play time?
                // TimeAnimator uses timestamps internally to determine the delta given
                // in the TimeListener. When resumed, the next delta received will the whole
                // pause duration, which might cause a huge jank in the animation.
                // By setting the current play time, it will pick of where it left off.
                mTimeAnimator.setCurrentPlayTime(mCurrentPlayTime);
            }
        }
    }
}