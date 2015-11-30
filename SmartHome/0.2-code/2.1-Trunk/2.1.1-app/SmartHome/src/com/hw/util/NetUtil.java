/**
 * 文件名：NetUtil.java
 *
 * 版本信息：
 * 日期：2012-10-8
 * Copyright 北京威果智能科技有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.params.HttpMethodParams;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.StrictMode;

import com.hw.util.context.AppContext;
import com.hw.util.exception.ExceptionSH;

/**
 * 
 * <p>
 * 网络访问工具类<br>
 * </p>
 */
@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint("NewApi")
public class NetUtil {

	public static final String UTF_8 = "UTF-8";

	public final static int TIMEOUT_CONNECTION = 10000;
	public final static int TIMEOUT_SOCKET = 10000;
	public final static int RETRY_TIME = 3;
	public static String appCookie;
	public static String appUserAgent;

	public static final int NETTYPE_WIFI = 0x01;
	public static final int NETTYPE_CMWAP = 0x02;
	public static final int NETTYPE_CMNET = 0x03;

	public static String responseBody = "";

	/**
	 * 强制访问网络在主线程
	 */
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	// 判断版本格式,如果版本 > 2.3,就是用相应的程序进行处理,以便影响访问网络
	// @TargetApi(9)
	@SuppressLint("NewApi")
	public static void stricNetthreadInMainthread() {
		String strVer = android.os.Build.VERSION.RELEASE; // 获得当前系统版本
		strVer = strVer.substring(0, 3).trim(); // 截取前3个字符 2.3.3转换成2.3
		float fv = Float.valueOf(strVer);
		if (fv > 2.3) {
			StrictMode
					.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
							.detectDiskReads()
							.detectDiskWrites().detectNetwork()
							.penaltyLog().build());
			StrictMode
					.setVmPolicy(new StrictMode.VmPolicy.Builder()
							.detectLeakedSqlLiteObjects()
							.penaltyLog().penaltyDeath().build());
		}

	}

	/**
	 * 判断是否联网。
	 * 
	 * @return boolean
	 */
	public static boolean isNetworkConnected(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		if (ni == null) {
			return false;
		}

		return ni != null && ni.isConnectedOrConnecting();
	}

	/**
	 * 获取当前网络类型
	 * 
	 * @return 0：没有网络 1：WIFI网络 2：WAP网络 3：NET网络
	 */
	public static int getNetworkType(Context context) {
		int netType = 0;
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager
				.getActiveNetworkInfo();
		if (networkInfo == null) {
			return netType;
		}
		int nType = networkInfo.getType();
		if (nType == ConnectivityManager.TYPE_MOBILE) {
			String extraInfo = networkInfo.getExtraInfo();
			if (!StringUtil.isEmpty(extraInfo)) {
				if (extraInfo.toLowerCase().equals("cmnet")) {
					netType = NETTYPE_CMNET;
				} else {
					netType = NETTYPE_CMWAP;
				}
			}
		} else if (nType == ConnectivityManager.TYPE_WIFI) {
			netType = NETTYPE_WIFI;
		}
		return netType;
	}

	public static String _MakeURL(String p_url,
			Map<String, String> params) {
		if (params == null) {
			return p_url;
		}
		StringBuilder url = new StringBuilder(p_url);
		if (url.indexOf("?") < 0)
			url.append('?');

		for (String name : params.keySet()) {
			url.append('&');
			url.append(name);
			url.append('=');
			// url.append(String.valueOf(params.get(name)));
			// 不做URLEncoder处理
			try {
				url.append(URLEncoder.encode(
						String.valueOf(params.get(name)), UTF_8));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}

		return url.toString().replace("?&", "?");
	}

	private static String getUserAgent(Context context) {
		if (StringUtil.isEmpty(appUserAgent)) {
			StringBuilder ua = new StringBuilder("hanwei.com");
			ua.append('/' + SoftUtil.getVersionName(context)
					+ '_' + SoftUtil.getVersionCode(context));// App版本
			ua.append("/Android");// 手机系统平台
			ua.append("/" + android.os.Build.VERSION.RELEASE);// 手机系统版本
			ua.append("/" + android.os.Build.MODEL); // 手机型号
			// ua.append("/" + appContext.getAppId());// 客户端唯一标识
			appUserAgent = ua.toString();
		}
		return appUserAgent;
	}

	private static HttpClient getHttpClient() {
		HttpClient httpClient = new HttpClient();
		// 设置 HttpClient 接收 Cookie,用与浏览器一样的策略
		httpClient.getParams().setCookiePolicy(
				CookiePolicy.BROWSER_COMPATIBILITY);
		// 设置 默认的超时重试处理策略
		httpClient.getParams().setParameter(
				HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
		// 设置 连接超时时间
		httpClient.getHttpConnectionManager().getParams()
				.setConnectionTimeout(TIMEOUT_CONNECTION);
		// 设置 读数据超时时间
		httpClient.getHttpConnectionManager().getParams()
				.setSoTimeout(TIMEOUT_SOCKET);
		// 设置 字符集
		httpClient.getParams().setContentCharset(UTF_8);
		return httpClient;
	}

	private static GetMethod getHttpGet(String url,
			String cookie, String userAgent) {
		GetMethod httpGet = new GetMethod(url);
		// 设置 请求超时时间
		httpGet.getParams().setSoTimeout(TIMEOUT_SOCKET);
		// httpGet.setRequestHeader("Host", appContext.);
		httpGet.setRequestHeader("Connection", "Keep-Alive");
		httpGet.setRequestHeader("Cookie", cookie);
		httpGet.setRequestHeader("User-Agent", userAgent);
		return httpGet;
	}

	private static PostMethod getHttpPost(String url,
			String cookie, String userAgent) {
		PostMethod httpPost = new PostMethod(url);
		// 设置 请求超时时间
		httpPost.getParams().setSoTimeout(TIMEOUT_SOCKET);
		// httpPost.setRequestHeader("Host", AppContext.getBASE_URL());
		httpPost.setRequestHeader("Connection", "Keep-Alive");
		httpPost.setRequestHeader("Cookie", cookie);
		httpPost.setRequestHeader("User-Agent", userAgent);
		return httpPost;
	}

	/**
	 * get请求URL
	 * 
	 * @param url
	 * @throws ExceptionSH
	 */
	public static String httpGet(String url) throws ExceptionSH {

		String cookie = "";
		String userAgent = "hanwei";

		HttpClient httpClient = null;
		GetMethod httpGet = null;

		String responseBody = "";
		int time = 0;
		do {
			try {
				httpClient = getHttpClient();
				httpGet = getHttpGet(url, cookie, userAgent);
				int statusCode = httpClient
						.executeMethod(httpGet);

				Ln.d("statusCode---->" + statusCode);

				if (statusCode != HttpStatus.SC_OK) {
					Ln.e("网络连接异常");
					// throw ExceptionSH.http(statusCode);
				}

				responseBody = httpGet.getResponseBodyAsString();

				Ln.d("responseBody---->" + responseBody);

				break;
			} catch (HttpException e) {
				time++;
				if (time < RETRY_TIME) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
					}
					continue;
				}
				// 发生致命的异常，可能是协议不对或者返回的内容有问题
				e.printStackTrace();
				throw ExceptionSH.http(e);
			} catch (IOException e) {
				time++;
				if (time < RETRY_TIME) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
					}
					continue;
				}
				// 发生网络异常
				e.printStackTrace();
				throw ExceptionSH.network(e);
			} finally {
				if (httpGet != null) {
					// 释放连接
					httpGet.releaseConnection();
				}
				httpClient = null;
			}
		} while (time < RETRY_TIME);

		return responseBody;
	}

	/**
	 * 公用post方法
	 * 
	 * @param url
	 * @param params
	 * @param files
	 * @throws ExceptionSH
	 */
	public static String http_post(String url,
			Map<String, String> params, Map<String, File> files)
			throws ExceptionSH {

		String cookie = "";
		String userAgent = "";

		HttpClient httpClient = null;
		PostMethod httpPost = null;

		// post表单参数处理
		int length = (params == null ? 0 : params.size())
				+ (files == null ? 0 : files.size());
		Part[] parts = new Part[length];
		int i = 0;
		if (params != null)
			for (String name : params.keySet()) {
				parts[i++] = new StringPart(name,
						String.valueOf(params.get(name)), UTF_8);
				if (AppContext.DEBUG) {
					Ln.d("post_key==> " + name + "    value==>"
							+ String.valueOf(params.get(name)));
				}
			}
		if (files != null)
			for (String file : files.keySet()) {
				try {
					parts[i++] = new FilePart(file,
							files.get(file));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				if (AppContext.DEBUG) {
					System.out.println("post_key_file==> "
							+ file);
				}
			}

		String responseBody = "";
		int time = 0;
		do {
			try {
				httpClient = getHttpClient();
				httpPost = getHttpPost(url, cookie, userAgent);
				httpPost.setRequestEntity(new MultipartRequestEntity(
						parts, httpPost.getParams()));
				int statusCode = httpClient
						.executeMethod(httpPost);
				// System.out
				// .println("statusCode==> " + statusCode);
				if (statusCode != HttpStatus.SC_OK) {
					throw ExceptionSH.http(statusCode);
				} else if (statusCode == HttpStatus.SC_OK) {
					/*
					 * Cookie[] cookies = httpClient.getState().getCookies();
					 * String tmpcookies = ""; for (Cookie ck : cookies) {
					 * tmpcookies += ck.toString()+";"; } //保存cookie
					 * if(appContext != null && tmpcookies != ""){
					 * appContext.setProperty("cookie", tmpcookies); appCookie =
					 * tmpcookies; }
					 */
				}
				responseBody = httpPost
						.getResponseBodyAsString();
				if (AppContext.DEBUG) {
					System.out.println("JSONDATA=====>"
							+ responseBody);
				}

				break;
			} catch (HttpException e) {
				time++;
				if (time < RETRY_TIME) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
					}
					continue;
				}
				// 发生致命的异常，可能是协议不对或者返回的内容有问题
				e.printStackTrace();
				throw ExceptionSH.http(e);
			} catch (IOException e) {
				time++;
				if (time < RETRY_TIME) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
					}
					continue;
				}
				// 发生网络异常
				e.printStackTrace();
				throw ExceptionSH.network(e);
			} finally {
				// 释放连接
				httpPost.releaseConnection();
				httpClient = null;
			}
		} while (time < RETRY_TIME);

		return responseBody;
	}

	/**
	 * get请求URL
	 * 
	 * @param url
	 * @throws AppException
	 */
	/*
	 * public static String http_get(String url) throws AppException { Ln.i(TAG,
	 * url); HttpGet httpRequest = new HttpGet(url); HttpClient httpClient = new
	 * DefaultHttpClient(new BasicHttpParams());
	 * 
	 * int time = 0; do { try { HttpResponse httpResponse =
	 * httpClient.execute(httpRequest); if
	 * (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
	 * HttpEntity httpEntity = httpResponse.getEntity(); responseBody =
	 * EntityUtils.toString(httpEntity, "UTF-8");
	 * System.out.println("json=====>" + responseBody); } else { throw
	 * AppException.http(httpResponse.getStatusLine() .getStatusCode()); }
	 * break; } catch (IOException e) { time++; if (time < RETRY_TIME) { try {
	 * Thread.sleep(1000); } catch (InterruptedException e1) { } continue; } //
	 * 发生网络异常 e.printStackTrace(); throw AppException.network(e); } catch
	 * (Exception e) { time++; if (time < RETRY_TIME) { try {
	 * Thread.sleep(1000); } catch (InterruptedException e1) { } continue; } //
	 * 发生网络异常 e.printStackTrace(); throw AppException.http(e); } finally { //
	 * 释放连接 httpClient = null; } } while (time < RETRY_TIME);
	 * 
	 * return responseBody; }
	 *//**
	 * 把String 转换为InputStream
	 */
	/*
	 * 
	 * public static InputStream http_get_String_toInputStream(String url) {
	 * 
	 * InputStream inputStream = null; try { http_get(url);
	 * 
	 * inputStream = new ByteArrayInputStream(responseBody.getBytes()); } catch
	 * (AppException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * return inputStream;
	 * 
	 * }
	 *//**
	 * 获取网络图片
	 * 
	 * @param url
	 * @return
	 */
	/*
	 * public static Bitmap getImage(String Url) throws Exception {
	 * 
	 * try {
	 * 
	 * URL url = new URL(Url);
	 * 
	 * String responseCode = url.openConnection().getHeaderField(0);
	 * 
	 * if (responseCode.indexOf("200") < 0)
	 * 
	 * throw new Exception("图片文件不存在或路径错误，错误代码：" + responseCode);
	 * 
	 * return BitmapFactory.decodeStream(url.openStream());
	 * 
	 * } catch (IOException e) {
	 * 
	 * // TODO Auto-generated catch block
	 * 
	 * throw AppException.network(e);
	 * 
	 * }
	 * 
	 * }
	 *//**
	 * 公用post方法
	 * 
	 * @param url
	 * @param params
	 * @param files
	 * @throws AppException
	 */
	/*
	 * public static String http_post(String url, Map<String, Object> params)
	 * throws AppException { // System.out.println("post_url==> "+url); //
	 * String cookie = getCookie(appContext); HttpPost httpPost = null;
	 * HttpClient httpClient = null; List<NameValuePair> nameValuePair = new
	 * ArrayList<NameValuePair>(); if (params != null) { for (String name :
	 * params.keySet()) { nameValuePair.add(new BasicNameValuePair(name, String
	 * .valueOf(params.get(name)))); } }
	 * 
	 * String responseBody = ""; int time = 0; do { try { httpPost = new
	 * HttpPost(url); httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair,
	 * HTTP.UTF_8)); httpClient = new DefaultHttpClient(new BasicHttpParams());
	 * HttpResponse httpResponse = httpClient.execute(httpPost); if
	 * (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
	 * HttpEntity httpEntity = httpResponse.getEntity(); responseBody =
	 * EntityUtils.toString(httpEntity, "UTF-8"); } else { throw
	 * AppException.http(httpResponse.getStatusLine() .getStatusCode()); }
	 * System.out.println("jsondata=====>" + responseBody); break; } catch
	 * (IOException e) { time++; if (time < RETRY_TIME) { try {
	 * Thread.sleep(1000); } catch (InterruptedException e1) { } continue; } //
	 * 发生网络异常 e.printStackTrace(); throw AppException.network(e); } catch
	 * (Exception e) { time++; if (time < RETRY_TIME) { try {
	 * Thread.sleep(1000); } catch (InterruptedException e1) { } continue; } //
	 * 发生网络异常 e.printStackTrace(); throw AppException.http(e); } finally { //
	 * 释放连接 httpClient = null; } } while (time < RETRY_TIME);
	 * 
	 * return responseBody; }
	 */

	/**
	 * 把String 转换为InputStream
	 */

	public static InputStream http_get_String_toInputStream(
			String url) {

		InputStream inputStream = null;
		try {
			httpGet(url);

			inputStream = new ByteArrayInputStream(
					responseBody.getBytes());
		} catch (ExceptionSH e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return inputStream;

	}

	public static byte[] getHtmlByteArray(final String url) {
		URL htmlUrl = null;
		InputStream inStream = null;
		try {
			htmlUrl = new URL(url);
			URLConnection connection = htmlUrl.openConnection();
			HttpURLConnection httpConnection = (HttpURLConnection) connection;
			int responseCode = httpConnection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				inStream = httpConnection.getInputStream();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] data = inputStreamToByte(inStream);

		return data;
	}

	public static byte[] inputStreamToByte(InputStream is) {
		try {
			ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
			int ch;
			while ((ch = is.read()) != -1) {
				bytestream.write(ch);
			}
			byte imgdata[] = bytestream.toByteArray();
			bytestream.close();
			return imgdata;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
