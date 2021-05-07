/*
 * Copyright (C) 2010 ZXing authors
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

package com.mining.app.zxing.camera.camera2;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.hardware.Camera;
import android.os.Build;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.WindowManager;

import com.mining.app.zxing.camera.camera2.open.CameraFacing;
import com.mining.app.zxing.camera.camera2.open.OpenCamera;

import java.util.List;


/**
 * A class which deals with reading, parsing, and setting the camera parameters which are used to
 * configure the camera hardware.
 */
@SuppressWarnings("deprecation") // camera APIs
final class CameraConfigurationManager {

  private static final String TAG = "CameraConfiguration";
  private static final int TEN_DESIRED_ZOOM = 27;
  private final Context context;
  private int cwNeededRotation;
  private int cwRotationFromDisplayToCamera;
  private Point screenResolution;
  private Point cameraResolution;
  private Point bestPreviewSize;
  private Point previewSizeOnScreen;

  CameraConfigurationManager(Context context) {
    this.context = context;
  }

  /**
   * Reads, one time, values from the camera that are needed by the app.
   */
  void initFromCameraParameters(OpenCamera camera) {
    Camera.Parameters parameters = camera.getCamera().getParameters();
    WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    Display display = manager.getDefaultDisplay();

    int displayRotation = display.getRotation();
    int cwRotationFromNaturalToDisplay;
    switch (displayRotation) {
      case Surface.ROTATION_0:
        cwRotationFromNaturalToDisplay = 0;
        break;
      case Surface.ROTATION_90:
        cwRotationFromNaturalToDisplay = 90;
        break;
      case Surface.ROTATION_180:
        cwRotationFromNaturalToDisplay = 180;
        break;
      case Surface.ROTATION_270:
        cwRotationFromNaturalToDisplay = 270;
        break;
      default:
        // Have seen this return incorrect values like -90
        if (displayRotation % 90 == 0) {
          cwRotationFromNaturalToDisplay = (360 + displayRotation) % 360;
        } else {
          throw new IllegalArgumentException("Bad rotation: " + displayRotation);
        }
    }
    Log.i(TAG, "Display at: " + cwRotationFromNaturalToDisplay);

    int cwRotationFromNaturalToCamera = camera.getOrientation();
    Log.i(TAG, "Camera at: " + cwRotationFromNaturalToCamera);

    // Still not 100% sure about this. But acts like we need to flip this:
    if (camera.getFacing() == CameraFacing.FRONT) {
      cwRotationFromNaturalToCamera = (360 - cwRotationFromNaturalToCamera) % 360;
      Log.i(TAG, "Front camera overriden to: " + cwRotationFromNaturalToCamera);
    }

    /*
    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
    String overrideRotationString;
    if (camera.getFacing() == CameraFacing.FRONT) {
      overrideRotationString = prefs.getString(PreferencesActivity.KEY_FORCE_CAMERA_ORIENTATION_FRONT, null);
    } else {
      overrideRotationString = prefs.getString(PreferencesActivity.KEY_FORCE_CAMERA_ORIENTATION, null);
    }
    if (overrideRotationString != null && !"-".equals(overrideRotationString)) {
      Log.i(TAG, "Overriding camera manually to " + overrideRotationString);
      cwRotationFromNaturalToCamera = Integer.parseInt(overrideRotationString);
    }
     */

    cwRotationFromDisplayToCamera =
        (360 + cwRotationFromNaturalToCamera - cwRotationFromNaturalToDisplay) % 360;
    Log.i(TAG, "Final display orientation: " + cwRotationFromDisplayToCamera);
    if (camera.getFacing() == CameraFacing.FRONT) {
      Log.i(TAG, "Compensating rotation for front camera");
      cwNeededRotation = (360 - cwRotationFromDisplayToCamera) % 360;
    } else {
      cwNeededRotation = cwRotationFromDisplayToCamera;
    }
    Log.i(TAG, "Clockwise rotation from display to camera: " + cwNeededRotation);

    Point theScreenResolution = new Point();
    display.getSize(theScreenResolution);
    screenResolution = theScreenResolution;
    Log.i(TAG, "Screen resolution in current orientation: " + screenResolution);
    cameraResolution = CameraConfigurationUtils.findBestPreviewSizeValue(parameters, screenResolution);
    Log.i(TAG, "Camera resolution: " + cameraResolution);
    bestPreviewSize = CameraConfigurationUtils.findBestPreviewSizeValue(parameters, screenResolution);
    Log.i(TAG, "Best available preview size: " + bestPreviewSize);

    boolean isScreenPortrait = screenResolution.x < screenResolution.y;
    boolean isPreviewSizePortrait = bestPreviewSize.x < bestPreviewSize.y;

    if (isScreenPortrait == isPreviewSizePortrait) {
      previewSizeOnScreen = bestPreviewSize;
    } else {
      previewSizeOnScreen = new Point(bestPreviewSize.y, bestPreviewSize.x);
    }
    Log.i(TAG, "Preview size on screen: " + previewSizeOnScreen);
  }

