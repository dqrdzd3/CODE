package com.hw.smarthome.cam.helper;

/**
 * @author 曾凡
 * @time 2015年3月23日 上午11:47:39
 */
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import android.hardware.Camera;
import android.hardware.Camera.Size;

public class CameraHelper {
	private static final String KEY_PREVIEW_SIZE = "preview-size";
	private static final String KEY_PICTURE_SIZE = "picture-size";
	private static final String SUPPORTED_VALUES_SUFFIX = "-values";
	private Camera.Parameters mParms;
	private Camera mCamera;

	public CameraHelper(Camera camera) {
		mCamera = camera;
		this.mParms = camera.getParameters();
	}

	
	public List<Size> getSupportedPreviewSizes() {
		String str = mParms.get(KEY_PREVIEW_SIZE
				+ SUPPORTED_VALUES_SUFFIX);
		return splitSize(str);
	}

	public List<Size> getSupportedPictureSizes() {
		String str = mParms.get(KEY_PICTURE_SIZE
				+ SUPPORTED_VALUES_SUFFIX);
		return splitSize(str);
	}

	private ArrayList<Size> splitSize(String str) {
		if (str == null)
			return null;
		StringTokenizer tokenizer = new StringTokenizer(str, ",");
		ArrayList<Size> sizeList = new ArrayList<Size>();
		while (tokenizer.hasMoreElements()) {
			Size size = strToSize(tokenizer.nextToken());
			if (size != null)
				sizeList.add(size);
		}
		if (sizeList.size() == 0)
			return null;
		return sizeList;
	}

	private Size strToSize(String str) {
		if (str == null) {
			return null;
		}
		int pos = str.indexOf('x');
		if (pos != -1) {
			String width = str.substring(0, pos);
			String height = str.substring(pos + 1);
			return mCamera.new Size(Integer.parseInt(width),
					Integer.parseInt(height));
		}
		return null;
	}

}