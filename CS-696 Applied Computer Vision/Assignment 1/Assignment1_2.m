%%% Part II:
% • Compute the  in grayscale.
% • Compute the average image in color, by averaging per RGB channel.
% • Compute a matrix holding the grayscale images’ standard deviation at each pixel (i.e., X(i,j) holds the standard deviation across all the images’ gray pixel intensities at row i, column j).
% • Display each of the above. 

% Some useful functions: dir, length, zeros, imshow, imread, rgb2gray, mean, std,  figure, title, subplot,


%%Set One
%Read all the images in set1
directory = '.\\set1';
image_path = dir(fullfile(directory,'im*.jpg')); % pattern to match filenames.
% image_list =(:,:,:);
numberOfImages = numel(image_path);
for ind = 1:(numberOfImages)    
    file = fullfile(directory,image_path(ind).name);
    image = imread(file);
    if ind ==1
        sum_image_Gray = zeros(size(image)) ;
        sum_image_Red = zeros(size(image)) ;
        sum_image_Green = zeros(size(image)) ;
        sum_image_Blue = zeros(size(image)) ;
    end
    
    sum_image_Red(:,:,1) = sum_image_Red(:,:,1) +  double(image(:,:,1));
    sum_image_Green(:,:,2) = sum_image_Green(:,:,2) +  double(image(:,:,2));
    sum_image_Blue(:,:,3) = sum_image_Blue(:,:,3) +  double(image(:,:,3));    
    
    image = rgb2gray(image);
    if ind ==1
        sum_image_Gray = zeros(size(image)) ;
        image_list = [];
    end    
    sum_image_Gray = sum_image_Gray +  double(image);
    
    image_list(:,:,ind) =double(image);
end

%% Average image in grayscale.
average_image_Gray = sum_image_Gray / numberOfImages;
average_image_Gray = uint8(average_image_Gray);
imshow(average_image_Gray);

%% Average image in color, by averaging per RGB channel

figure('name','Red Channel');
average_image_Red = sum_image_Red / numberOfImages;
average_image_Red = uint8(average_image_Red);
imshow(average_image_Red);

figure('name','Green Channel');
average_image_Green = sum_image_Green / numberOfImages;
average_image_Green = uint8(average_image_Green);
imshow(average_image_Green);

figure('name','Blue Channel');
average_image_Blue = sum_image_Blue / numberOfImages;
average_image_Blue = uint8(average_image_Blue);
imshow(average_image_Blue);


%% STD Matrix in Grayscale.
permuted_matrix = permute(image_list,[3,2,1]);
std_matrix = std(permuted_matrix);
std_matrix = permute(std_matrix,[3,2,1]);
std_matrix = uint8(std_matrix);

figure('name','STD Matrix');
imshow(std_matrix);

%% Show Four Images
subplot(2,2,1)
imshow(average_image_Red)

subplot(2,2,2)
imshow(average_image_Green);

subplot(2,2,3)
imshow(average_image_Blue);

subplot(2,2,4)
imshow(std_matrix);



%% My test codes
file = fullfile(directory,'im47.jpg');
% file = fullfile('./','my_im1.jpg');

image = imread(file);
% imshow(image(:,:,1));
subplot(2,2,1)
imshow(image)
subplot(2,2,2)
red_im = image;
red_im(:,:,2:3) = 0;
imshow(red_im);
subplot(2,2,3)
green_im = image;
green_im(:,:,1) = 0;
green_im(:,:,3) = 0; 
imshow(green_im);
subplot(2,2,4)
blue_im = image;
blue_im(:,:,1:2) = 0;
imshow(blue_im);



% sum_image_Red = sum_image_Red / numberOfImages;
% sum_image_Red = uint8(sum_image_Red);
% imshow(sum_image_Red);


%%Set Two