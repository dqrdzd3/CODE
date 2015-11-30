package com.hw.smarthome.ui.setting.sub;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.hw.smarthome.R;
import com.hw.smarthome.login.RegActivity;
import com.hw.smarthome.po.UserPO;
import com.hw.smarthome.server.constant.ServerConstant;
import com.hw.smarthome.server.deal.DealWithAccount;
import com.hw.smarthome.server.util.SmartHomeJsonUtil;
import com.hw.smarthome.service.SmartHomeService;
import com.hw.smarthome.ui.MainActivity;
import com.hw.smarthome.ui.util.MainAcUtil;
import com.hw.util.FileUtils;
import com.hw.util.Ln;
import com.hw.util.UIUtil;

import eu.inmite.android.lib.validations.form.FormValidator;
import eu.inmite.android.lib.validations.form.annotations.NotEmpty;
import eu.inmite.android.lib.validations.form.callback.SimpleErrorPopupCallback;

/**
 * @author 曾凡
 * @time 2014年6月9日 下午2:43:21
 */
public class AccountFragment extends Fragment {
	private static AccountFragment instance;
	@NotEmpty(messageId = R.string.infor_null, order = 1)
	private EditText pass1;
	@NotEmpty(messageId = R.string.infor_null, order = 2)
	private EditText pass2;
	@NotEmpty(messageId = R.string.infor_null, order = 3)
	private EditText pass3;
	@NotEmpty(messageId = R.string.infor_null, order = 4)
	private EditText accountName;
	public ImageView headImageView;
	private TextView tel;

	private AutoCompleteTextView mail;

	private final String IMAGE_TYPE = "image/*";
	private List<Bitmap> imageList = new ArrayList<Bitmap>();
	private List<File> uploadFile = new ArrayList<File>();
	private final int IMAGE_REQUEST_CODE = 0; // 相册请求
	private final int CAMERA_REQUEST_CODE = 1; // 相机请求
	String basePath = RegActivity.LOCAL_PIC_URL
			+ RegActivity.LOCAL_PIC;
	private String mPhotoPath;
	Handler handler;
	UserPO userPO;
	private static final int HANDLE_MESSAGE_IMAGELIST = -201;
	ProgressDialog pDialog;
	private byte[] tempBytePic = null;
	String orgPath = "";// 图片原始地址

	// 注册广播接收器
	MyReceiver receiver = new MyReceiver();

	public static AccountFragment getInstance() {
		if (instance == null) {
			instance = new AccountFragment();
		}
		return instance;
	}

