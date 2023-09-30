/**
 * 
 */
package BankAccoun;

/**
 * Main class for the Bank account project
 * @author tyler
 *
 */
public class ProcessBankAccount implements Runnable
{
	//create a new Account
	private Account acct = new Account();
	
	
	/**
	 * Main method 
	 * @param args
	 */
	public static void main(String[] args) 
	{
		//create an instance of BankAccountInClass 
		ProcessBankAccount r = new ProcessBankAccount();
		
		//create thread one
		Thread one = new Thread(r);
		
		//create thread two
		Thread two = new Thread(r);
		
		//set the name of thread one to Jade
		one.setName("Tyler");
		
		//set the name of thread two to Rocket
		two.setName("Bob");
		
		//start thread one (remember this executes the run method)
		one.start();
		
		//start thread two (remember this executes the run method)
		two.start();
	}
	/**
	 * run method to test the withdrawal process.
	 * 
	 */
	//overrides run method of the runnable interface as it is mandatory to override that method
	@Override
	public void run() 
	{
		//checks the account balance
		System.out.println(Thread.currentThread().getName() + " current account balance is " + acct.getBalance());
		
		//iterates so 5 with draws happen to the account
		for (int index = 0; index < 5; index += 1)
		{
			
			makeWithdrawal(100);
			
			if (acct.getBalance() < 0) 
			{
				System.out.println(Thread.currentThread().getName() +  "account is overdrawn!");
			}
		}
		//make 200 deposit to account
		makeDeposit(200);
	}
	
	/**
	 * run method to make a deposit
	 * @param amt
	 */
	public void makeDeposit(int amt) 
	{
		//make the deposit to the account
		if(amt > 0)
		{
			//everything checks out to make the deposit
			System.out.println(Thread.currentThread().getName() + " is going to deposit " + amt);
			
			try 
			{
				Thread.sleep(100);
				
			} catch (InterruptedException ex)
			{
				
			}
			acct.deposit(amt);
			System.out.println(Thread.currentThread().getName() + " completes the deposit of " + amt + " account balance is now " + acct.getBalance());
			
		}
		else
		{
			//if the amount is less then 0
			System.out.println(Thread.currentThread().getName() +  "can not deposite negative values in account.");
		}
		
	}
	/**
	 * run synchronized method to make withdraw
	 * @param amt
	 */
	public synchronized void makeWithdrawal(int amt) 
	{
		if (acct.getBalance() >= amt) 
		{
			//checks the account balance
			System.out.println(Thread.currentThread().getName() + " current account balence is " + acct.getBalance());
			
			//making a withdraw
			System.out.println(Thread.currentThread().getName() + " is going to withdraw");
			try {
				Thread.sleep(100);
			} catch (InterruptedException ex) {
			}
			acct.withdraw(amt);
			System.out.println(Thread.currentThread().getName() + " completes the withdrawal");
		} else {
			System.out.println("Not enough in account for " + Thread.currentThread().getName() + " to withdraw, account balance is " + acct.getBalance());
		}
	}
}

	
		
/**
 * Account class with methods to display the balance, subtract, and add to the balance
 */
class Account
{
	private int balance = 500;
	
	public int getBalance() {
		return balance;
	}
	public void withdraw(int amount) {
		balance = balance - amount;
	}
	public void deposit(int amount) {
		balance = balance + amount;
	}
}



