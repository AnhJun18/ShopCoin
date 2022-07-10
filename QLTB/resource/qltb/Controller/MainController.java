package qltb.Controller;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Transactional
@Controller
@RequestMapping("home")
public class MainController {
	@Autowired
	SessionFactory factory;
	@RequestMapping("index")
	public String index2() {
		return "home/index";
	}
}


