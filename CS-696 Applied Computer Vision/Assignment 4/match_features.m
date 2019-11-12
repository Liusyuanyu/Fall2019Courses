% Local Feature Stencil Code


% 'features1' and 'features2' are the n x feature dimensionality features
%   from the two images.
% If you want to include geometric verification in this stage, you can add
% the x and y locations of the features as additional inputs.
%
% 'matches' is a k x 2 matrix, where k is the number of matches. The first
%   column is an index in features 1, the second column is an index
%   in features2. 
% 'Confidences' is a k x 1 matrix with a real valued confidence for every
%   match.
% 'matches' and 'confidences' can empty, e.g. 0x2 and 0x1.
function [matches, confidences] = match_features(features1, features2)

% For extra credit you can implement various forms of spatial verification of matches.
    Dists = pdist2(features1,features2,'euclidean');

    [nn_val,nn_rank] = sort(Dists,2);

    first_nn = nn_val(:,1);
    second_nn = nn_val(:,2);
    nndr_list = first_nn ./ second_nn;

%     conf_t = 0.8;
    conf_t = 0.7;
%     conf_t = 0.6;
    [conf_i,~] = find(nndr_list < conf_t );
    
    nndr_list = nndr_list(conf_i);
    nn_rank = nn_rank(conf_i);

    matches = zeros(size(nn_rank, 1), 2);
    matches(:,1) = conf_i;
    matches(:,2) = nn_rank(:,1);
    confidences = 1./nndr_list;

% Sort the matches so that the most confident onces are at the top of the
% list. You should probably not delete this, so that the evaluation
% functions can be run on the top matches easily.
[confidences, ind] = sort(confidences, 'descend');
matches = matches(ind,:);