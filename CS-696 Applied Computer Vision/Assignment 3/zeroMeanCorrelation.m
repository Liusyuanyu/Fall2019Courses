function [match_rect, euclidean_dist] = zeroMeanCorrelation(input,template,pos)
    zero_mean = input - mean(input(:));
    result = xcorr2(zero_mean, template);
    [ypeak, xpeak] = find(result==max(result(:)));
    match_rect = [xpeak-size(template,2),ypeak-size(template,1),size(template,2),size(template,1)];
    
    mid_x = size(template,2)/2;
    mid_y = size(template,1)/2;
    euclidean_dist = norm(pos -[xpeak+mid_x , ypeak+mid_y]);
end
