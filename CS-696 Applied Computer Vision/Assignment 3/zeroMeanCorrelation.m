function [match_rect, euclidean_dist] = zeroMeanCorrelation(input,template,pos)
    [tmp_rows,tmp_columns] = size(template);
    zero_mean = input - mean(input(:));
    cor_mat = xcorr2(zero_mean, template);
    cor_mat = cor_mat(tmp_rows:end-tmp_rows+1,tmp_columns:end-tmp_columns+1);
    
    [ypeak, xpeak] = find(cor_mat==max(cor_mat(:)));
    mid_x = ceil(tmp_columns/2);
    mid_y = ceil(tmp_rows/2);
    
    if size(ypeak,1) ~= 1
        euclidean_list = [];
        match_list = [];
        for ind= 1: size(ypeak,1)
           a_match = [xpeak(ind),ypeak(ind),tmp_columns-1,tmp_rows-1];    
           a_dist = norm(pos -[a_match(1)+mid_x,a_match(2)+mid_y]);
           match_list = [match_list;a_match];
           euclidean_list = [euclidean_list,a_dist];
        end
        min_euclidean = find(euclidean_list==min(euclidean_list));
        
        match_rect = match_list(min_euclidean,:);
        euclidean_dist = euclidean_list(min_euclidean);
        
    else    
        match_rect = [xpeak,ypeak,tmp_columns-1,tmp_rows-1];        
        euclidean_dist = norm(pos -[match_rect(1)+mid_x , match_rect(2)+mid_y]);
    end
end