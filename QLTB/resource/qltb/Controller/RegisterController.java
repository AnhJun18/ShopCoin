package qltb.Controller;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import qltb.Entity.TaiKhoan;
import qltb.recaptcha.Recaptchaverification;

@Transactional
@Controller
public class RegisterController {
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);
	public static final Pattern VALID_PASS_REGEX = Pattern.compile(
			"^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", Pattern.CASE_INSENSITIVE);

	@Autowired
	SessionFactory factory;

	@RequestMapping("register")
	public String index(@ModelAttribute("account") TaiKhoan acc) {
		return "register/index";
	}

	public TaiKhoan getTKByID(String ID) {
		Session session = factory.getCurrentSession();
		String hql = "FROM TaiKhoan where id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", ID);
		TaiKhoan tk = (TaiKhoan) query.list().get(0);
		return tk;
	}

	public boolean isExistEmail(String email) {
		Session session = factory.getCurrentSession();
		String hql = "FROM TaiKhoan where email = :email";
		Query query = session.createQuery(hql);
		query.setParameter("email", email);

		return (query.list().size() == 0) ? false : true;
	}

	public boolean isExisUserName(String user) {
		Session session = factory.getCurrentSession();
		String hql = "FROM TaiKhoan where id = :user";
		Query query = session.createQuery(hql);
		query.setParameter("user", user);

		return (query.list().size() == 0) ? false : true;
	}

	public Integer add(TaiKhoan tk) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		try {
			tk.setStatus("0");
			session.save(tk);
			t.commit();
		} catch (Exception e) {

			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}

	public Boolean validateEmail(String emailStr) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
		return matcher.find();
	}

	public Boolean validatePass(String pass) {
		Matcher matcher = VALID_PASS_REGEX.matcher(pass);
		return matcher.find();
	}
	/*
	 * public Boolean validationEmail(String email) { Pattern p =
	 * Pattern.compile("\\b[A-Z0-9._%-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b"); Matcher m =
	 * p.matcher("anhle180101@gmail.com");
	 * 
	 * if (m.find()) { System.out.println("PPPP"); return true;} return false; }
	 * public Boolean validationPass(String pass) { Pattern p = Pattern.
	 * compile(" ^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$"
	 * ); Matcher m = p.matcher(pass); if (m.find()) return true; return false; }
	 */

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String index2(@ModelAttribute("account") TaiKhoan acc, ModelMap model, BindingResult errors,
			HttpServletRequest request, HttpSession ss) throws IOException {

		String gRepcaptchResponse = request.getParameter("g-recaptcha-response");
		boolean verity = Recaptchaverification.verify(gRepcaptchResponse);

		if (acc.getId().trim().isBlank() || acc.getId().trim().isEmpty()) {
			errors.rejectValue("id", "account", "Tên Đăng nhập không đc để trống");
		} else if (isExisUserName(acc.getId().trim())) {
			errors.rejectValue("id", "account", "Tên Đăng nhập đã tồn tại!");
		}

		if (acc.getEmail().trim().isBlank() || acc.getEmail().trim().isEmpty()) {
			errors.rejectValue("email", "account", "Email không đc để trống");
		} else if (!validateEmail(acc.getEmail().trim())) {
			errors.rejectValue("email", "account", "Email không hợp lệ");
		} else if (isExistEmail(acc.getEmail().trim())) {
			errors.rejectValue("email", "account", "Email đã tồn tại");
		}

		if (acc.getPass().trim().isBlank() || acc.getPass().trim().isEmpty()) {
			errors.rejectValue("pass", "account", "Mật khẩu ko đc để trống");
		}  else if (!validatePass(acc.getPass().trim())) {
			errors.rejectValue("pass", "account",
					"Mật khẩu phải có ít nhất 8 kí tự gồm: số, chữ hoa, chữ thường, kí tự đặc biệt");
		}
		if (errors.hasErrors() || !verity) {
			if (!verity)
				model.addAttribute("tb", "Vui long Nhap Captcha");
		} else {
			this.add(acc);
			return "redirect:/login.htm";
		}

		return "register/index";
	}

}
