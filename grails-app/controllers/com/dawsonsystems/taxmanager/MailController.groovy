package com.dawsonsystems.taxmanager

class MailController {
    def mailDownloadService
    def index = {

        mailDownloadService.checkDawsonsystemsMail()
        redirect(controller:"expenseItem", action:"unprocessedList")
    }

    def exampleExpenses = {
        //PDF
        new ExpenseItem(description: "Example PDF Expense",
                                contentType: "application/pdf", company: mailDownloadService.dawsonsystems(), content: new File("/home/david/Downloads/img-110115174305-0001.pdf").bytes).save()


        //JPG
        new ExpenseItem(description: "Example Image Expense",
                                        contentType: "image/jpeg", company: mailDownloadService.dawsonsystems(), content: new File("/home/david/Media/Pictures/halloween-mortalkombat.jpg").bytes).save()


    }


}
