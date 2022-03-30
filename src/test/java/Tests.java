import net.bytebuddy.asm.Advice;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

public class Tests extends BaseClass {

    @Test (dataProvider = "dataReserva")
    public void bookingDetinationCuscoWithTrainBelmondSuiteCabins(String cabins, String numOfCabin) throws InterruptedException {
        String subTotal = null;
        Select selectDestination = new Select (driver.findElement(By.name("destinoSelect")));
        selectDestination.selectByVisibleText("Cusco");
        Select selectRoute = new Select (driver.findElement(By.name("rutaSelect")));
        selectRoute.selectByVisibleText("Puno > Cusco");
        Select selectChooseTrain = new Select (driver.findElement(By.name("cbTrenSelect")));
        selectChooseTrain.selectByVisibleText("Andean Explorer, A Belmond Train");
        driver.findElement(By.id("salida")).click();
        //Thread.sleep(1000);
        driver.findElement(By.xpath("//a[@class='ui-datepicker-next ui-corner-all']")).click();
        driver.findElement(By.xpath("//a[normalize-space()='27']")).click();
        driver.findElement(By.id("btn_search")).click();

        if (cabins == "SUITE CABINS"){
            Select selectCabins = new Select (driver.findElement(By.name("selectRooms[suite]")));
            selectCabins.selectByVisibleText(numOfCabin);
            subTotal = driver.findElement(By.className("stUSD-suite")).getText();
        }else if (cabins=="TWIN BED CABINS"){
            Select selectCabins = new Select (driver.findElement(By.name("selectRooms[twin]")));
            selectCabins.selectByVisibleText(numOfCabin);
            subTotal = driver.findElement(By.className("stUSD-twin")).getText();
        }else if (cabins=="BUNKED BED CABINS"){
            Select selectCabins = new Select (driver.findElement(By.name("selectRooms[bunk]")));
            selectCabins.selectByVisibleText(numOfCabin);
            subTotal = driver.findElement(By.className("stUSD-bunk")).getText();
        }
        String total = driver.findElement(By.id("priceUSD")).getText();
        driver.findElement(By.xpath("//a[normalize-space()='purchase summary']")).click();
        Thread.sleep(1000);
        String cartShoppingTotal = driver.findElement(By.id("priceUSDrc")).getText();
        System.out.println("subtotal: " + subTotal);
        System.out.println("total: " + total);
        System.out.println("total shopping cart: " + cartShoppingTotal);
        Assert.assertEquals(cartShoppingTotal, subTotal);
        Assert.assertEquals(total, subTotal);
        driver.findElement(By.id("continuar_bae")).click();
    }

    @Test (dataProvider = "dataReserva")
    public void bookingDetinationCuscoWithTrainBelmondTwinBedCabins(String cabins) throws InterruptedException {
        Select selectDestination = new Select(driver.findElement(By.name("destinoSelect")));

    }

    @DataProvider (name = "dataReserva")
    public Object[][] getData(){
        return new Object[][] {{"SUITE CABINS", "1 CABIN"},{"SUITE CABINS","2 CABINS"},{"TWIN BED CABINS","1 CABIN"},{"BUNKED BED CABINS","1 CABIN"}};

    }

}
