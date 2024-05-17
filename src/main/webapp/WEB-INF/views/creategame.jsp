<%--
  Created by IntelliJ IDEA.
  User: Maryna
  Date: 16.05.2024
  Time: 18:50
  To change this template use File | Settings | File Templates.
--%>
<%@include file="header.jsp"%>

<div class="container">
  <h1>Create Game Request</h1>

  <form action="#" method="post">
    <div class="form-group">
      <label for="gameType">Game Type:</label>
      <select class="form-control" id="gameType" name="gameType">
        <option value="bullet">Bullet</option>
        <option value="blitz">Blitz</option>
        <option value="rapid">Rapid</option>
        <option value="classic">Classic</option>
      </select>
    </div>

    <div class="form-group">
      <label for="ratingDifference">Rating Difference (min/max):</label>
      <div class="row">
        <div class="col">
          <label for="ratingDifferenceMin">0</label>
          <input type="range" class="form-control-range" id="ratingDifferenceMin" name="ratingDifferenceMin" min="-150" max="0" step="1" value="0">
        </div>
        <div class="col">
          <label for="ratingDifferenceMax">150</label>
          <input type="range" class="form-control-range" id="ratingDifferenceMax" name="ratingDifferenceMax" min="0" max="150" step="1" value="150">
        </div>
      </div>
    </div>

    <div class="form-group">
      <label for="timeRestriction">Time Restriction (seconds):</label>
      <input type="number" class="form-control" id="timeRestriction" name="timeRestriction" min="1">
    </div>

    <div class="form-group">
      <label for="preferredColor">Preferred Color:</label>
      <select class="form-control" id="preferredColor" name="preferredColor">
        <option value="white">White</option>
        <option value="black">Black</option>
        <option value="none">None</option>
      </select>
    </div>

    <button type="submit" class="btn btn-primary">Play!</button>
  </form>
</div>

<%@include file="footer.jsp"%>
