package qltb.Controller;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.ParameterMode;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.procedure.ProcedureCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import qltb.Entity.TaiKhoan;
import qltb.model.Account;
import qltb.recaptcha.Recaptchaverification;

@Transactional
@Controller
public class LoginControll {
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);
	public static final Pattern VALID_PASS_REGEX = Pattern.compile(
			"^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", Pattern.CASE_INSENSITIVE);

	@Autowired
	SessionFactory factory;

	@RequestMapping("login")
	public String index(@ModelAttribute("account") Account acc) {
		return "login/index";
	}

	public Boolean checkLogin(String email, String pass) {
		Session session = factory.openSession();
		session.beginTransaction();
		ProcedureCall call = session.createStoredProcedureCall("sp_kiemTraDangNhap");
		call.registerParameter("Email", String.class, ParameterMode.IN).bindValue(email);
		call.registerParameter("Pass", String.class, ParameterMode.IN).bindValue(pass);
		call.registerParameter("Result", Boolean.class, ParameterMode.OUT);
		Boolean check = (Boolean) call.getOutputs().getOutputParameterValue("Result");
		session.getTransaction().commit();
		session.close();
		return check;
	}

	public Boolean validateEmail(String emailStr) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
		return matcher.find();
	}

	public Boolean validatePass(String pass) {
		Matcher matcher = VALID_PASS_REGEX.matcher(pass);
		return matcher.find();
	}

	public TaiKhoan getTKByID(String ID) {
		Session session = factory.getCurrentSession();
		String hql = "FROM TaiKhoan where email = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", ID);
		TaiKhoan tk = (TaiKhoan) query.list().get(0);
		return tk;
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String index2(@ModelAttribute("account") Account acc, ModelMap model, BindingResult errors,
			HttpServletRequest request, HttpSession ss) throws IOException {

		String gRepcaptchResponse = request.getParameter("g-recaptcha-response");
		boolean verity = Recaptchaverification.verify(gRepcaptchResponse);

		if (acc.getEmail().trim().isBlank() || acc.getEmail().trim().isEmpty()) {
			errors.rejectValue("email", "account", "Email không đc để trống");
		} else if (!validateEmail(acc.getEmail().trim())) {
			errors.rejectValue("email", "account", "Email không hợp lệ");
		}

		if (acc.getPass().trim().isBlank() || acc.getPass().trim().isEmpty()) {
			errors.rejectValue("pass", "account", "Mật khẩu ko đc để trống");
		} else if (!validatePass(acc.getPass().trim())) {
			errors.rejectValue("pass", "account",
					"Mật khẩu phải có ít nhất 8 kí tự gồm: số, chữ hoa, chữ thường, kí tự đặc biệt");
		}

		if (errors.hasErrors() || !verity) {
			if (!verity)
				model.addAttribute("tb", "Vui long Nhap Captcha");
		} else {

			if (checkLogin(acc.getEmail().trim().toString(), acc.getPass().trim().toString())) {

				TaiKhoan tk = this.getTKByID(acc.getEmail().trim().toString());
				ss.setAttribute("acc", tk.getId());
				ss.setAttribute("permission", "user");

				return "redirect:/home/index.htm";
			} else {
				ss.removeAttribute("permission");
				model.addAttribute("tb", "Sai Thong Tin");
			}
		}

		return "login/index";
	}

	@RequestMapping("logout")
	public String Logout(HttpSession ss) {
		ss.removeAttribute("acc");
		ss.removeAttribute("permission");
		return "redirect:/login.htm";
	}

}
