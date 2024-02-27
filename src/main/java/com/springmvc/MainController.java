package com.springmvc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

	@Autowired
	private MainDao dao;
	
	
	@RequestMapping("Adminform")
	 public String getAdlogForm()
	 {
	  return "Admin_form";
	 }

	@RequestMapping("logform")
	 public String getlogForm()
	 {
	  return "login_form";
	 }
	@RequestMapping("regform")
	 public String getregForm()
	 {
	  return "reg_form";
	 }
	@RequestMapping("At")
	 public String getAtForm()
	 {
	  return "assign_task";
	 }
	
	
	
	@RequestMapping(value="save",method=RequestMethod.POST)
	public String getRegistration(@ModelAttribute("reg") RegistrationModel reg,HttpSession session)
	{
		int status = dao.save(reg);
		session.setAttribute("sesreg", "success");
		return "login_form";
	}	
		
	@RequestMapping(value="log",method=RequestMethod.POST)
	public String doLogin(@ModelAttribute("log") LoginModel log,HttpSession session,Model m1)
	{
		List<RegistrationModel> list=dao.doLogin(log); 
		if(list==null)
		{
			session.setAttribute("seslog1", "failed");
			return "login_form";
		}
		else			
		{
			session.setAttribute("seslog",log.getEmp_name());
				List<AssigntaskModel> list2 =dao.getTask1(log);
				m1.addAttribute("list2",list2);
				return "emp_dashboard";
				
			
		}
	}	

	@RequestMapping(value="log2",method=RequestMethod.POST)
	public String doLogin2(@ModelAttribute("log2") LoginModel2 log2,HttpSession session)
	{
		List<RegModel2> list2 =dao.doLogin2(log2);
		if(list2==null)
		{
			session.setAttribute("seslog", "failed");
			return "Admin_form";
		}
		else			
		{
			session.setAttribute("seslog",log2.getAd_name());
			return "redirect:/view";
		}
	}
	
	@RequestMapping(value="assigntask",method=RequestMethod.POST)
	public String getAssigntask(@ModelAttribute("atask") AssigntaskModel atask)
	{
		dao.savetask(atask);
		
		return "redirect:/view";
	}	
	
	@RequestMapping("view")
	public String getview(Model m)
	{
		List<AssigntaskModel> list2 =dao.getTask();
		m.addAttribute("list2",list2);
		return "admin_dashboard";
		
	}
	
	@RequestMapping("update/{task_id}")
	public String updateStatus(@PathVariable int task_id,Model model)
	{
		AssigntaskModel atmm = dao.updateStatus(task_id);
		model.addAttribute("command", atmm);
		return "update_status";
		
	}
	
	
	  @RequestMapping(value="update",method=RequestMethod.POST) 
	  public String getUpdate_stat(@ModelAttribute("update") AssigntaskModel update,HttpSession session) {
		  dao.Update_stat(update); 
		  session.setAttribute("sesupd", "success");
		  return "emp_dashboard"; 
		  }
	 
 		
	}


 