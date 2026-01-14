package com.example.leaveapp.controller;
import com.example.leaveapp.entity.LeaveRequest;
import com.example.leaveapp.entity.User;
import com.example.leaveapp.service.LeaveService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LeaveController {
    @Autowired
    private LeaveService leaveService;

    @GetMapping("/student/dashboard")
    public String studentDashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/"; // if not logged in, redirect to login
        }
        model.addAttribute("user", user);
        model.addAttribute("leaves", leaveService.getLeavesByStudent(user));
        //model.addAttribute("leaves", leaveService.getLeavesByStudent(user));
        return "student_dashboard";
    }

    @GetMapping("/student/apply")
    public String applyForm(Model model) {
        model.addAttribute("leaveRequest", new LeaveRequest());
        return "apply_leave";
    }

    @PostMapping("/student/apply")
    public String applyLeave(@ModelAttribute LeaveRequest leaveRequest, HttpSession session) {
        leaveRequest.setStudent((User) session.getAttribute("user"));
        leaveService.applyLeave(leaveRequest);
        return "redirect:/student/dashboard";
    }

    @GetMapping("/teacher/dashboard")
    public String teacherDashboard(HttpSession session,Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/"; // redirect to login if not logged in
        }
        model.addAttribute("user", user); // add user to the model
        model.addAttribute("leaves", leaveService.getAllLeaves());
        return "teacher_dashboard";
    }

    @PostMapping("/teacher/approve/{id}")
    public String approve(@PathVariable Long id) {
        leaveService.updateStatus(id, "APPROVED");
        return "redirect:/teacher/dashboard";
    }

    @PostMapping("/teacher/reject/{id}")
    public String reject(@PathVariable Long id) {
        leaveService.updateStatus(id, "REJECTED");
        return "redirect:/teacher/dashboard";
    }
}