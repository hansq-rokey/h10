package com.ibaixiong.bbs.web;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSException;
import com.ibaixiong.bbs.util.LoginUserUtils;
import com.ibaixiong.core.utils.ALiYunUtil;
import com.ibaixiong.entity.User;
/**
 * 废弃
 * baixiong.com Inc.
 * Copyright (c) 1999-2001 All Rights Reserved.
 * 
 * @author yaoweiguo
 * @Email  yaoweiguo@ibaixiong.com
 * @Description TODO
 * @date 2015年11月24日
 *
 */
@Controller
@RequestMapping("/u/upload")
public class UploadPictureAction {
	/**
	 * 保存帖子
	 * 
	 * @author zhaolei
	 * @date 2015年7月30日
	 * @param article
	 *            帖子主对象
	 * @param publishType
	 *            发布帖子类型
	 * @param formId
	 *            帖子主类型
	 * @param checkFormId
	 *            选择的最终帖子ID
	 * @param model
	 * @return
	 */
	@ResponseBody
//	@RequestMapping("/bbs.html")
	public String upload(
			@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest request, Model model) {
		User loginUser = LoginUserUtils.getLoginUser();
		String fileUrl = "";
		if (file != null && !file.isEmpty() && file.getSize() > 0) {
			String fileName = file.getOriginalFilename();
			String contentType = fileName.substring(
					fileName.lastIndexOf(".") + 1, fileName.length());
			String key = ALiYunUtil.createUserKey(contentType,
					loginUser.getId());
			try {
				ALiYunUtil.uploadFile(key, file);
			} catch (OSSException e) {
				e.printStackTrace();
			} catch (ClientException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			fileUrl = ALiYunUtil.IMAGE_URL + key;
			return "{\"state\": \"SUCCESS\",\"title\": \"1448348980390006185.png\",\"original\": \"cry2.png\",\"type\": \".png\",\"url\": \""
					+ fileUrl + "\",\"size\": \"8979\"}";
		} else {

			return "{\"snapscreenInsertAlign\":\"none\",\"videoPathFormat\":\"/ueditor/jsp/upload/video/{yyyy}{mm}{dd}/{time}{rand:6}\",\"videoFieldName\":\"upfile\",\"fileManagerActionName\":\"listfile\",\"fileUrlPrefix\":\"\",\"imageUrlPrefix\":\"\",\"imageAllowFiles\":[\".png\",\".jpg\",\".jpeg\",\".gif\",\".bmp\"],\"videoAllowFiles\":[\".flv\",\".swf\",\".mkv\",\".avi\",\".rm\",\".rmvb\",\".mpeg\",\".mpg\",\".ogg\",\".ogv\",\".mov\",\".wmv\",\".mp4\",\".webm\",\".mp3\",\".wav\",\".mid\"],\"filePathFormat\":\"/ueditor/jsp/upload/file/{yyyy}{mm}{dd}/{time}{rand:6}\",\"fileMaxSize\":51200000,\"fileManagerListPath\":\"/ueditor/jsp/upload/file/\",\"catcherUrlPrefix\":\"\",\"videoActionName\":\"uploadvideo\",\"scrawlInsertAlign\":\"none\",\"videoUrlPrefix\":\"\",\"imageManagerUrlPrefix\":\"\",\"scrawlUrlPrefix\":\"\",\"imageFieldName\":\"upfile\",\"fileManagerAllowFiles\":[\".png\",\".jpg\",\".jpeg\",\".gif\",\".bmp\",\".flv\",\".swf\",\".mkv\",\".avi\",\".rm\",\".rmvb\",\".mpeg\",\".mpg\",\".ogg\",\".ogv\",\".mov\",\".wmv\",\".mp4\",\".webm\",\".mp3\",\".wav\",\".mid\",\".rar\",\".zip\",\".tar\",\".gz\",\".7z\",\".bz2\",\".cab\",\".iso\",\".doc\",\".docx\",\".xls\",\".xlsx\",\".ppt\",\".pptx\",\".pdf\",\".txt\",\".md\",\".xml\"],\"imageMaxSize\":2048000,\"catcherPathFormat\":\"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\",\"imageManagerInsertAlign\":\"none\",\"scrawlFieldName\":\"upfile\",\"imagePathFormat\":\"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\",\"scrawlActionName\":\"uploadscrawl\",\"imageManagerActionName\":\"listimage\",\"imageActionName\":\"uploadimage\",\"imageManagerListSize\":20,\"imageManagerAllowFiles\":[\".png\",\".jpg\",\".jpeg\",\".gif\",\".bmp\"],\"fileAllowFiles\":[\".png\",\".jpg\",\".jpeg\",\".gif\",\".bmp\",\".flv\",\".swf\",\".mkv\",\".avi\",\".rm\",\".rmvb\",\".mpeg\",\".mpg\",\".ogg\",\".ogv\",\".mov\",\".wmv\",\".mp4\",\".webm\",\".mp3\",\".wav\",\".mid\",\".rar\",\".zip\",\".tar\",\".gz\",\".7z\",\".bz2\",\".cab\",\".iso\",\".doc\",\".docx\",\".xls\",\".xlsx\",\".ppt\",\".pptx\",\".pdf\",\".txt\",\".md\",\".xml\"],\"snapscreenActionName\":\"uploadimage\",\"fileFieldName\":\"upfile\",\"fileActionName\":\"uploadfile\",\"catcherActionName\":\"catchimage\",\"fileManagerListSize\":20,\"catcherAllowFiles\":[\".png\",\".jpg\",\".jpeg\",\".gif\",\".bmp\"],\"snapscreenPathFormat\":\"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\",\"imageCompressBorder\":1600,\"snapscreenUrlPrefix\":\"\",\"imageCompressEnable\":true,\"catcherLocalDomain\":[\"127.0.0.1\",\"localhost\",\"img.baidu.com\"],\"imageManagerListPath\":\"/ueditor/jsp/upload/image/\",\"imageInsertAlign\":\"none\",\"catcherMaxSize\":2048000,\"videoMaxSize\":102400000,\"fileManagerUrlPrefix\":\"\",\"scrawlPathFormat\":\"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\",\"scrawlMaxSize\":2048000,\"catcherFieldName\":\"source\"}";
		}

	}

	@ResponseBody
//	@RequestMapping("/bbs2.html")
	public String uploadTee(HttpServletRequest request, Model model) {
		FileItemStream fileStream = null;
		FileItem item = null;
		boolean isAjaxUpload = request.getHeader("X_Requested_With") != null;

		if (!ServletFileUpload.isMultipartContent(request)) {
		}

		String fileUrl = "";
		User loginUser = LoginUserUtils.getLoginUser();
		ServletFileUpload upload = new ServletFileUpload(
				new DiskFileItemFactory());

		if (isAjaxUpload) {
			upload.setHeaderEncoding("UTF-8");
		}

		try {
			List<FileItem> fileItems = upload.parseRequest(request);
			FileItemIterator iterator = upload.getItemIterator(request);
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
			while (iterator.hasNext()) {
				fileStream = iterator.next();
				if (!fileStream.isFormField())
					break;
				fileStream = null;
			}

			if (item == null) {
				return "{\"snapscreenInsertAlign\":\"none\",\"videoPathFormat\":\"/ueditor/jsp/upload/video/{yyyy}{mm}{dd}/{time}{rand:6}\",\"videoFieldName\":\"upfile\",\"fileManagerActionName\":\"listfile\",\"fileUrlPrefix\":\"\",\"imageUrlPrefix\":\"\",\"imageAllowFiles\":[\".png\",\".jpg\",\".jpeg\",\".gif\",\".bmp\"],\"videoAllowFiles\":[\".flv\",\".swf\",\".mkv\",\".avi\",\".rm\",\".rmvb\",\".mpeg\",\".mpg\",\".ogg\",\".ogv\",\".mov\",\".wmv\",\".mp4\",\".webm\",\".mp3\",\".wav\",\".mid\"],\"filePathFormat\":\"/ueditor/jsp/upload/file/{yyyy}{mm}{dd}/{time}{rand:6}\",\"fileMaxSize\":51200000,\"fileManagerListPath\":\"/ueditor/jsp/upload/file/\",\"catcherUrlPrefix\":\"\",\"videoActionName\":\"uploadvideo\",\"scrawlInsertAlign\":\"none\",\"videoUrlPrefix\":\"\",\"imageManagerUrlPrefix\":\"\",\"scrawlUrlPrefix\":\"\",\"imageFieldName\":\"upfile\",\"fileManagerAllowFiles\":[\".png\",\".jpg\",\".jpeg\",\".gif\",\".bmp\",\".flv\",\".swf\",\".mkv\",\".avi\",\".rm\",\".rmvb\",\".mpeg\",\".mpg\",\".ogg\",\".ogv\",\".mov\",\".wmv\",\".mp4\",\".webm\",\".mp3\",\".wav\",\".mid\",\".rar\",\".zip\",\".tar\",\".gz\",\".7z\",\".bz2\",\".cab\",\".iso\",\".doc\",\".docx\",\".xls\",\".xlsx\",\".ppt\",\".pptx\",\".pdf\",\".txt\",\".md\",\".xml\"],\"imageMaxSize\":2048000,\"catcherPathFormat\":\"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\",\"imageManagerInsertAlign\":\"none\",\"scrawlFieldName\":\"upfile\",\"imagePathFormat\":\"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\",\"scrawlActionName\":\"uploadscrawl\",\"imageManagerActionName\":\"listimage\",\"imageActionName\":\"uploadimage\",\"imageManagerListSize\":20,\"imageManagerAllowFiles\":[\".png\",\".jpg\",\".jpeg\",\".gif\",\".bmp\"],\"fileAllowFiles\":[\".png\",\".jpg\",\".jpeg\",\".gif\",\".bmp\",\".flv\",\".swf\",\".mkv\",\".avi\",\".rm\",\".rmvb\",\".mpeg\",\".mpg\",\".ogg\",\".ogv\",\".mov\",\".wmv\",\".mp4\",\".webm\",\".mp3\",\".wav\",\".mid\",\".rar\",\".zip\",\".tar\",\".gz\",\".7z\",\".bz2\",\".cab\",\".iso\",\".doc\",\".docx\",\".xls\",\".xlsx\",\".ppt\",\".pptx\",\".pdf\",\".txt\",\".md\",\".xml\"],\"snapscreenActionName\":\"uploadimage\",\"fileFieldName\":\"upfile\",\"fileActionName\":\"uploadfile\",\"catcherActionName\":\"catchimage\",\"fileManagerListSize\":20,\"catcherAllowFiles\":[\".png\",\".jpg\",\".jpeg\",\".gif\",\".bmp\"],\"snapscreenPathFormat\":\"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\",\"imageCompressBorder\":1600,\"snapscreenUrlPrefix\":\"\",\"imageCompressEnable\":true,\"catcherLocalDomain\":[\"127.0.0.1\",\"localhost\",\"img.baidu.com\"],\"imageManagerListPath\":\"/ueditor/jsp/upload/image/\",\"imageInsertAlign\":\"none\",\"catcherMaxSize\":2048000,\"videoMaxSize\":102400000,\"fileManagerUrlPrefix\":\"\",\"scrawlPathFormat\":\"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\",\"scrawlMaxSize\":2048000,\"catcherFieldName\":\"source\"}";
			}

			String originFileName = item.getName();
			String suffix = originFileName.substring(
					originFileName.lastIndexOf(".")).toLowerCase();

			// InputStream is = fileStream.openStream();

			String key = ALiYunUtil.createBbsKey(suffix, loginUser.getId());
			try {
				ALiYunUtil.uploadFile(ALiYunUtil.BUCKET_NAME, key,
						item.getInputStream(), item.getSize());
			} catch (OSSException e) {
				e.printStackTrace();
			} catch (ClientException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			fileUrl = ALiYunUtil.IMAGE_URL + key;

			// is.close();

		} catch (FileUploadException e) {
			return "{\"snapscreenInsertAlign\":\"none\",\"videoPathFormat\":\"/ueditor/jsp/upload/video/{yyyy}{mm}{dd}/{time}{rand:6}\",\"videoFieldName\":\"upfile\",\"fileManagerActionName\":\"listfile\",\"fileUrlPrefix\":\"\",\"imageUrlPrefix\":\"\",\"imageAllowFiles\":[\".png\",\".jpg\",\".jpeg\",\".gif\",\".bmp\"],\"videoAllowFiles\":[\".flv\",\".swf\",\".mkv\",\".avi\",\".rm\",\".rmvb\",\".mpeg\",\".mpg\",\".ogg\",\".ogv\",\".mov\",\".wmv\",\".mp4\",\".webm\",\".mp3\",\".wav\",\".mid\"],\"filePathFormat\":\"/ueditor/jsp/upload/file/{yyyy}{mm}{dd}/{time}{rand:6}\",\"fileMaxSize\":51200000,\"fileManagerListPath\":\"/ueditor/jsp/upload/file/\",\"catcherUrlPrefix\":\"\",\"videoActionName\":\"uploadvideo\",\"scrawlInsertAlign\":\"none\",\"videoUrlPrefix\":\"\",\"imageManagerUrlPrefix\":\"\",\"scrawlUrlPrefix\":\"\",\"imageFieldName\":\"upfile\",\"fileManagerAllowFiles\":[\".png\",\".jpg\",\".jpeg\",\".gif\",\".bmp\",\".flv\",\".swf\",\".mkv\",\".avi\",\".rm\",\".rmvb\",\".mpeg\",\".mpg\",\".ogg\",\".ogv\",\".mov\",\".wmv\",\".mp4\",\".webm\",\".mp3\",\".wav\",\".mid\",\".rar\",\".zip\",\".tar\",\".gz\",\".7z\",\".bz2\",\".cab\",\".iso\",\".doc\",\".docx\",\".xls\",\".xlsx\",\".ppt\",\".pptx\",\".pdf\",\".txt\",\".md\",\".xml\"],\"imageMaxSize\":2048000,\"catcherPathFormat\":\"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\",\"imageManagerInsertAlign\":\"none\",\"scrawlFieldName\":\"upfile\",\"imagePathFormat\":\"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\",\"scrawlActionName\":\"uploadscrawl\",\"imageManagerActionName\":\"listimage\",\"imageActionName\":\"uploadimage\",\"imageManagerListSize\":20,\"imageManagerAllowFiles\":[\".png\",\".jpg\",\".jpeg\",\".gif\",\".bmp\"],\"fileAllowFiles\":[\".png\",\".jpg\",\".jpeg\",\".gif\",\".bmp\",\".flv\",\".swf\",\".mkv\",\".avi\",\".rm\",\".rmvb\",\".mpeg\",\".mpg\",\".ogg\",\".ogv\",\".mov\",\".wmv\",\".mp4\",\".webm\",\".mp3\",\".wav\",\".mid\",\".rar\",\".zip\",\".tar\",\".gz\",\".7z\",\".bz2\",\".cab\",\".iso\",\".doc\",\".docx\",\".xls\",\".xlsx\",\".ppt\",\".pptx\",\".pdf\",\".txt\",\".md\",\".xml\"],\"snapscreenActionName\":\"uploadimage\",\"fileFieldName\":\"upfile\",\"fileActionName\":\"uploadfile\",\"catcherActionName\":\"catchimage\",\"fileManagerListSize\":20,\"catcherAllowFiles\":[\".png\",\".jpg\",\".jpeg\",\".gif\",\".bmp\"],\"snapscreenPathFormat\":\"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\",\"imageCompressBorder\":1600,\"snapscreenUrlPrefix\":\"\",\"imageCompressEnable\":true,\"catcherLocalDomain\":[\"127.0.0.1\",\"localhost\",\"img.baidu.com\"],\"imageManagerListPath\":\"/ueditor/jsp/upload/image/\",\"imageInsertAlign\":\"none\",\"catcherMaxSize\":2048000,\"videoMaxSize\":102400000,\"fileManagerUrlPrefix\":\"\",\"scrawlPathFormat\":\"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\",\"scrawlMaxSize\":2048000,\"catcherFieldName\":\"source\"}";
		} catch (IOException e) {
			return "{\"snapscreenInsertAlign\":\"none\",\"videoPathFormat\":\"/ueditor/jsp/upload/video/{yyyy}{mm}{dd}/{time}{rand:6}\",\"videoFieldName\":\"upfile\",\"fileManagerActionName\":\"listfile\",\"fileUrlPrefix\":\"\",\"imageUrlPrefix\":\"\",\"imageAllowFiles\":[\".png\",\".jpg\",\".jpeg\",\".gif\",\".bmp\"],\"videoAllowFiles\":[\".flv\",\".swf\",\".mkv\",\".avi\",\".rm\",\".rmvb\",\".mpeg\",\".mpg\",\".ogg\",\".ogv\",\".mov\",\".wmv\",\".mp4\",\".webm\",\".mp3\",\".wav\",\".mid\"],\"filePathFormat\":\"/ueditor/jsp/upload/file/{yyyy}{mm}{dd}/{time}{rand:6}\",\"fileMaxSize\":51200000,\"fileManagerListPath\":\"/ueditor/jsp/upload/file/\",\"catcherUrlPrefix\":\"\",\"videoActionName\":\"uploadvideo\",\"scrawlInsertAlign\":\"none\",\"videoUrlPrefix\":\"\",\"imageManagerUrlPrefix\":\"\",\"scrawlUrlPrefix\":\"\",\"imageFieldName\":\"upfile\",\"fileManagerAllowFiles\":[\".png\",\".jpg\",\".jpeg\",\".gif\",\".bmp\",\".flv\",\".swf\",\".mkv\",\".avi\",\".rm\",\".rmvb\",\".mpeg\",\".mpg\",\".ogg\",\".ogv\",\".mov\",\".wmv\",\".mp4\",\".webm\",\".mp3\",\".wav\",\".mid\",\".rar\",\".zip\",\".tar\",\".gz\",\".7z\",\".bz2\",\".cab\",\".iso\",\".doc\",\".docx\",\".xls\",\".xlsx\",\".ppt\",\".pptx\",\".pdf\",\".txt\",\".md\",\".xml\"],\"imageMaxSize\":2048000,\"catcherPathFormat\":\"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\",\"imageManagerInsertAlign\":\"none\",\"scrawlFieldName\":\"upfile\",\"imagePathFormat\":\"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\",\"scrawlActionName\":\"uploadscrawl\",\"imageManagerActionName\":\"listimage\",\"imageActionName\":\"uploadimage\",\"imageManagerListSize\":20,\"imageManagerAllowFiles\":[\".png\",\".jpg\",\".jpeg\",\".gif\",\".bmp\"],\"fileAllowFiles\":[\".png\",\".jpg\",\".jpeg\",\".gif\",\".bmp\",\".flv\",\".swf\",\".mkv\",\".avi\",\".rm\",\".rmvb\",\".mpeg\",\".mpg\",\".ogg\",\".ogv\",\".mov\",\".wmv\",\".mp4\",\".webm\",\".mp3\",\".wav\",\".mid\",\".rar\",\".zip\",\".tar\",\".gz\",\".7z\",\".bz2\",\".cab\",\".iso\",\".doc\",\".docx\",\".xls\",\".xlsx\",\".ppt\",\".pptx\",\".pdf\",\".txt\",\".md\",\".xml\"],\"snapscreenActionName\":\"uploadimage\",\"fileFieldName\":\"upfile\",\"fileActionName\":\"uploadfile\",\"catcherActionName\":\"catchimage\",\"fileManagerListSize\":20,\"catcherAllowFiles\":[\".png\",\".jpg\",\".jpeg\",\".gif\",\".bmp\"],\"snapscreenPathFormat\":\"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\",\"imageCompressBorder\":1600,\"snapscreenUrlPrefix\":\"\",\"imageCompressEnable\":true,\"catcherLocalDomain\":[\"127.0.0.1\",\"localhost\",\"img.baidu.com\"],\"imageManagerListPath\":\"/ueditor/jsp/upload/image/\",\"imageInsertAlign\":\"none\",\"catcherMaxSize\":2048000,\"videoMaxSize\":102400000,\"fileManagerUrlPrefix\":\"\",\"scrawlPathFormat\":\"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\",\"scrawlMaxSize\":2048000,\"catcherFieldName\":\"source\"}";
		}

		return "{\"state\": \"SUCCESS\",\"title\": \"1448348980390006185.png\",\"original\": \"cry2.png\",\"type\": \".png\",\"url\": \""
				+ fileUrl + "\",\"size\": \"8979\"}";
	}
}
