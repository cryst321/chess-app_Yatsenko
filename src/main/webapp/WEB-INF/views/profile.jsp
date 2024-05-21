<%@include file="header.jsp"%>

<style>
    .container {
        max-width: 800px;
        margin: auto;
        padding: 20px;
    }

    .text-center {
        text-align: center;
    }

    .profile-img {
        width: 200px;
        height: 200px;
        object-fit: cover;
        border-radius: 50%;
    }

    .profile-info {
        margin-top: 20px;
        text-align: center;
    }

    .profile-info h3 {
        font-size: 1.5rem;
        margin-bottom: 15px;
    }

    .profile-info h3 span {
        font-weight: bold;
    }

    .profile-info textarea {
        width: 100%;
        font-size: 1.2rem;
        margin-top: 10px;
        border: 1px solid #ccc;
        border-radius: 5px;
        padding: 10px;
    }

    .btn-report, .btn-update,.btn-change-rating {
        display: inline-block;
        margin-top: 20px;
        padding: 10px 20px;
        font-size: 1.2rem;
        color: #fff;
        border: 1px solid transparent;
        border-radius: 5px;
        cursor: pointer;
        text-decoration: none;
        text-align: center;
        width: 200px;
    }

    .btn-report {
        background-color: #dc3545;
        border-color: #dc3545;
    }

    .btn-report:hover {
        background-color: #c82333;
        border-color: #c82333;
    }

    .btn-update {
        background-color: #007bff;
        border-color: #007bff;
    }

    .btn-update:hover {
        background-color: #0056b3;
        border-color: #0056b3;
    }

    .btn-change-rating {
        background-color: #28a745;
        border-color: #28a745;
    }

    .btn-change-rating:hover {
        background-color: #218838;
        border-color: #218838;
    }
    .joined-date {
        margin-top: 20px;
        padding-top: 20px;
        font-size: 1.2rem;
        text-align: center;
        border-top: 1px solid #ccc;
    }

    .joined-date span {
        font-weight: bold;
    }
</style>

<div class="container">
    <h1 class="text-center">User Profile</h1>

    <div class="text-center">
        <img src="${user.userDetails.profilePicture != null ? user.userDetails.profilePicture : 'https://i.ibb.co/R9hVKtm/blank.jpg'}" alt="Profile Picture" class="img-fluid profile-img">
    </div>

    <div class="profile-info">
        <h3>Nickname: <span id="nickname">${user.userCredentials.nickname}</span></h3>
        <h3>Rating (Bullet): <span id="ratingBullet">${user.userDetails.ratingBullet}</span></h3>
        <h3>Rating (Blitz): <span id="ratingBlitz">${user.userDetails.ratingBlitz}</span></h3>
        <h3>Rating (Rapid): <span id="ratingRapid">${user.userDetails.ratingRapid}</span></h3>
        <h3>Rating (Classic): <span id="ratingClassic">${user.userDetails.ratingClassic}</span></h3>
        <h3>Country: <span id="country">${user.userDetails.country}</span></h3>
        <h3>Gender: <span id="gender">${user.userDetails.gender}</span></h3>
        <h3>Bio:</h3>
        <textarea class="form-control" id="bio" rows="3" disabled>${user.userDetails.bio}</textarea>
    </div>

    <div class="text-center">
        <c:choose>
            <c:when test="${user.userCredentials.id == sessionScope.userCredentials.id}">
                <a href="/chess/updateProfile" class="btn-update">Update Info</a>
            </c:when>
            <c:otherwise>
                <a href="/chess/report?reportedId=${user.id}" class="btn-report">Report</a>
            </c:otherwise>
        </c:choose>
        <c:if test="${sessionScope.userCredentials.role == 'admin' || sessionScope.userCredentials.role == 'moderator'}">
            <button class="btn-change-rating" data-toggle="modal" data-target="#changeRatingModal">Change Rating</button>
        </c:if>

    </div>

    <div class="joined-date">
        <h4><strong>Joined at:</strong> <fmt:formatDate value="${user.userDetails.accountCreatedAt}" pattern="yyyy-MM-dd"/></h4>
    </div>
</div>



<!-- Change Rating Modal -->
<div class="modal fade" id="changeRatingModal" tabindex="-1" role="dialog" aria-labelledby="changeRatingModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="changeRatingModalLabel">Change Rating</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form action="/chess/changeRating" method="post">
                <div class="modal-body">
                    <input type="hidden" name="userId" value="${user.userCredentials.id}">
                    <div class="form-group">
                        <label for="ratingType">Rating Type</label>
                        <select class="form-control" id="ratingType" name="ratingType">
                            <option value="1">Bullet</option>
                            <option value="2">Blitz</option>
                            <option value="3">Rapid</option>
                            <option value="4">Classic</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="ratingChange">Rating Change</label>
                        <input type="number" class="form-control" id="ratingChange" name="ratingChange" required>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary">Change Rating</button>
                </div>
            </form>
        </div>
    </div>
</div>


<%@include file="footer.jsp"%>
