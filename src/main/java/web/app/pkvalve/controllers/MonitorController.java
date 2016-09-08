package web.app.pkvalve.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application monitor page.
 */
@Controller
public class MonitorController {
	
	/**
	 * Simply selects the monitor view to render by returning its name.
	 */
	@RequestMapping(value = "/monitor", method = RequestMethod.GET)
	public ModelAndView monitor() {

		ModelAndView mv = new ModelAndView("monitor");
		
		return mv;
	}
}
