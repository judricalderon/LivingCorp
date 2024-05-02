package co.edu.unbosque.livingcorp.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="RESOURCE_BOOKINGS")
public class ResourceBooking {

    @Id
    @Column(name = "BOOKING_ID")
    private int bookingId;

    @Column(name = "USER_NAME")
    private String userName;

    @OneToMany
    @JoinColumn(name = "PROP_RES_ID")
    private List<PropertyResource> propertyResourceId;

    @Column(name = "BOOKING_DATETIME")
    private LocalDateTime bookingDateTime;

    @Column(name = "BOOKING_START_DATE")
    private LocalDateTime bookingStartDate;

    @Column(name = "BOOKING_END_DATE")
    private LocalDateTime bookingEndDate;

    @Column(name = "BOOKING_COST")
    private double bookingCost;

    @Column(name = "PAYMENT_COMPLETE")
    private boolean paymentComplete;

    public ResourceBooking() {
    }

    public ResourceBooking(int bookingId, String userName, List<PropertyResource> propertyResourceId, LocalDateTime bookingDateTime, LocalDateTime bookingStartDate, LocalDateTime bookingEndDate, double bookingCost, boolean paymentComplete) {
        this.bookingId = bookingId;
        this.userName = userName;
        this.propertyResourceId = propertyResourceId;
        this.bookingDateTime = bookingDateTime;
        this.bookingStartDate = bookingStartDate;
        this.bookingEndDate = bookingEndDate;
        this.bookingCost = bookingCost;
        this.paymentComplete = paymentComplete;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<PropertyResource> getPropertyResourceId() {
        return propertyResourceId;
    }

    public void setPropertyResourceId(List<PropertyResource> propertyResourceId) {
        this.propertyResourceId = propertyResourceId;
    }

    public LocalDateTime getBookingDateTime() {
        return bookingDateTime;
    }

    public void setBookingDateTime(LocalDateTime bookingDateTime) {
        this.bookingDateTime = bookingDateTime;
    }

    public LocalDateTime getBookingStartDate() {
        return bookingStartDate;
    }

    public void setBookingStartDate(LocalDateTime bookingStartDate) {
        this.bookingStartDate = bookingStartDate;
    }

    public LocalDateTime getBookingEndDate() {
        return bookingEndDate;
    }

    public void setBookingEndDate(LocalDateTime bookingEndDate) {
        this.bookingEndDate = bookingEndDate;
    }

    public double getBookingCost() {
        return bookingCost;
    }

    public void setBookingCost(double bookingCost) {
        this.bookingCost = bookingCost;
    }

    public boolean isPaymentComplete() {
        return paymentComplete;
    }

    public void setPaymentComplete(boolean paymentComplete) {
        this.paymentComplete = paymentComplete;
    }
}
