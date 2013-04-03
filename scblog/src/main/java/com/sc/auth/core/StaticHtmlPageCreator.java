package com.sc.auth.core;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class StaticHtmlPageCreator {

	/**
	 * 生成静态的文章页面
	 * @param request
	 * @param response
	 * @param fileName 静态页面文件名
	 * @param fullFilePath  静态页面路径
	 * @param jspPath  处理响应的jsp页面
	 * @throws ServletException
	 * @throws IOException
	 */
	public static void createStaticHtmlPageAndRedirect(HttpServletRequest request,
			HttpServletResponse response, String fileName, String fullFilePath,
			String jspPath) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(jspPath);
		final ByteArrayOutputStream byteArrayOutput = new ByteArrayOutputStream();
		final ServletOutputStream servletOutput = new ServletOutputStream(){
			@Override
			public void write(int b) throws IOException {
				byteArrayOutput.write(b);
			}
			
			@Override
			public void write(byte[] b, int off, int len) throws IOException {
				// TODO Auto-generated method stub
				byteArrayOutput.write(b, off, len);
			}
		};
		
		final PrintWriter pw = new PrintWriter(new OutputStreamWriter(byteArrayOutput));
		
		HttpServletResponse httpServletResponse = new HttpServletResponseWrapper(response){
			@Override
			public ServletOutputStream getOutputStream() throws IOException {				
				return servletOutput;
			}
			
			@Override
			public PrintWriter getWriter() throws IOException {			
				return pw;
			}
		};
		
		rd.include(request, httpServletResponse);
		pw.flush();
		
		FileOutputStream fileOutput = new FileOutputStream(fullFilePath);
		byteArrayOutput.writeTo(fileOutput);
		fileOutput.close();
		request.getRequestDispatcher(fileName).forward(request, response);
	}
}
