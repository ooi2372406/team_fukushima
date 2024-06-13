<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../../head_ma.jsp" %>
<%@ include file="../../header.jsp" %>
<main>
    <div class="kamokucontainer-create">
        <%@ include file="base.jsp" %>

        <form id="subjectForm" style="width:100%; margin-left:20px;" action="TestListSubjectExecute.action" method="post">
            <div class="form-group-create">
                <h2 style="width: 100%; text-align: left; background-color: gainsboro; padding: 10px 20px; margin-bottom:20px;">
                    成績管理
                </h2>
                <div style="border:1px solid whitesmoke;">
                    <div style="display:flex; align-items: center; margin-top:10px; padding:10px;">
                        <table class="responsive-table-seiseki" style="width:100%;">
                            <thead>
                                <tr>
                                    <th>入学年度</th>
                                    <th>クラス</th>
                                    <th>科目</th>
                                    <th>回数</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>
                                        <select style="width:80%; border-radius:3%; padding:3px;" name="f1">
                                            <option selected>--------</option>
                                            <c:forEach var="seireki" begin="2010" end="2024">
                                                <option>${seireki}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td>
                                        <select style="width:80%; border-radius:5%; padding:5px;" name="f2">
                                            <option selected>--------</option>
                                            <c:forEach var="i" items="${classnum}">
                                                <option>${i}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td>
                                        <select style="width:90%; border-radius:5%; padding:5px;" name="f3">
                                            <option selected>--------</option>
                                            <c:forEach var="i" items="${subject}">
                                                <option>${i.name}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td>
                                        <select style="width:80%; border-radius:3%; padding:3px;" name="f4">
                                            <option selected>--------</option>
                                            <c:forEach var="i" items="${num}">
                                                <option>${i}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <div style="margin-left:20px;">
                            <button class="btn btn-primary" type="submit" value="検索">検索</button>
                        </div>
                    </div>
                </div>
            </div>
        </form>

        <form action="TestRegist.action" method="post">
            <!-- Additional form fields and elements can go here -->
        </form>
    </div>
</main>

<%@ include file="../../footer.jsp" %>




<script src="../javascript/subject.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
</body>
</html>
