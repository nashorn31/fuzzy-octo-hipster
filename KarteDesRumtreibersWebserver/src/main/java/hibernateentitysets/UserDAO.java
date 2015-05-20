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

/**
 * Data Access Object to access the database table "UserDAO"
 * 
 * @author Johannes
 *
 */
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

	/**
	 * Check the user and password
	 * 
	 * @param username
	 *            the user name
	 * @param password
	 *            the password
	 * @return return true if the user and password are right else false
	 */
	public static boolean login(String username, String password) {

		// create a sql to select the record where the inserted user name is
		// equals the user name in the record
		TypedQuery<UserDAO> searchQuery = InitEntityManager.getEntityManager()
				.createQuery("FROM UserDAO WHERE username='" + username + "'",
						UserDAO.class);

		// select the record
		List<UserDAO> userDAOs = searchQuery.getResultList();

		// check whether the list contains a record
		if (!userDAOs.isEmpty()) {

			// get the user record
			UserDAO user = userDAOs.get(0);

			// check the password for the user and return the value
			return user.getPassword().equals(
					UserDAO.hashSaltWithPW(user.getSalt(), password));
		}

		// if the list does't contains a record return false
		return false;
	}

	/**
	 * Set the password for an existing user (no check if the user is allowed to
	 * change the password)
	 * 
	 * @param username
	 *            user name for which the password should be changed
	 * @param password
	 *            new password of the user
	 */
	public static void setPassword(String username, String password) {

		// create a sql to select the record where the inserted user name is
		// equals the user name in the record
		TypedQuery<UserDAO> searchQuery = InitEntityManager.getEntityManager()
				.createQuery("FROM UserDAO WHERE username='" + username + "'",
						UserDAO.class);

		// select the record
		List<UserDAO> userDAOs = searchQuery.getResultList();

		// check whether the list contains a record
		if (!userDAOs.isEmpty()) {

			// get the user record
			UserDAO user = userDAOs.get(0);

			// set the password after the inserted password is hashed with a
			// salt
			user.setPassword(UserDAO.hashSaltWithPW(user.getSalt(), password));

			// write the record to the database
			EntityManager em = InitEntityManager.getEntityManager();
			em.getTransaction().begin();
			em.merge(user);
			em.getTransaction().commit();
		}

	}

	/**
	 * Create a new user with user name and password
	 * 
	 * @param username
	 *            user name for the new user
	 * @param password
	 *            password for the new user
	 */
	public static void createUser(String username, String password) {

		// create a sql to select the record where the inserted user name is
		// equals the user name in the record
		TypedQuery<UserDAO> searchQuery = InitEntityManager.getEntityManager()
				.createQuery("FROM UserDAO WHERE username='" + username + "'",
						UserDAO.class);

		// select the record
		List<UserDAO> userDAOs = searchQuery.getResultList();

		// check whether the list contains a record and only create a user if
		// the user doesn't exist
		if (userDAOs.isEmpty()) {
			UserDAO user = new UserDAO();

			// generate a salt
			user.setSalt(UserDAO.generateSalt());

			user.setUsername(username);
			user.setRole("user");
			user.setPassword(UserDAO.hashSaltWithPW(user.getSalt(), password));

			// write the record to the database
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

	/**
	 * Generate a salt to ensure a safe password. The salt and the password are
	 * hashed together and the hash is saved in the database
	 * 
	 * @return a salt which is a random String with the length 10
	 */
	private static String generateSalt() {

		RandomString randomString = new RandomString(10);

		return randomString.nextString();

	}

	/**
	 * Calculate a sha256 hash with the salt + the password
	 * 
	 * @param salt
	 *            the specific hash from the user
	 * @param pw
	 *            the password from the user
	 * @return the hashed value from the salt and the password
	 */
	private static String hashSaltWithPW(String salt, String pw) {

		// Build a String with the salt and the hash
		String passwordSaltString = salt + pw;

		// convert the String in an InputStream
		InputStream is = new ByteArrayInputStream(
				passwordSaltString.getBytes(StandardCharsets.UTF_8));

		// calculate the sha256 hash from the InputStream
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");

			int read;
			byte[] buffer = new byte[8192];

			while ((read = is.read(buffer)) > 0) {
				digest.update(buffer, 0, read);
			}
			byte[] hash = digest.digest();
			BigInteger bigInt = new BigInteger(1, hash);
			String output = bigInt.toString(16);
			while (output.length() < 32) {
				output = "0" + output;
			}

			return output;

		} catch (Exception e) {
			e.printStackTrace(System.err);
			return null;
		}

	}

}
