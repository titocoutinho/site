package desenv.util.web;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class ContadorSessionListener implements HttpSessionListener {
	private static int sessionCount = 0;
	private static int applicationCount = 0;

	public ContadorSessionListener() {

	}

	public void sessionCreated(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		session.setMaxInactiveInterval(30 * 60);
		synchronized (this) {
			++sessionCount;
			++applicationCount;
		}
	}

	public void sessionDestroyed(HttpSessionEvent arg0) {
		synchronized (this) {
			--sessionCount;
		}
	}

	
	public static int getSessionCount() {
		return sessionCount;
	}
	public static int getApplicationCount() {
		return applicationCount;
	}
}
