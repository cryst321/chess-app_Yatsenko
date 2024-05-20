<%--
  Created by IntelliJ IDEA.
  User: Maryna
  Date: 16.05.2024
  Time: 18:50
  To change this template use File | Settings | File Templates.
--%>
<%@include file="header.jsp"%>

<style>
  .container {
    max-width: 800px;
    margin: auto;
    padding: 20px;
  }

  .form-group {
    margin-bottom: 20px;
  }

  .form-group label {
    font-weight: bold;
  }

  .form-range {
    display: flex;
    align-items: center;
  }

  .form-range input[type="range"] {
    flex-grow: 1;
    margin: 0 10px;
  }

  .form-range label {
    min-width: 50px;
    text-align: center;
  }

  .range-value {
    font-weight: bold;
    margin-left: 10px;
  }

  .btn-play {
    display: block;
    width: 100%;
    padding: 15px;
    font-size: 1.2rem;
    color: #fff;
    background-color: #007bff;
    border: none;
    border-radius: 5px;
    cursor: pointer;
  }

  .btn-play:hover {
    background-color: #0056b3;
  }
</style>

<div class="container">
  <h1 class="text-center">Create Game Request</h1>

  <form action="/chess/createGame" method="post">
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
      <label for="ratingLess">Rating Difference (Min):</label>
      <div class="form-range">
        <label>150</label>
        <input type="range" class="form-control-range" id="ratingLess" name="ratingLess" min="150" max="500" step="1" value="150" oninput="document.getElementById('minValue').innerText = this.value">
        <span id="minValue" class="range-value">150</span>
      </div>
    </div>

    <div class="form-group">
      <label for="ratingMore">Rating Difference (Max):</label>
      <div class="form-range">
        <label>150</label>
        <input type="range" class="form-control-range" id="ratingMore" name="ratingMore" min="150" max="500" step="1" value="500" oninput="document.getElementById('maxValue').innerText = this.value">
        <span id="maxValue" class="range-value">500</span>
      </div>
    </div>

    <div class="form-group">
      <label for="timeRestriction">Time Restriction (seconds):</label>
      <input type="number" class="form-control" id="timeRestriction" name="timeRestriction" min="60" max="1800" placeholder="Enter time restriction in seconds">
    </div>

    <div class="form-group">
      <label for="preferredColor">Preferred Color:</label>
      <select class="form-control" id="preferredColor" name="preferredColor">
        <option value="white">White</option>
        <option value="black">Black</option>
        <option value="none">None</option>
      </select>
    </div>

    <div class="form-group">
      <label for="isRating">Is Rating:</label>
      <input type="checkbox" id="isRating" name="isRating" value="true">
    </div>


    <button type="submit" class="btn-play">Play!</button>
  </form>
</div>

<%@include file="footer.jsp"%>
