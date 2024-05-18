package co.edu.unbosque.livingcorp.model.dto;

import java.time.LocalDateTime;

public class ResourceBookingDto {
    private int bookingId;
    private UserDto userName;
    private PropertyResourceDto propertyResourceId;
    private LocalDateTime bookingDateTime;
    private LocalDateTime bookingStartDate;
    private LocalDateTime bookingEndDate;
    private double bookingCost;
    private boolean paymentComplete;

    public ResourceBookingDto() {
    }

    public ResourceBookingDto(int bookingId, UserDto userName, PropertyResourceDto propertyResourceId, LocalDateTime bookingDateTime, LocalDateTime bookingStartDate, LocalDateTime bookingEndDate, double bookingCost, boolean paymentComplete) {
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

    public UserDto getUserName() {
        return userName;
    }

    public void setUserName(UserDto userName) {
        this.userName = userName;
    }

    public PropertyResourceDto getPropertyResourceId() {
        return propertyResourceId;
    }

    public void setPropertyResourceId(PropertyResourceDto propertyResourceId) {
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

    @Override
    public String toString() {
        return "ResourceBookingDto{" +
                "bookingId=" + bookingId +
                ", userName=" + userName +
                ", propertyResourceId=" + propertyResourceId +
                ", bookingDateTime=" + bookingDateTime +
                ", bookingStartDate=" + bookingStartDate +
                ", bookingEndDate=" + bookingEndDate +
                ", bookingCost=" + bookingCost +
                ", paymentComplete=" + paymentComplete +
                '}';
    }
}
