function smoothedYValues = smoother(xValues, saltedYValues)
    windowValue = 2;
    printf('\nWindow value = 2\n');

    % Initialize the smoothed y values array
    smoothedYValues = zeros(size(saltedYValues));

    % Smooth the salted y values using a moving average based on the window value
    for i = 1:length(saltedYValues)
        leftBound = max(1, i - windowValue);
        rightBound = min(length(saltedYValues), i + windowValue);

        window = saltedYValues(leftBound:rightBound);
        smoothedYValues(i) = mean(window);
    end
end