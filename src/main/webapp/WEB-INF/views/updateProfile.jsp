<%--
  Created by IntelliJ IDEA.
  User: Maryna
  Date: 20.05.2024
  Time: 17:26
  To change this template use File | Settings | File Templates.
--%>
<%@include file="header.jsp"%>

<div class="container">
    <h1 class="text-center">Update Profile</h1>

    <form action="/chess/updateProfile" method="POST" class="form-horizontal">
        <div class="form-group">
            <label for="nickname" class="col-sm-2 control-label">Nickname</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="nickname" name="nickname" value="${user.userCredentials.nickname}" required>
            </div>
        </div>

        <div class="form-group">
            <label for="email" class="col-sm-2 control-label">Email</label>
            <div class="col-sm-10">
                <input type="email" class="form-control" id="email" name="email" value="${user.userCredentials.email}" required>
            </div>
        </div>

        <div class="form-group">
            <label for="password" class="col-sm-2 control-label">Password</label>
            <div class="col-sm-10">
                <input type="password" class="form-control" id="password" name="password" value="${user.userCredentials.password}" required>
            </div>
        </div>

        <div class="form-group">
            <label for="country" class="col-sm-2 control-label">Country</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="country" name="country" value="${user.userDetails.country}">
            </div>
        </div>

        <div class="form-group">
            <label for="gender" class="col-sm-2 control-label">Gender</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="gender" name="gender" value="${user.userDetails.gender}">
            </div>
        </div>

        <div class="form-group">
            <label for="dateOfBirth" class="col-sm-2 control-label">Date of Birth</label>
            <div class="col-sm-10">
                <input type="date" class="form-control" id="dateOfBirth" name="dateOfBirth" value="${user.userDetails.dateOfBirth}">
            </div>
        </div>

        <div class="form-group">
            <label for="profilePicture" class="col-sm-2 control-label">Profile Picture URL</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="profilePicture" name="profilePicture" value="${user.userDetails.profilePicture}">
            </div>
        </div>

        <div class="form-group">
            <label for="bio" class="col-sm-2 control-label">Bio</label>
            <div class="col-sm-10">
                <textarea class="form-control" id="bio" name="bio" rows="3">${user.userDetails.bio}</textarea>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-primary">Update Profile</button>
            </div>
        </div>
    </form>
</div>

<%@include file="footer.jsp"%>
