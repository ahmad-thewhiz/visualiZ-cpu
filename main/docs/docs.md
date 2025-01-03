### Project Flow and Functionality Overview

#### **1. Project Overview**
This project is a **CPU Scheduling Simulator** implemented in Java. It allows users to simulate and visualize different CPU scheduling algorithms, including:
- **First-Come, First-Served (FCFS)**
- **Round Robin**
- **Priority Scheduling**
- **Shortest Job First (SJF)**

The GUI (via `SchedulingGUI`) allows user interaction, while classes like `FCFSPolicy`, `RoundRobinPolicy`, and others implement the scheduling logic. Simulation results (e.g., waiting time, burst time) are displayed dynamically.

---

#### **2. GUI: SchedulingGUI.java**
**`SchedulingGUI`** initializes the user interface, allowing the selection of scheduling algorithms, specifying the number of processes, and browsing input files:
- **Algorithm Selection**: A dropdown menu offers algorithm options.
- **Process Input**: Users input the number of processes and a file source for process details.
- **Simulation Start**: On clicking "Start," jobs are created and passed to the `CalcSimulation` class for execution.

---

#### **3. Simulation Core: CalcSimulation.java**
**`CalcSimulation`** initializes the simulation and selects the appropriate scheduling algorithm:
- **Job Initialization**: Creates job objects (`Job[]`) with random `arrivalTime` and `burstTime`.
- **Algorithm Delegation**: Based on the selected algorithm, it instantiates the relevant policy (e.g., `FCFSPolicy`, `RoundRobinPolicy`) and starts the `CPUScheduler`.

---

#### **4. Scheduling Algorithms**
Each scheduling algorithm is encapsulated in its respective class implementing the `Scheduler` interface.

- **`FCFSPolicy`**:
  - Jobs are enqueued in the order they arrive.
  - **Methods**:
    - `enqueue(Job job)`: Adds jobs to the tail of the queue.
    - `dequeue()`: Removes and returns the head of the queue.
    - `getRemainingProcesses()`: Returns a string of all process IDs in the queue.

- **`RoundRobinPolicy`**:
  - Uses a **time quantum** to allocate CPU to jobs cyclically.
  - **Key Attribute**: `TIME_QUANTUM`.
  - **Methods**:
    - `enqueue` and `dequeue`: Similar to `FCFSPolicy`.
    - `allocateCPU(Job job)`: Simulates job execution in a round-robin manner.

- **`PriorityPolicy`**:
  - Jobs are scheduled based on priority (higher priority first).
  - **Key Attribute**: Uses a linked list for dynamic job handling.
  - **Methods**: Similar to `FCFSPolicy` but accounts for job priorities.

- **`MaxPriorityQueue`**:
  - Used for managing jobs in priority-based scheduling.
  - **Methods**:
    - `insert(Job job)`: Adds a job to the queue.
    - `getMax()`: Returns the job with the highest priority.
    - `extractMax()`: Removes and returns the job with the highest priority.

---

#### **5. Scheduling Execution: CPUScheduler.java**
**`CPUScheduler`** is the execution engine for the simulation:
- **Key Components**:
  - `jobBatch`: Array of jobs.
  - `Scheduler policy`: Selected scheduling policy.
  - **Progress Tracking**: Updates GUI components like progress bars and waiting times.
- **Execution Flow**:
  - Enqueues jobs into the policy.
  - Dequeues jobs one by one, simulating their execution using `ComputationThread`.

---

#### **6. Multithreading: ComputationThread.java**
**`ComputationThread`** simulates job execution:
- **`run()`**: Simulates job processing:
  - For **FCFS** and **Priority Scheduling**, processes are executed serially.
  - For **Round Robin**, processes are split into chunks (time quantum).

---

#### **7. Layout Management**
- **`AbsoluteLayout` and `AbsoluteConstraints`** manage GUI component positioning dynamically.
- **`TitleThread`** animates the GUI title.

---

#### **8. Simulation Flow**
1. **User Input**: `SchedulingGUI` collects user inputs (algorithm, processes).
2. **Simulation Initialization**: `CalcSimulation` sets up jobs and policies.
3. **Policy Selection**: Based on the algorithm, `CPUScheduler` uses the relevant policy.
4. **Job Execution**:
   - Jobs are processed as per the selected policy.
   - **Progress Visualization**: Updates GUI components like progress bars.
5. **Results Display**: Outputs waiting times, turnaround times, and total execution time.