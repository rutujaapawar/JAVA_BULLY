import java.util.*;
 
class Process
{
	public int id;
	public boolean active;
	public Process(int id)
	{
    	this.id=id;
    	this.active=true;
	}
}
public class Bully {
 
	Scanner sc;
	Process[] processes;
	int noOfProcess;
   
	public Bully(){
    	sc = new Scanner(System.in);
	}
   
	public void initialiseRing()
	{
    	System.out.println("Enter no of processes");
    	noOfProcess = sc.nextInt();
    	processes = new Process[noOfProcess];
    	for(int i=0;i<noOfProcess;i++)
    	{
        	processes[i] = new Process(i);
    	}
	}
   
	public void performElection(){
 
    	try {
        	Thread.sleep(1000);
    	} catch (InterruptedException e) {
        	e.printStackTrace();
    	}
    	System.out.println("Enter process no of initiator:");
    	int InitiatorProcessId = sc.nextInt();
    	System.out.println("Process no "+processes[getMax()].id+" fails");
    	processes[getMax()].active=false;
 
  	 
    	boolean notOver=true;
    	while(notOver){
 
        	boolean moreHigherProcesses=false;
        	for(int i=InitiatorProcessId+1;i<noOfProcess;i++){
            	if(processes[i].active){
 
                	System.out.println("Process "+InitiatorProcessId+" Passes Election("+InitiatorProcessId+") message to process " +i);
                	moreHigherProcesses=true;
 
            	}
        	}
        	if(moreHigherProcesses){
            	String answer = "";
            	for(int i=InitiatorProcessId+1;i<noOfProcess;i++){
                	if(processes[i].active){
                    	System.out.println("Do you want to pass OK("+i+") message to process " +InitiatorProcessId+"? (y/n)");
                    	answer = sc.next();
                    	if(answer.equals("y")){
                        	System.out.println("Process "+i+" Passes Ok("+i+") message to process " +InitiatorProcessId);
                    	}
                    	if(answer.equals("n")){
                        	processes[i].active = false;
                    	}
                  	 
                  	 
                  	 
                	}
 
            	}
            	int newInitiator = InitiatorProcessId + 1;
            	while((newInitiator < noOfProcess ) && (processes[newInitiator].active == false)){
                	newInitiator++;
            	}
            	InitiatorProcessId = newInitiator;
        	}else{
                	int coordinator = processes[getMax()].id;
                	System.out.println("Finally Process "+coordinator+" Becomes Coordinator");
                	for(int i=coordinator-1;i>=0;i--){
                    	if(processes[i].active){
                        	System.out.println("Process "+coordinator+" Passes Coordinator("+coordinator+") message to process " +i);
                    	}
                	}
 
                	System.out.println("End of Election");
                	notOver=false;
                	break;
        	}
    	}
 
	}
 
        	public int getMax(){
            	int maxId=-99;
            	int maxIdIndex=0;
            	for(int i=0;i<processes.length;i++){
                	if(processes[i].active && processes[i].id > maxId){
                    	maxId= processes[i].id;
                    	maxIdIndex=i;
                	}
            	}
            	return maxIdIndex;
        	}
 
	public static void main(String[] args) {
    	Bully b = new Bully();
    	b.initialiseRing();
    	b.performElection();
 
	}
 
}
