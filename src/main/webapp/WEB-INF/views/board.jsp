<%--
  Created by IntelliJ IDEA.
  User: Maryna
  Date: 21.05.2024
  Time: 1:20
  To change this template use File | Settings | File Templates.
--%>
<%@include file="header.jsp"%>

<div class="container mt-5">
  <h1 class="text-center">Chess Game</h1>
  <div class="row">
    <div class="col-md-8">
      <!-- Chessboard will be rendered here -->
      <img src="https://i.ibb.co/Kj5wCSh/chessboard.png" alt="Chessboard" style="width: 100%;">
    </div>
    <div class="col-md-4">
      <!-- Chat and game info -->
      <div id="game-info">
        <h3>Game Info</h3>
        <p>White: ${chessGame.whitePlayer.userCredentials.nickname}</p>
        <p>Black: ${chessGame.blackPlayer.userCredentials.nickname}</p>
        <p>Time Restriction: ${chessGame.timeRestriction}</p>
      </div>
      <div id="chat">
        <h3>Chat</h3>
        <div id="chat-messages" style="height: 300px; overflow-y: scroll;">
          <!-- Messages will be loaded here -->
        </div>
        <form id="chat-form">
          <input type="text" id="chat-input" class="form-control" placeholder="Type a message...">
          <button type="submit" class="btn btn-primary mt-2">Send</button>
        </form>
      </div>
    </div>
  </div>
</div>

<%@include file="footer.jsp"%>

<script src="path/to/chessboardjs.js"></script>
<script src="path/to/chess.js"></script>
<script>
  var board = Chessboard('board', {
    draggable: true,
    dropOffBoard: 'trash',
    sparePieces: true
  });

  // Chat functionality
  document.getElementById('chat-form').addEventListener('submit', function(event) {
    event.preventDefault();
    var message = document.getElementById('chat-input').value;
    // Send message to the server
    document.getElementById('chat-input').value = '';
  });
</script>
