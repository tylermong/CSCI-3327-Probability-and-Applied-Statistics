# Plotter, Salter, Smoother - Original

## Overview
This project implements functionality for plotting, salting, and smoothing data. It includes a `DataBuilder` class for creating data, a `Salter` class for salting data, and a `Smoother` class for smoothing salted data.

## Class Descriptions
`Main`: This class is the entry point of the program. It initializes the `DataBuilder`, `Salter`, and `Smoother` classes, and orchestrates the flow of the program by calling their methods in sequence.

`DataBuilder`: This class is responsible for creating data. It starts by prompting the user to select a function, which will be used to generate the data. The user can choose from a list of functions, including `f(x) = x + 1`, `f(x) = x^2`, and `f(x) = log(x)`. Then, the user is prompted to enter a series of comma-separated x values, which will be used to generate the data. Next, the class generates the y values by applying the selected function to each x value. Finally, the class writes the data to a CSV file, `data.csv`, in the format `x,y`.

`Salter`: This class is responsible for salting the data. It reads the data from the CSV file, `data.csv`, and applies a simple salting algorithm to the y values. The salting algorithm adds a random value to each y value, which is generated using the `random` module. The salted data is then written to a new CSV file, `salted_data.csv`, in the same format as the original data.

`Smoother`: This class is responsible for smoothing the salted data. It reads the salted data from the CSV file, `salted_data.csv`, and applies a simple smoothing algorithm to the y values. The smoothing algorithm calculates the average of all y values in a given window, which is defined by the `windowValue` parameter. The smoothed data is then written to a new CSV file, `smoothed_data.csv`, in the same format as the original data.

## How to Run
1. Navigate to the `/src` directory.
2. Run the `Main.java` file.
3. Follow the prompts to select a function, enter x values, and specify the `variability` and `windowValue` parameters.
4. The program will generate the data, salt it, and smooth it, writing the results to the respective CSV files, in the `/output` directory.

### Images
#### Console output:
![Console Output](./images/console_output.png)

#### Data.csv:
![Data.csv](./images/data_csv.png)

#### Salted_data.csv:
![Salted_data.csv](./images/salted_data_csv.png)

#### Smoothed_data.csv:
![Smoothed_data.csv](./images/smoothed_data_csv.png)

TODO:
- [x] Modify `variability` and `windowValue` to be user inputs, rather than values set in the source code.
- [x] Update the README to reflect the changes made above.
- [ ] Add a screenshot of the plotted data to the submission document.