package com.test.cotroller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.dao.LoginDao;
import com.test.model.LoginBean;


@Controller
public class LoginController {
	@Autowired
	LoginDao loginDao;
	@RequestMapping("/")
	public String login(Model model) {
		model.addAttribute("messessError","");
		return "login";
	}
	
	
	@RequestMapping("/login")
	public String outhenlogin( String email ,String password, Model model ,HttpServletRequest request ) {
		String role ="";
		String outhen ="";
		
		LoginBean bean = new LoginBean();
		try {
			bean = loginDao.login(email, password);
			if(bean.getLoEmail()!=null){
			role = bean.getLoRole();
				
				System.out.println(bean.getLoRole());
				System.out.println(role);
				if(bean.getLoRole().equals("1")) {
					 model.addAttribute("messessError","L");
						
					 outhen ="adminpage";
					
				}
				else	if(bean.getLoRole().equals("2")) {
					 model.addAttribute("messessError","L");
						
					 outhen ="userpage";
					
				}
				else	if(bean.getLoRole().equals("3")) {
					 model.addAttribute("messessError","L");
						
					 outhen ="memberpage";
					
				}
			
				else {
					model.addAttribute("messessError", "F");
				       outhen ="login";
					
				}
				 }
			else {
				model.addAttribute("messessError", "F");
			       outhen ="login";
			}
			}
		
		 catch (Exception e) {
			// TODO: handle exception
			 model.addAttribute("messessError", "F");
		       outhen ="login";
		}	
		request.getSession().setAttribute("LoginMember", bean);
		return outhen;
	}
	// endclass
}
