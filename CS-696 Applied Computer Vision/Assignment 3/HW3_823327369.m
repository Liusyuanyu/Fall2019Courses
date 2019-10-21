clear;
%% Case One: template matching with fixed scale: match the cropped template to the query image



image2 = im2single(imread('./data/image2.jpg'));
image2 = rgb2gray(image2);
image2 = im2uint8(image2);

% [template, rect] = imcrop(image2);
% [450.51,290.51,217.98,77.98]

rect = [451,291,218,78];
% template_pos = [140,110];
template_pos = [451+218/2,291+78/2];
template = imcrop(image2,rect);
imshow(template);


% image3 = im2single(imread('./data/image3.jpg'));

% image1 = im2single(imread('./data/image1.jpg'));
% image1 = rgb2gray(image1);
% image1 = im2uint8(image1);
% [template, rect] = imcrop(image1);
% [137.510000000000,110.510000000000,16.9800000000000,14.9800000000000]
% rect = [140,110,14,14];
% template_pos = [140,110];
% template_pos = [140+8,110+8];
% template = imcrop(image1,rect);
% imshow(template);

%% Zero-mean correlation
%% Resize template! factor = 0.5

half_temp = imresize(template, 0.5);
[match_rect, euclidean_dist] = zeroMeanCorrelation(image1,half_temp,template_pos);
imshow(image1);
rectangle('Position',match_rect)
fprintf('euclidean_dist: %f\n',euclidean_dist);

%% Resize template! factor = 1

[match_rect, euclidean_dist] = zeroMeanCorrelation(image1,template,template_pos);
imshow(image1);
rectangle('Position',match_rect)
fprintf('euclidean_dist: %f\n',euclidean_dist);


%% Resize template! factor = 2
double_temp = imresize(template, 2);

[match_rect, euclidean_dist] = zeroMeanCorrelation(image1,double_temp,template_pos);
imshow(image1);
rectangle('Position',match_rect)
fprintf('euclidean_dist: %f\n',euclidean_dist);


%% SSD Resize template! factor = 0.5
half_temp = imresize(template, 0.5);

[match_rect, euclidean_dist] = SumSquDifferences(image1,half_temp,template_pos);
imshow(image1);
rectangle('Position',match_rect,'EdgeColor','b','LineWidth',2)
% rectangle('Position',rect)
fprintf('euclidean_dist: %f\n',euclidean_dist);

%% SSD Resize template! factor = 1
template = imresize(template, 1);

[match_rect, euclidean_dist] = SumSquDifferences(image1,template,template_pos);
imshow(image1);
rectangle('Position',match_rect,'EdgeColor','b','LineWidth',2)
% rectangle('Position',rect)
fprintf('euclidean_dist: %f\n',euclidean_dist);

%% SSD Resize template! factor = 2
double_temp = imresize(template, 2);

[match_rect, euclidean_dist] = SumSquDifferences(image1,double_temp,template_pos);
imshow(image1);
rectangle('Position',match_rect,'LineWidth',2)
rectangle('Position',rect)
fprintf('SSD double template, euclidean_dist: %f\n',euclidean_dist);

%% NCC

%% NCC Resize template! factor = 0.5
half_temp = imresize(template, 0.5);

[match_rect, euclidean_dist] = NormCrossCorrelation(image1,half_temp,template_pos);
imshow(image1);
rectangle('Position',match_rect,'EdgeColor','b','LineWidth',2)
% rectangle('Position',rect)
fprintf('NCC half template,euclidean_dist: %f\n',euclidean_dist);

%% NCC Resize template! factor = 1
template = imresize(template, 1);

[match_rect, euclidean_dist] = NormCrossCorrelation(image1,template,template_pos);
imshow(image1);
rectangle('Position',match_rect,'EdgeColor','b','LineWidth',2)
rectangle('Position',rect)
fprintf('NCC template,euclidean_dist: %f\n',euclidean_dist);

%% NCC Resize template! factor = 2
double_temp = imresize(template, 2);

[match_rect, euclidean_dist] = NormCrossCorrelation(image1,double_temp,template_pos);
imshow(image1);
rectangle('Position',match_rect,'EdgeColor','b','LineWidth',2)
rectangle('Position',rect)
fprintf('NCC double template, euclidean_dist: %f\n',euclidean_dist);


%% Image 2

%% NCC Resize template! factor = 0.5
half_temp = imresize(template, 0.5);

[match_rect, euclidean_dist] = NormCrossCorrelation(image2,half_temp,template_pos);
imshow(image2);
rectangle('Position',match_rect,'EdgeColor','b','LineWidth',2)
rectangle('Position',rect)
fprintf('NCC half template,euclidean_dist: %f\n',euclidean_dist);


%% NCC Resize template! factor = 2
double_temp = imresize(template, 2);

[match_rect, euclidean_dist] = NormCrossCorrelation(image2,double_temp,template_pos);
imshow(image2);
rectangle('Position',match_rect,'EdgeColor','b','LineWidth',2)
rectangle('Position',rect)
fprintf('NCC double template, euclidean_dist: %f\n',euclidean_dist);
