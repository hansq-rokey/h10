package com.baidu.ueditor.upload;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSException;
import com.baidu.ueditor.PathFormat;
import com.baidu.ueditor.define.AppInfo;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.FileType;
import com.baidu.ueditor.define.State;
import com.ibaixiong.core.utils.ALiYunUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class BinaryUploader {

	public static final State save(HttpServletRequest request,
			Map<String, Object> conf) {
//		FileItemStream fileStream = null;
		FileItem item = null;
		boolean isAjaxUpload = request.getHeader( "X_Requested_With" ) != null;

		if (!ServletFileUpload.isMultipartContent(request)) {
			return new BaseState(false, AppInfo.NOT_MULTIPART_CONTENT);
		}

		ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());

        if ( isAjaxUpload ) {
            upload.setHeaderEncoding( "UTF-8" );
        }

		try {
//			FileItemIterator iterator = upload.getItemIterator(request);
			List<FileItem> fileItems = upload.parseRequest(request);
			Iterator<FileItem> iter = fileItems.iterator();
			while (iter.hasNext()) {
				item = iter.next();
				// item.isFormField()用来判断当前对象是否是file表单域的数据 如果返回值是true说明不是
				// 就是普通表单域
				if (!item.isFormField()) {
					break;
				}
				item = null;
			}
//			while (iterator.hasNext()) {
//				fileStream = iterator.next();
//
//				if (!fileStream.isFormField())
//					break;
//				fileStream = null;
//			}

			if (item == null) {
				return new BaseState(false, AppInfo.NOTFOUND_UPLOAD_DATA);
			}

			String savePath = (String) conf.get("savePath");
			String originFileName = item.getName();
			String suffix = FileType.getSuffixByFilename(originFileName);

			originFileName = originFileName.substring(0,originFileName.length() - suffix.length());
			savePath = savePath + suffix;

			long maxSize = ((Long) conf.get("maxSize")).longValue();

			if (!validType(suffix, (String[]) conf.get("allowFiles"))) {
				return new BaseState(false, AppInfo.NOT_ALLOW_FILE_TYPE);
			}

			savePath = PathFormat.parse(savePath, originFileName);

			InputStream is = item.getInputStream();
			State storageState = null;
			String key = ALiYunUtil.createBbsKey(suffix.replace(".", ""));
			try {
				ALiYunUtil.uploadFile(ALiYunUtil.BUCKET_NAME, key, is, item.getSize());
				storageState = new BaseState(true);
				storageState.putInfo( "size", item.getSize() );
				storageState.putInfo( "title", item.getName() );
			} catch (OSSException e) {
				e.printStackTrace();
			} catch (ClientException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
//			State storageState = StorageManager.saveFileByInputStream(is,physicalPath, maxSize);
			is.close();

			if (storageState.isSuccess()) {
				storageState.putInfo("url", ALiYunUtil.IMAGE_URL+key);
				storageState.putInfo("type", suffix);
				storageState.putInfo("original", originFileName + suffix);
			}

			return storageState;
		} catch (FileUploadException e) {
			return new BaseState(false, AppInfo.PARSE_REQUEST_ERROR);
		} catch (IOException e) {
		}
		return new BaseState(false, AppInfo.IO_ERROR);
	}

	private static boolean validType(String type, String[] allowTypes) {
		List<String> list = Arrays.asList(allowTypes);

		return list.contains(type);
	}
}
