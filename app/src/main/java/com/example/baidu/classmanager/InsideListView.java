package com.example.baidu.classmanager;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by baidu on 2018/3/6.
 */

public class InsideListView extends LinearLayout {
    private Context mContext;
    int xDown;
    int yDown;
    int xMove;
    int yMove;
    private boolean isDrag = false;

    public InsideListView(Context context) {
        this(context,null);
        mContext = context;

    }

    public InsideListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
        mContext = context;
    }




    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        int srcollX = getScrollX();
        int mDragOutWidth = dp2px(mContext, 80);
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDown = x;
                yDown = y;
                setClickable(true);
//                scrollTo(mDragOutWidth, 0);
                break;
            case MotionEvent.ACTION_MOVE:
                xMove = x;
                yMove = y;
                int dx = xMove - xDown;
                int dy = yMove - yDown;
                if (Math.abs(dx) + 100 < Math.abs(dy)) {
                    break;
                }
                if (dx != 0) {//手指正在横向滑动
//                    isDrag = true;
//                    int newScrollX = srcollX - dx;//当这个值变小时，view视图向左滑动
//                    if (newScrollX < 0) {//保持大于等于0，等于0时view左上角x值和屏幕左上角x值重合
//                        newScrollX = 0;
//                        setClickable(true);
//                    } else if (newScrollX > mDragOutWidth) {//当到达隐藏布局的边界时 是不能再侧滑了
//                        newScrollX = mDragOutWidth;
//                    }
//                    scrollTo(newScrollX, 0);
                    if(dx<0){
                        scrollTo(mDragOutWidth,0);
                    }else{
                        scrollTo(0,0);
                    }
                }
                Log.e("xdown",""+xDown);
                Log.e("ydown",""+yDown);
                Log.e("xmove",""+xMove);
                Log.e("ymove",""+yMove);
                Log.e("dx",""+dx);
                Log.e("dy",""+dy);
                Log.e("scroll",""+srcollX);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    public int dp2px(Context context, int dp) {
        try {
            float scale = context.getResources().getDisplayMetrics().density;
            return (int) (dp * scale + 0.5f);
        } catch (Exception e) {

        }
        return 0;
    }

    public void initView() {

    }
}
