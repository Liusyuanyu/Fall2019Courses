%%% Part II:
% • Compute the average image in grayscale.
% • Compute the average image in color, by averaging per RGB channel.
% • Compute a matrix holding the grayscale images’ standard deviation at each pixel (i.e., X(i,j) holds the standard deviation across all the images’ gray pixel intensities at row i, column j).
% • Display each of the above. 

%% Set One
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

%% Average image in grayscale.(Set 1)
average_image_Gray = sum_image_Gray / numberOfImages;
average_image_Gray = uint8(average_image_Gray);
imshow(average_image_Gray);

%% Average image in color, by averaging per RGB channel (Set 1)

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


%% STD Matrix in Grayscale. (Set 1)
permuted_matrix = permute(image_list,[3,2,1]);
std_matrix = std(permuted_matrix);
std_matrix = permute(std_matrix,[3,2,1]);
std_matrix = uint8(std_matrix);

figure('name','STD Matrix');
imshow(std_matrix);

%% Set Two
%Read all the images in set1
directory = '.\\set2';
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

%% Average image in grayscale. (Set 2)
average_image_Gray = sum_image_Gray / numberOfImages;
average_image_Gray = uint8(average_image_Gray);
imshow(average_image_Gray);

%% Average image in color, by averaging per RGB channel (Set 2)

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


%% STD Matrix in Grayscale. (Set 2)
permuted_matrix = permute(image_list,[3,2,1]);
std_matrix = std(permuted_matrix);
std_matrix = permute(std_matrix,[3,2,1]);
std_matrix = uint8(std_matrix);

figure('name','STD Matrix');
imshow(std_matrix);
