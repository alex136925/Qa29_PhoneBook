package manager;

import models.User;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderUser {
    @DataProvider
    public Iterator<Object[]> loginData(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"mara1@gmail.com", "Roma3456$"});

        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> loginModels(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{new User().setEmail("mara1@gmail.com").setPassword("Roma3456$")});

        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> loginFile() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/test.csv"));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] all = line.split(",");
            if (all.length == 2) {
                list.add(new Object[]{
                        new User().setEmail(all[0].trim()).setPassword(all[1].trim())
                });
            }
        }
        return list.iterator();
    }



}
