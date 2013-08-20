/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alberto Lorente Leal <albll@kth.se>, <a.lorenteleal@gmail.com>
 */
@XmlRootElement
public class EmploymentRecord {
    private String name;
    private List<Employment> employments;

    public EmploymentRecord() {
    }

    public EmploymentRecord(String name, List<Employment> employments) {
        this.name = name;
        this.employments = employments;
    }

    @XmlElement(name="employment")
    public List<Employment> getEmployments() {
        return employments;
    }

    public void setEmployments(List<Employment> employments) {
        this.employments = employments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
