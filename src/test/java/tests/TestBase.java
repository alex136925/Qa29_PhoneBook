package tests;

import manager.ApplicationManager;
import manager.TestNGListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import java.lang.reflect.Method;

@Listeners(TestNGListener.class)

public class TestBase {

   Logger logger = LoggerFactory.getLogger(TestBase.class);

   static ApplicationManager app = new ApplicationManager();

   @BeforeMethod
   public void startLogger(Method m){
      logger.info("=== Starting test: {} ===", m.getName());
   }

   @BeforeSuite
   public  void setUp(){
       app.init();
   }

   @AfterSuite
   public  void tearDown(){
       //app.stop();
   }

}
