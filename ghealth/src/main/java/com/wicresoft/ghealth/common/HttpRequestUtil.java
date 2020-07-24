package com.wicresoft.ghealth.common;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Locale;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

/**
 * Http请求工具类
 * 
 * @description
 *
 */
public class HttpRequestUtil {
	/**
	 * 从url请求中获得返回的字符串
	 * 
	 * @param requestUrl
	 * @return JSON字符串
	 * 
	 */
	public static String HttpRequest(String requestUrl) throws BusinessException {
		ApplicationContext act = ContextLoader.getCurrentWebApplicationContext();
		StringBuffer sb = new StringBuffer();
		InputStream ips = getInputStream(requestUrl);
		InputStreamReader isreader = null;
		try {
			isreader = new InputStreamReader(ips, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		BufferedReader bufferedReader = new BufferedReader(isreader);
		String temp = null;
		try {
			while ((temp = bufferedReader.readLine()) != null) {
				sb.append(temp);
			}
			bufferedReader.close();
			isreader.close();
			ips.close();
			ips = null;
		} catch (IOException e) {
			throw new BusinessException(act.getMessage("E0028", null, Locale.getDefault()));
		}
		return sb.toString();
	}

	/**
	 * 从请求的URL中获取返回的流数据
	 * 
	 * @param requestUrl
	 * @return InputStream
	 */
	private static InputStream getInputStream(String requestUrl) throws BusinessException {
		ApplicationContext act = ContextLoader.getCurrentWebApplicationContext();
		URL url = null;
		HttpURLConnection conn = null;
		InputStream in = null;
		try {
			url = new URL(requestUrl);
		} catch (MalformedURLException e) {
			throw new BusinessException(act.getMessage("E0028", null, Locale.getDefault()));
		}
		try {
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setRequestMethod("GET");
			conn.connect();
			conn.getResponseMessage();
			in = conn.getInputStream();
		} catch (IOException e) {
			throw new BusinessException(act.getMessage("E0028", null, Locale.getDefault()));
		}
		return in;
	}

	/**
	 * 
	 * @param urlPath
	 *            下载路径
	 * @param downloadDir
	 *            下载存放目录
	 * @return 返回下载文件
	 */
	@SuppressWarnings({ "unused", "finally" })
	public static File downloadFile(String urlPath, String downloadDir) {
		ApplicationContext act = ContextLoader.getCurrentWebApplicationContext();
		File file = null;
		try {
			// 统一资源
			URL url = new URL(urlPath);
			// 连接类的父类，抽象类
			URLConnection urlConnection = url.openConnection();
			// http的连接类
			HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
			// 设定请求的方法，默认是GET
			httpURLConnection.setRequestMethod("GET");
			// 设置字符编码
			httpURLConnection.setRequestProperty("Charset", "UTF-8");
			// 打开到此 URL 引用的资源的通信链接（如果尚未建立这样的连接）。
			httpURLConnection.connect();

			// 文件大小
			int fileLength = httpURLConnection.getContentLength();

			// 文件名
			String filePathUrl = httpURLConnection.getURL().getFile();
			String fileFullName = filePathUrl.substring(filePathUrl.lastIndexOf(File.separatorChar) + 1);

			System.out.println("file length---->" + fileLength);

			URLConnection con = url.openConnection();

			BufferedInputStream bin = new BufferedInputStream(httpURLConnection.getInputStream());

			String path = downloadDir + File.separatorChar + fileFullName;
			file = new File(path);
			if (!file.exists()) {
				if (!file.getParentFile().exists()) {
					file.getParentFile().mkdirs();
				}
				OutputStream out = new FileOutputStream(file);
				int size = 0;
				int len = 0;
				byte[] buf = new byte[1024];
				while ((size = bin.read(buf)) != -1) {
					len += size;
					out.write(buf, 0, size);
				}
				out.close();
			}
			bin.close();
		} catch (MalformedURLException e) {
			throw new BusinessException(act.getMessage("E0029", null, Locale.getDefault()));
		} catch (IOException e) {
			throw new BusinessException(act.getMessage("E0029", null, Locale.getDefault()));
		} finally {
			return file;
		}

	}

	public static void main(String[] args) throws BusinessException {
		String url = "http://manage.heartbooktech.com:10086/wc/getReport?sn=H100101762311349&date=2016-09-17&t=1474082108&sign=0c5990f3a74b027296c7945dd959f0d1";
		String res = HttpRequest(url);
		System.out.println(res);
	}

}
