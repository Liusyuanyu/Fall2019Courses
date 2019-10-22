clear;
%% Case One: template matching with fixed scale: match the cropped template to the query image

% image1 = im2single(imread('./data/image1.jpg'));
% image1 = rgb2gray(image1);
% image1 = im2uint8(image1);
% % [template, rect] = imcrop(image1);
% % [137.51,110.51,16.98,14.98]
% rect = [140,110,14,14];
% % template_pos = [140,110];
% template_pos = [140+8,110+8];
% template = imcrop(image1,rect);
% % imshow(template);
% input_image = image1;


image2 = im2single(imread('./data/image2.jpg'));
image2 = rgb2gray(image2);
image2 = im2uint8(image2);

% [template, rect] = imcrop(image2);
% [450.51,290.51,217.98,77.98]

rect = [451,291,218,78];
template_pos = [451+ceil(219/2),291+ceil(79/2)];
template = imcrop(image2,rect);
% imshow(template);
input_image = image2;


% image3 = im2single(imread('./data/image3.jpg'));
% image3 = rgb2gray(image3);
% image3 = im2uint8(image3);
% 
% % [template, rect] = imcrop(image3);
% % [116.51,118.51,21.98,14.98]
% 
% rect = [116,118,22,15];
% template_pos = [116+ceil(23/2),118+ceil(16/2)];
% template = imcrop(image3,rect);
% imshow(template);
% input_image = image3;

%% Zero-mean correlation
%% Zero-mean Resize template! factor = 0.5
% half_temp = impyramid(template,'reduce');
% [match_rect, euclidean_dist] = zeroMeanCorrelation(input_image,half_temp,template_pos);
% imshow(input_image);
% rectangle('Position',match_rect,'EdgeColor','b','LineWidth',2)
% rectangle('Position',rect)
% fprintf('Zero mean euclidean_dist: %f\n',euclidean_dist);
% 
% set(gca,'units','pixels'); % set the axes units to pixels
% x = get(gca,'position'); % get the position of the axes
% set(gcf,'units','pixels'); % set the figure units to pixels
% y = get(gcf,'position'); % get the figure position
% set(gcf,'position',[y(1) y(2) x(3) x(4)]);% set the position of the figure to the length and width of the axes
% set(gca,'units','normalized','position',[0 0 1 1]); % set the axes units to pixels


half_temp = impyramid(template,'reduce');
half_input_image = impyramid(input_image,'reduce');

[match_rect, euclidean_dist] = zeroMeanCorrelation(half_input_image,half_temp,template_pos/2);
imshow(half_input_image);
rectangle('Position',match_rect,'EdgeColor','b','LineWidth',2)
rectangle('Position',rect/2)
fprintf('Zero mean euclidean_dist: %f\n',euclidean_dist);

%% Zero-mean Resize template! factor = 1
% 
[match_rect, euclidean_dist] = zeroMeanCorrelation(input_image,template,template_pos);
imshow(input_image);
rectangle('Position',match_rect,'EdgeColor','b','LineWidth',2)
rectangle('Position',rect)
fprintf('Zero mean euclidean_dist: %f\n',euclidean_dist);

set(gca,'units','pixels'); % set the axes units to pixels
x = get(gca,'position'); % get the position of the axes
set(gcf,'units','pixels'); % set the figure units to pixels
y = get(gcf,'position'); % get the figure position
set(gcf,'position',[y(1) y(2) x(3) x(4)]);% set the position of the figure to the length and width of the axes
set(gca,'units','normalized','position',[0 0 1 1]); % set the axes units to pixels

%% Zero-mean Resize template! factor = 2
double_temp = impyramid(template, 'expand');
[match_rect, euclidean_dist] = zeroMeanCorrelation(input_image,double_temp,template_pos);
imshow(input_image);
rectangle('Position',match_rect,'EdgeColor','b','LineWidth',2)
rectangle('Position',rect)
fprintf('Zero mean euclidean_dist: %f\n',euclidean_dist);

set(gca,'units','pixels'); % set the axes units to pixels
x = get(gca,'position'); % get the position of the axes
set(gcf,'units','pixels'); % set the figure units to pixels
y = get(gcf,'position'); % get the figure position
set(gcf,'position',[y(1) y(2) x(3) x(4)]);% set the position of the figure to the length and width of the axes
set(gca,'units','normalized','position',[0 0 1 1]); % set the axes units to pixels


%%
%%%% SSD

%% SSD Resize template! factor = 0.5
half_temp = imresize(template, 0.5);

