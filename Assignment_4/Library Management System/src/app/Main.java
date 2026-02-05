import service.LMSService;

void main() {
    System.out.println("---Data Management System---");

    LMSService lmsService = new LMSService();
    while (true) {
        lmsService.run();
    }
}