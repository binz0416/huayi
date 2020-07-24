package com.wicresoft.ghealth.archives.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.internal.org.apache.commons.lang3.StringUtils;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.wicresoft.ghealth.archives.service.IArchivesmanagerService;
import com.wicresoft.ghealth.authorization.annotation.Authorization;
import com.wicresoft.ghealth.common.BusinessException;
import com.wicresoft.ghealth.common.DateUtil;
import com.wicresoft.ghealth.common.Response;
import com.wicresoft.ghealth.common.Utils;


@RestController
@RequestMapping(value = "/archivesmanager")
public class ArchivesmanagerController {
	@Resource
	private IArchivesmanagerService archivesmanagerService;

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@Authorization
	public Response list(HttpSession session, HttpServletRequest request, @RequestBody Map<String, Object> list)
			throws BusinessException {
		Response rslt = new Response();
		Utils.putUserInfoToList(request, list);
		if (StringUtils.equals("getCustomerArchives", (String) list.get("apiflag"))) {
			rslt = this.archivesmanagerService.getUserArchives(list);
		} else if (StringUtils.equals("getCustomers", (String) list.get("apiflag"))) {
			rslt = this.archivesmanagerService.getCustomers(list);
		} else if (StringUtils.equals("getEcgData", (String) list.get("apiflag"))) {
			rslt = this.archivesmanagerService.getEcgData(list);
		}
		return rslt;

	}

