<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../head.jsp" %>
<%@ include file="../../header.jsp" %>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
    response.setDateHeader("Expires", 0); // Proxies.
%>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<body>
<main>

    <div class="kamokucontainer-logout">
        <div class="form-group-create" style="width:100%; margin-left:20px;">
            <h2 style="width: 100%;text-align: left; background-color:gainsboro; padding:10px 20px 10px 20px;">ログアウト</h2>
            <div style="width: 100%;text-align: center; background-color:seagreen; padding:10px 20px 10px 20px; margin-bottom:100px;">ログアウトしました</div>
            <div class="d-flex flex-row bd-highlight mb-3">
                <div><a href="loginaction">ログイン</a></div>
            </div>
        </div>
    </div>

    <!-- ユーザーがクリックしやすいボタン -->
    <button id="triggerClick" style="display: none;">Click Me</button>
</main>
<%@ include file="../../footer.jsp" %>

<script>
document.addEventListener('DOMContentLoaded', function() {
    // 現在のページ状態を履歴に追加
    history.pushState(null, null, location.href);

    // popstateイベントが発生したときの処理
    window.addEventListener('popstate', function(event) {
        window.alert('ログインしなおしてください');
        window.location.href = 'loginaction'; // ログインページへの遷移
    });

    // キャッシュを無効にするためのHTTPヘッダー
    fetch(location.href, {
        method: 'GET',
        headers: {
            'Cache-Control': 'no-cache, no-store, must-revalidate',
            'Pragma': 'no-cache',
            'Expires': '0'
        }
    });
});
</script>

</body>
</html>
