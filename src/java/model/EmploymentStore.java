/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Alberto Lorente Leal <albll@kth.se>, <a.lorenteleal@gmail.com>
 */
public class EmploymentStore {
    private static Map<String, EmploymentRecord> store;
    private static EmploymentStore instance = null;
    private EmploymentStore(){
        store= new HashMap<String, EmploymentRecord>();
        initOneRecord();
    }
    
    public static Map<String,EmploymentRecord> getStore(){
        if(instance==null){
            instance= new EmploymentStore();
        }
        return store;
    }

    private void initOneRecord() {
        Employment[] employments = {
            new Employment("google", "swe"),
            new Employment("esa", "management")
        };
        EmploymentRecord rAlberto = new EmploymentRecord("alberto", Arrays.asList(employments));
        store.put(rAlberto.getName(), rAlberto);
    }
    
}
