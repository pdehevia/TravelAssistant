package proyect.travelassistant.carousels;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.LinearLayout;


/**
 * Created by pgarcia on 29/6/17.
 */

public class CarouselLayout extends LinearLayout {
    public final static float BIG_SCALE = 1.0f;
    private float scale = BIG_SCALE;

    public CarouselLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CarouselLayout(Context context) {
        super(context);
    }

    public void setScaleBoth(float scale) {
        this.scale = scale;
        this.invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int w = this.getWidth();
        int h = this.getHeight();
        canvas.scale(scale, scale, w / 2, h / 2);
        super.onDraw(canvas);
    }
}
