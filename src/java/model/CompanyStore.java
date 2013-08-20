/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Alberto Lorente Leal <albll@kth.se>, <a.lorenteleal@gmail.com>
 */
public class CompanyStore {
    private static Map<String, Company> store;
    private static CompanyStore instance = null;
    private CompanyStore(){
        store= new HashMap<String, Company>();
        initOneCompany();
    }
    
    public static Map<String,Company> getStore(){
        if(instance==null){
            instance= new CompanyStore();
        }
        return store;
    }

    private void initOneCompany() {
        Company cGoogle = new Company("google", "munich", "software");
        store.put(cGoogle.getName(), cGoogle);
    }
    
}
