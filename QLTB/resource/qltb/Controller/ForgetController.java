package qltb.Controller;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.websocket.server.PathParam;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import qltb.Entity.TaiKhoan;
import qltb.Entity.TokenForget;
import qltb.recaptcha.Recaptchaverification;

@Transactional
@Controller
public class ForgetController {
	public static final Pattern VALID_PASS_REGEX = Pattern.compile(
			"^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", Pattern.CASE_INSENSITIVE);

	@Autowired
	SessionFactory factory;

	@RequestMapping("forget-pass")
	public String index() {
		return "login/forget";
	}

	public TokenForget getTKByID(String ID) {
		Session session = factory.getCurrentSession();
		String hql = "FROM TokenForget where email = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", ID);
		TokenForget tk = (TokenForget) query.list().get(0);
		return tk;
	}


	public Integer add(TokenForget tk) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		try {
			session.save(tk);
			t.commit();
		} catch (Exception e) {
			System.out.println(e);
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}

	@Autowired
	JavaMailSender mailer;
	@Autowired
	JavaMailSenderImpl mailsender;
	
	public void sendmail(String email, String token) {
				try {
					MimeMessage mail = mailer.createMimeMessage();
					MimeMessageHelper helper = new MimeMessageHelper(mail, true);
					helper.setFrom("n19dccn006@student.ptithcm.edu.vn");
					helper.setTo(email);
					helper.setReplyTo(mailsender.getUsername());
					helper.setSubject("[ Khôi Phục Mật Khẩu ]");
					
					helper.setText(
							"<div style=\"margin:0;padding:0\" dir=\"ltr\" bgcolor=\"#ffffff\"><table style=\"border-style:solid;border-width:thin;border-color:#dadce0;border-radius:8px;padding:4px 20px\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" id=\"m_2897520027820035933email_table\" style=\"border-collapse:collapse\"><tbody><tr><td id=\"m_2897520027820035933email_content\" style=\"font-family:Helvetica Neue,Helvetica,Lucida Grande,tahoma,verdana,arial,sans-serif;background:#ffffff\"><table border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse\"><tbody><tr><td height=\"20\" style=\"line-height:20px\" colspan=\"3\">&nbsp;</td></tr><tr><td height=\"1\" colspan=\"3\" style=\"line-height:1px\"><span style=\"color:#ffffff;font-size:1px;opacity:0\">&nbsp; Xin chào Anh, &nbsp; Chúng tôi nhận thấy một lần đăng nhập khác thường từ thiết bị hoặc vị trí bạn thường không sử dụng. Có phải bạn đã đăng nhập không? &nbsp; Lượt đăng nhập mới &nbsp; &nbsp; 29 tháng 3, 2022 lúc 19:17 &nbsp; &nbsp; Gần Phúc Lý, Vietnam &nbsp; &nbsp; Facebook Messenger for Android &nbsp;</span></td></tr><tr><td width=\"15\" style=\"display:block;width:15px\">&nbsp;&nbsp;&nbsp;</td><td><table border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse\"><tbody><tr><td height=\"15\" style=\"line-height:15px\" colspan=\"3\">&nbsp;</td></tr><tr><td width=\"32\" align=\"left\" valign=\"middle\" style=\"height:32;line-height:0px\"><a style=\"color:#3b5998;text-decoration:none\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q=https://www.facebook.com/n/?login_alerts%252Fstart%252F%26fbid%3D742416433798381%26s%3De%26aref%3D1648556226056122%26medium%3Demail%26mid%3D5db5a1ea90ce8G5afc965ce04eG5db5a683f0fbaG2bf%26n_m%3Danhle180101%2540gmail.com&amp;source=gmail&amp;ust=1650373264816000&amp;usg=AOvVaw16TX3eMYr8d8GWGTIhDVbJ\"><img src=\"https://user-images.githubusercontent.com/81857289/161365448-2d3a897e-8a96-4525-86b5-a7aa0cd7bb4f.png\" width=\"60\" height=\"60\" style=\"border:0\" class=\"CToWUd\"></a></td><td width=\"15\" style=\"display:block;width:15px\">&nbsp;&nbsp;&nbsp;</td><td width=\"100%\"><p  style=\"color:#ff0000;text-decoration:none;font-family:Helvetica Neue,Helvetica,Lucida Grande,tahoma,verdana,arial,sans-serif;font-size:19px;line-height:32px\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q=https://www.facebook.com/n/?login_alerts%252Fstart%252F%26fbid%3D742416433798381%26s%3De%26aref%3D1648556226056122%26medium%3Demail%26mid%3D5db5a1ea90ce8G5afc965ce04eG5db5a683f0fbaG2bf%26n_m%3Danhle180101%2540gmail.com&amp;source=gmail&amp;ust=1650373264816000&amp;usg=AOvVaw16TX3eMYr8d8GWGTIhDVbJ\">"
									+ "Đổi mật khẩu mới</p></td></tr><tr style=\"border-bottom:solid 1px #e5e5e5\"><td height=\"15\" style=\"line-height:15px\" colspan=\"3\">&nbsp;</td></tr></tbody></table></td><td width=\"15\" style=\"display:block;width:15px\">&nbsp;&nbsp;&nbsp;</td></tr><tr><td width=\"15\" style=\"display:block;width:15px\">&nbsp;&nbsp;&nbsp;</td><td><table border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse\"><tbody><tr><td height=\"28\" style=\"line-height:28px\">&nbsp;</td></tr><tr><td><span class=\"m_2897520027820035933mb_text\" style=\"font-family:Helvetica Neue,Helvetica,Lucida Grande,tahoma,verdana,arial,sans-serif;font-size:16px;line-height:21px;font-weight:bold;color:#141823\">"
									+ "Vui lòng click vào link phía dưới để tiến hành đổi mật khẩu mới! " + ","
									+ "</span></td></tr><tr><td height=\"28\" style=\"line-height:28px\">&nbsp;</td></tr><tr><td><span class=\"m_2897520027820035933mb_text\" style=\"font-family:Helvetica Neue,Helvetica,Lucida Grande,tahoma,verdana,arial,sans-serif;font-size:16px;line-height:21px;color:#141823\">"
									+ "</span></td></tr><tr><td height=\"28\" style=\"line-height:28px\">&nbsp;</td></tr><tr><td></td></tr><tr style=\"border-top:solid 1px #e5e5e5\"><td height=\"0\" style=\"line-height:0px\">&nbsp;</td></tr><tr><td></td></tr> <tr><td><table border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\" style=\"border-collapse:collapse;min-width:420px\"><tbody><tr><td valign=\"top\" style=\"padding:10px;font-size:0px\"><img src=\"https://ci4.googleusercontent.com/proxy/d6rX5gkKj0t5NvoFInNVBzDwZrsQ_zJreoQzj-jUuiyPDxRhRVGSAQpZC8P2bKfRj6Lb84l_bu70mCJ-L2XkGMB6RVfwhXXxcyw-W9hA3Q=s0-d-e1-ft#https://static.xx.fbcdn.net/rsrc.php/v3/yv/r/RdSO_hsxQmk.png\" width=\"16px\" height=\"16px\" style=\"border:0\" class=\"CToWUd\"></td><td width=\"100%\" valign=\"top\" style=\"padding:10px\"><span style=\"font-family:Helvetica Neue,Helvetica,Lucida Grande,tahoma,verdana,arial,sans-serif;font-size:16px;line-height:21px;color:#141823\">"
									+ " <a href="+"http://localhost:8080/ShopCoin/forget-pass/token="+token+".htm><strong>CLICK ME</strong></a>"
						
									+ "</span></td></tr></tbody></table></td></tr> <tr><td></td></tr><tr style=\"border-top:solid 1px #e5e5e5;\"><td height=\"0\" style=\"line-height:0px\">&nbsp;</td></tr><tr><td><table border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\" style=\"border-collapse:collapse;min-width:420px\"><tbody><tr><td valign=\"top\" style=\"padding:10px;font-size:0px\"><img src=\"https://ci6.googleusercontent.com/proxy/XJJ9_W1e1lAf0DK343oJdaRXblm-vUxobgBwH-VCBLzypv2XcPj1g5Y0dsI3IL2kQqGlwSY9feRqwBjhyTDFkq4JQ-LhKCuSKEl0opvmug=s0-d-e1-ft#https://static.xx.fbcdn.net/rsrc.php/v3/yB/r/XazzlS6G-Oz.png\" width=\"16px\" height=\"16px\" style=\"border:0\" class=\"CToWUd\"></td><td width=\"100%\" valign=\"top\" style=\"padding:10px\"><span class=\"m_2897520027820035933mb_text\" style=\"font-family:Helvetica Neue,Helvetica,Lucida Grande,tahoma,verdana,arial,sans-serif;font-size:16px;line-height:21px;color:#141823\">"
									+ "</span></td></tr></tbody></table></td></tr><tr></tr><tr><td></td></tr><tr style=\"border-top:solid 1px #e5e5e5\"><td height=\"0\" style=\"line-height:0px\">&nbsp;</td></tr><tr><td><table border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\" style=\"border-collapse:collapse;min-width:420px\"><tbody><tr><td valign=\"top\" style=\"padding:10px;font-size:0px\"><img src=\"https://user-images.githubusercontent.com/81857289/163915325-fed7e057-dbed-4616-ac22-5585271e7a88.png\" width=\"16px\" height=\"19.18pxpx\" style=\"border:0\" class=\"CToWUd\"></td><td width=\"100%\" valign=\"top\" style=\"padding:10px\"><span class=\"m_2897520027820035933mb_text\" style=\"font-family:Helvetica Neue,Helvetica,Lucida Grande,tahoma,verdana,arial,sans-serif;font-size:16px;line-height:21px;color:#141823\">"
									
									+ "</span></td></tr></tbody></table></td></tr><tr></tr><tr><td></td></tr><tr style=\"border-top:solid 1px #e5e5e5\"><td height=\"0\" style=\"line-height:0px\">&nbsp;</td></tr><tr><td><table border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\" style=\"border-collapse:collapse;min-width:420px\"><tbody><tr><td valign=\"top\" style=\"padding:10px;font-size:0px\"><img src=\"https://user-images.githubusercontent.com/81857289/163916743-4bc66522-3224-4cce-9e92-bea249ad3235.png\" width=\"48px\" height=\"46px\" style=\"border:0\" class=\"CToWUd\"></td><td width=\"100%\" valign=\"top\" style=\"padding:10px\"><span class=\"m_2897520027820035933mb_text\" style=\"font-family:Helvetica Neue,Helvetica,Lucida Grande,tahoma,verdana,arial,sans-serif;font-size:16px;line-height:21px;color:#141823\">"
									+ "</span></td></tr></tbody></table></td></tr><tr><td height=\"28\" style=\"line-height:28px\">&nbsp;</td></tr></tbody></table></td><td width=\"15\" style=\"display:block;width:15px\">&nbsp;&nbsp;&nbsp;</td></tr>"
									+ "<tr><td width=\"15\" style=\"display:block;width:15px\">&nbsp;&nbsp;&nbsp;</td><td><table border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse\"><tbody><tr><td style=\"font-family:Helvetica Neue,Helvetica,Lucida Grande,tahoma,verdana,arial,sans-serif;font-size:11px;color:#aaaaaa;line-height:16px\"><span class=\"m_2897520027820035933mb_text\" style=\"font-family:Helvetica Neue,Helvetica,Lucida Grande,tahoma,verdana,arial,sans-serif;font-size:16px;line-height:21px;color:#141823;font-family:Helvetica Neue,Helvetica,Lucida Grande,tahoma,verdana,arial,sans-serif;font-size:12px;color:#aaaaaa;line-height:16px\">"
									+ "Token chỉ có tác dụng đổi lại mật khẩu trong 8 tiếng  </span></td></tr></tbody></table></td><td width=\"15\" style=\"display:block;width:15px\">&nbsp;&nbsp;&nbsp;</td></tr><tr><td height=\"20\" style=\"line-height:20px\" colspan=\"3\">&nbsp;</td></tr></tbody></table></td></tr></tbody></table></div><div dir=\"ltr\" data-smartmail=\"gmail_signature\"><div dir=\"ltr\"><div><div dir=\"ltr\"><div style=\"color:rgb(136,136,136);font-size:12.8px\"><i><font face=\"comic sans ms, sans-serif\">------------------------------<wbr>-----------------------------</font></i></div><div><p style=\"font-size:13px;color:rgb(136,136,136);margin:0px\"><i><font face=\"comic sans ms, sans-serif\"><span style=\"font-size:10pt;color:navy\"><b>"
									+ "AIKING</b><brCông ty......&nbsp;</span><span style=\"color:navy;font-size:10pt\"></span></font></i></p><p style=\"margin:0px\"><font face=\"comic sans ms, sans-serif\"><span style=\"font-size:13.3333px\"><i><font color=\"#000080\">"
									+ "Địa chỉ</font><font color=\"#073763\">:&nbsp;</font></i></span><span><font color=\"#0000ff\">97 Đường Man Thiện, Phường Hiệp Phú, Quận 9, TP. Hồ Chí Minh</font></span></font></p><p style=\"font-size:13px;color:rgb(136,136,136);margin:0px\"><span style=\"font-size:10pt;color:navy\"><i><font face=\"comic sans ms, sans-serif\">"
									+ "Phone: 028.389 666 75&nbsp;</font></i></span></p><p style=\"font-size:13px;color:rgb(136,136,136);margin:0px\"> \r\n<span style=\"font-size:10pt;color:navy\"><i><font face=\"comic sans ms, sans-serif\">"
									+ "Email:&nbsp;<a href=\"mailto:abc@ptithcm.edu.vn\" style=\"color:rgb(17,85,204)\" target=\"_blank\">pcsvc@ptithcm.edu.vn</a>&nbsp;</font></i></span></p></div></div></div></div></div>",
							true);
					mailer.send(mail);

				} catch (Exception e) {
					System.out.println(e);
				}
			

	}

	@RequestMapping(value = "forget-pass", method = RequestMethod.POST)
	public String index2(@RequestParam("email")String email, ModelMap model,
			HttpServletRequest request, HttpSession ss) throws IOException {

		String gRepcaptchResponse = request.getParameter("g-recaptcha-response");
		boolean verity = Recaptchaverification.verify(gRepcaptchResponse);

		
		if (!verity) {
				model.addAttribute("tb", "Vui long Nhap Captcha");
		} else {
			TokenForget token= new TokenForget();
			token.setEmail(email);
			token.setToken(" ");
			token.setDate(null);
			if(this.add(token)==1) {
				this.sendmail(email,this.getTKByID(email).getToken());
			}
			
			return "redirect:/login.htm";
		}

		return "register/index";
	}
	
	public Boolean checkToken (String ID) {
		Session session = factory.getCurrentSession();
		String hql = "FROM TokenForget where token = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", ID);
		if(query.list().size()==0)
			return false;
		return true;
	}
	
	public Integer update(TokenForget tk) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		try {
			session.update(tk);
			t.commit();
		} catch (Exception e) {
			System.out.println(e);
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}

