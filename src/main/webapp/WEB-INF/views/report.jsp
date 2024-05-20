<%--
  Created by IntelliJ IDEA.
  User: Maryna
  Date: 20.05.2024
  Time: 18:45
  To change this template use File | Settings | File Templates.
--%>
<%@include file="header.jsp"%>

<div class="container">
    <h1 class="text-center">Report User</h1>

    <form action="/chess/report" method="post">

        <input type="hidden" name="reportedId" value="${reportedId}" />

        <div class="form-group">
            <label for="complaintType">Complaint Type:</label>
            <select class="form-control" id="complaintType" name="complaintType">
                <option value="Cheat">Cheat</option>
                <option value="Insult">Insult</option>
                <option value="Rating manipulation">Rating manipulation</option>
                <option value="Trolling">Trolling</option>
                <option value="Other">Other</option>
            </select>
        </div>

        <div class="form-group">
            <label for="reason">Reason:</label>
            <textarea class="form-control" id="reason" name="reason" rows="3" required></textarea>
        </div>

        <button type="submit" class="btn btn-danger btn-report">Submit Report</button>
    </form>
</div>

<%@include file="footer.jsp"%>
