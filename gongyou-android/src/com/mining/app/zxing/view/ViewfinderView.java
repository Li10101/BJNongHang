/*
 * Copyright (C) 2008 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mining.app.zxing.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import com.google.zxing.ResultPoint;
import com.mining.app.zxing.camera.camera2.CameraManager;
import com.xyl.R;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import static android.graphics.PixelFormat.OPAQUE;

/**
 * This view is overlaid on top of the camera preview. It adds the viewfinder rectangle and partial
 * transparency outside it, as well as the laser scanner animation and result points.
 *
 * @author dswitkin@google.com (Daniel Switkin)
 */
public final class ViewfinderView extends View {

  private static final int[] SCANNER_ALPHA = {0, 64, 128, 192, 255, 192, 128, 64};
  private static final long ANIMATION_DELAY = 80L;
  private static final int CURRENT_POINT_OPACITY = 0xA0;
  private static final int MAX_RESULT_POINTS = 20;
  private static final int POINT_SIZE = 6;

  private CameraManager cameraManager;
  private final Paint paint;
  private Bitmap resultBitmap;
  private final int maskColor;
  private final int resultColor;
  private final int laserColor;
  private final int resultPointColor;
  private int scannerAlpha;
  private Collection<ResultPoint> possibleResultPoints;
  private Collection<ResultPoint> lastPossibleResultPoints;
  private int ScreenRate;
  private static final int CORNER_WIDTH = 10;
  private int slideTop;
  private static final int SPEEN_DISTANCE = 5;
  private static final int MIDDLE_LINE_PADDING = 5;
  private static final int MIDDLE_LINE_WIDTH = 6;
  private static float density;
  private int slideBottom;
  /**
   * �����С
   */
  private static final int TEXT_SIZE = 16;
  private static final int TEXT_PADDING_TOP = 30;

  boolean isFirst;
  // This constructor is used when the class is built from an XML resource.
  public ViewfinderView(Context context, AttributeSet attrs) {
    super(context, attrs);

    // Initialize these once for performance rather than calling them every time in onDraw().
    paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Resources resources = getResources();
    maskColor = resources.getColor(R.color.viewfinder_mask);
    resultColor = resources.getColor(R.color.result_view);
    laserColor = resources.getColor(R.color.viewfinder_laser);
    //设置闪烁颜色
    resultPointColor = resources.getColor(R.color.possible_result_points);
    scannerAlpha = 0;
    possibleResultPoints = new ArrayList<>(5);
    lastPossibleResultPoints = null;
    ScreenRate = (int)(20 * density);
  }

  public void setCameraManager(CameraManager cameraManager) {
    this.cameraManager = cameraManager;
  }