  void setDesiredCameraParameters(OpenCamera camera, boolean safeMode) {

    Camera theCamera = camera.getCamera();
    Camera.Parameters parameters = theCamera.getParameters();

    List<Camera.Size> supportedPreviewSizes = parameters.getSupportedPreviewSizes();
    int position =0;
    if(supportedPreviewSizes.size()>2){
      position=supportedPreviewSizes.size()/2+1;//supportedPreviewSizes.get();
    }else {
      position=supportedPreviewSizes.size()/2;
    }

    int width = supportedPreviewSizes.get(position).width;
    int height = supportedPreviewSizes.get(position).height;
    Log.d(TAG, "Setting preview size: " + cameraResolution);
    theCamera.setDisplayOrientation(90);
    cameraResolution.x=width;
    cameraResolution.y=height;
    parameters.setPreviewSize(width,height);
    setFlash(parameters);
    setZoom(parameters);
    theCamera.setParameters(parameters);
//    if (parameters == null) {
//      Log.w(TAG, "Device error: no camera parameters are available. Proceeding without configuration.");
//      return;
//    }
//
//    Log.i(TAG, "Initial camera parameters: " + parameters.flatten());
//
//    if (safeMode) {
//      Log.w(TAG, "In camera config safe mode -- most settings will not be honored");
//    }
//
//    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
//
//    initializeTorch(parameters, prefs, safeMode);
//
//    CameraConfigurationUtils.setFocus(
//        parameters,
//        prefs.getBoolean("preferences_auto_focus", true),
//        prefs.getBoolean("preferences_disable_continuous_focus", false),
//        safeMode);
//
//    if (!safeMode) {
//      if (prefs.getBoolean("preferences_invert_scan", false)) {
//        CameraConfigurationUtils.setInvertColor(parameters);
//      }
//
//      if (!prefs.getBoolean("preferences_disable_barcode_scene_mode", true)) {
//        CameraConfigurationUtils.setBarcodeSceneMode(parameters);
//      }
//
//      if (!prefs.getBoolean("preferences_disable_metering", true)) {
//        CameraConfigurationUtils.setVideoStabilization(parameters);
//        CameraConfigurationUtils.setFocusArea(parameters);
//        CameraConfigurationUtils.setMetering(parameters);
//      }
//
//      //SetRecordingHint to true also a workaround for low framerate on Nexus 4
//      //https://stackoverflow.com/questions/14131900/extreme-camera-lag-on-nexus-4
//      parameters.setRecordingHint(true);
//
//    }
//
//    parameters.setPreviewSize(bestPreviewSize.x, bestPreviewSize.y);
//
//    theCamera.setParameters(parameters);
//
//    theCamera.setDisplayOrientation(cwRotationFromDisplayToCamera);
//
//    Camera.Parameters afterParameters = theCamera.getParameters();
//    Camera.Size afterSize = afterParameters.getPreviewSize();
//    if (afterSize != null && (bestPreviewSize.x != afterSize.width || bestPreviewSize.y != afterSize.height)) {
//      Log.w(TAG, "Camera said it supported preview size " + bestPreviewSize.x + 'x' + bestPreviewSize.y +
//          ", but after setting it, preview size is " + afterSize.width + 'x' + afterSize.height);
//      bestPreviewSize.x = afterSize.width;
//      bestPreviewSize.y = afterSize.height;
//    }
  }

