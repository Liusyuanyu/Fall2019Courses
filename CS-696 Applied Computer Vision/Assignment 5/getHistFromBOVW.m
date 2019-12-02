function [feature_hist] = getHistFromBOVW(image_feats,bovw)
    
    feature_hist = zeros(1,size(bovw,1));
    for ind = 1:size(image_feats, 1)        
        sub_val = bovw - image_feats(ind,:);
        dist = vecnorm(sub_val,2, 2);
        [~,idx] = min(dist);
        
        feature_hist(idx) = feature_hist(idx)+1;
    end

    %Normalize L1
    feature_hist = feature_hist ./ sum(feature_hist(:));
end

