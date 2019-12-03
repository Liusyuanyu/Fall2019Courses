function [centers] = buildBagOfVisualWord(all_des, N)
    des_dim = size(all_des,2);
    k_idx = kmeans(all_des,N);
    centers = zeros(N, des_dim);
    
    for clu = 1:N
        des_ind = find(k_idx == clu);
        features = all_des(des_ind,:);
        all_distance = pdist(features);
        all_distance = squareform(all_distance);
        sum_dist = sum(all_distance);        
        [~,idx] = min(sum_dist);
        centers(clu,:) = all_des(des_ind(idx),:);
    end
end

