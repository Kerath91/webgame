<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div style="margin: 10px;">
<h2>Data presentation</h2>
    <h4>System contracts</h4>
    <table style="width: 600px" class="reference">
        <tbody>
        <tr>
            <th>#</th>
            <th>systemName</th>
            <th>request</th>
            <th>orderNumber</th>
            <th>fromDate</th>
            <th>toDate</th>
            <th>amount</th>
            <th>amountType</th>
            <th>amountPeriod</th>
            <th>authorizationPercent</th>
            <th>active</th>
        </tr>
        <c:forEach var="systemView" items="${requestScope.systemViews}"
            varStatus="loopCounter">
        <tr>
            <td><c:out value="${loopCounter.count}" /></td>
            <td><c:out value="${systemView.systemName}" /></td>
            <td><c:out value="${systemView.request}" /></td>
            <td><c:out value="${systemView.orderNumber}" /></td>
            <td><c:out value="${systemView.fromDate}" /></td>
            <td><c:out value="${systemView.toDate}" /></td>
            <td><c:out value="${systemView.amount}" /></td>
            <td><c:out value="${systemView.amountType}" /></td>
            <td><c:out value="${systemView.amountPeriod}" /></td>
            <td><c:out value="${systemView.authorizationPercent}" /></td>
            <td><c:out value="${systemView.active}" /></td>
            <td>x</td>
        </tr>
        </c:forEach>
        <tr>
	  		<!--<form:form method="POST" action= "${pageContext.request.contextPath}/add">
			        <td>Add</td>  
			        
			        <td><form:input path="systemName" /></td>   
			        <td><form:input path="request" /></td>
			        <td><form:input path="orderNumber" /></td>
			        <td><form:input path="fromDate" /></td>
			        <td><form:input path="toDate" /></td>
			        <td><form:input path="amount" /></td>
			        <td><form:input path="amountType" /></td>
			        <td><form:input path="amountPeriod" /></td>
			        <td><form:input path="authorizationPercent" /></td>
			        <td><form:input path="active" /></td>
			        
			        <td>  
			            <input type="submit" value="Add"/>  
			        </td>
			</form:form>-->
        </tr>
        </tbody>
    </table>
</div>



