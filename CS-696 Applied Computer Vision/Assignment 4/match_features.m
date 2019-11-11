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
    num_features1 = size(features1, 1);
    % num_features2 = size(features2, 1);
    feat_inds = 1:num_features1;

    first_nn = nn_val(:,1);
    second_nn = nn_val(:,2);
    nndr_list = first_nn ./ second_nn;

    matches = zeros(num_features1, 2);
    matches(:,1) = feat_inds;
    matches(:,2) = nn_rank(:,1);
    confidences = 1./nndr_list(feat_inds);

% Sort the matches so that the most confident onces are at the top of the
% list. You should probably not delete this, so that the evaluation
% functions can be run on the top matches easily.
[confidences, ind] = sort(confidences, 'descend');
matches = matches(ind,:);