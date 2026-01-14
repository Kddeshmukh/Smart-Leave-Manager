package com.example.leaveapp.repository;
import com.example.leaveapp.entity.LeaveRequest;
import com.example.leaveapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
    List<LeaveRequest> findByStudent(User student);
    List<LeaveRequest> findByStatus(String status);
}