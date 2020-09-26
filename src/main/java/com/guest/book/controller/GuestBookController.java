package com.guest.book.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.guest.book.model.IDDetails;
import com.guest.book.model.UserData;
import com.guest.book.model.UserEntry;
import com.guest.book.model.UserModel;
import com.guest.book.service.UserService;

@Controller
public class GuestBookController {
	Logger logger = LoggerFactory.getLogger(GuestBookController.class);
	private final String UPLOAD_DIR = "D://uploads//";
	@Autowired

	Environment env;
	@Autowired
	UserService userServ;

	@RequestMapping(path = "/")
	public String index(Model model) {
		logger.info("index");
		UserModel user=  new UserModel();
user.setStatus("success");
		model.addAttribute("user", user);
		return "index";

	}


	@RequestMapping(path = "/logout")
	public String logout(Model model) {
		logger.info("lohgout");
		UserModel user=  new UserModel();
user.setStatus("success");
		model.addAttribute("user", user);
		return "index";

	}

	@RequestMapping(path = "/memberRegisterNew")
	public String memberRegisterNew(Model model) {
		logger.info("index");
		model.addAttribute("user", new UserModel());
		return "register";

	}


	@RequestMapping(path = "/memberRegister")
	public String memberRegister(@ModelAttribute UserModel user,Model model) {
		
		logger.info("index");
		
		model.addAttribute("user", userServ.addNewUSer(user));
		return "registerSuccess";

	}

	@PostMapping("/memberLogin")
	public String memberLogin(@ModelAttribute UserModel user, Model model) {
		logger.info("memberLogin ");
		model.addAttribute("user", userServ.validateUser(user));

		if (user.getRole().equals("guest")&&user.getStatus().equals("success")) {
			logger.info("memberLogin End");
			UserEntry usernetry = new UserEntry();
			usernetry.setUser(user.getUserName());
			model.addAttribute("userEntry", usernetry);
			model.addAttribute("user", user);
			return "user.html";

		}  else if (user.getRole().equals("admin")&&user.getStatus().equals("success")) {

			model.addAttribute("entrylist", userServ.pendingadminentrys());
			model.addAttribute("entryModel", new UserEntry());

			logger.info("memberLogin End");

			return "admin.html";
		}
		else {
			model.addAttribute("user", user);
			return "index.html";
		}

	}

	@PostMapping("/approveentry")
	public String adminApproveEntrys(@ModelAttribute List<UserEntry> userList,UserEntry entryModel, Model model) {
		logger.info("approve ");
	
		UserEntry	entry=userList.get(0);
		model.addAttribute("user", userServ.approveEntry(entry));
		logger.info("approve End");
		return "admin.html";
	}

    
	
	@PostMapping("/removeEntrys")
	public String removeEntries(@ModelAttribute UserEntry entry, Model model) {
		logger.info("removeEntrys ");
		model.addAttribute("user", userServ.removeEntry(entry));
		logger.info("removeEntrys End");
		return "admin.html";
	}

	@PostMapping("/guestEntrySubmit")
	public String guestAddEntry(@ModelAttribute UserEntry userEntry, UserData data,UserModel user, Model model) {
		logger.info("guestAddEntry ");
		//userEntry.setUser(user.getUserName());
		model.addAttribute("userEntry", userServ.addUserEntry(userEntry));
		data.setStatus("suucess");
		model.addAttribute("user", user);
		
		logger.info("guestAddEntry End");
		return "user.html";
	}

	@PostMapping("/guestFileuploadEntry")
	public String uploadFile(@RequestParam("file") MultipartFile file, Model model, RedirectAttributes attributes,
			UserData data ,@ModelAttribute UserEntry userEntry) {

		// check if file is empty
		if (file.isEmpty()) {
			attributes.addFlashAttribute("message", "Please select a file to upload.");
			return "redirect:/";
		}

		// normalize the file path
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		// save the file on the local file system
		try {
			Path path = Paths.get(UPLOAD_DIR + fileName);
			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		// return success response
		userEntry= new UserEntry();
		userEntry.setEntry(file.getOriginalFilename());
		

 userServ.addUserEntry(userEntry);
		attributes.addFlashAttribute("message", "You successfully uploaded " + fileName + '!');
		data.setImageStatus("success");
		data.setStatus("successfully");
		model.addAttribute("user", data);

		return "user.html";
	}

}
