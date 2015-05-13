package hibernateentitysets;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TypedQuery;

import login.RandomString;
import databaseconnection.InitEntityManager;

@Entity
@Table(catalog = "WLAN", name = "UserDAO")
public class UserDAO implements Serializable {

	/**
	 * default serializable Id
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "idUserDAO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "salt")
	private String salt;

	@Column(name = "role")
	private String role;

	public static boolean login(String username, String password) {
		TypedQuery<UserDAO> searchQuery = InitEntityManager.getEntityManager()
				.createQuery("FROM UserDAO WHERE username='" + username + "'",
						UserDAO.class);

		List<UserDAO> userDAOs = searchQuery.getResultList();

		if (!userDAOs.isEmpty()) {
			UserDAO user = userDAOs.get(0);

			return user.getPassword().equals(
					UserDAO.hashSaltWithPW(user.getSalt(), password));
		}

		return false;
	}

	public static void setPassword(String username, String password) {

		TypedQuery<UserDAO> searchQuery = InitEntityManager.getEntityManager()
				.createQuery("FROM UserDAO WHERE username='" + username + "'",
						UserDAO.class);

		List<UserDAO> userDAOs = searchQuery.getResultList();

		if (!userDAOs.isEmpty()) {

			UserDAO user = userDAOs.get(0);

			user.setPassword(UserDAO.hashSaltWithPW(user.getSalt(), password));

			EntityManager em = InitEntityManager.getEntityManager();
			em.getTransaction().begin();
			em.merge(user);
			em.getTransaction().commit();
		}

	}

	public static void createUser(String username, String password) {

		TypedQuery<UserDAO> searchQuery = InitEntityManager.getEntityManager()
				.createQuery("FROM UserDAO WHERE username='" + username + "'",
						UserDAO.class);

		List<UserDAO> userDAOs = searchQuery.getResultList();

		if (userDAOs.isEmpty()) {
			UserDAO user = new UserDAO();
			user.setUsername(username);
			user.setSalt(UserDAO.generateSalt());
			user.setRole("user");
			user.setPassword(UserDAO.hashSaltWithPW(user.getSalt(), password));

			EntityManager em = InitEntityManager.getEntityManager();

			em.getTransaction().begin();
			em.persist(user);
			em.getTransaction().commit();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	private static String generateSalt() {

		RandomString randomString = new RandomString(10);

		return randomString.nextString();

	}

	private static String hashSaltWithPW(String salt, String pw) {

		String passwordSaltString = salt + pw;

		InputStream is = new ByteArrayInputStream(
				passwordSaltString.getBytes(StandardCharsets.UTF_8));

		String output;
		int read;
		byte[] buffer = new byte[8192];

		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			while ((read = is.read(buffer)) > 0) {
				digest.update(buffer, 0, read);
			}
			byte[] hash = digest.digest();
			BigInteger bigInt = new BigInteger(1, hash);
			output = bigInt.toString(16);
			while (output.length() < 32) {
				output = "0" + output;
			}
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return null;
		}

		return output;
	}

}