@RequestMapping(value = "forget-pass/token={id}")
public String inde22(@PathVariable("id") String id)  {
   if(checkToken(id))
	   return "login/changepass";
   else
	  return "redirect:/login.htm";
}

public TaiKhoan getToken (String ID) {
	Session session = factory.getCurrentSession();
	String hql = "FROM TaiKhoan where email = (select email from TokenForget where token=:id)";
	Query query = session.createQuery(hql);
	query.setParameter("id", ID);
	TaiKhoan tk =(TaiKhoan) query.list().get(0);
	return tk;
}

public Boolean validatePass(String pass) {
	Matcher matcher = VALID_PASS_REGEX.matcher(pass);
	return matcher.find();
}
@RequestMapping(value = "forget-pass/token={id}",method = RequestMethod.POST)
public String inde21(@PathParam("pass") String pass,@PathParam("rppass") String rppass, ModelMap md,@PathVariable("id") String id)  {

	if(pass.trim().isBlank()||pass.trim().isEmpty()||rppass.trim().isBlank()||rppass.trim().isEmpty()) {
		md.addAttribute("tb","Mật khẩu không được để trông");
		return "login/changepass"; 
	}
	if(!pass.equals(rppass)) {
		md.addAttribute("tb","Mật khẩu xác nhận không trùng");
		return "login/changepass";
	}
	if (!validatePass(pass.trim())) {
		md.addAttribute("tb","Mật khẩu phải có ít nhất 8 kí tự gồm: số, chữ hoa, chữ thường, kí tự đặc biệt");
		return "login/changepass";
	}
	
	this.getToken(id).setPass(rppass);
	 return "redirect:/login.htm";
}
}


