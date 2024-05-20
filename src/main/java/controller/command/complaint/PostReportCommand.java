package controller.command.complaint;

import constants.Page;
import controller.command.Command;
import entity.Complaint;
import entity.UserCredentials;
import service.ComplaintService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;

public class PostReportCommand implements Command {

    private final ComplaintService complaintService;
    private final UserService userService;

    public PostReportCommand(ComplaintService complaintService, UserService userService) {
        this.complaintService = complaintService;
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserCredentials complainant = (UserCredentials) session.getAttribute("userCredentials");

        if (complainant == null) {
            throw new ServletException("User not found in session.");
        }

        Integer reportedId = Integer.valueOf(request.getParameter("reportedId"));

        Complaint complaint = new Complaint();
        complaint.setCreatedAt(Timestamp.from(Instant.now()));
        complaint.setComplainant(userService.getUserById(complainant.getId()).orElseThrow(() -> new ServletException("Complainant not found")));
        complaint.setReported(userService.getUserById(reportedId).orElseThrow(() -> new ServletException("Reported user not found")));
        complaint.setComplaintType(request.getParameter("complaintType"));
        complaint.setReason(request.getParameter("reason"));
        complaint.setStatus("pending");

        complaintService.createComplaint(complaint);

        return Page.HOME_VIEW;
    }
}
