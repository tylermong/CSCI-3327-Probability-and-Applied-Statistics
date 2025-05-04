% Plots the original, salted, and smoothed data on the same graph
function plotAllData(xValues, yValues, saltedYValues, smoothedYValues)
    % Create the figure window
    figure;

    % Graph the original, salted, and smoothed data
    plot(xValues, yValues, 'g-*', 'LineWidth', 2, 'DisplayName', 'Original Data');
    hold on; % Keeps the current plot rather than replacing it
    plot(xValues, saltedYValues, 'r-*', 'LineWidth', 1, 'DisplayName', 'Salted Data');
    plot(xValues, smoothedYValues, 'b-*', 'LineWidth', 2, 'DisplayName', 'Smoothed Data');

    % Set the chart title, axis labels, and grid
    title('Comparison of Original, Salted, and Smoothed Data');
    xlabel('X Values');
    ylabel('Y Values');
    grid on;

    % Set legend and its properties for each plotted line
    lgd = legend('show');
    set(lgd, 'Location', 'northwest');
    set(lgd, 'FontSize', 10);
    set(lgd, 'Box', 'on');
end