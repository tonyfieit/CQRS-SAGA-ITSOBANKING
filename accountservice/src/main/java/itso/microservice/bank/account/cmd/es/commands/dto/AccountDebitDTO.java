package itso.microservice.bank.account.cmd.es.commands.dto;

public class AccountDebitDTO {

    private double debitAmount;

  
    public double getDebitAmount() {
        return debitAmount;
    }

    public void setDebitAmount(double debitAmount) {
        this.debitAmount = debitAmount;
    }

   
}
