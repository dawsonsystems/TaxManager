<%@ page import="com.dawsonsystems.taxmanager.IncomeItem" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'incomeItem.label', default: 'IncomeItem')}"/>
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
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <div class="list">
        <table>
            <thead>
            <tr>

                <g:sortableColumn property="id" title="${message(code: 'incomeItem.id.label', default: 'Id')}"/>

                <g:sortableColumn property="content"
                                  title="${message(code: 'incomeItem.content.label', default: 'Content')}"/>

                <th><g:message code="incomeItem.amount.label" default="Amount"/></th>

                <th><g:message code="incomeItem.company.label" default="Company"/></th>

                <g:sortableColumn property="contentType"
                                  title="${message(code: 'incomeItem.contentType.label', default: 'Content Type')}"/>

                <g:sortableColumn property="date" title="${message(code: 'incomeItem.date.label', default: 'Date')}"/>

            </tr>
            </thead>
            <tbody>
            <g:each in="${incomeItemInstanceList}" status="i" var="incomeItemInstance">
                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

                    <td><g:link action="show"
                                id="${incomeItemInstance.id}">${fieldValue(bean: incomeItemInstance, field: "id")}</g:link></td>

                    <td>${fieldValue(bean: incomeItemInstance, field: "content")}</td>

                    <td>${fieldValue(bean: incomeItemInstance, field: "amount")}</td>

                    <td>${fieldValue(bean: incomeItemInstance, field: "company")}</td>

                    <td>${fieldValue(bean: incomeItemInstance, field: "contentType")}</td>

                    <td>${fieldValue(bean: incomeItemInstance, field: "date")}</td>

                </tr>
            </g:each>
            </tbody>
        </table>
    </div>

    <div class="paginateButtons">
        <g:paginate total="${incomeItemInstanceTotal}"/>
    </div>
</div>
</body>
</html>
