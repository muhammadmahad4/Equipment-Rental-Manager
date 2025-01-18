//you dont need to explicitly tell public etc. all are automatically public.


interface Command {
    void execute(String[] cmdParts) throws ExMemberIDinUse, ExInsufficientCommands, ExInvalidFormat, ExNotLater, ExMemberNotFound,ExEquipmentNotFound, ExEquipmentCodeAlreadyInUse,ExEquipmentCodeNotFound,ExNoEquipmentSetAvailable,ExMemberAlreadyBorrowedThisEquipment, ExPeriodOverLapRequest,ExPeriodIsLessThan,ExNoOfDaysMustBeInteger,ExPeriodOverlapsBorrow;
}
