import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    private static final int ARRIVAL = 0;
    private static final int DEPARTURE = 1;

    public static void main(String[] args) {

        //Stepp 1  - Create scanner so user inputs No of Customers
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of customers: ");
        int numCustomers = scanner.nextInt();

        double[] IAT = new double[numCustomers];
        double[] ST = new double[numCustomers];

        System.out.println("Enter inter-arrival times (IAT) and service times (ST) for each customer:");
        // Step 2 - For all Customers Users have to input IATs and STs, thats why for loop is created
        for (int i = 0; i < numCustomers; i++) {
            System.out.print("IAT[" + i + "]: ");
            IAT[i] = scanner.nextDouble();

            System.out.print("ST[" + i + "]: ");
            ST[i] = scanner.nextDouble();
        }

        //Step 3 -  Run the simulation with IAT and ST that user provides
        QueueStatistics predictedQueueStats = predictQueueStatistics(IAT, ST);

        //Step 4 - Displaying the predicted queue stats
        System.out.println("Predicted Queue Statistics:");
        System.out.println("Number of Customers in Queue: " + predictedQueueStats.getNumCustomersInQueue());
        System.out.println("Number of Customers in System: " + predictedQueueStats.getNumCustomersInSystem());
        System.out.println("Total Customers Served: " + predictedQueueStats.getTotalCustomersServed());
        System.out.println("Average Queue Wait Time: " + predictedQueueStats.getAverageQueueWaitTime());

        scanner.close();
    }

    private static QueueStatistics predictQueueStatistics(double[] IAT, double[] ST) {
        // Step 1 Initializing Simulation variables
        int numCustomersInQueue = 0;
        int numCustomersInSystem = 0;
        int totalCustomersServed = 0;
        double totalQueueWaitTime = 0.0;
        double simulationTime = 0.0;

        // Step 2 - Using linked listing for Event queue
        Queue<Event> eventQueue = new LinkedList<>();

        // Step 3 - Initialize with the first arrival event
        eventQueue.add(new Event(ARRIVAL, simulationTime + IAT[0]));

        while (!eventQueue.isEmpty()) {
            Event currentEvent = eventQueue.poll();
            simulationTime = currentEvent.getTime();

            if (currentEvent.getType() == ARRIVAL) {
                numCustomersInSystem++;

                if (numCustomersInQueue == 0) {
                    eventQueue.add(new Event(DEPARTURE, simulationTime + ST[totalCustomersServed]));
                } else {
                    numCustomersInQueue++;
                }

                if (totalCustomersServed < IAT.length - 1) {
                    eventQueue.add(new Event(ARRIVAL, simulationTime + IAT[totalCustomersServed + 1]));
                }

            } else if (currentEvent.getType() == DEPARTURE) {
                numCustomersInQueue--;
                totalCustomersServed++;
                totalQueueWaitTime += simulationTime - IAT[totalCustomersServed];

                if (numCustomersInQueue > 0) {
                    eventQueue.add(new Event(DEPARTURE, simulationTime + ST[totalCustomersServed]));
                }
            }
        }

        double averageQueueWaitTime = totalQueueWaitTime / totalCustomersServed;

        // Step 4 - Creating and returning QueueStatistics objects
        QueueStatistics queueStatistics = new QueueStatistics();
        queueStatistics.setNumCustomersInQueue(numCustomersInQueue);
        queueStatistics.setNumCustomersInSystem(numCustomersInSystem);
        queueStatistics.setTotalCustomersServed(totalCustomersServed);
        queueStatistics.setTotalQueueWaitTime(averageQueueWaitTime);

        return queueStatistics;
    }

    private static class Event {
        private int type;
        private double time;

        public Event(int type, double time) {
            this.type = type;
            this.time = time;
        }

        public int getType() {
            return type;
        }

        public double getTime() {
            return time;
        }
    }
}
