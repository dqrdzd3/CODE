package com.hw.hwsafe.smart.weixin.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.HttpClientErrorException;

import com.hw.hwsafe.smart.weixin.advanced.AuthedWeixin;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;



public class UploadAttach {
	//private static Logger log = LoggerFactory.getLogger(UploadAttach.class);
	/*
	 * 文件名必须是完整绝对路径，另外需要绝对路径前加上“@”以示区分。
	 */
	// 在Windows服务器上，格式示例为：”@F:\dupload\winter.jpg”，而在Linux服务器上，格式示例为：”@home/upload/winter.jpg”。

	public final static String upload_attach_url = "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
	public final static String download_attach_url="http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
	// 上传附件
	/*
	 * 图片（image）: 1M，支持JPG格式 语音（voice）：2M，播放长度不超过60s，支持AMR\MP3格式
	 * 视频（video）：10MB，支持MP4格式 缩略图（thumb）：64KB，支持JPG格式
	 * 媒体文件在后台保存时间为3天，即3天后media_id失效。
	 */
	// filePath 文件绝对路径 @
	public static Map<String, Object> Upload(String token, String type,
			String filePath) throws IOException {

		File file = new File(filePath);

		if (!file.exists() || !file.isFile()) {

			throw new IOException("文件不存在");

		}

		Map<String, Object> resultMap = new HashMap<String, Object>();

		String requestUrl = upload_attach_url.replace("ACCESS_TOKEN", token);
		requestUrl = requestUrl.replace("TYPE", type);
		
		JSONObject jsonObject = send(requestUrl,filePath);
		try {
			LogUtil.writeLog("地址"+requestUrl);
			LogUtil.writeLog("返回"+jsonObject.toString());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (null != jsonObject) {
			try {
				if (jsonObject.has("type")) {
					resultMap.put("created_at",
							jsonObject.getLong("created_at"));
					resultMap.put("media_id", jsonObject.getString("media_id"));
					resultMap.put("type", jsonObject.getString("type"));
					return resultMap;
				} else
					return null;

			} catch (JSONException e) {
				// 获取token失败
//				log.error("获取token失败 errcode:{} errmsg:{}",
//						jsonObject.getInt("errcode"),
//						jsonObject.getString("errmsg"));
				return null;

			}
		}
		return null;
	}
/*
 * 下载  除视频
 */
	public static Map<String, Object> Download(String token, String media_id) {

		

		Map<String, Object> resultMap = new HashMap<String, Object>();

		String requestUrl = download_attach_url.replace("ACCESS_TOKEN", token);
		requestUrl = requestUrl.replace("MEDIA_ID", media_id);

		JSONObject jsonObject = AuthedWeixin.sendGet(requestUrl, null);
		if (null != jsonObject) {
			try {
				if (jsonObject.has("type")) {
					resultMap.put("created_at",
							jsonObject.getLong("created_at"));
					resultMap.put("media_id", jsonObject.getString("media_id"));
					resultMap.put("type", jsonObject.getString("type"));
					return resultMap;
				} else
					return null;

			} catch (JSONException e) {
				// 获取token失败
//				log.error("获取token失败 errcode:{} errmsg:{}",
//						jsonObject.getInt("errcode"),
//						jsonObject.getString("errmsg"));
				return null;

			}
		}
		return null;
	}
	/**
	 * 模拟form表单的形式 ，上传文件 以输出流的形式把文件写入到url中，然后用输入流来获取url的响应
	 * 
	 * @param url
	 *            请求地址 form表单url地址
	 * @param filePath
	 *            文件在服务器保存路径
	 * @return String url的响应信息返回值
	 * @throws IOException
	 */
	public static JSONObject send(String url, String filePath) throws IOException {

	
		 JSONObject jsonObject = null;
		File file = new File(filePath);
		if (!file.exists() || !file.isFile()) {
			throw new IOException("文件不存在");
		}

		/**
		 * 第一部分
		 */
		URL urlObj = new URL(url);
		// 连接
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();

		/**
		 * 设置关键值
		 */
		con.setRequestMethod("POST"); // 以Post方式提交表单，默认get方式
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setUseCaches(false); // post方式不能使用缓存

		// 设置请求头信息
		con.setRequestProperty("Connection", "Keep-Alive");
		con.setRequestProperty("Charset", "UTF-8");

		// 设置边界
		String BOUNDARY = "----------" + System.currentTimeMillis();
		con.setRequestProperty("Content-Type", "multipart/form-data; boundary="
				+ BOUNDARY);

		// 请求正文信息

		// 第一部分：
		StringBuilder sb = new StringBuilder();
		sb.append("--"); // 必须多两道线
		sb.append(BOUNDARY);
		sb.append("\r\n");
		sb.append("Content-Disposition: form-data;name=\"file\";filename=\""
				+ file.getName() + "\"\r\n");
		sb.append("Content-Type:application/octet-stream\r\n\r\n");

		byte[] head = sb.toString().getBytes("utf-8");

		// 获得输出流
		OutputStream out = new DataOutputStream(con.getOutputStream());
		// 输出表头
		out.write(head);

		// 文件正文部分
		// 把文件已流文件的方式 推入到url中
		DataInputStream in = new DataInputStream(new FileInputStream(file));
		int bytes = 0;
		byte[] bufferOut = new byte[1024];
		while ((bytes = in.read(bufferOut)) != -1) {
			out.write(bufferOut, 0, bytes);
		}
		in.close();

		// 结尾部分
		byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线

		out.write(foot);

		out.flush();
		out.close();

		StringBuffer buffer = new StringBuffer();
		BufferedReader reader = null;
		try {
			// 定义BufferedReader输入流来读取URL的响应
			reader = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				// System.out.println(line);
				buffer.append(line);
			}
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (IOException e) {
			LogUtil.writeExceptionLog("发送POST请求出现异常！" + e);
			e.printStackTrace();
			throw new IOException("数据读取异常");
		} finally {
			if (reader != null) {
				reader.close();
			}

		}

		return jsonObject;

	}
	public static void main(String[] args) throws IOException {
		String filePath = "f:/bg.png";
		String sendUrl = "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=weixinCourse&type=image";
		String result = null;
	//	UploadAttach fileUpload = new UploadAttach()
		result = send(sendUrl, filePath).toString();
		System.out.println(result);

		} 

}

