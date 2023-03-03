package org.antwalk;

import javax.servlet.http.HttpServletRequest;

import org.antwalk.controller.AdminController;
import org.antwalk.entity.Admin;
import org.antwalk.entity.Stop;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class addcontroller {
	
	@RequestMapping("/admin/adding")
	public String call()
	{
		return "addadmin";
	}
	

	@RequestMapping("/admin/updating")
	public String call2()
	{
		return "adminupdate";
	}
	

	
	@RequestMapping("/admin/addingStop")
	public String add()
	{
		return "stopform";
	}
	
	@RequestMapping("/admin/managestop")
	public String add2()
	{
		return "managestop";
	}
	
	@RequestMapping("/admin/updatestop")
	public String add3()
	{
		return "updatestop";
	}
	
	@RequestMapping("/admin/updatingstop")
	public void updating(HttpServletRequest req)
	{
		Stop s=new Stop();
		s.setName(req.getParameter("Nname"));
		
		
	}
	
//	@RequestMapping("/admin/add")
//	public void create(HttpServletRequest req)
//	{
//		AdminController ad=new AdminController();
//		String n=req.getParameter("username");
//		String p=req.getParameter("password");
//		Admin a=new Admin(n,p);
//		ad.insert(a);
//		
//	}

}
