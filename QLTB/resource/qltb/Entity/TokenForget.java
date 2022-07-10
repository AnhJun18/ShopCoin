package qltb.Entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TOKENFORGET")
public class TokenForget {
	@Id
	@Column(name = "TOKEN")
	private String token;

	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "HSD")
	private Timestamp date;
	
	
	
	public TokenForget() {
		super();
	}



	public String getToken() {
		return token;
	}



	public void setToken(String token) {
		this.token = token;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String pass) {
		this.email = pass;
	}



	public Timestamp getDate() {
		return date;
	}



	public void setDate(Timestamp date) {
		this.date = date;
	}


	
}