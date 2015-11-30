package com.hw.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Matrix;
import android.os.Build.VERSION;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;

@SuppressLint("NewApi")
public class ImageUtil {
	
	/**
	 * 低版本毛玻璃算法
	 * 
	 * @author 曾凡
	 * @param data
	 * @param width
	 * @param height
	 * @param radius
	 * @param sigma
	 * @time 2015年7月10日 下午4:07:46
	 */
	public static void gaussBlur(int[] data, int width,
			int height, int radius, float sigma) {

		float pa = (float) (1 / (Math.sqrt(2 * Math.PI) * sigma));
		float pb = -1.0f / (2 * sigma * sigma);

		// generate the Gauss Matrix
		float[] gaussMatrix = new float[radius * 2 + 1];
		float gaussSum = 0f;
		for (int i = 0, x = -radius; x <= radius; ++x, ++i) {
			float g = (float) (pa * Math.exp(pb * x * x));
			gaussMatrix[i] = g;
			gaussSum += g;
		}

		for (int i = 0, length = gaussMatrix.length; i < length; ++i) {
			gaussMatrix[i] /= gaussSum;
		}

		// x direction
		for (int y = 0; y < height; ++y) {
			for (int x = 0; x < width; ++x) {
				float r = 0, g = 0, b = 0;
				gaussSum = 0;
				for (int j = -radius; j <= radius; ++j) {
					int k = x + j;
					if (k >= 0 && k < width) {
						int index = y * width + k;
						int color = data[index];
						int cr = (color & 0x00ff0000) >> 16;
						int cg = (color & 0x0000ff00) >> 8;
						int cb = (color & 0x000000ff);

						r += cr * gaussMatrix[j + radius];
						g += cg * gaussMatrix[j + radius];
						b += cb * gaussMatrix[j + radius];

						gaussSum += gaussMatrix[j + radius];
					}
				}

				int index = y * width + x;
				int cr = (int) (r / gaussSum);
				int cg = (int) (g / gaussSum);
				int cb = (int) (b / gaussSum);

				data[index] = cr << 16 | cg << 8 | cb
						| 0xff000000;
			}
		}

		// y direction
		for (int x = 0; x < width; ++x) {
			for (int y = 0; y < height; ++y) {
				float r = 0, g = 0, b = 0;
				gaussSum = 0;
				for (int j = -radius; j <= radius; ++j) {
					int k = y + j;
					if (k >= 0 && k < height) {
						int index = k * width + x;
						int color = data[index];
						int cr = (color & 0x00ff0000) >> 16;
						int cg = (color & 0x0000ff00) >> 8;
						int cb = (color & 0x000000ff);

						r += cr * gaussMatrix[j + radius];
						g += cg * gaussMatrix[j + radius];
						b += cb * gaussMatrix[j + radius];

						gaussSum += gaussMatrix[j + radius];
					}
				}

				int index = y * width + x;
				int cr = (int) (r / gaussSum);
				int cg = (int) (g / gaussSum);
				int cb = (int) (b / gaussSum);
				data[index] = cr << 16 | cg << 8 | cb
						| 0xff000000;
			}
		}
	}
	
