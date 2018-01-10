package com.afeng.maskingprogress.imageview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import com.afeng.maskingprogress.utils.DisplayUtil;

/**
 * @author Created by fengunion
 * @Description: 遮罩进度图片
 * @date 2018/1/8
 */

@SuppressLint("AppCompatCustomView")
public class MaskingProgressView extends ImageView {

    private static final String TAG = "MaskingProggress";
    //最大进度值
    private static final int MAX_PROGRESS = 100;

    // view 宽
    private int width;
    // view 高
    private int height;
    // 遮罩矩形
    private Rect rect;
    private String maskingColor = "#4Dca0d0d";
    private int textSize = DisplayUtil.sp2px(getContext(), 12);
    private int textColor = Color.BLACK;
    private Paint maskingPaint;
    private Paint textPaint;

    public MaskingProgressView(Context context) {
        this(context, null);
    }

    public MaskingProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MaskingProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    public void init() {
        // 遮罩画笔
        maskingPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        // 进度画笔
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        maskingPaint.setStyle(Paint.Style.FILL);
        maskingPaint.setColor(Color.parseColor(maskingColor));
        textPaint.setTextSize(textSize);
        textPaint.setColor(textColor);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureSize(widthMeasureSpec), measureSize(heightMeasureSpec));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (percentage > 0 && percentage < MAX_PROGRESS) {
            rect.top = height * percentage / MAX_PROGRESS;
            // 绘制图片遮罩
            canvas.drawRect(rect, maskingPaint);
            if (percentage < MAX_PROGRESS) {
                String text = "图片上传中";
                //测量文字的长度
                int textLength = (int) textPaint.measureText(text);
                Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
                // 获取文字的高度
                int textHeight = (int) (fontMetrics.descent - fontMetrics.ascent);
                // 计算x轴居中的坐标
                int centerX = (width - textLength) / 2;
                int centerY = (int) ((height + textHeight) / 2 - fontMetrics.descent);
                canvas.drawText(text, centerX, centerY, textPaint);
                String percentageText = percentage + "%";
                int percentageTextLength = (int) textPaint.measureText(percentageText);
                canvas.drawText(percentageText, (width - percentageTextLength) / 2, (int) (height * 0.75), textPaint);
            }

        }

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //获取view的宽高
        width = getWidth();
        height = getHeight();
        Log.d(TAG, "onSizeChanged: " + width + "----" + height);
        rect = new Rect();
        rect.left = 0;
        rect.top = 0;
        rect.right = width;
        rect.bottom = height;
    }

    int percentage = 0;

    /**
     * 设置进度之
     *
     * @param percentage 进度值
     */
    public void setPercentage(int percentage) {
        if (percentage > 0 && percentage <= MAX_PROGRESS) {
            this.percentage = percentage;
            Log.d(TAG, "setPercentage: " + percentage);
            invalidate();
        }
    }

    /**
     * 重置进度
     */
    public void reset() {
        this.percentage = 0;
        invalidate();
    }

    /**
     * 遮罩的颜色
     *
     * @param maskingColor
     */
    public void setMaskingColor(String maskingColor) {
        if (null != maskingPaint) {
            maskingPaint.setColor(Color.parseColor(maskingColor));
        }
    }

    /**
     * 进度字体大小
     *
     * @param textSize
     */
    public void setTextSize(int textSize) {
        if (null != textPaint) {
            textPaint.setTextSize(textSize);
        }


    }

    /**
     * 进度颜色
     *
     * @param textColor
     */
    public void setTextColor(String textColor) {
        if (null != textPaint) {
            textPaint.setColor(Color.parseColor(textColor));
        }

    }

    /**
     * 测量宽高模式
     *
     * @param MeasureSpecSize
     * @return
     */
    private int measureSize(int MeasureSpecSize) {
        int size = 0;
        int[] ints = measureSpec(MeasureSpecSize);
        if (ints[0] == MeasureSpec.EXACTLY) {
            size = ints[1];
        } else {
            size = DisplayUtil.dip2px(getContext(), 70);
            if (ints[0] == MeasureSpec.AT_MOST) {
                size = Math.min(size, ints[1]);
            }
        }

        return size;
    }


    private int[] measureSpec(int measureSpec) {
        int[] measure = new int[2];
        measure[0] = MeasureSpec.getMode(measureSpec);
        measure[1] = MeasureSpec.getSize(measureSpec);
        return measure;
    }
}

