function [match_rect, euclidean_dist] = SumSquDifferences(input,template,pos)

    [rows,columns] = size(input);
    [tmp_rows,tmp_columns] = size(template);
    ssd_mat = zeros(rows-tmp_rows+1,columns-tmp_columns+1);
    for row_ind=1:rows
        for col_ind=1:columns
            width_end = col_ind+tmp_columns-1;
            height_end = row_ind+tmp_rows-1;
            
            if width_end > columns
                break;
            elseif height_end > rows
                break;
            end
            
            sub_image = input(row_ind:height_end,col_ind:width_end);
            difference = sub_image - template;
            ssd_mat(row_ind,col_ind) = sum(difference(:).^2); 
        end
    end
    
    [ypeak, xpeak] = find(ssd_mat==min(ssd_mat(:)));
    mid_x = ceil(tmp_columns/2);
    mid_y = ceil(tmp_rows/2);
    
    if size(ypeak,1) ~= 1
        euclidean_list = [];
        match_list = [];
        for ind= 1: size(ypeak,1)
           a_match = [xpeak(ind),ypeak(ind),tmp_columns-1,tmp_rows-1];
           a_dist = norm(pos -[xpeak(ind)+mid_x,ypeak(ind)+mid_y]);
           match_list = [match_list;a_match];
           euclidean_list = [euclidean_list,a_dist];
        end
        min_euclidean = find(euclidean_list==min(euclidean_list));
        
        match_rect = match_list(min_euclidean,:);
        euclidean_dist = euclidean_list(min_euclidean);
        
    else    
        match_rect = [xpeak,ypeak,tmp_columns-1,tmp_rows-1];
        euclidean_dist = norm(pos -[xpeak+mid_x , ypeak+mid_y]);
    end
end
