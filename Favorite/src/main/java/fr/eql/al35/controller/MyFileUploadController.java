package fr.eql.al35.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import fr.eql.al35.entity.MyUploadForm;
 
@Controller
public class MyFileUploadController {
	
	static Logger log = LoggerFactory.getLogger(MyFileUploadController.class);
 
   // GET: Show upload form page.
   @RequestMapping(value = "/uploadOneFile", method = RequestMethod.GET)
   public String uploadOneFileHandler(Model model) {
 
      MyUploadForm myUploadForm = new MyUploadForm();
      model.addAttribute("myUploadForm", myUploadForm);
 
      return "uploadOneFile";
   }
 
   // POST: Do Upload
   @RequestMapping(value = "/uploadOneFile", method = RequestMethod.POST)
   public String uploadOneFileHandlerPOST(HttpServletRequest request, //
         Model model, //
         @ModelAttribute("myUploadForm") MyUploadForm myUploadForm) {
 
      return this.doUpload(request, model, myUploadForm);
 
   }
 
   // GET: Show upload form page.
   @RequestMapping(value = "/uploadMultiFile", method = RequestMethod.GET)
   public String uploadMultiFileHandler(Model model) {
 
      MyUploadForm myUploadForm = new MyUploadForm();
      model.addAttribute("myUploadForm", myUploadForm);
 
      return "uploadMultiFile";
   }
 
   // POST: Do Upload
   @RequestMapping(value = "/uploadMultiFile", method = RequestMethod.POST)
   public String uploadMultiFileHandlerPOST(HttpServletRequest request, //
         Model model, //
         @ModelAttribute("myUploadForm") MyUploadForm myUploadForm) {
 
      return this.doUpload(request, model, myUploadForm);
 
   }
 
   private String doUpload(HttpServletRequest request, Model model, //
         MyUploadForm myUploadForm) {
 
      String description = myUploadForm.getDescription();
      BufferedOutputStream stream = null;
      // Root Directory.
      String uploadRootPath = "src/main/resources/static/photos/product/";
 
      File uploadRootDir = new File(uploadRootPath);
      // Create directory if it not exists.
      if (!uploadRootDir.exists()) {
         uploadRootDir.mkdirs();
      }
      MultipartFile[] fileDatas = myUploadForm.getFileDatas();
      //
      List<File> uploadedFiles = new ArrayList<File>();
      List<String> failedFiles = new ArrayList<String>();
 
      for (MultipartFile fileData : fileDatas) {
 
         // Client File Name
         String name = fileData.getOriginalFilename();
 
         if (name != null && name.length() > 0) {
            try {
               // Create the file at server
               File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);
 
               stream= new BufferedOutputStream(new FileOutputStream(serverFile));
               stream.write(fileData.getBytes());
               uploadedFiles.add(serverFile);
            } catch (Exception e) {
               failedFiles.add(name);
            } finally {
               try {
				stream.close();
			} catch (IOException e) {
				log.error(e.getMessage());
			}
            }
         }
      }
      model.addAttribute("description", description);
      model.addAttribute("uploadedFiles", uploadedFiles);
      model.addAttribute("failedFiles", failedFiles);
      return "uploadResult";
   }
 
}