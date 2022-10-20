import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class Calculator {

    AndroidDriver<AndroidElement> driver;
    WebDriverWait wait;

    @BeforeClass
    void setup() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","android");
        capabilities.setCapability("deviceName","emulator-5554");
        capabilities.setCapability("appPackage","com.android.calculator2");
        capabilities.setCapability("appActivity","com.android.calculator2.Calculator");
        //URL url = new URL();
        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub/"),capabilities);
        wait = new WebDriverWait(driver,10);
    }

    @Test
    void calc(){
        MobileElement[] numbers = new MobileElement[10];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = driver.findElement(By.id("com.android.calculator2:id/digit_" + i));
        }

        HashMap<String,MobileElement> operations = new HashMap<>();
        operations.put("equal",driver.findElement(By.id("com.android.calculator2:id/eq")));
        operations.put("plus",driver.findElement(By.id("com.android.calculator2:id/op_add")));
        operations.put("minus",driver.findElement(By.id("com.android.calculator2:id/op_sub")));
        operations.put("mult",driver.findElement(By.id("com.android.calculator2:id/op_mul")));
        operations.put("div",driver.findElement(By.id("com.android.calculator2:id/op_div")));
        operations.put("delete",driver.findElement(By.id("com.android.calculator2:id/del")));
        operations.put("point",driver.findElement(By.id("com.android.calculator2:id/dec_point")));

        numbers[2].click();
        numbers[2].click();
        operations.get("plus").click();
        numbers[8].click();
        operations.get("equal").click();

        MobileElement result = driver.findElement(By.id("com.android.calculator2:id/result"));
        System.out.println("THE RESULT IS " + result.getText());
    }

    @AfterClass
    void quit(){
        driver.quit();
    }

}
