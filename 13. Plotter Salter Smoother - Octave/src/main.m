% Main script for Plotter Salter Smoother

% Build the initial data
[xValues, yValues] = databuilder();

% Salt the data
saltedYValues = salter(xValues, yValues);

% Smooth the salted data
smoothedYValues = smoother(xValues, saltedYValues);

% Plot all datasets together for comparison
plotalldata(xValues, yValues, saltedYValues, smoothedYValues);
printf('X values: %s\n', mat2str(xValues));
printf('Y values: %s\n', mat2str(yValues));
printf('Salted Y values: %s\n', mat2str(saltedYValues));
printf('Smoothed Y values: %s\n', mat2str(smoothedYValues));