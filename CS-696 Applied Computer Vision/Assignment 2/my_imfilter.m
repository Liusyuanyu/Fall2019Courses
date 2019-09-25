function output = my_imfilter(image, filter)
    %% Check size of the image and type
    [rows_filter, columns_filter] = size(filter);
    
    if mod(rows_filter,2) == 0|| mod(columns_filter,2) == 0
        ME = MException('my_imfilter:inputError','Both dimensions of the filter must be odd .');
        throw(ME)
    end    
    
    [rows, columns,Num_ch] = size(image);
    output_image = (zeros(size(image)));
    
    center = [rows_filter/2+1/2,columns_filter/2+1/2];
    left = center(2) - 1;
    right = columns_filter - center(2);
    top = center(1) - 1;
    bottom = rows_filter - center(1);
    pad_matrix = zeros(rows + top + bottom, columns + left + right);
    
    for channel=1:Num_ch
        pad_matrix = pad_matrix*0;
        pad_matrix(1+top:end-bottom,1+left:end-right) = image(:,:,channel);
        
        filtered_page = zeros(rows , columns);
        for row = 1 : rows
            for col = 1 : columns
                for row_filter = 1 : rows_filter
                    for col_filter = 1 : columns_filter
                        exp_row = row - 1;
                        exp_col = col -1;
                        filtered_page(row, col) = filtered_page(row, col) + ...
                            (pad_matrix(row_filter + exp_row, col_filter + exp_col) * filter(row_filter, col_filter));
                    end
                end
            end
        end
        output_image(:,:,channel) = filtered_page;
    end
    output = output_image;
end

