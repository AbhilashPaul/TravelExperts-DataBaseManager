package travelexperts.models;


import java.math.BigDecimal;
import java.sql.Date;

public class Bookingdetail {
    private int bookingDetailId;

    private BigDecimal agencyCommission;

    private BigDecimal basePrice;

    private String classId;


    private String description;


    private String destination;

    private String feeId;

    private double itineraryNo;

    private int productSupplierId;

    private String regionId;


    private Date tripEnd;


    private Date tripStart;





    public Bookingdetail() {
    }

    public int getBookingDetailId() {
        return this.bookingDetailId;
    }

    public void setBookingDetailId(int bookingDetailId) {
        this.bookingDetailId = bookingDetailId;
    }

    public BigDecimal getAgencyCommission() {
        return this.agencyCommission;
    }

    public void setAgencyCommission(BigDecimal agencyCommission) {
        this.agencyCommission = agencyCommission;
    }

    public BigDecimal getBasePrice() {
        return this.basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public String getClassId() {
        return this.classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDestination() {
        return this.destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getFeeId() {
        return this.feeId;
    }

    public void setFeeId(String feeId) {
        this.feeId = feeId;
    }

    public double getItineraryNo() {
        return this.itineraryNo;
    }

    public void setItineraryNo(double itineraryNo) {
        this.itineraryNo = itineraryNo;
    }

    public int getProductSupplierId() {
        return this.productSupplierId;
    }

    public void setProductSupplierId(int productSupplierId) {
        this.productSupplierId = productSupplierId;
    }

    public String getRegionId() {
        return this.regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public Date getTripEnd() {
        return this.tripEnd;
    }

    public void setTripEnd(Date tripEnd) {
        this.tripEnd = tripEnd;
    }

    public Date getTripStart() {
        return this.tripStart;
    }

    public void setTripStart(Date tripStart) {
        this.tripStart = tripStart;
    }



}