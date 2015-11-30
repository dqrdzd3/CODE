package com.hw.smarthome.cam;

import java.io.IOException;
import java.util.List;

import com.hw.util.PreferenceUtil;

import android.app.Activity;
import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CameraPreview extends SurfaceView implements
		SurfaceHolder.Callback {
	private SurfaceHolder mHolder;
	private Camera mCamera;
	private Size mPreviewSize;
	private Activity mActivity;
	private List<Size> mSupportedPreviewSizes;
	public CameraPreview(Context context) {
		super(context);
		mHolder = getHolder();
		mHolder.addCallback(this);
		mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}
	public CameraPreview(Activity context, Camera camera, Size previewSize) {
		super(context);
		mActivity = context;
		mPreviewSize = previewSize;
		mCamera = camera;

		// Install a SurfaceHolder.Callback so we get notified when the
		// underlying surface is created and destroyed.
		mHolder = getHolder();
		mHolder.addCallback(this);
		// deprecated setting, but required on Android versions prior to 3.0
		mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

	}

	// @Override
	// protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	// int[] wh = UIUtil.getScreenMetrics(mActivity);
	// setMeasuredDimension(wh[0], wh[1]);
	// }
	public void surfaceCreated(SurfaceHolder holder) {
		// The Surface has been created, now tell the camera where to draw the
		// preview.
		try {
			if (mCamera != null) {
				mCamera.setDisplayOrientation(90);
				mCamera.setPreviewDisplay(holder);
				mCamera.startPreview();
			}

		} catch (IOException e) {
			Log.d("DG_DEBUG", "Error setting camera preview: " + e.getMessage());
			mCamera.release();
			mCamera = null;
		}

	}
	public void setCamera(Camera camera) {
		mCamera = camera;
		if (mCamera != null) {
			mSupportedPreviewSizes = mCamera.getParameters()
					.getSupportedPreviewSizes();// ��ȡӲ����֧�ֵĳߴ�
			requestLayout();
		}
	}

	public void switchCamera(Camera camera) {
		setCamera(camera);
		try {
			camera.setPreviewDisplay(mHolder);
		} catch (IOException exception) {
			mCamera.release();
			mCamera = null;
		}
		Camera.Parameters parameters = camera.getParameters();
		requestLayout();
		// parameters.setPreviewSize(640, 480);
		camera.setParameters(parameters);
		mCamera.setDisplayOrientation(90);
	}
	public void setFlashLight(Activity context, Camera camera) {

		if (!PreferenceUtil.getCameraLight(context)) {
			PreferenceUtil.saveCameraLight(context, true);
			// 打开
			Camera.Parameters parameter = camera.getParameters();
			requestLayout();                      //必须有
			parameter.setFlashMode(Parameters.FLASH_MODE_TORCH);

			camera.setParameters(parameter);
		} else {
			// 关闭
			PreferenceUtil.saveCameraLight(context, false);
			Camera.Parameters parameter = camera.getParameters();
			requestLayout();                             //必须有
			parameter.setFlashMode(Parameters.FLASH_MODE_OFF);

			camera.setParameters(parameter);
		}

	}
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// If your preview can change or rotate, take care of those events here.
		// Make sure to stop the preview before resizing or reformatting it.

		if (mHolder.getSurface() == null) {
			// preview surface does not exist
			return;
		}

		// stop preview before making changes
		try {
			mCamera.stopPreview();
		} catch (Exception e) {
			// ignore: tried to stop a non-existent preview
		}

		// make any resize, rotate or reformatting changes here

		// start preview with new settings
		try {
			mCamera.setPreviewDisplay(mHolder);
			mCamera.startPreview();

		} catch (Exception e) {
			Log.d("DG_DEBUG",
					"Error starting camera preview: " + e.getMessage());
		}
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		// empty. Take care of releasing the Camera preview in your activity.
	}

}
