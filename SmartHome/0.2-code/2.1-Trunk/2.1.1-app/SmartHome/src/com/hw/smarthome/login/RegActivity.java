package com.hw.smarthome.login;

import static eu.inmite.android.lib.validations.form.annotations.RegExp.EMAIL;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.google.gson.JsonObject;
import com.hanwei.androidpn.LogUtil;
import com.hw.smarthome.R;
import com.hw.smarthome.login.server.LoginServerReq;
import com.hw.smarthome.po.UserPO;
import com.hw.smarthome.server.constant.ServerConstant;
import com.hw.smarthome.server.deal.DealWithAccount;
import com.hw.smarthome.server.util.SmartHomeJsonUtil;
import com.hw.smarthome.ui.MainActivity;
import com.hw.smarthome.ui.constant.UIConstant;
import com.hw.smarthome.ui.setting.sub.AccountFragment;
import com.hw.util.FileUtils;
import com.hw.util.ImageUtil;
import com.hw.util.Ln;
import com.hw.util.NetUtil;
import com.hw.util.StringUtil;
import com.hw.util.UIUtil;
import com.hw.util.WindowTools;
import com.hw.util.application.SHApplication;

import eu.inmite.android.lib.validations.form.FormValidator;
import eu.inmite.android.lib.validations.form.annotations.NotEmpty;
import eu.inmite.android.lib.validations.form.annotations.RegExp;
import eu.inmite.android.lib.validations.form.callback.SimpleErrorPopupCallback;

public class RegActivity extends Activity {

	public static final String LOCAL_PIC_URL = Environment
			.getExternalStorageDirectory().getAbsolutePath() + "/smarthome/";
	public static final String LOCAL_VIDEO_URL = Environment
			.getExternalStorageDirectory().getAbsolutePath()
			+ "/smarthome/video/";

	public static final String LOCAL_PIC = "picture";
	private static final String tag = LogUtil.makeLogTag(RegActivity.class);
	public static RegActivity mContext;

	@NotEmpty(messageId = R.string.infor_null, order = 1)
	private EditText testname;

	@NotEmpty(messageId = R.string.infor_null, order = 2)
	@RegExp(value = "^[0-9]{11}$", messageId = R.string.phone_wrong)
	private EditText telphone;
	@NotEmpty(messageId = R.string.infor_null, order = 3)
	private EditText pass;
	@NotEmpty(messageId = R.string.infor_null, order = 4)
	private EditText passAgain;

	
	private AutoCompleteTextView account;
	public ImageView mypic;
	private CheckBox cbProtocol;
	private TextView lookpro;
	private final String IMAGE_TYPE = "image/*";
	private List<Bitmap> imageList = new ArrayList<Bitmap>();
	private List<File> uploadFile = new ArrayList<File>();
	private final int IMAGE_REQUEST_CODE = 0; // 相册请求
	private final int CAMERA_REQUEST_CODE = 1; // 相机请求
	String basePath = LOCAL_PIC_URL + LOCAL_PIC;
	private String mPhotoPath;
	Handler handler;

