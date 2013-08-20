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
public class JobStore {
    private static Map<String, Job> store;
    private static JobStore instance = null;
    private JobStore(){
        store= new HashMap<String, Job>();
        initOneJoblisting();
    }
    
    public static Map<String,Job> getStore(){
        if(instance==null){
            instance= new JobStore();
        }
        return store;
    }

    private void initOneJoblisting() {
        
        Job google = new Job("google", "software engineer","software","Open Position");
        Job esa    = new Job("esa", "team manager","management","Open Position");
        
        
        store.put(google.getCompanyName(), google);
       // store.put(esa.getCompanyName(), esa);
        
    }
}
