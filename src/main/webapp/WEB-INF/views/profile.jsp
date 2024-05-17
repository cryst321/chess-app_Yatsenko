
<%@include file="header.jsp"%>

<div class="container">
    <h1 class="text-center">User Profile</h1>

    <div class="text-center">
        <img src="https://i.ibb.co/R9hVKtm/blank.jpg" alt="Profile Picture" class="img-fluid rounded-circle" style="width: 200px; height: 200px;">
    </div>

    <div class="mt-4">
        <h3>Nickname: <span id="nickname">JohnDoe321</span></h3>
        <h3>Rating (Bullet): <span id="ratingBullet">1500</span></h3>
        <h3>Rating (Blitz): <span id="ratingBlitz">1400</span></h3>
        <h3>Rating (Rapid): <span id="ratingRapid">1300</span></h3>
        <h3>Rating (Classic): <span id="ratingClassic">1200</span></h3>
        <h3>Country: <span id="country">USA</span></h3>
        <h3>Gender: <span id="gender">Male</span></h3>
        <h3>Bio:</h3>
        <textarea class="form-control" id="bio" rows="3" disabled>This is a mock bio for the user. It contains some information about the user's interests, background, and other relevant details.</textarea>
    </div>

    <div class="text-center mt-4">
        <button class="btn btn-danger" onclick="reportUser()">Report User</button>
    </div>
</div>

<%@include file="footer.jsp"%>
