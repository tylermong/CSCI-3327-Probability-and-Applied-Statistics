% Main script for Plotter Salter Smoother

% Build the initial data
[xValues, yValues] = dataBuilder();

% Salt the data
saltedYValues = salter(xValues, yValues);

% Smooth the salted data
smoothedYValues = smoother(xValues, saltedYValues);

% Plot all datasets together for comparison
plotAllData(xValues, yValues, saltedYValues, smoothedYValues);
