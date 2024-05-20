package controller.command.complaint;

import constants.Attribute;
import constants.Page;
import controller.command.Command;
import entity.Complaint;
import entity.User;
import service.ComplaintService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AllReportsCommand implements Command {

    private final ComplaintService complaintService;

    public AllReportsCommand(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Complaint> complaints = complaintService.getAllComplaints();

        request.setAttribute(Attribute.COMPLAINTS, complaints);
        return Page.ALL_REPORTS_VIEW;
    }
}
