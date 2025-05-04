function saltedYValues = salter(xValues, yValues)
    variability = 30;
    printf('\nVariability = 30: ');
    
    % Salts the y values by adding random noise based on the variability
    saltedYValues = yValues + (rand(size(yValues)) * 2 - 1) * variability;
        
    printf('Salting complete\n');
end