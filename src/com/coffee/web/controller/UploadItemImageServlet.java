package com.coffee.web.controller;

import java.io.*;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.coffee.service.IItemService;
import com.coffee.service.impl.ItemServiceImpl;

/**
 * @ClassName: UploadHandleServlet
 * @Description: 用于上传商品图片，并将URL传入数据库中
 * 
 * @author: K
 */
@WebServlet(name = "UploadItemImageServlet", urlPatterns = "/servlet/uploadItemImageServlet")
public class UploadItemImageServlet extends HttpServlet {
	private IItemService itemService = new ItemServiceImpl();

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("------------UploadItemImageServlet work start-----------");

		//上传图片，同时更新数据库中图片的url
		try {
			uploadImage(request, response);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		//回显
		request.getRequestDispatcher("/pages/admin/manage-items.jsp").forward(request, response);
		
		System.out.println("------------UploadItemImageServlet work finished-----------");
	}

	private void uploadImage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		//获取实际的保存地址
		String savePath = this.getServletContext().getRealPath("/images/item");
		File savePathFile = new File(savePath);
		if (!savePathFile.exists()) {
			savePathFile.mkdir();
		}
		
		// 上传时生成的临时文件保存目录
		String tempPath = this.getServletContext().getRealPath("/WEB-INF/temp");
		File tmpFile = new File(tempPath);
		if (!tmpFile.exists()) {
			tmpFile.mkdir();
		}

		int itemId = 0;
		String imageUrl = "";
		try {
			// 使用Apache文件上传组件处理文件上传步骤：
			// 1、创建一个DiskFileItemFactory工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 设置工厂的缓冲区的大小，当上传的文件大小超过缓冲区的大小时，就会生成一个临时文件存放到指定的临时目录当中。
			factory.setSizeThreshold(1024 * 100);// 设置缓冲区的大小为100KB，如果不指定，那么缓冲区的大小默认是10KB
			// 设置上传时生成的临时文件的保存目录
			factory.setRepository(tmpFile);
			// 2、创建一个文件上传解析器
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 监听文件上传进度
			upload.setProgressListener(new ProgressListener() {
				public void update(long pBytesRead, long pContentLength, int arg2) {
					System.out.println("文件大小为：" + pContentLength + ",当前已处理：" + pBytesRead);
				}
			});
			// 解决上传文件名的中文乱码
			upload.setHeaderEncoding("UTF-8");
			// 3、判断提交上来的数据是否是上传表单的数据
			if (!ServletFileUpload.isMultipartContent(request)) {
				// 按照传统方式获取数据
				return;
			}
			upload.setFileSizeMax(1024 * 1024);// 设置上传单个文件的大小的最大值=10MB
			upload.setSizeMax(1024 * 1024 * 50); // 设置上传文件总量的最大值=50MB
			// 4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
			List<FileItem> list = upload.parseRequest(request);
			for (FileItem item : list) {
				// 如果fileitem中封装的是普通输入项的数据
				if (item.isFormField()) {
					String name = item.getFieldName();
					// 解决普通输入项的数据的中文乱码问题
					String value = item.getString("UTF-8");
					System.out.println(name + "=" + value);
					if (name.equals("itemId")) {
						itemId = Integer.parseInt(value);
					}
				} else {// 如果fileitem中封装的是上传文件
						// 得到上传的文件名称，
					String filename = item.getName();
					System.out.println(filename);
					if (filename == null || filename.trim().equals("")) {
						continue;
					}
					// 注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如： c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
					// 处理获取到的上传文件的文件名的路径部分，只保留文件名部分
					filename = filename.substring(filename.lastIndexOf("\\") + 1);
					// 得到上传文件的扩展名
					String fileExtName = filename.substring(filename.lastIndexOf(".") + 1);

					if (isImage(fileExtName) == false) {
						throw new RuntimeException("不能上传除jpg/png/bmp以外的图片文件！！");
					}
					// 获取item中的上传文件的输入流
					InputStream in = item.getInputStream();
					// 创建一个文件输出流
					FileOutputStream out = new FileOutputStream(savePath + "\\" + filename);
					imageUrl = "/images/item" + "/" + filename;
					// 创建一个缓冲区
					byte buffer[] = new byte[1024];
					// 判断输入流中的数据是否已经读完的标识
					int len = 0;
					// 循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
					while ((len = in.read(buffer)) > 0) {
						out.write(buffer, 0, len);
					}
					// 关闭输入流
					in.close();
					// 关闭输出流
					out.close();
					// 删除处理文件上传时生成的临时文件
					item.delete();
				}
			}
		} catch (FileUploadBase.FileSizeLimitExceededException e) {
			request.setAttribute("operateError", "单个文件超出最大值！！！");
			return;
		} catch (FileUploadBase.SizeLimitExceededException e) {
			request.setAttribute("operateError", "上传文件的总的大小超出限制的最大值！！！");
			return;
		} catch (Exception e) {
			request.setAttribute("operateError", "文件上传失败！！！");
			return;
		}
		request.setAttribute("operateSuccess", "添加图片成功！！");

		System.out.println("itemId:" + itemId);
		System.out.println(imageUrl);
		itemService.update(itemId, imageUrl);
	}

	private boolean isImage(String fileExtName) {
		if (fileExtName.equals("jpg") || fileExtName.equals("png") || fileExtName.equals("bmp")
				|| fileExtName.equals("gif")) {
			return true;
		} else {
			return false;
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}