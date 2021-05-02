import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.SocketTimeoutException;


public class BaseTest {


    public static void main(String[] args) throws InterruptedException {


        System.setProperty("webdriver.chrome.driver", "ChromeDriver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();           // WebDriver tanımladık.
        driver.get("https://www.gittigidiyor.com/");     // tanımladığımız webdriver ile browser açtık
        driver.manage().window().maximize();             // açılan ekranı büyüttük

        Thread.sleep(2000);                        // 2 sn lik bekleme süresi verdik.


        // Anasayfa giriş yapıldığı kontrol edildi.
        String anaSayfaUrl = driver.getCurrentUrl();
        String anaSayfaUrlC = "https://www.gittigidiyor.com/";

        if (anaSayfaUrl.equals(anaSayfaUrlC)) {
            System.out.println("Anasayfaya giriş yaptınız.");
        } else {
            System.out.println("Anasayfaya giriş yapamadınız.");
        }

        //Giriş yap
        driver.get("https://www.gittigidiyor.com/uye-girisi");
        driver.findElement(By.id("L-UserNameField")).sendKeys("selahattinaltunkurt@gmail.com");
        driver.findElement(By.id("L-PasswordField")).sendKeys("Altun74");
        driver.findElement(By.id("gg-login-enter")).click();


        //Giriş Kontrol
        String girisControl = driver.findElement(By.cssSelector("div[title='Hesabım']>:nth-child(2)>:nth-child(1)")).getText();
        String girisControlC = "selahattinaltu1781";
        if (girisControl.equals(girisControlC)) {
            System.out.println("Hesabınıza giriş yaptınız.");
        } else {
            System.out.println("Hesabınıza giriş yapamadınız.");
        }
        Thread.sleep(2000);


        //Bilgisayar kelimesini aratmak
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("bilgisayar");
        driver.findElement(By.cssSelector(".qjixn8-0.sc-1bydi5r-0.hKfdXF")).click();
        Thread.sleep(2000);

        //2. sayfaya gidiş
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,8500)");
        driver.findElement(By.cssSelector("ul[class='clearfix'] [href='/arama/?k=bilgisayar&sf=2']")).click();
        Thread.sleep(2000);

        //2. sayfa kontrol
        String ikinciSayfa = "https://www.gittigidiyor.com/arama/?k=bilgisayar&sf=2";
        String ikinciSayfaC = driver.getCurrentUrl();

        if(ikinciSayfa.equals(ikinciSayfaC)){
            System.out.println("2. sayfadasınız.");
        }else{
            System.out.println("2. sayfada değilsiniz");
        }
        Thread.sleep(2000);

        //Sepete ekleme ve Fiyat kontrolü
        driver.findElement(By.cssSelector("[class='catalog-view clearfix products-container']>:nth-child(1)")).click();
        String ucret = driver.findElement(By.cssSelector("span[id=\"sp-price-highPrice\"]")).getText();
        jse.executeScript("window.scrollBy(0,800)");
        driver.findElement( By.cssSelector("[id='purchaseSoldInfoActionButtons'] [id='add-to-basket']")).click();
        Thread.sleep(2000);
        driver.get("https://www.gittigidiyor.com/sepetim");
        String ucretC = driver.findElement(By.cssSelector("div[class=\"old-price\"]>:nth-child(1)")).getText();
        Thread.sleep(2000);
        System.out.println(ucretC);
        System.out.println(ucret);
        if (ucret.equals(ucretC)) {
            System.out.println("Ürün fiyatları eşit."+ ucretC);
        } else {
            System.out.println("Ürün fiyatlarınız eşit değildir.");
        }

        //Adet arttırma

        Thread.sleep(2000);
        driver.findElement(By.cssSelector("option[value='2']")).click();
        Thread.sleep(2000);
        String adet = driver.findElement(By.cssSelector("li[class='clearfix total-price-sticky-container']>:nth-child(1)")).getText();
        String adetC= "Ürün Toplamı (2 Adet)";


        if(adet.equals(adetC)){
            System.out.println("Sepetinizdeki " + adet);
        }else{
            System.out.println("Sepetinizdeki ürün adedi farklıdır");
        }

        Thread.sleep(2000);

        //Ürün sepetten silinir.
        driver.findElement(By.cssSelector("a[title='Sil']")).click();
        Thread.sleep(2000);

        //Sepet kontrol
        String sepet = "Sepetinizde ürün bulunmamaktadır.";
        String sepetC = driver.findElement(By.cssSelector("div[class='gg-w-22 gg-d-22 gg-t-21 gg-m-18']>:nth-child(1)")).getText();
        Thread.sleep(2000);

        if (sepet.equals(sepetC)) {
            System.out.println("Sepetinizde ürün bulunmamaktadır.");
        } else {
            System.out.println("Sepetiniz boş değildir.");
        }

        driver.quit();
    }


}
