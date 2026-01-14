package com.example.leaveapp.service;
import com.example.leaveapp.entity.LeaveRequest;
import com.example.leaveapp.entity.User;
import com.example.leaveapp.repository.LeaveRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LeaveService {
    @Autowired
    private LeaveRequestRepository leaveRepo;
    public LeaveRequest applyLeave(LeaveRequest leave) {
        leave.setStatus("PENDING");
        return leaveRepo.save(leave);
    }
    public List<LeaveRequest> getLeavesByStudent(User student) {
        return leaveRepo.findByStudent(student);
    }
    public List<LeaveRequest> getAllLeaves() {
        return leaveRepo.findAll();
    }
    public LeaveRequest getById(Long id) {
        return leaveRepo.findById(id).orElse(null);
    }
    public LeaveRequest updateStatus(Long id, String status) {
        LeaveRequest leave = leaveRepo.findById(id).orElse(null);
        if (leave != null) {
            leave.setStatus(status);
            return leaveRepo.save(leave);
        }
        return null;
    }
}