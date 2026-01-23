public abstract class RequisitionWithPhone extends RequisitionBase {
    public String phone;

    public RequisitionWithPhone() {
    }

    public RequisitionWithPhone(RequisitionWithPhone requisitionWithPhone) {
        super(requisitionWithPhone);
        phone = requisitionWithPhone.phone;
    }
}
