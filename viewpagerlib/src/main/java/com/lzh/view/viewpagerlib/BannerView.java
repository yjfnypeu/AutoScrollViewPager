package com.lzh.view.viewpagerlib;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * A View to draw
 * Created by zhihaol on 16/8/30.
 */
public class BannerView extends View {

    private int count;
    private int curItem;
    private float offset;// 对当前curItem的偏移量
    private Bitmap normal;// 普通状态的指示标
    private Bitmap select;// 选中状态的指示标

    private Paint paint;

    public BannerView(Context context) {
        this(context,null);
    }

    public BannerView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BannerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.WHITE);
        normal = createIndicator();
        paint.setColor(Color.RED);
        select = createIndicator();
    }

    public void setCount(int count) {
        this.count = Math.max(count,0);
        setCurItem(curItem,0);
    }

    public void setCurItem (int position) {
        setCurItem(position,0);
    }

    public void setCurItem (int position,float offset) {
        this.offset = offset;
        this.curItem = Math.min(position,count - 1);
        this.curItem = Math.max(curItem,0);
        invalidate();
    }

    private Bitmap createIndicator() {
        Bitmap bitmap = Bitmap.createBitmap(60, 60, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawCircle(30,30,15,paint);
        return bitmap;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measuredWidth = count * normal.getWidth();
        int measuredHeight = normal.getHeight();
        setMeasuredDimension(measuredWidth,measuredHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawNormalIndicator(canvas);
        drawSelectIndicator(canvas);
    }

    private void drawSelectIndicator(Canvas canvas) {
        int offset = (int) (normal.getWidth() * (curItem + this.offset));
        canvas.drawBitmap(select,offset,0,null);
    }

    private void drawNormalIndicator(Canvas canvas) {
        for (int i = 0; i < count; i++) {
            canvas.drawBitmap(normal,i * normal.getWidth(),0,null);
        }
    }

}
