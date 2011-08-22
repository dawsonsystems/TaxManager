package com.dawsonsystems.taxmanager

class ExpenseItemController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [title:"All Expenses", expenseItemInstanceList: ExpenseItem.list(params), expenseItemInstanceTotal: ExpenseItem.count()]
    }

    def unprocessedList = {
        def expenseItemInstanceList = ExpenseItem.findAllByDateIsNullOrAmountIsNull()
        render(view:"list", model:[title:"Unprocessed Expenses", expenseItemInstanceList:expenseItemInstanceList, expenseItemInstanceTotal:expenseItemInstanceList ])
    }

    def create = {
        def expenseItemInstance = new ExpenseItem()
        expenseItemInstance.properties = params
        return [expenseItemInstance: expenseItemInstance]
    }

    def save = {
        def expenseItemInstance = new ExpenseItem(params)
        if (expenseItemInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'expenseItem.label', default: 'ExpenseItem'), expenseItemInstance.id])}"
            redirect(action: "show", id: expenseItemInstance.id)
        }
        else {
            render(view: "create", model: [expenseItemInstance: expenseItemInstance])
        }
    }

    def show = {
        def expenseItemInstance = ExpenseItem.get(params.id)
        if (!expenseItemInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'expenseItem.label', default: 'ExpenseItem'), params.id])}"
            redirect(action: "list")
        }
        else {
            [expenseItemInstance: expenseItemInstance]
        }
    }

    def edit = {
        def expenseItemInstance = ExpenseItem.get(params.id)
        if (!expenseItemInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'expenseItem.label', default: 'ExpenseItem'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [expenseItemInstance: expenseItemInstance]
        }
    }

    def update = {
        def expenseItemInstance = ExpenseItem.get(params.id)
        if (expenseItemInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (expenseItemInstance.version > version) {

                    expenseItemInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'expenseItem.label', default: 'ExpenseItem')] as Object[], "Another user has updated this ExpenseItem while you were editing")
                    render(view: "edit", model: [expenseItemInstance: expenseItemInstance])
                    return
                }
            }
            expenseItemInstance.properties = params
            if (!expenseItemInstance.hasErrors() && expenseItemInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'expenseItem.label', default: 'ExpenseItem'), expenseItemInstance.id])}"
                redirect(action: "show", id: expenseItemInstance.id)
            }
            else {
                render(view: "edit", model: [expenseItemInstance: expenseItemInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'expenseItem.label', default: 'ExpenseItem'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def expenseItemInstance = ExpenseItem.get(params.id)
        if (expenseItemInstance) {
            try {
                expenseItemInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'expenseItem.label', default: 'ExpenseItem'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'expenseItem.label', default: 'ExpenseItem'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'expenseItem.label', default: 'ExpenseItem'), params.id])}"
            redirect(action: "list")
        }
    }

    def render = {
        def expenseItemInstance = ExpenseItem.get(params.id)

        response.contentType = expenseItemInstance.contentType
        response.outputStream << expenseItemInstance.content

    }
}
