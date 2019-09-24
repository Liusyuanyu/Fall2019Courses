% Before trying to construct hybrid images, it is suggested that you
% implement my_imfilter.m and then debug it using proj1_test_filtering.m

% Debugging tip: You can split your MATLAB code into cells using "%%"
% comments. The cell containing the cursor has a light yellow background,
% and you can press Ctrl+Enter to run just the code in that cell. This is
% useful when projects get more complex and slow to rerun from scratch

clear; % closes all figures

%% Setup
% % read images and convert to floating point format
% image1 = im2single(imread('../data/dog.bmp'));
% image2 = im2single(imread('../data/cat.bmp'));

image1 = im2single(imread('../data/marilyn.bmp'));
image2 = im2single(imread('../data/einstein.bmp'));
% 
% image1 = im2single(imread('../data/bird.bmp'));
% image2 = im2single(imread('../data/plane.bmp'));
% 
% image1 = im2single(imread('../data/plane.bmp'));
% image2 = im2single(imread('../data/bird.bmp'));

% image1 = im2single(imread('../data/fish.bmp'));
% image2 = im2single(imread('../data/submarine.bmp'));

% image1 = im2single(imread('../data/motorcycle.bmp'));
% image2 = im2single(imread('../data/bicycle.bmp'));


% image1 = imresize(image1, 0.9, 'bilinear'); %resizing to speed up testing
% image2 = imresize(image2, 0.9, 'bilinear'); %resizing to speed up testing

% Several additional test cases are provided for you, but feel free to make
% your own (you'll need to align the images in a photo editor such as
% Photoshop). The hybrid images will differ depending on which image you
% assign as image1 (which will provide the low frequencies) and which image
% you asign as image2 (which will provide the high frequencies)


%% Add one empty row or column to avoid an exception
[rows, columns, numberOfColorChannels] = size(image1);
if mod(rows,2) == 0
    empty_row = zeros(1, columns, numberOfColorChannels);
    image1(end +1 ,:,:) = empty_row;
elseif mod(columns,2) == 0
    empty_col = zeros(rows, 1 , numberOfColorChannels);
    image1(:,end +1 ,:) = empty_col;
end

[rows, columns, numberOfColorChannels] = size(image2);
if mod(rows,2) == 0
    empty_row = zeros(1, columns, numberOfColorChannels);
    image2(end +1 ,:,:) = empty_row;
elseif mod(columns,2) == 0
    empty_col = zeros(rows, 1 , numberOfColorChannels);
    image2(:,end +1 ,:) = empty_col;
end


%% Filtering and Hybrid Image construction
% cutoff_frequency = 5; 

cutoff_frequency = 5; 
% cutoff_frequency = 3; 
%This is the standard deviation, in pixels, of the 
% Gaussian blur that will remove the high frequencies from one image and 
% remove the low frequencies from another image (by subtracting a blurred
% version from the original version). You will want to tune this for every
% image pair to get the best results.

filter = fspecial('Gaussian', cutoff_frequency*4+1, cutoff_frequency);

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% YOUR CODE BELOW. Use my_imfilter create 'low_frequencies' and
% 'high_frequencies' and then combine them to create 'hybrid_image'
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Remove the high frequencies from image1 by blurring it. The amount of
% blur that works best will vary with different image pairs
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% low_frequencies =  my_imfilter(image1, filter); % my_imfilter
low_frequencies =  imfilter(image1, filter); %imfilter

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Remove the low frequencies from image2. The easiest way to do this is to
% subtract a blurred version of image2 from the original version of image2.
% This will give you an image centered at zero with negative values.
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

laplacian_filter = [0 1 0; 1 -4 1; 0 1 0];
 
% high_frequencies =  my_imfilter(image2, filter); % low frequencies
% high_frequencies =  imfilter(image2, filter); % imfilter
% high_frequencies =  image2 - high_frequencies;


high_frequencies =   imfilter(image2, laplacian_filter);
% high_frequencies =   my_imfilter(image2, laplacian_filter);

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Combine the high frequencies and low frequencies
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

hybrid_image = low_frequencies+high_frequencies;

%%
% Visualize and save outputs
figure(1); imshow(low_frequencies)
figure(2); imshow(high_frequencies + 0.5);
vis = vis_hybrid_image(hybrid_image);
figure(3); imshow(vis);

% figure(2); imshow(high_frequencies);
% imshow(hybrid_image);

%% Save the images
imwrite(low_frequencies, 'low_frequencies.jpg', 'quality', 95);
imwrite(high_frequencies + 0.5, 'high_frequencies.jpg', 'quality', 95);
imwrite(hybrid_image, 'hybrid_image.jpg', 'quality', 95);
imwrite(vis, 'hybrid_image_scales.jpg', 'quality', 95);

%%
% 
% subplot(2,2,1);
% imshow(low_frequencies)
% 
% subplot(2,2,2);
% imshow(high_frequencies + 0.5);
% 
% subplot(2,2,3);
% vis = vis_hybrid_image(hybrid_image);
% imshow(vis);
% 
% subplot(2,2,4);
% imshow(hybrid_image);
