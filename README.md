# AI-Traveling-Salesman-Problem
## Project name 
Traveling Salesman Problem (TSP) using 1)Branch and Bound Depth First Search 2)Stochastic Local Search

## Introduction
The traveling salesman problem (TSP) is an algorithmic problem tasked with finding the shortest route 
between a set of points and locations that must be visited.
1. In the Branch and Bound method, we calculate a bound on the best possible solution we could obtain 
if we go down this node from the tree. We disregard the subtree rooted with the node if the bound 
on the best possible solution is in fact worse than the current best.
2. In the Stochastic Local Search method, we use Simulated annealing.
Simulated annealing uses a noise model inspired by statistical mechanics.

## Requirements
This module requires the following:
[IntelliJ]https://www.jetbrains.com/idea/
or any other Java IDE

## Installation
Install as you would normally install an IntelliJ IDE. For further
information, see
[Installing IntelliJ](https://www.jetbrains.com/idea/)

## Usage
Open the downloaded project in the IntelliJ IDE. 
In the BnBDFS folder, there is a file called as Constants.
In that file, change the value for the variable folderPath to wherever input files are. Make sure its the absolute path.
Example: public String filePath= "Dir1/Dir2/Dir3/"

1)Run file BnBDFS.java
2)Run file SimulatedAnnealingSls.java

