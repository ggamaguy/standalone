package web.app.pkvalve.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BaselineController {
	private static final Logger logger = LoggerFactory.getLogger(BaselineController.class);
	
	@RequestMapping(value = "/baseline", method = RequestMethod.GET)
	public ModelAndView monitor() {

		ModelAndView mv = new ModelAndView("baseline");
		
		return mv;
	}
}
