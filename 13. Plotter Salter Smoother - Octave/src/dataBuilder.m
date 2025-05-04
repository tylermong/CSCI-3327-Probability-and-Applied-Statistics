% Gets x values from user (or defaults to 1:10) and calculates y values
function [xValues, yValues] = dataBuilder()
    fprintf('Function to plot: f(x) = x^2\n');

    fprintf('\nSpecify x values using brackets (e.g. [1, 2, 3, 4])\n');
    fprintf('Or press Enter to use the default range (1 to 10): ');
    xValues = input('');

    if isempty(xValues)
        xValues = 1:10;
    end

    yValues = xValues.^2;
end