[match_rect, euclidean_dist] = SumSquDifferences(input_image,half_temp,template_pos);
imshow(input_image);
rectangle('Position',match_rect,'EdgeColor','b','LineWidth',2)
rectangle('Position',rect)
fprintf('SSD euclidean_dist: %f\n',euclidean_dist);

set(gca,'units','pixels'); % set the axes units to pixels
x = get(gca,'position'); % get the position of the axes
set(gcf,'units','pixels'); % set the figure units to pixels
y = get(gcf,'position'); % get the figure position
set(gcf,'position',[y(1) y(2) x(3) x(4)]);% set the position of the figure to the length and width of the axes
set(gca,'units','normalized','position',[0 0 1 1]); % set the axes units to pixels

%% SSD Resize template! factor = 1
template = imresize(template, 1);

[match_rect, euclidean_dist] = SumSquDifferences(input_image,template,template_pos);
imshow(input_image);
rectangle('Position',match_rect,'EdgeColor','b','LineWidth',2)
rectangle('Position',rect)
fprintf('SSD euclidean_dist: %f\n',euclidean_dist);

set(gca,'units','pixels'); % set the axes units to pixels
x = get(gca,'position'); % get the position of the axes
set(gcf,'units','pixels'); % set the figure units to pixels
y = get(gcf,'position'); % get the figure position
set(gcf,'position',[y(1) y(2) x(3) x(4)]);% set the position of the figure to the length and width of the axes
set(gca,'units','normalized','position',[0 0 1 1]); % set the axes units to pixels

%% SSD Resize template! factor = 2
double_temp = impyramid(template,'expand');

[match_rect, euclidean_dist] = SumSquDifferences(input_image,double_temp,template_pos);
imshow(input_image);
rectangle('Position',match_rect,'EdgeColor','b','LineWidth',2)
rectangle('Position',rect)
fprintf('SSD double template, euclidean_dist: %f\n',euclidean_dist);

set(gca,'units','pixels'); % set the axes units to pixels
x = get(gca,'position'); % get the position of the axes
set(gcf,'units','pixels'); % set the figure units to pixels
y = get(gcf,'position'); % get the figure position
set(gcf,'position',[y(1) y(2) x(3) x(4)]);% set the position of the figure to the length and width of the axes
set(gca,'units','normalized','position',[0 0 1 1]); % set the axes units to pixels
%% NCC

%% NCC Resize template! factor = 0.5
half_temp = impyramid(template, 'reduce');

[match_rect, euclidean_dist] = NormCrossCorrelation(input_image,half_temp,template_pos);
imshow(input_image);
rectangle('Position',match_rect,'EdgeColor','b','LineWidth',2)
rectangle('Position',rect)
fprintf('NCC half template,euclidean_dist: %f\n',euclidean_dist);

set(gca,'units','pixels'); % set the axes units to pixels
x = get(gca,'position'); % get the position of the axes
set(gcf,'units','pixels'); % set the figure units to pixels
y = get(gcf,'position'); % get the figure position
set(gcf,'position',[y(1) y(2) x(3) x(4)]);% set the position of the figure to the length and width of the axes
set(gca,'units','normalized','position',[0 0 1 1]); % set the axes units to pixels

%% NCC Resize template! factor = 1
[match_rect, euclidean_dist] = NormCrossCorrelation(input_image,template,template_pos);
imshow(input_image);
rectangle('Position',match_rect,'EdgeColor','b','LineWidth',2)
rectangle('Position',rect)
fprintf('NCC template,euclidean_dist: %f\n',euclidean_dist);

set(gca,'units','pixels'); % set the axes units to pixels
x = get(gca,'position'); % get the position of the axes
set(gcf,'units','pixels'); % set the figure units to pixels
y = get(gcf,'position'); % get the figure position
set(gcf,'position',[y(1) y(2) x(3) x(4)]);% set the position of the figure to the length and width of the axes
set(gca,'units','normalized','position',[0 0 1 1]); % set the axes units to pixels

%% NCC Resize template! factor = 2
double_temp = impyramid(template,'expand');

[match_rect, euclidean_dist] = NormCrossCorrelation(input_image,double_temp,template_pos);
imshow(input_image);
rectangle('Position',match_rect,'EdgeColor','b','LineWidth',2)
rectangle('Position',rect)
fprintf('NCC double template, euclidean_dist: %f\n',euclidean_dist);

set(gca,'units','pixels'); % set the axes units to pixels
x = get(gca,'position'); % get the position of the axes
set(gcf,'units','pixels'); % set the figure units to pixels
y = get(gcf,'position'); % get the figure position
set(gcf,'position',[y(1) y(2) x(3) x(4)]);% set the position of the figure to the length and width of the axes
set(gca,'units','normalized','position',[0 0 1 1]); % set the axes units to pixels