  @SuppressLint("DrawAllocation")
  @Override
  public void onDraw(Canvas canvas) {
    if (cameraManager == null) {
      return; // not ready yet, early draw before done configuring
    }

    //frame表示扫描框
    Rect frame = cameraManager.getFramingRect();
    Rect previewFrame = cameraManager.getFramingRectInPreview();
    if (frame == null || previewFrame == null) {
      return;
    }

    if(!isFirst){
      isFirst = true;
      slideTop = frame.top;
      slideBottom = frame.bottom;
    }
    int width = canvas.getWidth();
    int height = canvas.getHeight();

    // Draw the exterior (i.e. outside the framing rect) darkened
    //将外部（即框架外矩形）变暗


    paint.setColor(resultBitmap != null ? resultColor : maskColor);
    //绘画出扫描框上部的颜色变暗
    canvas.drawRect(0, 0, width, frame.top, paint);
    canvas.drawRect(0, frame.top, frame.left, frame.bottom + 1, paint);
    canvas.drawRect(frame.right + 1, frame.top, width, frame.bottom + 1, paint);
    canvas.drawRect(0, frame.bottom + 1, width, height, paint);
//    canvas.drawRect(0, 0, width, frame.top, paint);
//    canvas.drawRect(0, frame.top, frame.left, frame.bottom + 1, paint);
//    canvas.drawRect(frame.right + 1, frame.top, width, frame.bottom + 1, paint);
//    canvas.drawRect(0, frame.bottom + 1, width, height, paint);



    if (resultBitmap != null) {
      // Draw the opaque result bitmap over the scanning rectangle
      paint.setAlpha(CURRENT_POINT_OPACITY);
      canvas.drawBitmap(resultBitmap, null, frame, paint);
    } else {

      // Draw a red "laser scanner" line through the middle to show decoding is active
      //��ɨ�����ϵĽǣ��ܹ�8������
      //设置扫描线的颜色
      paint.setColor(Color.GREEN);
//      canvas.drawRect(frame.left, frame.top, frame.left + ScreenRate,
//              frame.top + CORNER_WIDTH, paint);
//      canvas.drawRect(frame.left, frame.top, frame.left + CORNER_WIDTH, frame.top
//              + ScreenRate, paint);
//      canvas.drawRect(frame.right - ScreenRate, frame.top, frame.right,
//              frame.top + CORNER_WIDTH, paint);
//      canvas.drawRect(frame.right - CORNER_WIDTH, frame.top, frame.right, frame.top
//              + ScreenRate, paint);
//      canvas.drawRect(frame.left, frame.bottom - CORNER_WIDTH, frame.left
//              + ScreenRate, frame.bottom, paint);
//      canvas.drawRect(frame.left, frame.bottom - ScreenRate,
//              frame.left + CORNER_WIDTH, frame.bottom, paint);
//      canvas.drawRect(frame.right - ScreenRate, frame.bottom - CORNER_WIDTH,
//              frame.right, frame.bottom, paint);
//      canvas.drawRect(frame.right - CORNER_WIDTH, frame.bottom - ScreenRate,
//              frame.right, frame.bottom, paint);


      //�����м����,ÿ��ˢ�½��棬�м���������ƶ�SPEEN_DISTANCE
      //修改横线数值
      slideTop += SPEEN_DISTANCE;
      if(slideTop >= frame.bottom){
        slideTop = frame.top;
      }
      canvas.drawRect(frame.left + MIDDLE_LINE_PADDING, slideTop - MIDDLE_LINE_WIDTH/2, frame.right - MIDDLE_LINE_PADDING,slideTop + MIDDLE_LINE_WIDTH/2, paint);


      //��ɨ����������
      paint.setColor(Color.WHITE);
      paint.setTextSize(TEXT_SIZE * density);
      paint.setAlpha(0x40);
      paint.setTypeface(Typeface.create("System", Typeface.BOLD));
      canvas.drawText(getResources().getString(R.string.scan_text), frame.left, (float) (frame.bottom + (float)TEXT_PADDING_TOP *density), paint);



      Collection<ResultPoint> currentPossible = possibleResultPoints;
      Collection<ResultPoint> currentLast = lastPossibleResultPoints;
      if (currentPossible.isEmpty()) {
        lastPossibleResultPoints = null;
      } else {
        possibleResultPoints = new HashSet<ResultPoint>(5);
        lastPossibleResultPoints = currentPossible;
        paint.setAlpha(OPAQUE);
        paint.setColor(resultPointColor);
        for (ResultPoint point : currentPossible) {
          canvas.drawCircle(frame.left + point.getX(), frame.top
                  + point.getY(), 6.0f, paint);
        }
      }
      if (currentLast != null) {
        paint.setAlpha(OPAQUE / 2);
        paint.setColor(resultPointColor);
        for (ResultPoint point : currentLast) {
          canvas.drawCircle(frame.left + point.getX(), frame.top
                  + point.getY(), 3.0f, paint);
        }
      }


      //ֻˢ��ɨ�������ݣ�����ط���ˢ��
      //扫描线动画刷新
      postInvalidateDelayed(ANIMATION_DELAY, frame.left, frame.top,
              frame.right, frame.bottom);
    }
  }

  public void drawViewfinder() {
    Bitmap resultBitmap = this.resultBitmap;
    this.resultBitmap = null;
    if (resultBitmap != null) {
      resultBitmap.recycle();
    }
    invalidate();
  }

  /**
   * Draw a bitmap with the result points highlighted instead of the live scanning display.
   *
   * @param barcode An image of the decoded barcode.
   */
  public void drawResultBitmap(Bitmap barcode) {
    resultBitmap = barcode;
    invalidate();
  }

  public void addPossibleResultPoint(ResultPoint point) {
    Collection<ResultPoint> points = possibleResultPoints;
    synchronized (points) {
      points.add(point);
//      int size = points.size();
//      if (size > MAX_RESULT_POINTS) {
//        // trim it
//        points.subList(0, size - MAX_RESULT_POINTS / 2).clear();
//      }
    }
  }

}