	@RequestMapping(value = "/listreport", method = RequestMethod.GET)
	@Authorization
	public ResponseEntity<byte[]> listreport(HttpSession session, HttpServletRequest request) throws BusinessException {
		Map<String, Object> report = new HashMap<String, Object>();
		report.put("uid", request.getParameter("uid"));
		return Utils.getReportFile(report);
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@Authorization
	public Response edit(HttpServletRequest request, @RequestBody Map<String, Object> list) 
			throws BusinessException, UnsupportedEncodingException {
		Response rslt = new Response();
		Map<String, Object> reportpath = new HashMap<String, Object>();
		Utils.putUserInfoToList(request, list);
		// 模板路径
		String templatePath = request.getSession().getServletContext().getRealPath("/templet/model/PDF.pdf");
		// 生成的新文件路径
		// 获取当前日期
		String currentDate = DateUtil.getDateString(new Date(), "yyyyMMdd");
		String path = request.getSession().getServletContext().getRealPath("/archivesPdfs") + "/" + currentDate + "/";
		// linux系统路径
//		String path = Utils.getPathSet("pdfDownloadPath") + "/" + currentDate + "/";
//		String path = Utils.getPathSet("pdfDownloadPathTest") + "/" + currentDate + "/";
		File saveDirectory = new File(path);
		if (!saveDirectory.exists()) {
			saveDirectory.mkdirs();
		}

		Map<String, Object> archiveInfo = this.archivesmanagerService.getCustArchiveInfoForReport(list);
		if (archiveInfo != null) {

			String filename = "";
			String cardId = "";
			// if (archiveInfo.containsKey("customerId")) {
			// filename = archiveInfo.get("customerId").toString();
			// }

			if (archiveInfo.containsKey("customerName")) {
				filename = archiveInfo.get("customerName").toString();
			}
			if (archiveInfo.containsKey("customerIdcard")) {
				cardId = archiveInfo.get("customerIdcard").toString();
			}
//			String newPDFPath = path + filename + "_" + cardId + ".pdf";
//			String newOutPDFPath = "archivesPdfs/" + currentDate + "/" + filename + "_" + cardId + ".pdf";
			String newPDFPath = path + cardId + ".pdf";
			String newOutPDFPath = "archivesPdfs/" + currentDate + "/" + cardId + ".pdf";
			// linux系统路径
//			String newOutPDFPath = "pdfDownloadPath/" + currentDate + "/" + filename + "_" + cardId + ".pdf";
//			String newOutPDFPath = "pdfDownloadPathTest/" + currentDate + "/" + filename + "_" + cardId + ".pdf";

			// 在文件加下查询文件，如果有则删除
			File[] files = saveDirectory.listFiles();
			for (File file : files) {
				if (file.getName().equals(newPDFPath)) {
					file.delete();
				}
			}

			File newPDF = new File(newPDFPath);
			PdfReader reader;
			FileOutputStream out;
			ByteArrayOutputStream bos;
			PdfStamper stamper;

			try {
				out = new FileOutputStream(newPDF);// 输出流
				reader = new PdfReader(templatePath);// 读取pdf模板
				bos = new ByteArrayOutputStream();
				stamper = new PdfStamper(reader, bos);
				AcroFields form = stamper.getAcroFields();
				String fontsPath = request.getSession().getServletContext().getRealPath(Utils.getPathSet("fontsPathLocal"))+",1";
//				String fontsPath = Utils.getPathSet("fontsPathLine");
//				String fontsPath = Utils.getPathSet("fontsPathLineTest");
				BaseFont bf = BaseFont.createFont(fontsPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
				// Font font = new Font(bf,12);
				form.addSubstitutionFont(bf);

				java.util.Iterator<String> it = form.getFields().keySet().iterator();
				while (it.hasNext()) {
					String name = it.next().toString();
					if (archiveInfo.get(name) != null && name != "toxindetection") {
						form.setField(name, archiveInfo.get(name).toString());
					}
				}
				int reportPage = 1;
				form.setField("page1", Integer.toString(reportPage));
				if (archiveInfo.containsKey("BloodFlag")) {
					reportPage++;
					form.setField("page2", Integer.toString(reportPage));
				}
				if (archiveInfo.containsKey("UrineFlag")) {
					reportPage++;
					form.setField("page3", Integer.toString(reportPage));
				}
				if (archiveInfo.containsKey("BFlag")) {
					reportPage++;
					form.setField("page4", Integer.toString(reportPage));
				}
				if (archiveInfo.containsKey("HeartFlag")) {
					reportPage++;
					form.setField("page5", Integer.toString(reportPage));
				}
				stamper.setFormFlattening(true);// 如果为false那么生成的PDF文件还能编辑，一定要设为true
				stamper.close();

				Document doc = new Document();
				PdfCopy copy = new PdfCopy(doc, out);
				doc.open();
				PdfImportedPage importPage;
				// for (int j = 1; j < 6; j++) {
				// importPage = copy.getImportedPage(new
				// PdfReader(bos.toByteArray()), j);
				// copy.addPage(importPage);
				// }
				importPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), 1);
				copy.addPage(importPage);
				// 有数据打印，无数据不打印
				if (archiveInfo.containsKey("BloodFlag")) {
					importPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), 2);
					copy.addPage(importPage);
				}
				if (archiveInfo.containsKey("UrineFlag")) {
					importPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), 3);
					copy.addPage(importPage);
				}
				if (archiveInfo.containsKey("BFlag")) {
					importPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), 4);
					copy.addPage(importPage);
				}
				if (archiveInfo.containsKey("HeartFlag")) {
					importPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), 5);
					copy.addPage(importPage);
				}
				//

				// 合并毒素报告start
				// PdfReader reader1 = new PdfReader(toxinPath);
				// PdfImportedPage page;
				// int n = reader1.getNumberOfPages();
				// for (int j = 1; j <= n; j++) {
				// // doc.newPage();
				// page = copy.getImportedPage(reader1, j);
				// copy.addPage(page);
				// }
				// 合并毒素报告end
				doc.close();
				bos.close();
				reader.close();
				out.close();

				// 毒素html转换 start
				// Document document = new Document();
				// PdfWriter writer = PdfWriter.getInstance(document, new
				// FileOutputStream(toxinPath));
				// document.open();
				// ElementList elementList;
				//
				// elementList = MyXMLWorkerHelper.parseToElementList(html,
				// null);
				//
				// Paragraph context = new Paragraph();
				// for (Element element : elementList) {
				// context.add(element);
				// }
				// document.add(context);
				// document.close();
				// writer.close();
				// 毒素html转换 end

				// String report = "D:" + newPDFPath;
				// String[] files={report,toxinPath};
				// File test = new File(report);
				// FileOutputStream out1 = new FileOutputStream(test,true) ;//
				// 输出流
				// mergePdfFiles(files,out1);

			} catch (IOException e) {
				System.out.println(e);
			} catch (DocumentException e) {
				System.out.println(e);
			}
			reportpath.put("path", newOutPDFPath);
			rslt.success(reportpath);
		} else {
			reportpath.put("path", "");
			rslt.success(reportpath);
		}
		return rslt;
	}
	
	public static void mergePdfFiles(String[] files, FileOutputStream  os) {  
	    try {  
	        Document document = new Document(new PdfReader(files[0]).getPageSize(1));  
	        PdfCopy copy = new PdfCopy(document, os);  
	        document.open();  
	        for (int i = 0; i < files.length; i++) {  
	            PdfReader reader = new PdfReader(files[i]);  
	            int n = reader.getNumberOfPages();  
	            for (int j = 1; j <= n; j++) {  
	                document.newPage();  
	                PdfImportedPage page = copy.getImportedPage(reader, j);
	                copy.addPage(page);  
	            }  
	        } 
	        document.close();  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    } catch (DocumentException e) {  
	        e.printStackTrace();  
	    }  
	}  
}
