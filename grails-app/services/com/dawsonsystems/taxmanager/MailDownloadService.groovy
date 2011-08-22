package com.dawsonsystems.taxmanager

import javax.mail.Session
import javax.mail.Store
import javax.mail.Folder
import com.sun.mail.pop3.POP3Message
import javax.mail.internet.MimeMessage.RecipientType
import javax.mail.Multipart
import javax.mail.BodyPart
import com.sun.mail.util.BASE64DecoderStream
import javax.mail.NoSuchProviderException
import javax.mail.MessagingException

class MailDownloadService {

    static transactional = true

    def checkDawsonsystemsMail() {
         Properties props = System.getProperties();
        props.setProperty("mail.store.protocol", "imaps");
        println "CONNECTING...."
        try {
          Session session = Session.getDefaultInstance(props, null);
          Store store = session.getStore("pop3");
          store.connect("mail.dawsonsystems.co.uk", "expenses@dawsonsystems.co.uk", "9790153");

            Folder folder = store.getDefaultFolder()
            folder = folder.getFolder("INBOX")
            folder.open(Folder.READ_ONLY)

            println "${folder.messages}"

            for (POP3Message message in folder.getMessages()) {
                println "TO=${message.getRecipients(RecipientType.TO)}"
                println "FROM=${message.getFrom()}"
                Multipart multi = message.getContent()

                println "Part Count == ${multi.count}"
                boolean processedFile = false
                for (int i = 0; i < multi.count; i++) {
                    BodyPart part = multi.getBodyPart(i)

                    if (part.content instanceof BASE64DecoderStream) {

                        ExpenseItem item = new ExpenseItem(description: part.fileName,
                                contentType: part.contentType, content: part.inputStream.bytes,
                        company: dawsonsystems())

                        item.save()

                        println "Binary/ File attachment saved as expense -- ${part.fileName}"
                        processedFile = true
                    }

                }

                if (!processedFile) {
                    //TODO send a message to FROM, CC david.dawson@dawsonsystems.com saying that we couldn't understand this.
                }

            }

            //TODO, purge the mailbox

        } catch (NoSuchProviderException e) {
          e.printStackTrace();
        } catch (MessagingException e) {
          e.printStackTrace();
        }
    }

    def dawsonsystems() {
        Company.findByCompanyName("Dawson Systems Ltd")
    }
}
