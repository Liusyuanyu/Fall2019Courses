function output = my_imfilter(image, filter)
% This function is intended to behave like the built in function imfilter()
% See 'help imfilter' or 'help conv2'. While terms like "filtering" and
% "convolution" might be used interchangeably, and they are indeed nearly
% the same thing, there is a difference:
% from 'help filter2'
%    2-D correlation is related to 2-D convolution by a 180 degree rotation
%    of the filter matrix.

% Your function should work for color images. Simply filter each color
% channel independently.

% Your function should work for filters of any width and height
% combination, as long as the width and height are odd (e.g. 1, 7, 9). This
% restriction makes it unambigious which pixel in the filter is the center
% pixel.

% Boundary handling can be tricky. The filter can't be centered on pixels
% at the image boundary without parts of the filter being out of bounds. If
% you look at 'help conv2' and 'help imfilter' you see that they have
% several options to deal with boundaries. You should simply recreate the
% default behavior of imfilter -- pad the input image with zeros, and
% return a filtered image which matches the input resolution. A better
% approach is to mirror the image content over the boundaries for padding.

% % Uncomment if you want to simply call imfilter so you can see the desired
% % behavior. When you write your actual solution, you can't use imfilter,
% % filter2, conv2, etc. Simply loop over all the pixels and do the actual
% % computation. It might be slow.
% output = imfilter(image, filter);


%%%%%%%%%%%%%%%%
% Your code here
%%%%%%%%%%%%%%%%


    %% Check size of the image and type
    [rows, columns, numberOfColorChannels] = size(image);

    output_image = uint8(zeros(size(image)));

    if mod(rows,2) == 0
        return
    elseif mod(columns,2) == 0
        return
    end

    [rows_filter, columns_filter] = size(filter);
    half = fix(rows_filter/2);

    for row =1:rows 
        for column = 1:columns
            sub_mat =  zeros(rows_filter); 

            extract_row_start = row - half;
            extract_row_end = row + half;
            extract_col_start = column - half;
            extract_col_end = column + half;

            sub_row_start = 1;
            sub_row_end = rows_filter;
            sub_col_start = 1;
            sub_col_end = columns_filter;

            if extract_row_start <=0
                sub_row_start = sub_row_start + abs(extract_row_start) +1;
                extract_row_start = 1;
            end

            if extract_row_end > rows
                sub_row_end = sub_row_end - ( extract_row_end - rows);
                extract_row_end = rows;
            end

            if extract_col_start <=0
                sub_col_start = sub_col_start + abs(extract_col_start) +1;
                extract_col_start = 1;
            end
            if extract_col_end > columns
                sub_col_end = sub_col_end  - (extract_col_end - columns);
                extract_col_end = columns;
            end

            sub_matrix = image(extract_row_start:extract_row_end, extract_col_start:extract_col_end);
            sub_mat(sub_row_start:sub_row_end,sub_col_start:sub_col_end) =sub_matrix; 

            sub_mat = sub_mat.*filter ;
            output_image(row,column) = sum(sub_mat(:));
        end
    end
    output = output_image;

end

