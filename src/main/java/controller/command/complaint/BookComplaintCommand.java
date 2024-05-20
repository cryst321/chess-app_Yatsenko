package controller.command.complaint;


import constants.Page;
import controller.command.Command;
import entity.UserCredentials;
import service.ComplaintService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class BookComplaintCommand implements Command {

    private final ComplaintService complaintService;

    public BookComplaintCommand(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        UserCredentials currentUserCredentials = (UserCredentials) session.getAttribute("userCredentials");

        Integer moderatorId = currentUserCredentials.getId();
        Integer complaintId = Integer.parseInt(request.getParameter("complaintId"));

        complaintService.setModerator(complaintId, moderatorId);

        return Page.ALL_REPORTS_VIEW;
    }
}