	private static final int HANDLE_MESSAGE_IMAGELIST = -201;
	ProgressDialog pDialog;
	String orgPath = "";// 图片原始地址
	// int type = Constant.SERVER_METER_TYPE_GAS;
	private String[] arrayString = new String[] { "@3g.sina.cn", "@sina.com",
			"@163.com", "@qq.com", "@126.com", "@vip.sina.com", "@sina.cn",
			"@hotmail.com", "@gmail.com", "@sohu.com", "@yahoo.com", "@tom.com" };
	private String[] arrayString2 = new String[arrayString.length];
	private LocationClient mLocationClient;
	SHApplication application;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.login_reg);
		mContext = this;
		testname = (EditText) findViewById(R.id.test_name);
		account = (AutoCompleteTextView) findViewById(R.id.accountId);
		telphone = (EditText) findViewById(R.id.mobileId);
		pass = (EditText) findViewById(R.id.pass);
		passAgain = (EditText) findViewById(R.id.pass_again);
		mypic = (ImageView) findViewById(R.id.mypic);
		cbProtocol = (CheckBox) findViewById(R.id.protocol);
		lookpro = (TextView) findViewById(R.id.look_pro);
		WindowTools.initSystemBar(getWindow());
		mLocationClient = ((SHApplication)getApplication()).mLocationClient;
		initMail();
	
		
		((TextView)findViewById(R.id.tip_tel)).setText(Html.fromHtml(UIConstant.ACCOUNT));
		((TextView)findViewById(R.id.tip_pass)).setText(Html.fromHtml(UIConstant.ACCOUNT_PASS));
		((TextView)findViewById(R.id.tip_passa)).setText(Html.fromHtml(UIConstant.ACCOUNT_PASSA));
		
		application = (SHApplication)getApplication();
		application.mTv = (TextView)findViewById(R.id.lat);
		TextView iforTextView = (TextView) findViewById(R.id.tyxy);
		iforTextView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(RegActivity.this, ProtocolActivity.class);
				startActivityForResult(i, 0);
			}
		});
		
		mypic.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				imageList.clear();
				showSinChosDia();
				handleMessage();
			}
		});
		// 查看协议内容
		lookpro.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(RegActivity.this, ProtocolActivity.class);
				startActivityForResult(i, 0);

			}
		});
		account.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (event != null && event.ACTION_DOWN == event.getAction()
						&& keyCode == event.KEYCODE_ENTER) {
					doReg(findViewById(R.id.regbtn));
					return true;
				}
				return false;
			}
		});
		InitLocation();
		//定位开始
		mLocationClient.start();
		
		
	}

	private void initMail() {
		// TODO Auto-generated method stub
		account.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				String input = s.toString();
				if (input.length() > 0) {
					if (input.indexOf("@") == -1) {
						for (int i = 0; i < arrayString.length; i++) {
							arrayString2[i] = input + arrayString[i];
						}
						account.setAdapter(new ArrayAdapter<String>(
								RegActivity.this, R.layout.mailauto_item,
								R.id.textView1, arrayString2));
						account.setThreshold(0);
						// account.setCompletionHint("选择后缀");

						account.showDropDown();
					}
				}
			}
		});
	}
	private String latString,lngString,locString;//纬度、经度、位置
	private String province,city,area; //省市区
	public void doReg(final View view) {
		String urlj = ServerConstant.LOC_INFO+"&location="+application.mTv.getText();
		FinalHttp fhh = new FinalHttp();
		fhh.get(urlj, new AjaxCallBack<Object>(){
			@Override
			public void onSuccess(Object t) {
				try {
					JSONObject jsonObject = new JSONObject(t.toString());
					jsonObject = jsonObject.getJSONObject("result");
					if (jsonObject.has("location")) {
						latString = jsonObject.getJSONObject("location").getString("lat");
						lngString = jsonObject.getJSONObject("location").getString("lng");
						
						locString =  jsonObject.getString("formatted_address");
					}
					
					if (jsonObject.has("addressComponent")) {
						province = jsonObject.getJSONObject("addressComponent").getString("province");
						city  = jsonObject.getJSONObject("addressComponent").getString("city");
						area  = jsonObject.getJSONObject("addressComponent").getString("district");
					}
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					submitReg(view);
				}
			}
		});
		
		/* TODO 验证逻辑 */
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
		

	}
	private void submitReg(final View view){
		if (!NetUtil.isNetworkConnected(RegActivity.this)) {
			UIUtil.ToastMessage(mContext, "网络不通");
			return;
		}
		boolean isValid = FormValidator.validate(this,
				new SimpleErrorPopupCallback(this, true));

		if (isValid) {
			if (!telphone.getText().toString().startsWith("1")) {
				UIUtil.ToastMessage(mContext, "手机号码错误");
				return;
			}
			if (testname.getText().length()>16 && testname.getText().length()<1) {
				UIUtil.ToastMessage(mContext, "匿名长度不符合规范");
				return;
			}
			if (account.getText().toString().length()>0) {
				if (account.getText().toString().indexOf("@") == -1 || account.getText().toString().indexOf(".") == -1) {
					UIUtil.ToastMessage(mContext, "邮箱不符合规范");
					return;
				}
			}
			if (!pass.getText().toString()
							.equals(passAgain.getText().toString())) {
				UIUtil.ToastMessage(mContext, "两次密码输入不一致");
				return;
			}
			if (!StringUtil.isEmpty(pass.getText().toString()) 
					&& pass.getText().toString().length() > 5
					&& pass.getText().toString()
							.equals(passAgain.getText().toString())) {

				AjaxParams paramMap = new AjaxParams();

				paramMap.put("USERNAME", testname.getText().toString());
				paramMap.put("EMAIL", account.getText().toString());
				paramMap.put("PHONE", telphone.getText().toString());
				paramMap.put("PASSWORD", pass.getText().toString());
				paramMap.put("LAT", latString);
				paramMap.put("LNG", lngString);
				paramMap.put("LOC", locString);
				paramMap.put("PROVICE", province);
				paramMap.put("CITY", city);
				paramMap.put("AREA", area);
				if (uploadFile != null && uploadFile.size() > 0) {
					try {
						paramMap.put("file", uploadFile.get(0));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				if (cbProtocol.isChecked()) {
					/* 登陆这个页面后从服务器获取最新的列表 */
					String url = ServerConstant.SERVER_BASE_URI
							+ "u001!doCreateAccount";

					FinalHttp fh = new FinalHttp();

					/**
					 * 设置错误连接次数
					 */
					fh.configRequestExecutionRetryCount(0);
					fh.post(url, paramMap, new AjaxCallBack<Object>() {

						@Override
						public void onSuccess(Object t) {
							JSONObject jo;

							try {

								jo = new JSONObject(t.toString());

								Toast.makeText(RegActivity.this,
										jo.getString("message"), 5).show();
								if (jo.getString("code").equals("1")) {
									String userString = jo.getString("data");
									UserPO userPO = SmartHomeJsonUtil
											.getUserPOFrom(userString);
									if (userPO != null) {
										/* 2.将结果持久化 */
										view.setEnabled(false);
										DealWithAccount.saveUser(
												RegActivity.this, userPO);
										DealWithAccount.saveAccountAndPwd(
												RegActivity.this, telphone
														.getText().toString(),
												pass.getText().toString());
									}
									Intent intent = new Intent();
									intent.setClass(RegActivity.this,
											LoginActivity.class);
									startActivity(intent);
									
									mLocationClient.stop();
									finish();
								}

							} catch (JSONException e) {
								Ln.e(e);
								Toast.makeText(RegActivity.this, "提交失败", 5)
										.show();
							}

						}

						@Override
						public void onFailure(Throwable t, int errorNo,
								String strMsg) {
							// TODO Auto-generated method stub
							super.onFailure(t, errorNo, strMsg);
							Toast.makeText(RegActivity.this, "网络不正常，请稍后再试", 5).show();
						}

					});

				} else {
					UIUtil.ToastMessage(mContext, "请仔细查看本协议并同意");
				}

			} else {
				UIUtil.ToastMessage(mContext, "密码出错或长度小于六位");
			}

		}
	}
	/* 单项选择对话框 */
	int yourChose = -1;

	private void showSinChosDia() {
		delFiles();
		final String[] mList = { "相机拍照", "手机相册" };
		yourChose = -1;
		AlertDialog.Builder sinChosDia = new AlertDialog.Builder(this);
		sinChosDia.setTitle("选择照片");
		sinChosDia.setItems(mList, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				yourChose = which;
				Log.d(tag, "choose-->" + which);
				dialog.dismiss();
				switch (which) {
				case 0:
					getCameraImage();
					break;
				case 1:
					getAlbumImage();
					break;
				}

			}
		});

		sinChosDia.create().show();
	}

	private void getAlbumImage() {
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		intent.setType(IMAGE_TYPE);
		intent.putExtra("return-data", true);
		Intent wrapperIntent = Intent.createChooser(intent, null);
		startActivityForResult(wrapperIntent, IMAGE_REQUEST_CODE);
	}

	private void getCameraImage() {

		File fos = null;
		try {
			String fileName = getPhotoFileName();

			fos = FileUtils.createFile(basePath, fileName);
			takePhotoName = fileName;
		} catch (Exception e) {
			e.printStackTrace();
		}
		Uri u = Uri.fromFile(fos);
		Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		i.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
		i.putExtra(MediaStore.EXTRA_OUTPUT, u);

		this.startActivityForResult(i, CAMERA_REQUEST_CODE);
	}

	/**
	 * 
	 * @Title: showImage
	 * @Description: TODO 在界面上展示图片
	 * @author: lijing
	 * @throws
	 */
	private void showImage() {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					if (!TextUtils.isEmpty(mPhotoPath)) {
						String[] arr = mPhotoPath.split(",");
						int length = arr.length;
						Message msg = new Message();
						msg.what = HANDLE_MESSAGE_IMAGELIST;
						for (int i = 0; i < length; i++) {
							Log.e(tag, "path-->" + arr[i]);
							File f = new File(arr[i]);
							uploadFile.add(f);
							Bitmap bm = decodeFile(f);
							imageList.add(bm);

							// imageAdapter.setProducts(imageList);
						}
						handler.sendMessage(msg);
					}
				} catch (Exception e) {

				}
			}
		});
		thread.start();
	}

	/**
	 * 
	 * 
	 * 函 数 名：reciveImage 功能描述：(显示从相册或相机拍照获取的图片) 输入参数：(按照参数定义顺序,
	 * 
	 * @param后面空格后跟着参数的变量名字（不是类型）， 空格后跟着对该参数的描述.) 返 回 值： (返回为空（void）的构造函数或者函数
	 * @return可以省略; 如果返回值就是输入参数， 必须用与输入参数的@param相同的描述信息; 必要的时候注明特殊条件写的返回值) 异 常：
	 *              (按照异常名字的字母顺序) 创建人：lijing 创建时间：2013-11-13 上午10:10:15 修改人：
	 *              修改时间： 修改原因描述：
	 */
	private void reciveImage(final File file) {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Bitmap bm = decodeFile(file);
					imageList.add(bm);
					Message msg = new Message();
					msg.what = HANDLE_MESSAGE_IMAGELIST;
					handler.sendMessage(msg);

				} catch (Exception e) {
					Log.e(tag, "Exception-->" + e.toString());
				}
			}
		});
		thread.start();
	}

	/**
	 * 用时间戳生成照片名称
	 * 
	 * @return
	 */
	private String getPhotoFileName() {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"'IMG'_yyyyMMdd_HHmmss");
		int n = (int) (Math.random() * 1000);
		return dateFormat.format(date) + "_" + n + ".jpg";
	}

	public static Bitmap decodeFile(File f) {

		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inJustDecodeBounds = true;

		int temp = 1;

		long fileSize = f.length();

		if (fileSize > 4000000) {
			temp = 20;
		} else if (fileSize > 1000000) {
			temp = 10;
		} else if (fileSize > 100000) {
			temp = (int) (fileSize / (1024 * 100));
		} else {
			temp = 1;
		}
		opts.inSampleSize = temp; // 改变图片大小
		opts.inJustDecodeBounds = false;
		opts.inPreferredConfig = Bitmap.Config.ARGB_8888;
		try {
			return ImageUtil.scaleImg(BitmapFactory.decodeFile(f.getAbsolutePath(),
					opts),90,90);
		} catch (OutOfMemoryError err) {
			Log.w(tag, err.toString());
		}

		return null;

	}

	String takePhotoName = "";
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == 3) {
			cbProtocol.setChecked(true);
		}
		if (resultCode == 4) {
			cbProtocol.setChecked(false);
		}
		// 外界的程序访问ContentProvider所提供数据 可以通过ContentResolver接口
		if (resultCode == RESULT_OK) {
			
			if (requestCode == CAMERA_REQUEST_CODE) {

				File bb = FileUtils.createFile(basePath, takePhotoName);
				DealWithAccount.saveMypic(RegActivity.this, basePath
						+ takePhotoName);
				File f = null;

				String fileName = getPhotoFileName();

				f = FileUtils.createFile(basePath,
						fileName);
				Bitmap bm = decodeFile(bb);
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				bm.compress(CompressFormat.PNG,
						0 /* ignored for PNG */, bos);
				byte[] bitmapdata = bos.toByteArray();

				// write the bytes in file
				FileOutputStream fos;
				try {
					fos = new FileOutputStream(f);
					fos.write(bitmapdata);

					DealWithAccount.saveMypic(this, basePath
							+ fileName);
					AccountFragment.getInstance().setFile(f);
					uploadFile.add(f);
					fos.flush();
					fos.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				reciveImage(f);
				return;
			}
			if (requestCode == IMAGE_REQUEST_CODE
					|| requestCode == CAMERA_REQUEST_CODE) {
				try {
					if (data == null)
						return;
					Uri originalUri = data.getData(); // 获得图片的uri

					ContentResolver cr = this.getContentResolver();

					try {
						BitmapFactory.Options opt = new BitmapFactory.Options();


						opt.inPreferredConfig = Bitmap.Config.RGB_565;
						opt.inJustDecodeBounds = true;

						opt.inPurgeable = true;


						opt.inInputShareable = true;

//						   final int REQUIRED_SIZE = 90;
//
//				            // Find the correct scale value. It should be the power of 2.
//				            int scale = 1;
//				            while (opt.outWidth / scale / 2 >= REQUIRED_SIZE
//				                    && opt.outHeight / scale / 2 >= REQUIRED_SIZE)
//				                scale *= 2;
//
//			
//				            opt.inSampleSize = scale;
//				       




						Bitmap bmp = BitmapFactory.decodeStream(cr
								.openInputStream(originalUri));
						bmp = ImageUtil.scaleImg(bmp, 90, 90);
						mypic.setImageBitmap(bmp);
						File f = null;

						String fileName = getPhotoFileName();

						f = FileUtils.createFile(basePath, fileName);

						ByteArrayOutputStream bos = new ByteArrayOutputStream();
						bmp.compress(CompressFormat.PNG,
								0 /* ignored for PNG */, bos);
						byte[] bitmapdata = bos.toByteArray();

						// write the bytes in file
						FileOutputStream fos = new FileOutputStream(f);
						fos.write(bitmapdata);
						DealWithAccount.saveMypic(RegActivity.this, basePath
								+ fileName);

						uploadFile.add(f);

					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} catch (Exception e) {

					Log.e("TAG-->Error", e.toString());

				}
			}
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		// 图片释放，防止OOM

		if (!imageList.isEmpty()) {
			for (Bitmap b : imageList) {
				if (b != null)
					b.recycle();
			}
		}

	}
	private void delFiles(){
		if (FileUtils.checkSaveLocationExists()) {
			FileUtils.deleteDirectoryFile(LOCAL_PIC_URL + LOCAL_PIC);
		}
	}
	private LocationMode tempMode = LocationMode.Hight_Accuracy;
	private String tempcoor="bd09ll";
	private void InitLocation(){
		LocationClientOption option = new LocationClientOption();
		option.setLocationMode(tempMode);//设置定位模式
		option.setCoorType(tempcoor);//返回的定位结果是百度经纬度，默认值gcj02
		int span=1000;
		
		option.setScanSpan(span);//设置发起定位请求的间隔时间为5000ms
		option.setIsNeedAddress(true);
		mLocationClient.setLocOption(option);
	}
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		mLocationClient.stop();
		super.onStop();
	}
	private void handleMessage() {
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case HANDLE_MESSAGE_IMAGELIST:
					// imageAdapter.setProducts(imageList);
					mypic.setImageBitmap(imageList.get(0));
					break;
				}
				super.handleMessage(msg);

			}
		};
	}
}
