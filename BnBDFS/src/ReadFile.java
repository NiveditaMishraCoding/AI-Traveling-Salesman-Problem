import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFile {

    public int nodes;
    public Double[][] readFileAndReturnInputForBnbDFS( String name){

        try {

            String file = Constants.folderPath + name;
            Scanner myReader = new Scanner(new File(file));
            int numberOfNodes = Integer.parseInt(myReader.nextLine());
            nodes = numberOfNodes;

            Double[][] inputMatrix = new Double[numberOfNodes][numberOfNodes];

            for(int i = 0; i<numberOfNodes; ++i){

                String data = myReader.nextLine();
                String[] distance = data.split(" ");

                for(int j = 0; j<numberOfNodes; ++j){

                    inputMatrix[i][j] = Double.parseDouble(distance[j]);
                }
            }

            myReader.close();
            return inputMatrix;

        } catch (FileNotFoundException e) {

            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return null;
    }

}
