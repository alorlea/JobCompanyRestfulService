/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alberto Lorente Leal <albll@kth.se>, <a.lorenteleal@gmail.com>
 */
@XmlRootElement
public class Cv {
    private Transcript transcript;
    private EmploymentRecord employment;

    public Cv() {
    }

    public Cv(Transcript transcript, EmploymentRecord employment) {
        this.transcript = transcript;
        this.employment = employment;
    }
    
    @XmlElement(name="employmentRecord")
    public EmploymentRecord getEmployment() {
        return employment;
    }

    public void setEmployment(EmploymentRecord employment) {
        this.employment = employment;
    }

    @XmlElement(name="transcript")
    public Transcript getTranscript() {
        return transcript;
    }

    public void setTranscript(Transcript transcript) {
        this.transcript = transcript;
    }
    
    
}
