package taxmanager


class DownloadMailJob {
    def timeout = 20 * 60 *1000 // 20 mins

    def mailDownloadService

    def execute() {
        mailDownloadService.checkDawsonsystemsMail()
    }
}
