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
public class TranscriptStore {
    private static Map<String, Transcript> store;
    private static TranscriptStore instance = null;
    private TranscriptStore(){
        store= new HashMap<String, Transcript>();
        initOneTranscript();
    }
    
    public static Map<String,Transcript> getStore(){
        if(instance==null){
            instance= new TranscriptStore();
        }
        return store;
    }

    private void initOneTranscript() {
        Course[] courses = {
            new Course("distributed", "A"),
            new Course("java", "B")
        };
        Transcript tAlberto = new Transcript("alberto", "KTH", "doubledegree", "2011", Arrays.asList(courses));
        store.put(tAlberto.getName(), tAlberto);
    }
}
