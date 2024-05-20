package controller.command.complaint;

import constants.Attribute;
import constants.Page;
import controller.command.Command;
import service.ComplaintService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MarkResolvedCommand implements Command {

    private final ComplaintService complaintService;

    public MarkResolvedCommand(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer complaintId = Integer.parseInt(request.getParameter("complaintId"));
        complaintService.setStatus(complaintId, "resolved");


        request.setAttribute(Attribute.COMPLAINTS,complaintService.getAllComplaints());
        return Page.ALL_REPORTS_VIEW;
    }
}
