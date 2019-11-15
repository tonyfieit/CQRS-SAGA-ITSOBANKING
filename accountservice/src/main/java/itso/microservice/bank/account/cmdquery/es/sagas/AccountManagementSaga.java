package itso.microservice.bank.account.cmdquery.es.sagas;

import javax.inject.Inject;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@Saga
public class AccountManagementSaga {

	private static final Logger logger = LoggerFactory.getLogger(AccountManagementSaga.class);

    private boolean withdrawCompleted = false;
    private boolean depositCompleted = false;
    private String transactionId;
    private double amount;
    private String sourceAccountId;
    private String destinationAccountId;
    
	@Inject
    private transient CommandGateway commandGateway;
	
	/*
	 * @StartSaga
	 * 
	 * @SagaEventHandler(associationProperty = "transactionId") public void
	 * handle(TransferTransactionStartedEvent event) { logger.
	 * info("A new transfer transaction is started with id {}, from account {} to account {} and amount {}"
	 * , event.getTransactionId(), event.id, event.getDestinationAccountId(),
	 * event.getAmount()); try { sourceAccountId = event.id; destinationAccountId =
	 * event.getDestinationAccountId(); amount = event.getAmount(); transactionId =
	 * event.getTransactionId(); DebitAccountCommand command = new
	 * DebitAccountCommand (event.id, event.getAmount(), event.getTransactionId(),
	 * true);
	 * 
	 * //associate Saga SagaLifecycle.associateWith("transactionId", transactionId);
	 * commandGateway.send(command);
	 * 
	 * logger.
	 * info(" commandGateway.send(command) successfully inside AccountManagementSaga.handle(TransferTransactionStartedEvent event)"
	 * );
	 * 
	 * } catch (Exception e) { commandGateway.send(new
	 * FailTransactionCommand(sourceAccountId, destinationAccountId,
	 * transactionId)); } }
	 * 
	 * 
	 * @SagaEventHandler(associationProperty = "transactionId") public void
	 * handle(TransferMoneyDebitEvent transmoneyDebitEvent) { logger.
	 * info("Transfer transaction with id {}, did transfer from {} successfully",
	 * transmoneyDebitEvent.getTransactionId(), transmoneyDebitEvent.id);
	 * withdrawCompleted = true; try { CreditAccountCommand command = new
	 * CreditAccountCommand(destinationAccountId, transmoneyDebitEvent.getAmount(),
	 * transmoneyDebitEvent.getTransactionId(), true);
	 * 
	 * SagaLifecycle.associateWith("transactionId", transactionId);
	 * commandGateway.send(command);
	 * 
	 * logger.
	 * info(" commandGateway.send(command) successfully inside AccountManagementSaga.handle(TransferMoneyDebitEvent transmoneyDebitEvent)"
	 * ); } catch (Exception e) { commandGateway.send(new
	 * FailTransactionCommand(sourceAccountId, destinationAccountId, transactionId,
	 * e.getMessage())); } }
	 * 
	 * 
	 * @SagaEventHandler(associationProperty = "transactionId") public void
	 * handle(TransferFailedEvent event) { // change this name and kick of cancel
	 * logger.info("Transfer transaction {} failed to start",
	 * event.getTransactionId()); commandGateway.send(new
	 * CancelTransferCommand(sourceAccountId, transactionId, event.getMsg())); }
	 * 
	 * @EndSaga
	 * 
	 * @SagaEventHandler(associationProperty = "transactionId") public void
	 * handle(TransferCanceledEvent event) {
	 * logger.info("Transaction {} was canceled.", event.getTransactionId()); if
	 * (withdrawCompleted) { commandGateway.send(new
	 * RevertAccountBalanceCommand(sourceAccountId, transactionId, amount)); }
	 * 
	 * if (depositCompleted) { commandGateway.send(new
	 * RevertAccountBalanceCommand(destinationAccountId, transactionId, amount)); }
	 * }
	 * 
	 * 
	 * @EndSaga
	 * 
	 * @SagaEventHandler(associationProperty = "transactionId") public void
	 * handle(TransferMoneyCreditEvent transMoneyCreditEvent) {
	 * logger.info("Transaction {} concluded",
	 * transMoneyCreditEvent.getTransactionId()); depositCompleted = true;
	 * commandGateway.send(new ReleaseAccountCommand(sourceAccountId,
	 * transMoneyCreditEvent.getTransactionId())); commandGateway.send(new
	 * ReleaseAccountCommand(destinationAccountId,
	 * transMoneyCreditEvent.getTransactionId())); }
	 * 
	 */
}
