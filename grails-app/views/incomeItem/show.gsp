<%@ page import="com.dawsonsystems.taxmanager.IncomeItem" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'incomeItem.label', default: 'IncomeItem')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
    </span>
    <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label"
                                                                           args="[entityName]"/></g:link></span>
    <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label"
                                                                               args="[entityName]"/></g:link></span>
</div>

<div class="body">
    <h1><g:message code="default.show.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <div class="dialog">
        <table>
            <tbody>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="incomeItem.id.label" default="Id"/></td>

                <td valign="top" class="value">${fieldValue(bean: incomeItemInstance, field: "id")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="incomeItem.content.label" default="Content"/></td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="incomeItem.amount.label" default="Amount"/></td>

                <td valign="top" class="value">${fieldValue(bean: incomeItemInstance, field: "amount")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="incomeItem.company.label" default="Company"/></td>

                <td valign="top" class="value"><g:link controller="company" action="show"
                                                       id="${incomeItemInstance?.company?.id}">${incomeItemInstance?.company?.encodeAsHTML()}</g:link></td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="incomeItem.contentType.label"
                                                         default="Content Type"/></td>

                <td valign="top" class="value">${fieldValue(bean: incomeItemInstance, field: "contentType")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="incomeItem.date.label" default="Date"/></td>

                <td valign="top" class="value">${fieldValue(bean: incomeItemInstance, field: "date")}</td>

            </tr>

            </tbody>
        </table>
    </div>

    <div class="buttons">
        <g:form>
            <g:hiddenField name="id" value="${incomeItemInstance?.id}"/>
            <span class="button"><g:actionSubmit class="edit" action="edit"
                                                 value="${message(code: 'default.button.edit.label', default: 'Edit')}"/></span>
            <span class="button"><g:actionSubmit class="delete" action="delete"
                                                 value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                                                 onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/></span>
        </g:form>
    </div>
</div>
</body>
</html>
