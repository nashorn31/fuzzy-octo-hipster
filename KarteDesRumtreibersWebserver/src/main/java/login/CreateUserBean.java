package login;

import hibernateentitysets.UserDAO;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "createUserBean")
@ViewScoped
public class CreateUserBean {

	private String username;

	private String password;

	public void createUser() {
		UserDAO.createUser(username, password);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
