package com.example.leaveapp.entity;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "leave_requests")
public class LeaveRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate fromDate;
    private LocalDate toDate;
    private String reason;
    private String status;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private User student;
    public Long getId() {
        return id; }
    public void setId(Long id) {
        this.id = id; }
    public LocalDate getFromDate() {
        return fromDate; }
    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate; }
    public LocalDate getToDate() {
        return toDate; }
    public void setToDate(LocalDate toDate) {
        this.toDate = toDate; }
    public String getReason() {
        return reason; }
    public void setReason(String reason) {
        this.reason = reason; }
    public String getStatus() {
        return status; }
    public void setStatus(String status) {
        this.status = status; }
    public User getStudent() {
        return student; }
    public void setStudent(User student) {
        this.student = student; }
}