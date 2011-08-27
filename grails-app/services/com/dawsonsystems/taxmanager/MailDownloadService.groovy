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
import javax.mail.Flags.Flag

class MailDownloadService {

    static transactional = false

    def checkDawsonsystemsMail() {
         Properties props = System.getProperties();
        props.setProperty("mail.store.protocol", "imaps");
        println "CONNECTING...."

        Store store
        Folder folder
        try {
          Session session = Session.getDefaultInstance(props, null);
          store = session.getStore("pop3");

          store.connect("mail.dawsonsystems.co.uk", "expenses@dawsonsystems.co.uk", "9790153");

            folder = store.getDefaultFolder()
            folder = folder.getFolder("INBOX")
            folder.open(Folder.READ_WRITE)

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
                    throw new RuntimeException("Unable to process email ${message.subject} from ${message.sender}")
                } else {
                    log.info("Setting to DELETED")
                    message.setFlag(Flag.DELETED, true)
                }
            }


        } catch (NoSuchProviderException e) {
          e.printStackTrace();
        } catch (MessagingException e) {
          e.printStackTrace();
        }  finally {
            folder?.close(true)
            store?.close()
        }
    }

    def dawsonsystems() {
        Company.findByCompanyName("Dawson Systems Ltd")
    }
}