	/**
	 * 获得一定大小的图片
	 * 
	 * @param bm
	 * @param newWidth
	 * @param newHeight
	 * @return
	 */
	public static Bitmap scaleImg(Bitmap bm, int newWidth,
			int newHeight) {
		// 图片源// Bitmap bm = BitmapFactory.decodeStream(getResources()//
		// .openRawResource(id));
		// 获得图片的宽高
		int width = bm.getWidth();
		int height = bm.getHeight();
		// 设置想要的大小
		int newWidth1 = newWidth;
		int newHeight1 = newHeight;
		// 计算缩放比例
		float scaleWidth = ((float) newWidth1) / width;
		float scaleHeight = ((float) newHeight1) / height;
		// 取得想要缩放的matrix参数
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);
		// 得到新的图片
		Bitmap newbm = Bitmap.createBitmap(bm, 0, 0, width,
				height, matrix, true);
		return newbm;
	}

	public static Bitmap blurBitmap(Context context, Bitmap bitmap,
			int radius) {
		// Let's create an empty bitmap with the same size of the bitmap we want
		// to blur
		Bitmap outBitmap = Bitmap.createBitmap(
				bitmap.getWidth(), bitmap.getHeight(),
				Config.ARGB_8888);

		// Instantiate a new Renderscript
		RenderScript rs = RenderScript.create(context
				.getApplicationContext());

		// Create an Intrinsic Blur Script using the Renderscript
		ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur
				.create(rs, Element.U8_4(rs));

		// Create the Allocations (in/out) with the Renderscript and the in/out
		// bitmaps
		Allocation allIn = Allocation.createFromBitmap(rs,
				bitmap);
		Allocation allOut = Allocation.createFromBitmap(rs,
				outBitmap);

		// Set the radius of the blur
		blurScript.setRadius(radius);

		// Perform the Renderscript
		blurScript.setInput(allIn);
		blurScript.forEach(allOut);

		// Copy the final bitmap created by the out Allocation to the outBitmap
		allOut.copyTo(outBitmap);

		// recycle the original bitmap
		bitmap.recycle();

		// After finishing everything, we destroy the Renderscript.
		rs.destroy();

		return outBitmap;

	}

	

	/**
	 * 
	 * 毛玻璃处理类
	 * 
	 * @author ycf
	 */

	@SuppressLint("NewApi")
	public static Bitmap fastblur(Context context,
			Bitmap sentBitmap, int radius) {
		if (VERSION.SDK_INT > 16) // 判断SDK的版本，可以更换
		{
			Bitmap bitmap = sentBitmap.copy(
					sentBitmap.getConfig(), true);
			final RenderScript rs = RenderScript.create(context);
			final Allocation input = Allocation
					.createFromBitmap(
							rs,
							sentBitmap,
							Allocation.MipmapControl.MIPMAP_NONE,
							Allocation.USAGE_SCRIPT);
			final Allocation output = Allocation.createTyped(rs,
					input.getType());
			final ScriptIntrinsicBlur script = ScriptIntrinsicBlur
					.create(rs, Element.U8_4(rs));
			script.setRadius(radius);/* e.g. 3.f */
			script.setInput(input);
			script.forEach(output);
			output.copyTo(bitmap);
			return bitmap;
		}

		Bitmap bitmap = sentBitmap.copy(sentBitmap.getConfig(),
				true);

		if (radius < 1) {
			return (null);
		}

		int w = bitmap.getWidth();
		int h = bitmap.getHeight();

		int[] pix = new int[w * h];

		bitmap.getPixels(pix, 0, w, 0, 0, w, h);

		int wm = w - 1;
		int hm = h - 1;
		int wh = w * h;
		int div = radius + radius + 1;

		int r[] = new int[wh];
		int g[] = new int[wh];
		int b[] = new int[wh];
		int rsum, gsum, bsum, x, y, i, p, yp, yi, yw;
		int vmin[] = new int[Math.max(w, h)];

		int divsum = (div + 1) >> 1;
		divsum *= divsum;
		int dv[] = new int[256 * divsum];
		for (i = 0; i < 256 * divsum; i++) {
			dv[i] = (i / divsum);
		}

		yw = yi = 0;

		int[][] stack = new int[div][3];
		int stackpointer;
		int stackstart;
		int[] sir;
		int rbs;
		int r1 = radius + 1;
		int routsum, goutsum, boutsum;
		int rinsum, ginsum, binsum;

		for (y = 0; y < h; y++) {
			rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
			for (i = -radius; i <= radius; i++) {
				p = pix[yi + Math.min(wm, Math.max(i, 0))];
				sir = stack[i + radius];
				sir[0] = (p & 0xff0000) >> 16;
				sir[1] = (p & 0x00ff00) >> 8;
				sir[2] = (p & 0x0000ff);
				rbs = r1 - Math.abs(i);
				rsum += sir[0] * rbs;
				gsum += sir[1] * rbs;
				bsum += sir[2] * rbs;
				if (i > 0) {
					rinsum += sir[0];
					ginsum += sir[1];
					binsum += sir[2];
				} else {
					routsum += sir[0];
					goutsum += sir[1];
					boutsum += sir[2];
				}
			}
			stackpointer = radius;

			for (x = 0; x < w; x++) {
				r[yi] = dv[rsum];
				g[yi] = dv[gsum];
				b[yi] = dv[bsum];

				rsum -= routsum;
				gsum -= goutsum;
				bsum -= boutsum;

				stackstart = stackpointer - radius + div;
				sir = stack[stackstart % div];

				routsum -= sir[0];
				goutsum -= sir[1];
				boutsum -= sir[2];

				if (y == 0) {
					vmin[x] = Math.min(x + radius + 1, wm);
				}
				p = pix[yw + vmin[x]];

				sir[0] = (p & 0xff0000) >> 16;
				sir[1] = (p & 0x00ff00) >> 8;
				sir[2] = (p & 0x0000ff);

				rinsum += sir[0];
				ginsum += sir[1];
				binsum += sir[2];

				rsum += rinsum;
				gsum += ginsum;
				bsum += binsum;

				stackpointer = (stackpointer + 1) % div;
				sir = stack[(stackpointer) % div];

				routsum += sir[0];
				goutsum += sir[1];
				boutsum += sir[2];

				rinsum -= sir[0];
				ginsum -= sir[1];
				binsum -= sir[2];

				yi++;
			}
			yw += w;
		}
		for (x = 0; x < w; x++) {
			rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
			yp = -radius * w;
			for (i = -radius; i <= radius; i++) {
				yi = Math.max(0, yp) + x;

				sir = stack[i + radius];

				sir[0] = r[yi];
				sir[1] = g[yi];
				sir[2] = b[yi];

				rbs = r1 - Math.abs(i);

				rsum += r[yi] * rbs;
				gsum += g[yi] * rbs;
				bsum += b[yi] * rbs;

				if (i > 0) {
					rinsum += sir[0];
					ginsum += sir[1];
					binsum += sir[2];
				} else {
					routsum += sir[0];
					goutsum += sir[1];
					boutsum += sir[2];
				}

				if (i < hm) {
					yp += w;
				}
			}
			yi = x;
			stackpointer = radius;
			for (y = 0; y < h; y++) {
				pix[yi] = (0xff000000 & pix[yi])
						| (dv[rsum] << 16) | (dv[gsum] << 8)
						| dv[bsum];

				rsum -= routsum;
				gsum -= goutsum;
				bsum -= boutsum;

				stackstart = stackpointer - radius + div;
				sir = stack[stackstart % div];

				routsum -= sir[0];
				goutsum -= sir[1];
				boutsum -= sir[2];

				if (x == 0) {
					vmin[y] = Math.min(y + r1, hm) * w;
				}
				p = x + vmin[y];

				sir[0] = r[p];
				sir[1] = g[p];
				sir[2] = b[p];

				rinsum += sir[0];
				ginsum += sir[1];
				binsum += sir[2];

				rsum += rinsum;
				gsum += ginsum;
				bsum += binsum;

				stackpointer = (stackpointer + 1) % div;
				sir = stack[stackpointer];

				routsum += sir[0];
				goutsum += sir[1];
				boutsum += sir[2];

				rinsum -= sir[0];
				ginsum -= sir[1];
				binsum -= sir[2];

				yi += w;
			}
		}

		bitmap.setPixels(pix, 0, w, 0, 0, w, h);
		return (bitmap);
	}
	/*
	 * 调用方法
	 * 
	 * layout=(RelativeLayout)this.findViewById(R.id.layout); final Bitmap
	 * blurBmp = BlurUtil.fastblur(MainActivity.this, bmp, 10);//0-25，表示模糊值
	 * 
	 * final Drawable newBitmapDrawable = new BitmapDrawable(blurBmp); //
	 * 将Bitmap转换为Drawable
	 * 
	 * layout.post(new Runnable() //调用UI线程
	 * 
	 * {
	 * 
	 * @Override
	 * 
	 * public void run()
	 * 
	 * {
	 * 
	 * layout.setBackgroundDrawable(newBitmapDrawable);//设置背景
	 * 
	 * }
	 * 
	 * });
	 */

}
