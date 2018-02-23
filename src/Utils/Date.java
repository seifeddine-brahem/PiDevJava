/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

/**
 *
 * @author Maha
 */
public class Date {
    
    private String yyyy;
    private String mm;
    private String dd;

    public Date(String yyyy, String mm, String dd) {
        this.yyyy = yyyy;
        this.mm = mm;
        this.dd = dd;
    }

    public String getYyyy() {
        return yyyy;
    }

    public void setYyyy(String yyyy) {
        this.yyyy = yyyy;
    }

    public String getMm() {
        return mm;
    }

    public void setMm(String mm) {
        this.mm = mm;
    }

    public String getDd() {
        return dd;
    }

    public void setDd(String dd) {
        this.dd = dd;
    }

    @Override
    public String toString() {
        return yyyy + mm + dd ;
    }
    
    
    
    
    
    
}
