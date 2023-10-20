public class QueueStatistics {
    private int numCustomersInQueue;
    private int numCustomersInSystem;
    private int totalCustomersServed;
    private double averageQueueWaitTime;


    public QueueStatistics() {
        // Default constructor
    }

    public QueueStatistics(int numCustomersInQueue, int numCustomersInSystem, int totalCustomersServed, double averageQueueWaitTime) {
        this.numCustomersInQueue = numCustomersInQueue;
        this.numCustomersInSystem = numCustomersInSystem;
        this.totalCustomersServed = totalCustomersServed;
        this.averageQueueWaitTime = averageQueueWaitTime;
    }

    // Getters
    public int getNumCustomersInQueue() {
        return numCustomersInQueue;
    }

    public int getNumCustomersInSystem() {
        return numCustomersInSystem;
    }

    public int getTotalCustomersServed() {
        return totalCustomersServed;
    }

    public double getAverageQueueWaitTime() {
        return averageQueueWaitTime;
    }

    // Setters
    public void setNumCustomersInQueue(int numCustomersInQueue) {
        this.numCustomersInQueue = numCustomersInQueue;
    }

    public void setNumCustomersInSystem(int numCustomersInSystem) {
        this.numCustomersInSystem = numCustomersInSystem;
    }

    public void setTotalCustomersServed(int totalCustomersServed) {
        this.totalCustomersServed = totalCustomersServed;
    }

    public void setTotalQueueWaitTime(double averageQueueWaitTime) {
        this.averageQueueWaitTime = averageQueueWaitTime;
    }
}
