package com.dawsonsystems.taxmanager

class IncomeItemController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [incomeItemInstanceList: IncomeItem.list(params), incomeItemInstanceTotal: IncomeItem.count()]
    }

    def create = {
        def incomeItemInstance = new IncomeItem()
        incomeItemInstance.properties = params
        return [incomeItemInstance: incomeItemInstance]
    }

    def save = {
        def incomeItemInstance = new IncomeItem(params)
        if (incomeItemInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'incomeItem.label', default: 'IncomeItem'), incomeItemInstance.id])}"
            redirect(action: "show", id: incomeItemInstance.id)
        }
        else {
            render(view: "create", model: [incomeItemInstance: incomeItemInstance])
        }
    }

    def show = {
        def incomeItemInstance = IncomeItem.get(params.id)
        if (!incomeItemInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'incomeItem.label', default: 'IncomeItem'), params.id])}"
            redirect(action: "list")
        }
        else {
            [incomeItemInstance: incomeItemInstance]
        }
    }

    def edit = {
        def incomeItemInstance = IncomeItem.get(params.id)
        if (!incomeItemInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'incomeItem.label', default: 'IncomeItem'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [incomeItemInstance: incomeItemInstance]
        }
    }

    def update = {
        def incomeItemInstance = IncomeItem.get(params.id)
        if (incomeItemInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (incomeItemInstance.version > version) {

                    incomeItemInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'incomeItem.label', default: 'IncomeItem')] as Object[], "Another user has updated this IncomeItem while you were editing")
                    render(view: "edit", model: [incomeItemInstance: incomeItemInstance])
                    return
                }
            }
            incomeItemInstance.properties = params
            if (!incomeItemInstance.hasErrors() && incomeItemInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'incomeItem.label', default: 'IncomeItem'), incomeItemInstance.id])}"
                redirect(action: "show", id: incomeItemInstance.id)
            }
            else {
                render(view: "edit", model: [incomeItemInstance: incomeItemInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'incomeItem.label', default: 'IncomeItem'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def incomeItemInstance = IncomeItem.get(params.id)
        if (incomeItemInstance) {
            try {
                incomeItemInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'incomeItem.label', default: 'IncomeItem'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'incomeItem.label', default: 'IncomeItem'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'incomeItem.label', default: 'IncomeItem'), params.id])}"
            redirect(action: "list")
        }
    }
}
