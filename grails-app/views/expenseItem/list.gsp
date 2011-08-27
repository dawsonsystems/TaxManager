<%@ page import="com.dawsonsystems.taxmanager.ExpenseItem" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'expenseItem.label', default: 'ExpenseItem')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
    </span>
    <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label"
                                                                               args="[entityName]"/></g:link></span>
</div>

<div class="body">
    <h1>${title}</h1>
    <g:form controller="mail" action="index">

        <button type="submit">Refresh This List</button>
    </g:form>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <div class="list">
        <table>
            <thead>
            <tr>

                <g:sortableColumn property="id" title="${message(code: 'expenseItem.id.label', default: 'Id')}"/>

                <g:sortableColumn property="content"
                                  title="${message(code: 'expenseItem.description.label', default: 'Description')}"/>

                <th><g:message code="expenseItem.amount.label" default="Amount"/></th>

                <th><g:message code="expenseItem.company.label" default="Company"/></th>

                <g:sortableColumn property="date" title="${message(code: 'expenseItem.date.label', default: 'Date')}"/>

            </tr>
            </thead>
            <tbody>
            <g:each in="${expenseItemInstanceList}" status="i" var="expenseItemInstance">
                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

                    <td><g:link action="show"
                                id="${expenseItemInstance.id}">${fieldValue(bean: expenseItemInstance, field: "id")}</g:link></td>

                    <td>${fieldValue(bean: expenseItemInstance, field: "description")}</td>

                    <td>${fieldValue(bean: expenseItemInstance, field: "amount")}</td>

                    <td>${fieldValue(bean: expenseItemInstance?.company, field: "companyName")}</td>

                    <td>${fieldValue(bean: expenseItemInstance, field: "date")}</td>

                </tr>
            </g:each>
            </tbody>
        </table>
    </div>

    <div class="paginateButtons">
        <g:paginate total="${expenseItemInstanceTotal}"/>
    </div>
</div>
</body>
</html>