  Point getBestPreviewSize() {
    return bestPreviewSize;
  }

  Point getPreviewSizeOnScreen() {
    return previewSizeOnScreen;
  }

  Point getCameraResolution() {
    return cameraResolution;
  }

  Point getScreenResolution() {
    return screenResolution;
  }

  int getCWNeededRotation() {
    return cwNeededRotation;
  }

  boolean getTorchState(Camera camera) {
    if (camera != null) {
      Camera.Parameters parameters = camera.getParameters();
      if (parameters != null) {
        String flashMode = parameters.getFlashMode();
        return
            Camera.Parameters.FLASH_MODE_ON.equals(flashMode) ||
            Camera.Parameters.FLASH_MODE_TORCH.equals(flashMode);
      }
    }
    return false;
  }

  void setTorch(Camera camera, boolean newSetting) {
    Camera.Parameters parameters = camera.getParameters();
    doSetTorch(parameters, newSetting, false);
    camera.setParameters(parameters);
  }

  private void initializeTorch(Camera.Parameters parameters, SharedPreferences prefs, boolean safeMode) {
//    boolean currentSetting = FrontLightMode.readPref(prefs) == FrontLightMode.ON;
//    doSetTorch(parameters, currentSetting, safeMode);
  }

  private void doSetTorch(Camera.Parameters parameters, boolean newSetting, boolean safeMode) {
//    CameraConfigurationUtils.setTorch(parameters, newSetting);
//    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
//    if (!safeMode && !prefs.getBoolean(PreferencesActivity.KEY_DISABLE_EXPOSURE, true)) {
//      CameraConfigurationUtils.setBestExposure(parameters, newSetting);
//    }
  }
  private void setFlash(Camera.Parameters parameters) {
    // FIXME: This is a hack to turn the flash off on the Samsung Galaxy.
    // And this is a hack-hack to work around a different value on the Behold II
    // Restrict Behold II check to Cupcake, per Samsung's advice
    //if (Build.MODEL.contains("Behold II") &&
    //    CameraManager.SDK_INT == Build.VERSION_CODES.CUPCAKE) {
    if (Build.MODEL.contains("Behold II") && CameraManager.SDK_INT == 3) { // 3 = Cupcake
      parameters.set("flash-value", 1);
    } else {
      parameters.set("flash-value", 2);
    }
    // This is the standard setting to turn the flash off that all devices should honor.
    parameters.set("flash-mode", "off");
  }

  private void setZoom(Camera.Parameters parameters) {

    String zoomSupportedString = parameters.get("zoom-supported");
    if (zoomSupportedString != null && !Boolean.parseBoolean(zoomSupportedString)) {
      return;
    }

    int tenDesiredZoom = TEN_DESIRED_ZOOM;

    String maxZoomString = parameters.get("max-zoom");
    if (maxZoomString != null) {
      try {
        int tenMaxZoom = (int) (10.0 * Double.parseDouble(maxZoomString));
        if (tenDesiredZoom > tenMaxZoom) {
          tenDesiredZoom = tenMaxZoom;
        }
      } catch (NumberFormatException nfe) {
        Log.w(TAG, "Bad max-zoom: " + maxZoomString);
      }
    }
  }
}
