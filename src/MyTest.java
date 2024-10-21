
import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import dev.failsafe.internal.util.Assert;

public class MyTest {

	WebDriver driver = new ChromeDriver();

	String MyWebSite = "https://automationteststore.com/";

	String[] firstNames = { "ahmad", "Khaled", "anas", "omar", "sara", "alaa", "Muna", "jood" ,"Rana"};
	String[] LastNames = { "Khalil", "mustafa", "hammad", "Kamel", "zain", "odeh","yassen" };
	String GlobalUserName = "";
	String GlobalUserNameForTheLogIn = "";

	String GlobalPassword = "AutoMation123@?";
	Random rand = new Random();

	@BeforeTest
	public void MySetup() {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();

		driver.get(MyWebSite);

	}

	@Test(priority = 1)
	public void SingUp() throws InterruptedException {
		int RandomIndexForTheFirstName = rand.nextInt(firstNames.length);
		int RandomIndexForTheLastName = rand.nextInt(LastNames.length);

		String UserFirstName = firstNames[RandomIndexForTheFirstName];
		String UserLastName = LastNames[RandomIndexForTheLastName];

		GlobalUserName = UserFirstName;
		int randomNumberForTheEmail = rand.nextInt(658984);

		String domainName = "@gmail.com";

		String email = UserFirstName + UserLastName + randomNumberForTheEmail + domainName;

		;

		driver.findElement(By.linkText("Login or register")).click();

		WebElement SignUpButton = driver.findElement(By.xpath("//button[@title='Continue']"));

		SignUpButton.click();

		Thread.sleep(2000);

		WebElement FirstNameInput = driver.findElement(By.id("AccountFrm_firstname"));
		FirstNameInput.sendKeys(UserFirstName);
		WebElement LastNameInput = driver.findElement(By.id("AccountFrm_lastname"));
		LastNameInput.sendKeys(UserLastName);
		WebElement EmailInput = driver.findElement(By.id("AccountFrm_email"));
		EmailInput.sendKeys(email);
		WebElement AdressInput = driver.findElement(By.id("AccountFrm_address_1"));
		AdressInput.sendKeys("amman city - tlaa al ali");
		WebElement CityInput = driver.findElement(By.id("AccountFrm_city"));
		CityInput.sendKeys("capital city");

		WebElement CountryInput = driver.findElement(By.id("AccountFrm_country_id"));

		Select selector2 = new Select(CountryInput);

		int randomCountry = rand.nextInt(1, 240);

		selector2.selectByIndex(randomCountry);

		Thread.sleep(3000);

		WebElement ZoneIdInput = driver.findElement(By.id("AccountFrm_zone_id"));
		Select selector = new Select(ZoneIdInput);
		int randomState = rand.nextInt(1, 6);

		selector.selectByIndex(randomState);

		WebElement PostalCodeInput = driver.findElement(By.id("AccountFrm_postcode"));
		PostalCodeInput.sendKeys("13310");
		WebElement LoginNameInput = driver.findElement(By.id("AccountFrm_loginname"));
		String LocalUserName = UserFirstName + UserLastName + randomNumberForTheEmail;
		LoginNameInput.sendKeys(LocalUserName);

		GlobalUserNameForTheLogIn = LocalUserName;
		WebElement PasswordInput = driver.findElement(By.id("AccountFrm_password"));
		PasswordInput.sendKeys(GlobalPassword);
		WebElement ConfirmPasswordInput = driver.findElement(By.id("AccountFrm_confirm"));
		ConfirmPasswordInput.sendKeys(GlobalPassword);

		WebElement AgreeCheckBox = driver.findElement(By.id("AccountFrm_agree"));
		AgreeCheckBox.click();

		WebElement ContinueButton = driver.findElement(By.xpath("//button[@title='Continue']"));

		ContinueButton.click();

	}

	@Test(priority = 2)

	public void Logout() throws InterruptedException {

		Thread.sleep(2000);
		WebElement UserNav = driver.findElement(By.id("customernav"));
		Actions action = new Actions(driver);
		action.moveToElement(UserNav).perform();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Not " + GlobalUserName + "? Logoff")).click();

		// غالبا هي المستخدمه هون طريقه ثانيه عشان اللوق اوت
		// Thread.sleep(5000);
		// String LogoutURl =
		// "https://automationteststore.com/index.php?rt=account/logout";
		// driver.get(LogoutURl);

	}

	@Test(priority = 3)

	public void LogoIn() throws InterruptedException {
		driver.findElement(By.linkText("Login or register")).click();

		WebElement LogInInput = driver.findElement(By.id("loginFrm_loginname"));
		LogInInput.sendKeys(GlobalUserNameForTheLogIn);

		WebElement PassWordInput = driver.findElement(By.id("loginFrm_password"));
		PassWordInput.sendKeys(GlobalPassword);

		WebElement LoginButton = driver.findElement(By.xpath("//button[@title='Login']"));

		LoginButton.click();
	}

	@Test(priority = 4)
	public void AddItem() throws InterruptedException {

		String[] WebSitesForTheItems = { "https://automationteststore.com/index.php?rt=product/category&path=68",
				"https://automationteststore.com/index.php?rt=product/category&path=36",
				"https://automationteststore.com/index.php?rt=product/category&path=43",
				"https://automationteststore.com/index.php?rt=product/category&path=49",
				"https://automationteststore.com/index.php?rt=product/category&path=58",
				"https://automationteststore.com/index.php?rt=product/category&path=52",
				"https://automationteststore.com/index.php?rt=product/category&path=65" };

		int randomIndex = rand.nextInt(WebSitesForTheItems.length);
		driver.get(WebSitesForTheItems[randomIndex]);

		WebElement ListOfItem = driver.findElement(By.cssSelector(".thumbnails.row"));

		int TotalOfItem = ListOfItem.findElements(By.tagName("li")).size();

		int RandomIndexFortheItem = rand.nextInt(TotalOfItem);

		Thread.sleep(3000);
		ListOfItem.findElements(By.tagName("Li")).get(RandomIndexFortheItem).click();

		WebElement Container = driver.findElement(By.cssSelector(".thumbnails.grid.row.list-inline"));

		int NamberOfProducts = Container.findElements(By.cssSelector(".col-md-3.col-sm-6.col-xs-12")).size();

		int RandomOfProduct = rand.nextInt(NamberOfProducts);

		Container.findElements(By.cssSelector(".col-md-3.col-sm-6.col-xs-12")).get(RandomOfProduct).click();

		Thread.sleep(3000);

		WebElement UlList = driver.findElement(By.className("productpagecart"));
		int LiList = UlList.findElements(By.tagName("li")).get(0).findElements(By.tagName("span")).size();

		if (LiList > 0) {

			driver.get(MyWebSite);

			String ExpectedResult = "https://automationteststore.com/";

			String ActualResult = driver.getCurrentUrl();

			org.testng.Assert.assertEquals(ActualResult, ExpectedResult, "Error Message");

		} else {

			driver.findElement(By.className("cart")).click();
			Thread.sleep(2000);
			String ExpectedResult = "Shopping Cart";

			String ActualResult = driver.findElement(By.className("heading1")).getText();

			org.testng.Assert.assertEquals(ActualResult, ExpectedResult.toUpperCase());
			boolean ExbectedValueForCheckOut = true;
			boolean ActualValueForCheckOut = driver.findElement(By.id("cart_checkout1")).isDisplayed();
			org.testng.Assert.assertEquals(ExbectedValueForCheckOut, ActualValueForCheckOut, "Error Message");

		}

	}

}
