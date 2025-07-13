package practice;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

import java.util.regex.Pattern;

public class Login_Logout {
    public static void main(String[] args) {
        Browser browser = null;
        Page page = null;
        Playwright pw = Playwright.create();
        try {
            //browser = pw.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(500));
            System.out.println("Playwright instance open => success");
            BrowserType browserType = pw.chromium();
            System.out.println("Selected chrome browser => success");
            browser = browserType.launch(new BrowserType.LaunchOptions().setSlowMo(500));
            System.out.println("Launched chrome browser");
            page = browser.newPage();
            System.out.println("Opened new tab");
            page.navigate("https://freelance-learn-automation.vercel.app/login");
            System.out.println("Launched the url: "+"https://freelance-learn-automation.vercel.app/login");
            // By default playwright assertions waits for 5 seconds before throwing AssertionFailedError
            PlaywrightAssertions.assertThat(page).hasTitle("Learn Automation Courses");
            System.out.println("Title validation completed");
            // For webelements Playwright waits for 30s before throwing Timeouterror
            page.locator("#email1").fill("abhi86365@gmail.com");
            System.out.println("User entered the email id => success");
            //page.locator("xpath=//input[@name='email1']").fill("abhi86365@gmail.com");
            //page.locator("css=input[name='email1']").fill("abhi86365@gmail.com");
            page.getByPlaceholder("Enter Password").fill("Xavier@8296");
            System.out.println("User entered the password => success");
            page.getByText("Sign in").nth(1).click();
            System.out.println("Login => success");
            page.getByText("Sign in").last().click();
            PlaywrightAssertions.assertThat(page.locator(".welcomeMessage")).containsText("Welcome");
            System.out.println("Home page validation completed");
            page.getByAltText("menu").click();
            System.out.println("User clicked on menu => success");
            page.getByText("Sign out").click();
            System.out.println("Sign out success");
            PlaywrightAssertions.assertThat(page).hasURL(Pattern.compile("login"));
            System.out.println("Navigation back to login page, validation completed");
            page.waitForTimeout(3000);
        }
        finally {
            page.close();
            System.out.println("Tab close => success");
            browser.close();
            System.out.println("Chrome browser close => success");
            pw.close();
            System.out.println("Playwright close => success");
        }
    }
}
