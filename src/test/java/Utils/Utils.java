package Utils;

import com.github.javafaker.Faker;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Utils {
    public void takeScreenshot(WebDriver driver) throws IOException {
        File screenshotFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String time=new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss-aa").format(new Date());
        String fileWithPath="./src/test/resources/screenshots/"+time+".png";
        File DestFile= new File(fileWithPath);
        FileUtils.copyFile(screenshotFile,DestFile);
    }
    private String username;
    private String password;

    public String username() {
        return username;
    }

    public void setUserId(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void getUserCreds(int pos) throws IOException, ParseException {
        String fileName="./src/test/resources/loginUsers.json";
        JSONParser jsonParser = new JSONParser();
        Object obj =jsonParser.parse(new FileReader(fileName));
        JSONArray jsonArray=(JSONArray) obj;
        JSONObject jsonObject=(JSONObject) jsonArray.get(pos);
        setUserId((String)jsonObject.get("username"));
        setPassword((String)jsonObject.get("password"));
    }
//    public String generateRandomPassword(int len){
//        String chars="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghi"
//                +"jklmnopqrstuvwxyz!@#$%&";
//        StringBuilder sb=new StringBuilder(len);
//        Random rand=new Random();
//        for(int i=0;i<len;i++){
//            sb.append(chars.charAt(rand.nextInt(chars.length())));
//        }
//        return sb.toString();
//    }
//    public int generateRandomNumber(int min,int max){
//        int randomId= (int)(Math.random() * ((max - min) + 1)) + min;
//        return randomId;
//    }

      // RandomData create formula
//    public void generateRandomData(){
//        Faker faker =new Faker();
//        setFirstname(faker.name().firstName());
//        setMiddleName(faker.name().nameWithMiddle());
//        setLastname(faker.name().lastName());
//    }
//    public void saveJsonList(String id,String username, String password) throws IOException, ParseException {
//        String fileName = "./src/test/resources/users.json";
//        JSONParser jsonParser = new JSONParser();
//        Object obj = jsonParser.parse(new FileReader(fileName));
//        JSONArray jArray = (JSONArray) obj;
//
//        JSONObject usersObj = new JSONObject();
//        usersObj.put("userName", username);
//        usersObj.put("password", password);
//        usersObj.put("id", id);
//
//        jArray.add(usersObj);
//        FileWriter file = new FileWriter(fileName);
//        file.write(jArray.toJSONString());
//        file.flush();
//        file.close();
//        System.out.println("Saved");
//    }
//    public static List readJSONArray(String filename) throws IOException, ParseException {
//        JSONParser parser = new JSONParser();
//        Object object = parser.parse(new FileReader(filename));
//        JSONArray jsonArray = (JSONArray) object;
//        return jsonArray;
//    }
//    public static void waitForElement(WebDriver driver, WebElement element, int TIME_UNIT_SECONDS){
//        WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(TIME_UNIT_SECONDS));
//        wait.until(ExpectedConditions.visibilityOf(element));
//    }
//    public static String pasteValue() throws IOException, UnsupportedFlavorException {
//        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
//        DataFlavor flavor = DataFlavor.stringFlavor;
//        return (String) clipboard.getData(flavor);
//    }
}
