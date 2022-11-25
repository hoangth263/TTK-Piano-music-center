/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangth.db.courses;

import java.util.Date;

/**
 *
 * @author ACER
 */
public class CourseDTO {
    private String couId;
    private String couName;
    private String image;
    private double fee;
    private int caId;
    private Date startDate;
    private Date endDate;
    private Date lastUpdateDate;
    private String lastUpdateUser;

    public CourseDTO() {
    }

    public CourseDTO(String couId, String couName, int caId, String image, double fee, Date startDate, Date endDate) {
        this.couId = couId;
        this.couName = couName;
        this.caId = caId;
        this.image = image;
        this.fee = fee;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public CourseDTO(String couId, String couName, String image, double fee, int caId, Date startDate, Date endDate, Date lastUpdateDate, String lastUpdateUser) {
        this.couId = couId;
        this.couName = couName;
        this.image = image;
        this.fee = fee;
        this.caId = caId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.lastUpdateDate = lastUpdateDate;
        this.lastUpdateUser = lastUpdateUser;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getLastUpdateUser() {
        return lastUpdateUser;
    }

    public void setLastUpdateUser(String lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser;
    }
     
    

    public String getCouId() {
        return couId;
    }

    public void setCouId(String couId) {
        this.couId = couId;
    }

    public String getCouName() {
        return couName;
    }

    public void setCouName(String couName) {
        this.couName = couName;
    }

    public int getCaId() {
        return caId;
    }

    public void setCaId(int caId) {
        this.caId = caId;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    
}
