<%@ page import="com.dawsonsystems.taxmanager.ExpenseItem" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'expenseItem.label', default: 'ExpenseItem')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
</head>

<body>
<div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
    </span>
    <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label"
                                                                           args="[entityName]"/></g:link></span>
</div>

<div class="body">
    <h1><g:message code="default.create.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${expenseItemInstance}">
        <div class="errors">
            <g:renderErrors bean="${expenseItemInstance}" as="list"/>
        </div>
    </g:hasErrors>
    <g:form action="save" enctype="multipart/form-data">
        <div class="dialog">
            <table>
                <tbody>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="content"><g:message code="expenseItem.content.label" default="Content"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: expenseItemInstance, field: 'content', 'errors')}">
                        <input type="file" id="content" name="content"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="amount"><g:message code="expenseItem.amount.label" default="Amount"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: expenseItemInstance, field: 'amount', 'errors')}">

                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="company"><g:message code="expenseItem.company.label" default="Company"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: expenseItemInstance, field: 'company', 'errors')}">
                        <g:select name="company.id" from="${com.dawsonsystems.taxmanager.Company.list()}" optionKey="id"
                                  value="${expenseItemInstance?.company?.id}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="contentType"><g:message code="expenseItem.contentType.label"
                                                            default="Content Type"/></label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: expenseItemInstance, field: 'contentType', 'errors')}">
                        <g:textField name="contentType" value="${expenseItemInstance?.contentType}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="date"><g:message code="expenseItem.date.label" default="Date"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: expenseItemInstance, field: 'date', 'errors')}">

                    </td>
                </tr>

                </tbody>
            </table>
        </div>

        <div class="buttons">
            <span class="button"><g:submitButton name="create" class="save"
                                                 value="${message(code: 'default.button.create.label', default: 'Create')}"/></span>
        </div>
    </g:form>
</div>
</body>
</html>
