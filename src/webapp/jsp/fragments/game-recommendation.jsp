<%--
  Author: Daniel Mehlber
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<section class="game-recommendation-container">
  <div class="game-recommendation-panel">
    <c:if test="${not empty requestScope.dashboardBean.gameRecommendation}">
      <img class="game-background-image" alt="game background" src="games/images?type=background&id=${requestScope.dashboardBean.gameRecommendation.game.id}"/>
      <h2>Let's play</h2>
      <div class="game-data-container">
        <div class="game-cover-container">
          <img alt="game cover" src="games/images?type=profile&id=${requestScope.dashboardBean.gameRecommendation.game.id}"/>
        </div>
        <div class="game-title">
            ${requestScope.dashboardBean.gameRecommendation.game.name}
        </div>
        <div class="game-stats-container">
          <span>${requestScope.dashboardBean.gameRecommendation.teamsCount}</span> Teams and <br/>
          <span>${requestScope.dashboardBean.gameRecommendation.playerCount}</span> Players are playing this game
        </div>
      </div>
      <a class="button primary-button game-recommendation-button" href="games/game?id=${requestScope.dashboardBean.gameRecommendation.game.id}">I'm in</a>
    </c:if>
  </div>
</section>
