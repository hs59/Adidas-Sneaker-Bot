package main.Java.ADC;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.logging.Level;

import com.gargoylesoftware.htmlunit.html.*;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;

/**		Project: 	Adidas.com Bot
 * 		Purpose:	To auto-checkout customers on ADC
 * 		Author:		tersch
 * 		Date:		December 12, 2017
 */
	public class HTMLunit {
		// Create a new web client, using FIREFOX_52 web browser
		private final WebClient WEB_CLIENT = new WebClient(BrowserVersion.CHROME);

		//Log-in info variables
		private final String username;
		private final String password;

		// Constructor to set username and password and sets client info
		HTMLunit(String username, String password) {
			this.username = username;
			this.password = password;

			// Try and clear some of the random errors I was getting
			LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
			java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
			java.util.logging.Logger.getLogger("org.apache.commons.httpclient").setLevel(Level.OFF);

			// Retrieves WebClient's cookie manager
			WEB_CLIENT.getCookieManager().setCookiesEnabled(true);
			WEB_CLIENT.getOptions().setJavaScriptEnabled(false);
			WEB_CLIENT.getOptions().setThrowExceptionOnScriptError(true);

			// WEB_CLIENT.getOptions().setThrowExceptionOnScriptError(false);
			// WEB_CLIENT.getOptions().setCssEnabled(false);
			// WEB_CLIENT.getOptions().setUseInsecureSSL(true);
			// WEB_CLIENT.getOptions().setThrowExceptionOnFailingStatusCode(false);
			// WEB_CLIENT.getCookieManager().setCookiesEnabled(true);
		}

		// If the user has an account, use this method to log them in
		public void login() {
			String loginURL = "https://www.adidas.ca/on/demandware.store/Sites-adidas-CA-Site/en_CA/MyAccount-CreateOrLogin";
			try {

				// WORKING - create an HtmlPage of the login screen
				final HtmlPage loginPage1 = WEB_CLIENT.getPage(loginURL);
				System.out.println("HtmlPage created successfully");

				// WORKING - find the signin form within the login page
				final HtmlForm loginForm = loginPage1.getFirstByXPath("//*[@class='returningcustomers']");
				System.out.println("HtmlForm created successfully");

				// WORKING - find the username input and fill it with given string
				final HtmlTextInput userInput = loginForm.getFirstByXPath("//*[@class='formfield username style']");
				userInput.setValueAttribute(username);
				System.out.println("Username submitted");

				// WORKING - find the password input and fill it with given string
				final HtmlPasswordInput pwInput = loginForm.getFirstByXPath("//*[@class='formfield password style']");
				pwInput.setValueAttribute(password);
				System.out.println("Password submitted");

				// WORKING - find the button and click it
				final HtmlSubmitInput loginButton = loginForm.getFirstByXPath("//*[@class='co-btn_primary']");
				loginButton.click();
				System.out.println("You have been logged in successfully.");

			} catch (FailingHttpStatusCodeException e) {
				e.printStackTrace();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// Haven't decided what I'm doing with this yet..
		public String get(String URL) {
			try {
				// Return the HTML response for URL
				return WEB_CLIENT.getPage(URL).getWebResponse().getContentAsString();
			} catch (FailingHttpStatusCodeException e) {
				e.printStackTrace();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
	}
