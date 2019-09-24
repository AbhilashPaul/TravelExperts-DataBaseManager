package travelexperts.models;

import java.sql.Date;

public class TravelPackage {
    private int pkgId;
    private String pkgName;
    private String pkgDesc;
    private Date pkgStartDate;
    private Date pkgEndDate;
    private double pkgBasePrice;
    //Double is an object version of primitive double. Since it is an object, it can be set to null.
    private Double pkgCommission;
    private String PkgTripType;

    public int getPkgId() {
        return pkgId;
    }
    public void setPkgId(int pkgId) {
        this.pkgId = pkgId;
    }
    public String getPkgName() {
        return pkgName;
    }
    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }
    public String getPkgDesc() {
        return pkgDesc;
    }
    public void setPkgDesc(String pkgDesc) {
        this.pkgDesc = pkgDesc;
    }
    public Date getPkgStartDate() {
        return pkgStartDate;
    }
    public void setPkgStartDate(Date pkgStartDate) {
        this.pkgStartDate = pkgStartDate;
    }
    public Date getPkgEndDate() {
        return pkgEndDate;
    }
    public void setPkgEndDate(Date pkgEndDate) {
        this.pkgEndDate = pkgEndDate;
    }
    public double getPkgBasePrice() {
        return pkgBasePrice;
    }
    public void setPkgBasePrice(double pkgBasePrice) {
        this.pkgBasePrice = pkgBasePrice;
    }
    public Double getPkgCommission() {
        return pkgCommission;
    }
    public void setPkgCommission(Double pkgCommission) {
        this.pkgCommission = pkgCommission;
    }
    public String getPkgTripType() {
        return PkgTripType;
    }
    public void setPkgTripType(String pkgTripType) {
        PkgTripType = pkgTripType;
    }


    public TravelPackage() {
    }

    public TravelPackage(int pkgId, String pkgName, double pkgBasePrice, String pkgTripType) {
        this.pkgId = pkgId;
        this.pkgName = pkgName;
        this.pkgBasePrice = pkgBasePrice;
        this.PkgTripType = pkgTripType;
    }

    public TravelPackage(int pkgId, String pkgName, String pkgDesc,
                         Date pkgStartDate, Date pkgEndDate,
                         double pkgBasePrice, Double pkgCommission, String pkgTripType) {
        this.pkgId = pkgId;
        this.pkgName = pkgName;
        this.pkgDesc = pkgDesc;
        this.pkgStartDate = pkgStartDate;
        this.pkgEndDate = pkgEndDate;
        this.pkgBasePrice = pkgBasePrice;
        this.pkgCommission = pkgCommission;
        this.PkgTripType = pkgTripType;
    }
}
