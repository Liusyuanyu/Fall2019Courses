function output = my_imfilter(image, filter)
    %% Check size of the image and type
    [rows_filter, columns_filter] = size(filter);
    
    if mod(rows_filter,2) == 0|| mod(columns_filter,2) == 0
        ME = MException('my_imfilter:inputError','Both dimensions of the filter must be odd .');
        throw(ME)
    end    
    
    [rows, columns,Num_ch] = size(image);
%     clear Num_ch; % We don't need to consider the image is color or black&white.
    center_row = rows_filter/2+1/2;
    center_col = columns_filter/2+1/2;

    shift_matrix=zeros(rows,columns,Num_ch);
    output_image = (zeros(size(image)));
    for row_ind = 1:rows_filter
        for col_ind = 1:columns_filter
            %Zero matrix to contain shift matrix
            shift_matrix=shift_matrix*0;
            shift_row_start = 1;
            shift_row_end = rows;
            shift_col_start = 1;
            shift_col_end = columns;
            
            image_row_start = 1;
            image_row_end = rows;
            image_col_start = 1;
            image_col_end = columns;
            
            row_n = abs(row_ind - center_row);
            col_n = abs(col_ind - center_col);
            
            if row_ind < center_row
                shift_row_start = shift_row_start + row_n;
                image_row_end = image_row_end - row_n;
            elseif row_ind > center_row
                shift_row_end = shift_row_end - row_n;
                image_row_start = image_row_start + row_n;
            end

            if col_ind < center_col
                shift_col_start = shift_col_start + col_n;
                image_col_end = image_col_end - col_n;
            elseif col_ind > center_col
                shift_col_end = shift_col_end - col_n;
                image_col_start = image_col_start + col_n;
            end

            shift_matrix(shift_row_start:shift_row_end,shift_col_start:shift_col_end, :)= image(image_row_start:image_row_end,image_col_start:image_col_end, :);
            filtered_value  = shift_matrix * filter(row_ind,col_ind); 
            output_image = output_image + filtered_value;
        end
    end
    output = output_image;
end