	private String[] arrayString = new String[] { "@3g.sina.cn",
			"@sina.com", "@163.com", "@qq.com", "@126.com",
			"@vip.sina.com", "@sina.cn", "@hotmail.com",
			"@gmail.com", "@sohu.com", "@yahoo.com", "@tom.com" };
	private String[] arrayString2 = new String[arrayString.length];
	private View parentView;

	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		parentView = inflater.inflate(
				R.layout.ui_settings_account, container, false);
		return parentView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		getActivity()
				.getWindow()
				.setSoftInputMode(
						WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
		pass1 = (EditText) parentView.findViewById(R.id.chgPw);
		pass2 = (EditText) parentView
				.findViewById(R.id.chgPwNew);
		pass3 = (EditText) parentView
				.findViewById(R.id.pwNewRepeat);

		accountName = (EditText) parentView
				.findViewById(R.id.accountname);
		tel = (TextView) parentView.findViewById(R.id.mobileId);
		mail = (AutoCompleteTextView) parentView
				.findViewById(R.id.accountId);
		headImageView = (ImageView) parentView
				.findViewById(R.id.headinfo);

		headImageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				((MainActivity) getActivity()).imageList.clear();
				showSinChosDia();
				handleMessage();
			}
		});
		userPO = DealWithAccount.getUser(getActivity());

		accountName.setText(userPO.getMa008());
		tel.setText(userPO.getMa006());
		mail.setText(userPO.getMa005());
		// if (!NetUtil.isNetworkConnected(getActivity())) {// 无网络
		// String pathString = DealWithAccount.getMypic(getActivity());
		// if (pathString != null && pathString.length() > 0) {
		// headImageView.setImageBitmap(BitmapFactory
		// .decodeFile(pathString));
		// }
		//
		// } else {
		// if (userPO.getMa012() != null && userPO.getMa012().length() > 0) {
		// // headImageView.setImageBitmap(returnBitMap(userPO.getMa012()));
		// returnBitMap(userPO.getMa012());
		// }
		//
		// }
		if (userPO.getMa017() != null) {
			tempBytePic = userPO.getMa017();
			headImageView.setImageBitmap(BitmapFactory
					.decodeByteArray(userPO.getMa017(), 0,
							userPO.getMa017().length));
		}

		initMail();

		BootstrapButton chaninfoButton = (BootstrapButton) getActivity()
				.findViewById(R.id.changeInfo);
		BootstrapButton chanPwButton = (BootstrapButton) getActivity()
				.findViewById(R.id.changepw);

		chaninfoButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				/* 登陆这个页面后从服务器获取最新的列表 */
				InputMethodManager imm = (InputMethodManager) getActivity()
						.getSystemService(
								Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(v.getWindowToken(),
						0);
				if (accountName.getText().toString().trim()
						.length() < 0) {
					UIUtil.ToastMessage(getActivity(), "昵称不能为空");
					return;
				}
				if (mail.getText().length() > 0) {
					if (mail.getText().toString().indexOf("@") < 0
							|| mail.getText().toString()
									.indexOf(".") < 0) {
						UIUtil.ToastMessage(getActivity(),
								"邮箱不合法");
						mail.setText(userPO.getMa005());
						return;
					}
				}

				boolean isValid = FormValidator.validate(
						getActivity(),
						new SimpleErrorPopupCallback(
								getActivity(), true));
				if (isValid) {
					// Map<String, String> paramMap = new HashMap<String,
					// String>();
					//
					// paramMap.put("USERNAME",
					// accountName.getText().toString());
					// paramMap.put("EMAIL", mail.getText().toString());
					// paramMap.put("PHONE", tel.getText().toString());
					// MainAcUtil.send2Service(getActivity(),
					// ServerConstant.SH01_03_01_02, 0, paramMap);
					if (uploadFile != null
							&& uploadFile.size() > 0) {
						File imgFile = uploadFile.get(0);
						if (imgFile != null) {
							tempBytePic = fileToStream(imgFile);
						}
					}


					if (userPO.getMa008().equals(
							accountName.getText().toString())
							&& tel.getText().toString()
									.equals(userPO.getMa006())

					) {
						if (mail.getText().toString()
								.equals(userPO.getMa005())) {
							if (userPO.getMa017() == null
									&& tempBytePic == null) {
								UIUtil.ToastMessage(
										getActivity(), "无修改内容");
								return;
							}
							if (userPO.getMa017() != null
									&& tempBytePic != null && userPO.getMa017().equals(
									tempBytePic)) {
								UIUtil.ToastMessage(
										getActivity(), "无修改内容");
								return;
							}
						}

					}

					AjaxParams paramMap = new AjaxParams();

					paramMap.put("USERNAME", accountName
							.getText().toString());
					paramMap.put("EMAIL", mail.getText()
							.toString());
					paramMap.put("PHONE", tel.getText()
							.toString());
					paramMap.put("USERID",
							SmartHomeService.getUser()[0]);
					paramMap.put("SESSIONID",
							SmartHomeService.getUser()[1]);
					if (uploadFile != null
							&& uploadFile.size() > 0) {
						try {
							paramMap.put("file",
									uploadFile.get(0));
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}
					}
					String url = ServerConstant.SERVER_BASE_URI
							+ "u001!doEditInfo";

					FinalHttp fh = new FinalHttp();

					/**
					 * 设置错误连接次数
					 */
					fh.configRequestExecutionRetryCount(0);
					fh.post(url, paramMap,
							new AjaxCallBack<Object>() {

								@Override
								public void onSuccess(Object t) {
									JSONObject jo;

									try {

										jo = new JSONObject(t
												.toString());

										Toast.makeText(
												getActivity(),
												jo.getString("message"),
												5).show();
										if (jo.getString("code")
												.equals("1")) {
											String userString = jo
													.getString("data");
											userPO = SmartHomeJsonUtil
													.getUserPOFrom(userString);
											String pathString = DealWithAccount
													.getMypic(getActivity());

											if (userPO != null) {
												/* 2.将结果持久化 */
												DealWithAccount
														.saveUser(
																getActivity(),
																userPO);
												userPO.setMa017(tempBytePic);
												// DealWithAccount.saveMypic(
												// getActivity(), uploadFile
												// .get(0).getPath());
											}

										}

									} catch (Exception e) {
										Ln.e(e);
										try {
											Toast.makeText(
													getActivity(),
													"提交失败", 5)
													.show();
										} catch (Exception e1) {
											Ln.e(e1, "弹出提交失败异常");
										}
									}

								}

								@Override
								public void onFailure(
										Throwable t,
										int errorNo,
										String strMsg) {
									// TODO Auto-generated method stub
									super.onFailure(t, errorNo,
											strMsg);
									Toast.makeText(
											getActivity(),
											strMsg, 5).show();
								}

							});
				}
			}
		});

		chanPwButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				InputMethodManager imm = (InputMethodManager) getActivity()
						.getSystemService(
								Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(v.getWindowToken(),
						0);
				if (pass2.getText().toString().trim().length() < 6) {
					UIUtil.ToastMessage(getActivity(),
							"新密码长度小于六位");
					return;
				}
				if (pass1.getText().toString().trim().length() > 0
						&& pass2.getText().toString().trim()
								.length() > 0
						&& pass3.getText().toString().trim()
								.length() > 0) {
					if (pass2.getText().toString()
							.equals(pass3.getText().toString())) {
						/* 登陆这个页面后从服务器获取最新的列表 */
						Map<String, String> paramMap = new HashMap<String, String>();
						paramMap.put("oldpass", pass1.getText()
								.toString());
						paramMap.put("MA009", pass2.getText()
								.toString());

						MainAcUtil.send2Service(getActivity(),
								ServerConstant.SH01_03_02_03, 0,
								paramMap);
					} else {
						UIUtil.ToastMessage(getActivity(),
								"新密码和重复密码不一致");
						return;
					}

				}
			}
		});

		IntentFilter filter = new IntentFilter();
		filter.addAction(SmartHomeService.class.getName());
		getActivity().registerReceiver(receiver, filter);
		// /* 登陆这个页面后从服务器获取最新的列表 */
		// MainAcUtil.send2Service(getActivity(),
		// ServerConstant.SH01_03_02_03, 0);

	}

	@Override
	public void onResume() {
		super.onResume();
		accountName.setText(userPO.getMa008());
		tel.setText(userPO.getMa006());
		mail.setText(userPO.getMa005());
	}

	private void initMail() {
		// TODO Auto-generated method stub
		mail.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start,
					int before, int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s,
					int start, int count, int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				String input = s.toString();
				if (input.length() > 0) {
					if (input.indexOf("@") == -1) {
						for (int i = 0; i < arrayString.length; i++) {
							arrayString2[i] = input
									+ arrayString[i];
						}
						mail.setAdapter(new ArrayAdapter<String>(
								getActivity(),
								R.layout.mailauto_item,
								R.id.textView1, arrayString2));
						mail.setThreshold(0);
						// account.setCompletionHint("选择后缀");

						mail.showDropDown();
					}
				}
			}
		});
	}

	private class DownloadImageTask extends
			AsyncTask<String, Void, Drawable> {

		protected Drawable doInBackground(String... urls) {
			return loadImageFromNetwork(urls[0]);
		}

		protected void onPostExecute(Drawable result) {
			headImageView.setImageDrawable(result);
		}
	}

	private Drawable loadImageFromNetwork(String imageUrl) {
		Drawable drawable = null;
		try {
			// 可以在这里通过文件名来判断，是否本地有此图片
			drawable = Drawable.createFromStream(new URL(
					imageUrl).openStream(), userPO.getMa012());
		} catch (IOException e) {
			Log.d("test", e.getMessage());
		} catch (Exception e) {
			Log.d("test", e.getMessage());
		}
		if (drawable == null) {
			Log.d("test", "null drawable");
		} else {
			Log.d("test", "not null drawable");
		}

		return drawable;
	}

	public void returnBitMap(String url) {
		// URL myFileUrl = null;
		// Bitmap bitmap = null;
		url = url.replace('\\', '/');
		// try {
		// myFileUrl = new URL(ServerConstant.BASE_URI+url);
		// } catch (MalformedURLException e) {
		// e.printStackTrace();
		// }
		new DownloadImageTask().execute(ServerConstant.BASE_URI
				+ "upload/" + url);

		// FinalBitmap finalBitmap = FinalBitmap.create(getActivity());
		// try {
		// finalBitmap.display(headImageView,
		// ServerConstant.BASE_URI+"upload/"+url);
		// }catch (Exception e) {
		// // TODO: handle exception
		// headImageView.setImageResource(R.drawable.mini_avatar_shadow);
		// }

	}

	/**
	 * 获取广播数据验证是否正确
	 */
	public class MyReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			Bundle bundle = intent.getBundleExtra("action");
			if (ServerConstant.SH01_03_02_03.equals(bundle
					.getString("name"))) {
				if (bundle.getBoolean("result")) { // 返回正确

					Toast.makeText(getActivity(), "更新成功",
							Toast.LENGTH_LONG).show();

					pass1.setText("");
					pass2.setText("");
					pass3.setText("");
				} else {
					Toast.makeText(getActivity(), "输入原密码错误",
							Toast.LENGTH_LONG).show();
				}
			}
			if (ServerConstant.SH01_03_01_02.equals(bundle
					.getString("name"))) {
				if (bundle.getBoolean("result")) { // 返回正确

					Toast.makeText(getActivity(), "更新成功",
							Toast.LENGTH_LONG).show();
				} else {
					Toast.makeText(getActivity(), "修改失败",
							Toast.LENGTH_LONG).show();
				}
			}

		}
	}

	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		getActivity().unregisterReceiver(receiver);
		if (!imageList.isEmpty()) {
			for (Bitmap b : imageList) {
				if (b != null)
					b.recycle();
			}
		}
	}

	/* 单项选择对话框 */
	int yourChose = -1;

	private void showSinChosDia() {
		delFiles();
		final String[] mList = { "相机拍照", "手机相册" };
		yourChose = -1;
		AlertDialog.Builder sinChosDia = new AlertDialog.Builder(
				getActivity());
		sinChosDia.setTitle("选择照片");
		sinChosDia.setItems(mList,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog,
							int which) {
						// TODO Auto-generated method stub
						yourChose = which;

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
		startActivityForResult(intent, IMAGE_REQUEST_CODE);
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

	public String getFilename() {
		return takePhotoName;
	}

	private void delFiles() {
		if (FileUtils.checkSaveLocationExists()) {
			FileUtils
					.deleteDirectoryFile(RegActivity.LOCAL_PIC_URL
							+ RegActivity.LOCAL_PIC);
		}
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
		opts.inSampleSize = temp;
		opts.inJustDecodeBounds = false;
		opts.inPreferredConfig = Bitmap.Config.ARGB_8888;
		try {
			return BitmapFactory.decodeFile(f.getAbsolutePath(),
					opts);
		} catch (OutOfMemoryError err) {

		}

		return null;

	}

	public void setFile(File file) {
		uploadFile.clear();
		uploadFile.add(file);
	}

	String takePhotoName = "";

	private void handleMessage() {
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				switch (msg.what) {
				// case FinalHttpUtil.RESULT_SUCCESS:
				// pDialog.dismiss();
				// UIUtil.ToastMessage(RegActivity.this, "添加成功");
				// setResult(Constant.INTENT_RESULTCODE_METERADDACTIVITY);
				// finish();
				// break;
				// case FinalHttpUtil.RESULT_FAILURE_CONNECT:
				// pDialog.dismiss();
				// UIUtil.ToastMessage(RegActivity.this, "网络连接失败，请重试");
				// break;
				// case FinalHttpUtil.RESULT_FAILURE_OPERATE:
				// pDialog.dismiss();
				// String s = (String) msg.obj;
				// UIUtil.ToastMessage(RegActivity.this, s);
				// break;
				case HANDLE_MESSAGE_IMAGELIST:
					// imageAdapter.setProducts(imageList);
					// headImageView.setImageBitmap(imageList.get(0));
					break;
				}
				super.handleMessage(msg);

			}
		};
	}

	private byte[] fileToStream(File file) {
		InputStream is = null;
		ByteArrayOutputStream baos = null;
		byte[] b = new byte[4096];
		int len = -1;
		try {
			is = new FileInputStream(file);
			baos = new ByteArrayOutputStream(4096);
			while ((len = is.read(b)) != -1) {
				baos.write(b, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
				baos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return baos.toByteArray();
	}
}
