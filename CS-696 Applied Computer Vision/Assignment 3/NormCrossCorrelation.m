function [match_rect, euclidean_dist] = NormCrossCorrelation(input,template,pos)

    [tmp_rows,tmp_columns] = size(template);
   
    ncc_mat = normxcorr2(template,input);
    
    [ypeak, xpeak] = find(ncc_mat==max(ncc_mat(:)));
    mid_x = tmp_rows/2;
    mid_y = tmp_columns/2;
    
    if size(ypeak,1) ~= 1
        euclidean_list = [];
        match_list = [];
        for ind= 1: size(ypeak,1)
           a_match = [xpeak(ind)-tmp_columns,ypeak(ind)-tmp_rows,tmp_columns-1,tmp_rows-1];
           
           a_dist = norm(pos -[a_match(0)+mid_x,a_match(1)+mid_y]);
           
           match_list = [match_list;a_match];
           euclidean_list = [euclidean_list,a_dist];
        end
        min_euclidean = find(euclidean_list==min(euclidean_list));
        
        match_rect = match_list(min_euclidean,:);
        euclidean_dist = euclidean_list(min_euclidean);
        
    else    
        match_rect = [xpeak-tmp_columns,ypeak-tmp_rows,tmp_columns-1,tmp_rows-1];
        euclidean_dist = norm(pos -[xpeak+mid_x , ypeak+mid_y]);
    end
end


