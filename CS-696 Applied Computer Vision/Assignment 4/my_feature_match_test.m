clear;
image1 = imread('../data/Notre Dame/921919841_a30df938f2_o.jpg');
gray_image = rgb2gray(image1);
gray_image = im2double(gray_image);

[x, y, confidence, scale, orientation] = get_interest_points(image1,1);

features = get_features(gray_image,x,y,16);

%%
features1
features2
pdist2(features1,features2,'euclidean')

%%
features1 = [1,1;2,2;3,3];
features2 = [3,3;1,1;2,2];
Dists = pdist2(features1,features2,'euclidean');
[nn_val,nn_rank] = sort(Dists,2);

num_features1 = size(features1, 1);
num_features2 = size(features2, 1);
feat_inds = 1:num_features1;
% if (num_features1>num_features2)
%     less_num = num_features2;
% else
%     less_num = num_features1; 
% end

first_nn = nn_val(:,1);
second_nn = nn_val(:,2);
nndr_list = first_nn ./ second_nn;

matches = zeros(num_features1, 2);
matches(:,1) = feat_inds;
matches(:,2) = nn_rank(:,1);

confidences = 1./nndr_list(feat_inds);

% [B,I] = sort(Dists,2);
% nearest_neighbor = B(:,1);
% second_nearest_neighbor = B(:,2);
% confidences = nearest_neighbor ./ second_nearest_neighbor;
% i = find(confidences)
% s = size(i);
% matches = zeros(s(1),2);
% matches(:,1) = i; 
% matches(:,2) = I(i);
% confidences = 1./confidences(i)
% [confidences, ind] = sort(confidences, 'descend');
% matches = matches(ind,:);
% matches = matches(1:100,:); 
% confidences = confidences(1:100,:)
% 

%%
clear;
image1 = imread('../data/Notre Dame/921919841_a30df938f2_o.jpg');
image2 = imread('../data/Notre Dame/4191453057_c86028ce1f_o.jpg');

gray_image = rgb2gray(image1);
gray_image = im2double(gray_image);

[x, y, confidence, scale, orientation] = get_interest_points(gray_image,1);
features1 = get_features(gray_image,x,y,16);

gray_image = rgb2gray(image2);
gray_image = im2double(gray_image);
[x, y, confidence, scale, orientation] = get_interest_points(gray_image,1);
features2 = get_features(gray_image,x,y,16);


[matches, confidences] = match_features(features1, features2);

%%
[x, y, ~, ~, ~] = get_interest_points(gray_image,1);
figure('name','Result');
imshow(gray_image),title('my-Harris'),
hold on
plot(x,y, 'b*'),
hold off


