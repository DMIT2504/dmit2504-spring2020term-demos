package ca.nait.dmit2504.touchscreeninputdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    // Multitouch Step 1: Add a ScaleGestureDetector variable to handle multitouch events
    private ScaleGestureDetector mScaleGestureDetector;
    private float mScaleFactor = 1.0f;
    private ImageView mImageView;

    // Multitouch Step 2: Create a inner class that extends ScaleGestureDetector.SimpleOnScaleGestureListener
    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(final ScaleGestureDetector detector) {
//            return super.onScale(detector);
            mScaleFactor = mScaleFactor * detector.getScaleFactor();
            // Prevent the image from becoming to large or too small
            mScaleFactor = Math.max(0.5f, Math.min(mScaleFactor, 8.0f));
            mImageView.setScaleX(mScaleFactor);
            mImageView.setScaleY(mScaleFactor);
            return true;
        }
    }



    // Step 1: Add a GestureDetectorCompat/GestureDetector variable to detect gestures
    private GestureDetectorCompat mGestureDetector;

    // Step 2: Create a inner class that extends GestureDector.SimpleOnGestureListener
    // and override one or more of these methods:
    //  - onDown()
    //  - onFling()
    //  - onLongPress()
    //  - onScroll()
    //  - onShowPress()
    //  - onDoubleTap()
    //  - onDoubleTapEvent()
    //  - onSingleTapConfirmed()
    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapUp(final MotionEvent e) {
            Toast.makeText(getApplicationContext(), "onSingleTapUp", Toast.LENGTH_SHORT).show();
            Log.i(TAG, "onSingleTapUp");
            return super.onSingleTapUp(e);
        }

        @Override
        public void onLongPress(final MotionEvent e) {
            Toast.makeText(getApplicationContext(), "onLongPress", Toast.LENGTH_SHORT).show();
            Log.i(TAG, "onLongPress");
            super.onLongPress(e);
        }

        @Override
        public boolean onScroll(final MotionEvent e1, final MotionEvent e2, final float distanceX, final float distanceY) {
            Toast.makeText(getApplicationContext(), "onScroll", Toast.LENGTH_SHORT).show();
            Log.i(TAG, "onScroll");
            return super.onScroll(e1, e2, distanceX, distanceY);
        }

        @Override
        public boolean onFling(final MotionEvent e1, final MotionEvent e2, final float velocityX, final float velocityY) {
            Toast.makeText(getApplicationContext(), "onFling", Toast.LENGTH_SHORT).show();
            Log.i(TAG, "onFling");
            return super.onFling(e1, e2, velocityX, velocityY);
        }

        @Override
        public void onShowPress(final MotionEvent e) {
            Toast.makeText(getApplicationContext(), "onShowPress", Toast.LENGTH_SHORT).show();
            Log.i(TAG, "onShowPress");
            super.onShowPress(e);
        }

        @Override
        public boolean onDown(final MotionEvent e) {
            Toast.makeText(getApplicationContext(), "onDown", Toast.LENGTH_SHORT).show();
            Log.i(TAG, "onDown");
            return super.onDown(e);
        }

        @Override
        public boolean onDoubleTap(final MotionEvent e) {
            Toast.makeText(getApplicationContext(), "onDoubleTap", Toast.LENGTH_SHORT).show();
            Log.i(TAG, "onDoubleTap");
            return super.onDoubleTap(e);
        }

        @Override
        public boolean onDoubleTapEvent(final MotionEvent e) {
            Toast.makeText(getApplicationContext(), "onDoubleTapEvent", Toast.LENGTH_SHORT).show();
            Log.i(TAG, "onDoubleTapEvent");
            return super.onDoubleTapEvent(e);
        }

        @Override
        public boolean onSingleTapConfirmed(final MotionEvent e) {
            Toast.makeText(getApplicationContext(), "onSingleTapConfirmed", Toast.LENGTH_SHORT).show();
            Log.i(TAG, "onSingleTapConfirmed");
            return super.onSingleTapConfirmed(e);
        }

        @Override
        public boolean onContextClick(final MotionEvent e) {
            Toast.makeText(getApplicationContext(), "onContextClick", Toast.LENGTH_SHORT).show();
            Log.i(TAG, "onContextClick");
            return super.onContextClick(e);
        }
    }

    // Multitouch Step 3: Override the onTouchEvent() method to handle touch events
    // Step 3: Override the onTouchEvent() method to handle touch events notifications
    @Override
    public boolean onTouchEvent(final MotionEvent event) {

        mScaleGestureDetector.onTouchEvent(event);

        mGestureDetector.onTouchEvent(event);

        return super.onTouchEvent(event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Multitouch Step 4: Create an instance of a ScaleGestureDetector
        mScaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());
        mImageView = findViewById(R.id.imageView);

        // Step 4: Create an instance of a GestureDetector
        mGestureDetector = new GestureDetectorCompat(this, new GestureListener());

        // Find the Button in the layout
        Button button = findViewById(R.id.button);
        // Create an onClick event handler for the button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Toast.makeText(getApplicationContext(),"onClick", Toast.LENGTH_SHORT).show();
                Log.i(TAG,"onClick");
            }
        });
        // Create an onLongClick event handler for the button
        button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View v) {
                Toast.makeText(getApplicationContext(),"onLongClick", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onLongClick");
                return false;
            }
        });
    }
}