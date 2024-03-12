package com.bcs.controller;

import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bcs.dto.LoanInfo;

@Controller
public class LoanInfoController {

	@GetMapping("/loan-info.htm")
	public String loanInformation(Model model, HttpSession httpSession) {
		LoanInfo loanInfo = LoanInfo.of().loanNo(UUID.randomUUID().toString().substring(0, 10)).loanType("home loan")
				.loanHolderName("Mathew s").tenure(120).emiAmount(40000).build();
		model.addAttribute("loanInfo", loanInfo);

		/*
		 * httpSession.getAttributeNames().asIterator().forEachRemaining(System.out::
		 * println); SecurityContext securityContext =
		 * (SecurityContext)httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
		 * Authentication authentication = securityContext.getAuthentication();
		 * System.out.println(authentication.getCredentials());
		 */

		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();

		if (authentication instanceof UsernamePasswordAuthenticationToken) {
			UserDetails userDetails = (UserDetails)((UsernamePasswordAuthenticationToken) authentication).getPrincipal();
			System.out.println(userDetails.getUsername());
		}
		return "loan-info";

	}
}
