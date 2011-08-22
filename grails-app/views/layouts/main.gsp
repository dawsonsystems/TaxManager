<!DOCTYPE html>
<html>
    <head>
        <title><g:layoutTitle default="Grails" /></title>
        <link rel="stylesheet" href="${resource(dir:'css',file:'main.css')}" />
        <link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <g:layoutHead />
        <g:javascript library="application" />
    </head>
    <body>
        <div id="menu">
            <div><g:link controller="company" action="list">Companies</g:link></div>
            <div><g:link controller="client" action="list">Clients</g:link></div>
            <div><g:link controller="incomeItem" action="list">Income Items</g:link></div>
            <div><g:link controller="expenseItem" action="list">Expense Items</g:link></div>
        </div>

        <g:layoutBody />
    </body>
</html>